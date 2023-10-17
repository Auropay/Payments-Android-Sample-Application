# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontshrink
-dontoptimize
-keepparameternames

-dontwarn java.lang.invoke.StringConcatFactory

-keepclasseswithmembernames class androidx.lifecycle.LiveData {*;}
-keepclasseswithmembernames class net.auropay.payments.AuroPay {*;}
-keepclasseswithmembernames class net.auropay.payments.AuroPay$Initialize {*;}

-keepclasseswithmembernames interface net.auropay.payments.domain.service.PaymentResultListener{*;}
-keepclasseswithmembernames interface net.auropay.payments.domain.service.PaymentResultWithDataListener{*;}
-keepclasseswithmembernames interface net.auropay.payments.domain.service.EventListener{*;}

-keepclasseswithmembernames class net.auropay.payments.domain.service.PaymentResultData {*;}

-keepclasseswithmembers class net.auropay.payments.data.remote.response.PaymentReceiptResponseModel {*;}
-keepclasseswithmembers class net.auropay.payments.domain.service.MerchantProfile {*;}
-keepclasseswithmembers class net.auropay.payments.domain.service.Theme {*;}
-keepclasseswithmembers class net.auropay.payments.domain.service.CustomerProfile {*;}
-keepclasseswithmembers class net.auropay.payments.data.remote.request.PaymentRequestModel {*;}

-keepattributes Exceptions,*Annotation*

-keepnames class * extends net.auropay.payments.ui.base.BaseFragment

-keepclasseswithmembers class * extends java.lang.Enum {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep class org.bouncycastle.jcajce.provider.** { *; }
-keep class org.bouncycastle.jce.provider.** { *; }