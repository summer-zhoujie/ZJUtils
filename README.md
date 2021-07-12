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

* ## 身份证工具类 -> [ZJIdCardUtils.java](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/ZJIdCardUtils.java) -> [Test](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/androidTest/java/com/zj/tools/mylibrary/ZJIdCardUtilsTest.java)
```
isIdCardValid : 是否是有效身份证号码(本地校验,只是校验格式是否正确)
getGender     : 性别
getAge        : 年龄
```

* ## Log打印的工具类 -> [ZJLog.java](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/ZJLog.java) -> [Test](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/androidTest/java/com/zj/tools/mylibrary/ZJIdCardUtilsTest.java)
```
init    : 初始化(配置TAG和使能状态)
d       : 打印D级别Log
e       : 打印E级别Log
i       : 打印I级别Log
w       : 打印W级别Log
v       : 打印V级别Log
```

* ## 通知类工具 -> [ZJNotificationUtils.java](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/ZJNotificationUtils.java) -> [Demo](https://github.com/summer-zhoujie/ZJUtils/blob/master/app/src/main/java/com/zj/tools/zjutils/ZJNotificationUtilsDemoActivity.java)
```
show  : 展示模板通知/展示自定义通知(需要自行传入View)
```

* ## 反射工具包 -> [ZJReflectUtils.java](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/ZJReflectUtils.java) -> [Test](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/androidTest/java/com/zj/tools/mylibrary/ZJReflectUtilsTest.java)
```
reflect    : 设置要反射的类
newInstance: 实例化反射对象
field      : 设置反射的字段
method     : 设置反射的方法
get        : 获取反射想要获取的
```
> [CV参考链接](https://github.com/Blankj/AndroidUtilCode/blob/master/lib/utilcode/README-CN.md)

* ## 屏幕参数工具 -> [ZJScreenUtils.java](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/ZJScreenUtils.java) -> [Test](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/androidTest/java/com/zj/tools/mylibrary/ZJScreenUtilsTest.java)
```
getScreenWidth getScreenHeight  : 获取屏幕的宽/高
```

* ## SharePreferences工具 -> [ZJSPUtils.java](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/ZJSPUtils.java) -> [Test](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/androidTest/java/com/zj/tools/mylibrary/ZJSPUtilsTest.java)
```
initSp                              : 使用之前需要初始化
put(...) get(...)                   : 存/取各种类型数据
put(...)ForToday get(...)ForToday   : 存/取各种类型数据(当天有效,隔天会被清零)
contains                            : 是否包含某个Key
remove/clear                        : 移除某个Key/清除全部
```

* ## SpannableString拼接工具 -> [ZJTextUtils.java](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/ZJTextUtils.java) -> [Demo](https://github.com/summer-zhoujie/ZJUtils/blob/master/app/src/main/java/com/zj/tools/zjutils/ZJRichTextUtilsDemoActivity.java)
```
generateSpannableString : 生成一个用于拼接的SpannableString
```

* ## ZJToast吐司工具 -> [ZJToast.java](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/ZJToast.java)
```
init  : 使用之前需要初始化
show  : 弹出吐司
```

* ## 自定义视图-圆环进度 -> [CircleProgressBar.java](https://github.com/summer-zhoujie/ZJUtils/blob/master/mylibrary/src/main/java/com/zj/tools/mylibrary/view/CircleProgressBar.java) -> [Demo](https://github.com/summer-zhoujie/ZJUtils/blob/master/app/src/main/java/com/zj/tools/zjutils/CirProgressBarActivity.java)
```
max                     : 最大进度值(目标值)
progress                : 当前进度值
progress_color          : 进度条的颜色(当设置渐变色则此属性无效)
progress_color_start    : 进度条颜色(渐变色起始颜色)
progress_color_mid      : 进度条颜色(渐变色中间颜色)
progress_color_end      : 进度条颜色(渐变色结束颜色)
progress_bg_color       : 背景轨道颜色(当设置渐变色则此属性无效)
progress_bg_color_start : 背景轨道颜色(渐变色起始颜色)
progress_bg_color_mid   : 背景轨道颜色(渐变色中间颜色)
progress_bg_color_end   : 背景轨道颜色(渐变色结束颜色)
progress_circle_percent : 圆环的百分比, 决定是否是整圆还是圆弧
progress_start_angle    : 进度条的起始角度
progress_stroke_width   : 进度条的宽度
progress_thumb_width    : thumb图片的大小
progress_thumb_src      : thumb图片
```