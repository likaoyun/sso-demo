FROM openjdk:8u332-jdk
# 设置时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
WORKDIR /apps
# 添加程序jar文件
COPY target/*.jar /apps/app.jar
# 设置应用的jvm参数
ENV jvm_opts="-server -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -Xms512m -Xmx1024m -Xmn512m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dtask=true"
EXPOSE 8080
ENTRYPOINT exec java -jar $jvm_opts app.jar