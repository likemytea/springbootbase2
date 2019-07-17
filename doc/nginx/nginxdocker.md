docker容器之nginx官方配置篇
docker pull nginx
先执行 
docker run --name nginx1 -d --net=mcv --ip=192.168.90.134 -v /var/nginx/webroot:/usr/share/nginx/html:ro -v /var/nginx/logs:/var/log/nginx -d nginx
这儿简单介绍下ro,默认容器对这个目录有可读写权限，可以通过指定ro，将权限改为只读 , 
-v 后 host下的目录:containner目录
执行完上面的后，从container获取配置文件，注意要获取nginx目录下所有的哈
docker cp nginx1:/etc/nginx  /etc/
然后以后就可以使用这个命令了，如果想修改配置文件，只需要修改host下的/etc/nginx下的文件即可。配置时一定要铆定一个思路：挂载出来的文件运行时是要加载到docker线程中去的！这样就不容易混淆。 
docker run --name nginx1 -d --net=mcv --ip=192.168.90.134 -v /var/nginx/webroot:/usr/share/nginx/html:ro -v /var/nginx/logs:/var/log/nginx -v /etc/nginx:/etc/nginx:ro -d nginx

----------------------------------
vpc网络 ，朱zhisheng配置的nginx
对于vpc网络，如果不配置 端口映射的话，telnet也可以ping通，但是在host下用 ss-nat命令看的话，看不到这个（listen端口），连也连不上。
[root@fz-nginx2 config]# docker run --name nginx2 --net=multi-host-network -v /opt/server/nginx/config:/etc/nginx/conf.d -v /opt/server/nginx/www/:/data/www/ -p 80:80 -p 443:443 -p 9093:9093  -d nginx


---下面是配置阿里云的docker swarm vpc网络的nginx,其它的和上面的一样，唯独下面几行不同----------
docker run --name nginx1 -d --net=multi-host-network -p 80:80 -v /var/nginx/webroot:/usr/share/nginx/html:ro -v /var/nginx/logs:/var/log/nginx  nginx
docker cp nginx1:/etc/nginx  /etc/dockernginx/
docker run --name nginx1 -d --net=multi-host-network -p 80:80 -p 443:443 -v /var/nginx/webroot:/usr/share/nginx/html:ro -v /etc/localtime:/etc/localtime:ro -v /var/nginx/logs:/var/log/nginx  -v /etc/dockernginx:/etc/nginx:ro  nginx