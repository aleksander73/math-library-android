# Mathematics library for Android development
A mathematics library written in Java using Android Studio IDE.

## Definition
The library supports most frequently used entities in 3d game development, i.e. `vectors`, `matrices` and `quaternions`.

## Usage
1. Add the JitPack repository to project build file, i.e. add it in the root `build.gradle` at the end of repositories
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
2. Add the dependency
```
dependencies {
    implementation 'com.github.aleksander73:math-library-android:v1.0'
}
```

3. Synchronize Gradle files in Android Studio, i.e. click `File` > `Sync Project with Gradle Files`
