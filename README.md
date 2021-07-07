# 1.简介
开发工具包集合, 可以从下面的java文件中选择拷贝, 也可直接选择安装的方式(安装的方式, 如果开了R8压缩,没有用到的类会自动去除的, 不必担心代码量的问题)

# 2.安装

[最新版本查阅地址](https://www.jitpack.io/#summer-zhoujie/ZJUtils)

```gradle
// Add it in your root build.gradle at the end of repositories:
allprojects {
    repositories {
	...
	maven { url 'https://www.jitpack.io' }
    }
}
```
```gradle
// Add the dependency
dependencies {
    implementation 'com.github.summer-zhoujie:ZJUtils:latest.release'
}
```


# 3.功能介绍

* ## apk文件相关 -> [源文件](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/ZJAPKUtils.java) -> [Demo](https://github.com/summer-zhoujie/ZJUtils/blob/master/app/src/main/java/com/zj/tools/zjutils/ZJApkUtilsDemoActivity.java)
isApkFileInstalled : apk文件是否安装
installApkFile     : 调起apk文件进行安装
getPackageName     : 获取apk文件的包名
