#!/bin/sh

export JAVA_HOME=/DISK/software/java/jdk1.8.0_77
export PATH=$JAVA_HOME/bin:$PATH

pid=`ps aux | grep java.*spring-boot-angularjs.jar | grep -v grep | awk '{print $2}'`
if [ $pid ]
then
    kill -9 $pid
fi

nohup java -Xms256m -Xmx256m  -jar spring-boot-angularjs.jar > info.out 2>&1 &
tail -200f info.out
