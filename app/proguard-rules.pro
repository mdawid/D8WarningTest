#-target 1.8
#-dump build/class_files.txt
#-printseeds build/seeds.txt
#-printusage build/unused.txt
#-printmapping build/mapping.txt
#-verbose

### OkHttp 3 - https://github.com/square/okhttp/blob/master/okhttp/src/main/resources/META-INF/proguard/okhttp3.pro
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*
# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform
### end of OkHttp 3

### Spring framework
-dontwarn org.springframework.http.client.*
-dontwarn org.springframework.http.converter.json.**
-dontwarn org.simpleframework.xml.**
-dontwarn org.springframework.core.convert.support.ConvertingPropertyEditorAdapter
### end of Spring framework

### ---> The lines below have no effect on D8 warnings <---
-keep public class org.apache.http.** { *; }
-dontwarn org.apache.http.**

-keep public class com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-keep, includedescriptorclasses public class org.springframework.http.** { *; }
-dontwarn org.springframework.http.**
### end of D8 warnings