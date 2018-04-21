Learn for Java
====
[![Build Status](https://travis-ci.org/Ryan-Miao/l4Java.svg?branch=master)](https://travis-ci.org/Ryan-Miao/l4Java)
[![codecov](https://codecov.io/gh/Ryan-Miao/l4Java/branch/master/graph/badge.svg)](https://codecov.io/gh/Ryan-Miao/l4Java)


本项目宗旨在学习和测试Java的基础类库。支持最低jdk版本为1.8。


## dependency
- JDK1.8+
- gradle 4.4+
- jacoco
- checkstyle
- findbugs
- pmd
- dependency report


## how to run

```
git clone git@github.com:Ryan-Miao/l4Java.git
```
然后，导入idea。


在idea里设置-editor-codestyle导入`config/intellij-java-google-style.xml`

idea需要安装插件
- lombok
- checkstyle
- pmd

lombok 需要在设置-compile-annotation允许

checkstyle添加配置文件`config/checkstyle/checkstyle.xml`

pmd添加配置文件`config/pmd/pmd-ruleset.xml`
