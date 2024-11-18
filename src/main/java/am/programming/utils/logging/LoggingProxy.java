package am.programming.utils.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggingProxy {
    private static final Logger logger = LogManager.getLogger(LoggingProxy.class);
    private static final boolean LOG_FULL_STACK_TRACE = false;

    private LoggingProxy(){}

    /**
     * Creates a proxy instance for the target object that logs method execution.
     *
     * @param target The target object to create a proxy for.
     * @param <T>    The type of the target object.
     * @return A proxy instance with logging capabilities.
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(T target) {
        Class<?> targetClass = target.getClass();

        return (T) Proxy.newProxyInstance(
                targetClass.getClassLoader(),
                targetClass.getInterfaces(),
                new LoggingHandler(target)
        );
    }

    /**
     * InvocationHandler implementation to handle method invocation with logging.
     */
    private static class LoggingHandler implements InvocationHandler {
        private final Object target;
        private final boolean classAnnotatedWithLogExecution;

        public LoggingHandler(Object target) {
            this.target = target;
            this.classAnnotatedWithLogExecution = target.getClass().isAnnotationPresent(LogExecution.class);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // Get class and method names
            String className = target.getClass().getSimpleName();
            String methodName = method.getName();

            // Check if the method or its class is annotated with @LogExecution
            boolean methodAnnotated = method.isAnnotationPresent(LogExecution.class);
            if (methodAnnotated || classAnnotatedWithLogExecution) {
                logger.info("Entering method: {}.{} with arguments: {}", className, methodName, args);

                try {
                    // Invoke the original method
                    Object result = method.invoke(target, args);
                    logger.info("Exiting method: {}.{} with result: {}", className, methodName, result);
                    return result;
                } catch (Exception e) {
                    // Simplified exception logging
                    if (LOG_FULL_STACK_TRACE) {
                        logger.error("Exception in method: {}.{} with arguments: {} - {}", className, methodName, args, e.getMessage(), e);
                    } else {
                        logger.error("Exception in method: {}.{} with arguments: {} - {}", className, methodName, args, e.getMessage());
                    }

                    // Re-throw the original exception cause (if available)
                    if (e.getCause() != null) {
                        throw e.getCause();
                    }
                    throw e;
                }
            } else {
                // If the method is not annotated, just invoke it
                return method.invoke(target, args);
            }
        }
    }
}
