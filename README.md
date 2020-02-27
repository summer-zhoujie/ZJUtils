## 简介
开发工具包集合

## 使用
```
// Add it in your root build.gradle at the end of repositories:
allprojects {
    repositories {
	...
	maven { url 'https://www.jitpack.io' }
    }
}
```
```
// Add the dependency
dependencies {
    implementation 'com.github.summer-zhoujie:DevUtils:Tag'
}
```
## 内容
1. 隐藏API(@hide修饰)访问工具 --ZjHiddenApiUtil
2. Log工具 --ZjLog
