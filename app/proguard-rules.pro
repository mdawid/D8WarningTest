-target 1.8
#-dump build/class_files.txt
#-printseeds build/seeds.txt
#-printusage build/unused.txt
#-printmapping build/mapping.txt
#-verbose

### OkHttp 3
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
-dontwarn okhttp3.internal.platform.*
-dontnote okhttp3.internal.platform.*
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
### end of OkHttp 3

### Spring framework
-dontwarn org.springframework.http.client.*
-dontwarn org.springframework.http.converter.json.**
-dontwarn org.simpleframework.xml.**
-dontwarn org.springframework.core.convert.support.ConvertingPropertyEditorAdapter
### end of Springframework

### The lines below have no effect on D8 warnings
-keep class org.apache.http.** { *; }
-dontwarn org.apache.http.**
-keep class com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**
-keep class org.springframework.http.client.** { *; }
-dontwarn org.springframework.http.client.**
###