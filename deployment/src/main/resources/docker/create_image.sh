#!/usr/bin/bash

# Author      : zhysunny
# Date        : 2020/8/7 22:30
# Description : 生成镜像

# 当前文件所在目录，这里是相对路径
LOCAL_PATH=`dirname $0`
# 当前文件所在目录转为绝对路径
BASE_PATH=`cd ${LOCAL_PATH};pwd`

docker build -t centos7/java:1.0.0 ${BASE_PATH}