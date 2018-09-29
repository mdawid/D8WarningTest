# D8WarningTest

Simple project to show problems when using D8 from Gradle Android Plugin 3.2.0

When compiling with Java 8 compatibility enabled 
```
compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
}
```
and minifyEnabled true
```
buildTypes {
        debug {
            minifyEnabled true
            useProguard false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
}
```
D8 shows multiple warnings like:
`
D8: Type X was not found, it is required for default or static interface methods desugaring of Y`

Running this project with: `./gradlew clean assembleDebug`
will show multiple warnings from D8 shrinker
```...
D8: Type `org.apache.http.impl.client.HttpClients` was not found, it is required for default or static interface methods desugaring of `void org.springframework.http.client.HttpComponentsClientHttpRequestFactory.<init>()`
D8: Type `org.apache.http.client.protocol.HttpClientContext` was not found, it is required for default or static interface methods desugaring of `org.springframework.http.client.ClientHttpRequest org.springframework.http.client.HttpComponentsClientHttpRequestFactory.createRequest(java.net.URI, org.springframework.http.HttpMethod)`
D8: Type `org.apache.http.client.config.RequestConfig` was not found, it is required for default or static interface methods desugaring of `org.springframework.http.client.ClientHttpRequest org.springframework.http.client.HttpComponentsClientHttpRequestFactory.createRequest(java.net.URI, org.springframework.http.HttpMethod)`
D8: Type `com.google.gson.reflect.TypeToken` was not found, it is required for default or static interface methods desugaring of `com.google.gson.reflect.TypeToken org.springframework.http.converter.json.GsonHttpMessageConverter.getTypeToken(java.lang.reflect.Type)`
D8: Type `com.squareup.okhttp.MediaType` was not found, it is required for default or static interface methods desugaring of `com.squareup.okhttp.MediaType org.springframework.http.client.OkHttpClientHttpRequest.getContentType(org.springframework.http.HttpHeaders)`
D8: Type `com.squareup.okhttp.RequestBody` was not found, it is required for default or static interface methods desugaring of `org.springframework.http.client.ClientHttpResponse org.springframework.http.client.OkHttpClientHttpRequest.executeInternal(org.springframework.http.HttpHeaders, byte[])`
D8: Type `org.apache.http.util.EntityUtilsHC4` was not found, it is required for default or static interface methods desugaring of `void org.springframework.http.client.HttpComponentsClientHttpResponse.closeInternal()`
D8: Type `org.apache.http.conn.scheme.PlainSocketFactory` was not found, it is required for default or static interface methods desugaring of `void org.springframework.http.client.HttpComponentsAndroidClientHttpRequestFactory.<init>()`
D8: Type `org.apache.http.conn.params.ConnManagerParams` was not found, it is required for default or static interface methods desugaring of `void org.springframework.http.client.HttpComponentsAndroidClientHttpRequestFactory.<init>()`
D8: Type `org.apache.http.params.HttpProtocolParams` was not found, it is required for default or static interface methods desugaring of `void org.springframework.http.client.HttpComponentsAndroidClientHttpRequestFactory.postProcessHttpRequest(org.apache.http.client.methods.HttpUriRequest)`
D8: Interface `org.apache.http.HttpEntity` not found. It's needed to make sure desugaring of `org.springframework.http.client.HttpComponentsStreamingClientHttpRequest$StreamingHttpEntity` is correct. Desugaring will assume that this interface has no default method.
D8: Type `org.conscrypt.Conscrypt` was not found, it is required for default or static interface methods desugaring of `okhttp3.internal.platform.Platform okhttp3.internal.platform.ConscryptPlatform.buildIfSupported()`
```

Adding below lines
```-dontwarn org.apache.http.**
-dontwarn com.squareup.okhttp.**
-downwarn org.springframework.http.client.**
```
to `proguard-rules.pro` file does not remove warnings.

**Only downgrading Gradle Android Plugin to version to 3.1.4 helps.**