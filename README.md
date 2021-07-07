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

* ## apk文件相关 -> [ZJAPKUtils.java](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/ZJAPKUtils.java) -> [Demo](https://github.com/summer-zhoujie/ZJUtils/blob/master/app/src/main/java/com/zj/tools/zjutils/ZJApkUtilsDemoActivity.java)
```
isApkFileInstalled : apk文件是否安装
installApkFile     : 调起apk文件进行安装
getPackageName     : 获取apk文件的包名
```

* ## 单位转换 -> [ZJConvertUtils.java](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/ZJAPKUtils.java) -> [Test](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/androidTest/java/com/zj/tools/mylibrary/ZJConvertUtilsTest.java)
```
dp2px px2dp : px和dp的相互转换
sp2px px2sp : px和sp的相互转换 
```

* ## Handler工具 -> [ZJHandlerUtil.java](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/ZJHandlerUtil.java) -> [Test](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/androidTest/java/com/zj/tools/mylibrary/ZJHandlerUtilTest.java)
```
postToMain postToBackground                : 切换到主线程/子线程的延时
removeTaskFromMan removeTaskFromBackground : 主线程/子线程延时任务执行之前可以操作取消
```

* ## 隐藏API(@hide修饰)访问工具 -> [ZJHiddenApiUtils.java](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/ZJHiddenApiUtils.java) -> [Demo](https://github.com/summer-zhoujie/ZJUtils/blob/master/app/src/main/java/com/zj/tools/zjutils/ZJHiddenApiUtilsDemoActivity.java)
```
setExemption  : 指定某些@hide修饰的方法进入白名单
exemptAll     : 安排所有@hide方法进入白名单
```
> 进入白名单的方法之后, 就可以顺利通过反射访问了, 详情查看标题的Demo链接