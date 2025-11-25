# 🚀 Common SDK for Microservices

Welcome to **[Backend Verse](https://www.youtube.com/@BackendVerse)**! 🎥

### 📦 Part of: *[Common SDK for Scalable Microservices](https://www.youtube.com/playlist?list=PLdUn31k8Q722IvcYPsZ_WLG_LO86bU9rt)*

**Tech Stack:** Spring Boot | Java 17 | Gradle | GitHub Packages | MDC | AOP

---

## 🧭 Overview

The **Common SDK** is a reusable, plug-and-play library designed to bring **consistency, reliability, and traceability** to all your microservices.

Instead of rewriting boilerplate code across projects, this SDK offers:

* Standardized API responses
* Global exception handling
* Request tracing using MDC
* Reusable base classes
* Centralized response handler
* Easy publishing to Maven Local & GitHub Packages

This SDK ensures **clean architecture, zero duplication, and highly maintainable microservice systems**.

---

## ⚙️ What This SDK Provides

### ✅ **1. Standard API Response Format**

Every microservice returns **same structure**:

```
{
  "success": true,
  "data": {
    "id": 1,
    "username": "emilys",
    "email": "emily.johnson@x.dummyjson.com",
    "firstName": "Emily",
    "lastName": "Johnson"
  },
  "code": 200,
  "errorMessage": "",
  "traceId": "35d85e5b-3efc-4370-b00d-fe5e1183d648"
}
```

or

```
{
  "success": false,
  "data": null,
  "code": 401,
  "errorMessage": "Unauthorized Access For User Listing API",
  "traceId": "8880ce98-9e45-4b17-b932-f00f0854fe55"
}
```

---

### ✅ **2. Global Exception Handling**

Centrally handles:

* Custom exceptions
* Validation failures
* Runtime exceptions
* Bad requests
* Missing fields

Consistent formatting → better debugging → predictable client behavior.

---

### ✅ **3. MDC Request Tracking**

Automatic addition of:

* `x-correlation-id`

This ID travels across logs to easily trace requests end-to-end.

---

### ✅ **4. Reusable Base Classes & Interfaces**

Core abstractions include:

* Base exception classes
* Base response structures
* Enum-driven status codes
* Standard DTO contracts

All microservices follow the same architectural blueprint.

---

### ✅ **5. Response Handler Utility**

Utility class to return:

* `success(data)`
* `failure(message)`

Removes duplicate response mapping logic.

---

### ✅ **6. Ready for Publishing**

The SDK can be published to:

#### 📌 **Maven Local**

For instant local development usage.

#### 📌 **GitHub Packages**

For team-wide & CI/CD-ready distribution.

### ✅ **7. Publish to GitHub Packages**

Add a `gradle.properties` file:

```
github.host=https://maven.pkg.github.com/OWNER/REPOSITORY
github.username=github_username
github.password=github_token
```

Run:

```
gradle clean build publish
```

---

## ✅ **8. 🧪How to Use the SDK in Microservices**

Add your dependency:

```
implementation 'com.common.sdk:common-sdk:0.0.1'
```

Now you get:

* `APIResponse<T>`
* `ResponseHandler`
* `GlobalExceptionHandler`
* `MDC Filter`
* `Base classes`

**Without writing the same code again** 🎉

---

## 🧠 Design Principles

💡 **Single Source of Truth**
All microservices use the same shared SDK for exceptions, responses, and filters.

💡 **Clean Architecture Driven**
Controller → Service → SDK utilities → Consistent output.

💡 **Highly Reusable Components**
Designed once, used forever across all services.

---

## 🏗️ Modules Covered in the Series

| Step   | Topic                                 | Description                                         |
| ------ | ------------------------------------- | --------------------------------------------------- |
| **01** | SDK Architecture & Introduction       | Why SDK? What problems it solves? Overall design.   |
| **02** | Project Setup, Packaging, MDC Filter  | Folder structure, Gradle setup, MDC logging filter. |
| **03** | Creating Base Classes                 | Enums, Interfaces, Base Exceptions, Core entities.  |
| **04** | Implementing `APIResponse<T>`         | Generic success & error wrapper.                    |
| **05** | Implementing ResponseHandler          | Utility for standardized responses.                 |
| **06** | Implementing Global Exception Handler | Handles all exceptions & maps them to APIResponse.  |
| **07** | Publishing SDK to Maven Local         | Using `publishToMavenLocal` with Gradle.            |
| **08** | Publishing SDK to GitHub Packages     | Uploading the SDK to GitHub registry for reuse.     |

---

## 🧱 Suggested Package Structure

```
com.common.sdk
│
├── constants/
│   ├── ApplicationConstant.java
│
├── exception/
│   ├── GlobalExceptionHandler.java
│   ├── BaseException.java
│
├── filter/
│   └── CorrelationIdFilter.java
│
├── models/
│   ├── dto
|   |    └── APIResponse.java
│   ├── enums
|   |    └── APIResponseCode(enum)
|   |    └── NotificationResponseCode(enum)
│   ├── interfaces
|        └── GenericAPIResponse(interface)
|        └── ResponseCode(interface)
│
├── services/
│   └── ResponseHandler.java
│
└── util/
    └── CommonUtils.java
```

---

## 🔧 Publishing Options

### 📌 **Publish to Maven Local**

```
gradle publishToMavenLocal
```

Installed under:

```
~/.m2/repository/com/<your-computer-username>/com/common/sdk
```

### 📌 **Publish to GitHub Packages**

```
gradle publish
```

Requires:

* GitHub Token
* Correct repository URL
* gradle.properties configuration

---

## 📊 Future Enhancements

* Add Request Signature Validation
* Tracing with OpenTelemetry
* Multi-environment configuration support

---

## 🧩 Related Playlists

| Playlist                                                                                                        | Purpose                                      |
|-----------------------------------------------------------------------------------------------------------------| -------------------------------------------- |
| **[Notification System using Kafka](https://www.youtube.com/playlist?list=PLdUn31k8Q723lAfT2QOWoSA4C-UFQLDGI)** | Large-scale microservice project             |
| **[Microservice Best Practices](https://www.youtube.com/playlist?list=PLdUn31k8Q723-NsQF3vo_mDR3NSyLWpYL)**     | Logging, retry, circuit breaker, patterns    |
| **[Full Common SDK Series](https://www.youtube.com/playlist?list=PLdUn31k8Q722IvcYPsZ_WLG_LO86bU9rt)**                                                                                  | Reusability, consistency, clean architecture |

---

## 🧪 Real Usage Example (Inside Any Microservice)

```java
return ResponseHandler.success(userData);
```

```java
throw new ValidationException("Invalid email address");
```

All automatically formatted via SDK. 🚀

---

## 🧑‍💻 Author

🎥 YouTube: [BackendVerse](https://www.youtube.com/@backendverse)
💬 Build real-world backend systems with Spring Boot, Kafka & Microservices Architecture (Hindi)

---