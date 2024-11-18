package am.programming.exceptions;

public class AuthenticationException extends BaseException {
    public AuthenticationException() {
        super(ErrorCode.INCORRECT_CREDENTIALS);
    }
}
