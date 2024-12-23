<br/>
<br/>
<br/>

<p align="center">
  <img src="https://v1-994035768921-aspl-auropay.s3.ap-south-1.amazonaws.com/UIUX/default_invoice_logo.png">
</p>

<br/>
<br/>

## Android Demo Application

This repository contains sample application for Kotlin and Java to demonstrates how to integrate AuroPay payments SDK in your application.


### Prerequisite
- You should be onboarded on AuroPay merchant portal.
- You can get your sub domain id, accessKey, secretKey from AuroPay merchant portal.

<br/>

### Demo Project Setup
- Clone the repository through Android Studio or Git Bash as per your use case.

- Open the sample project of your prefered language Kotlin/Java in Android Studio.

- Replace the placeholder values  of sub domain id, accessKey and secretKey with your detail in AuroPay initializer.

- ### Kotlin
```kotlin
    AuroPay.Initialize().subDomainId("Your subDomainId").accessKey("Your accessKey").secretKey("Your secretKey")
```
- ### Java
```java 
    new AuroPay.Initialize().subDomainId("Your subDomainId").accessKey("Your accessKey").secretKey("Your secretKey")
```

- Run the demo application.

<br/>

### About AuroPay SDK
AuroPay Android SDK allows you to accept in-app payments by providing you with the building blocks you need to create a checkout experience.
