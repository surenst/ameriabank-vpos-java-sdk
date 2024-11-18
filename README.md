
# Payment Integration SDK

## Overview
This project is a Java SDK designed for seamless integration with AmeriaBank’s payment processing services. It handles operations like payment initialization, card binding management, refunds, and error handling. The SDK aims to simplify integrating with AmeriaBank’s API by providing pre-configured classes and methods.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Configuration](#configuration)
- [Class Dependencies](#class-dependencies)
- [Usage](#usage)
    - [Initializing Payments](#initializing-payments)
    - [Managing Bindings](#managing-bindings)
    - [Transaction Operations](#transaction-operations)
    - [Error Handling](#error-handling)
- [Testing](#testing)
- [Logging](#logging)
- [License](#license)

## Features
- **Payment Initialization**: Initiate payments and retrieve transaction details.
- **Card Binding**: Manage card bindings, activation, deactivation, and payments.
- **Transaction Management**: Handle confirmations, cancellations, and refunds.
- **Error Handling**: Centralized error handling for fraud detection and validation.
- **Logging**: Integrated logging using Log4j2.

## Getting Started

### Prerequisites
- **Java 21**
- **Maven** installed

### Installation
To set up the project:

```bash
git clone <repository-url>
cd <project-directory>
mvn clean install
```

### Configuration
To start using the SDK, configure your credentials in `SdkConfig.java`:

```java
SdkConfig config = SdkConfig.builder()
        .baseUrl("https://api.ameriabank.am")
        .initPaymentPath("/InitPayment")
        .paymentDetailsPath("/PaymentDetails")
        .confirmPaymentPath("/ConfirmPayment")
        .cancelPaymentPath("/CancelPayment")
        .refundPaymentPath("/RefundPayment")
        .makeBindingPaymentPath("/MakeBindingPayment")
        .getBindingsPath("/GetBindings")
        .activateBindingPath("/ActivateBinding")
        .deactivateBindingPath("/DeactivateBinding")
        .username("your-username")
        .password("your-password")
        .build();

```

No further changes are needed; once configured, you can use the SDK in your application.

## Class Dependencies

### Overview
Below is a breakdown of how the classes interact:

```plaintext
+--------------------------------------+
|           SdkConfig.java             |
+--------------------------------------+
                |
                v
+--------------------------------------+
|        AmeriaBankClient.java         |
+--------------------------------------+
                |
                +------------------------------+
                |                              |
                v                              v
+----------------------------+     +--------------------------+
|     PaymentService.java    |     |    BindingService.java   |
+----------------------------+     +--------------------------+
                |                              |
                v                              |
+-----------------------------------------------+
|              HttpClientWrapper.java            |
+-----------------------------------------------+
                |
                v
+-----------------------------------------------+
|              JsonUtil.java                     |
+-----------------------------------------------+
```

### Explanation of Dependencies

#### `SdkConfig.java`
- The core configuration class containing API credentials and endpoints.
- Provides configuration settings used by other classes.

#### `AmeriaBankClient.java`
- The main client for interacting with the AmeriaBank API using configurations from `SdkConfig`.
- Handles all HTTP communication using `HttpClientWrapper`.
- Uses `JsonUtil` for parsing JSON responses.
- Provides methods used by both `PaymentService` and `BindingService`.

#### `PaymentService.java`
- Handles payment-related operations such as initializing, confirming, and refunding payments.
- Relies on `AmeriaBankClient` for all interactions with the payment API.

#### `BindingService.java`
- Manages card bindings, including activation, deactivation, and binding payments.
- Utilizes `AmeriaBankClient` for API interactions.

#### `HttpClientWrapper.java`
- Handles low-level HTTP requests and responses.
- Used exclusively by `AmeriaBankClient` to communicate with the API.

#### `JsonUtil.java`
- Utility class for JSON serialization and deserialization.
- Used internally by `AmeriaBankClient` for parsing API responses.



### Exception Handling
- **Custom exceptions**: `FraudDetectionException`, `ParameterException`, and `AuthenticationException` are used for error handling.

## Usage

### Initializing Payments
```java
PaymentService paymentService = new PaymentService(new AmeriaBankClient(config));
InitPaymentRequest request = new InitPaymentRequest("client123", config.getUsername(), config.getPassword(), Currency.USD, "Order Payment", 12345, new BigDecimal("100.00"), "https://callback.url", null, null, 900);
InitPaymentResponse response = paymentService.initPayment(request);
System.out.println("Payment ID: " + response.paymentId());
```

### Managing Bindings
```java
BindingService bindingService = new BindingService(new AmeriaBankClient(config));
ActivateBindingRequest activateRequest = new ActivateBindingRequest("client123", config.getUsername(), config.getPassword(), "bindingId123");
ActivateBindingResponse activateResponse = bindingService.activateBinding(activateRequest);
System.out.println("Activation Response: " + activateResponse.responseMessage());
```

### Transaction Operations
```java
ConfirmPaymentRequest confirmRequest = new ConfirmPaymentRequest("paymentId", config.getUsername(), config.getPassword());
ConfirmPaymentResponse confirmResponse = paymentService.confirmPayment(confirmRequest);
System.out.println("Confirmation Status: " + confirmResponse.responseCode());
```

## Testing
To run tests:

```bash
mvn test
```

## Logging
The SDK uses Log4j2 for logging. Configuration can be found in the `log4j2.xml` file.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
