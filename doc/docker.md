注意：springcloud的 yml中的key-value中，value和:之间要有空格。

1、安装docker
https://docs.docker.com/engine/installation/linux/docker-ce/centos/#set-up-the-repository

2、获取镜像 
 docker images --查看镜像
 docker pull centos:latest --从官网拉取镜像
 删除镜像，可以用 repo+tag删除 docker rmi centos:latest
3、自定义镜像
.制作Dockerfile
---------------------
FROM lwieske/java-8:latest
ENV JAVA_OPTS="-server -Xms64m -Xmx64m -XX:+UseConcMarkSweepGC -XX:+UseFastAccessorMethods -XX:+UseParNewGC"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /usr/liuxing/app.jar" ]
---------------------
说明：lwieske/java是从docker repository里边下载的，可以使用 docker search java 然后docker pull
.docker build -t liuxingjavabase .  --别忽略了尾部这个‘.’
建立容器 docker run --name client1 -dit --net=mcv  --privileged=true --ip=10.0.15.64 -v /opt/client1:/usr/liuxing:ro javabase3
docker run --name client1 -dit --net=mcv  --privileged=true --ip=192.168.1.124 -v /opt/client1:/usr/liuxing:ro javabase3
docker run --name feign1 -dit --net=mcv  --privileged=true --ip=10.0.15.96 -v /var/log/applog:/var/log/applog  -v /opt/feign1:/usr/liuxing:ro liuxingjavabase4

docker run 命令参数详解：
  --oom-score-adj=170 数值越大，越容易被kill
  --oom-kill-disable 保护该容器进程不被杀死，此参数在实际环境中作用不大，因为进程通常默认都是此值，所以设置前面的参数更为有效
  --memory=256m 限制容器支取宿主机内存的最大值
  --cpu="1.5"  如果时两核的，则此设置含义为可以用1.5个，生产环境没用过这个配置，意义不大


4、建立容器
首先建立网络，前提是设置混杂模式 ：ip link set eno16777984  promisc on
我们使用macvlan，请参考使用下面的链接
docker network --help
https://www.cnblogs.com/eshizhan/p/7255565.html
//mcv是自定义网络名称，macvlan是最新的网络驱动类型，如bridge，nat等等。
docker network create -d macvlan     --subnet=192.168.90.0/24     --gateway=192.168.90.254  -o parent=ens33 mcv
docker network list
然后建立容器
docker run --name registry1 -dit --net=mcv -v /opt/server/liuxing/:/opt/server/liuxing/ --privileged=true --ip=192.168.90.131 liuxingjavabase  java -Xms128m -Xmx128m -XX:+UseConcMarkSweepGC -XX:+UseFastAccessorMethods -XX:+UseParNewGC -jar -Dspring.config.location=classpath:/application-dev2.properties /opt/server/liuxing/ApiRegistry.jar
docker run --name initsocket1 -dit --net=mcv -v /opt/server/liuxing/:/opt/server/liuxing/ --privileged=true --ip=192.168.90.132 liuxingjavabase  java -Xms128m -Xmx128m -XX:+UseConcMarkSweepGC -XX:+UseFastAccessorMethods -XX:+UseParNewGC -jar -Dspring.config.location=classpath:/application-dev2.properties /opt/server/liuxing/ApiInitSocket.jar


docker run --name zhuce1 -dit --net=mcv -v /opt/eurekaserver/:/opt/eurekaserver --privileged=true --ip=10.0.15.62 lwieske/java-8  java -Xms64m -Xmx64m -XX:+UseConcMarkSweepGC -XX:+UseFastAccessorMethods -XX:+UseParNewGC -jar -Dspring.config.location=classpath:/application-dev2.properties /opt/eurekaserver/eurekaserver-0.0.1-SNAPSHOT.jar



5、常用命令
docker inspect  registry1 |grep IP   --根据容器名查找容器信息。
systemctl enable docker.service

docker stop registry1 && docker rm registry1
docker rmi liuxingjavabase
docker build -t liuxingjavabase .

docker save -o lwieske-java-8.tar lwieske/java-8
docker load -i lwieske-java-8.tar