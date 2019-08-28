参考资料
网上资料  ：https://www.linuxidc.com/Linux/2017-04/143022.htm
官网参数配置说明：http://nginx.org/en/docs/configure.html
本demo在华宇东升172.16.31.43
1.准备工作
下载 wget http://nginx.org/download/nginx-1.12.2.tar.gz
yum install openssl openssl-devel -y
yum install pcre pcre-devel -y
yum install gcc gcc-c++ kernel-devel -y
setenforce 0
systemctl status  firewalld ,查看并关闭
systemctl stop  firewalld
useradd -r -s /sbin/nologin -M www
2.install
指定安装目录，用户，模块。
下载解压，并进入解压目录后执行:
./configure --prefix=/usr/local/src/nginx/installdir-nginx --user=www --group=www --with-http_ssl_module --with-http_stub_status_module --add-module=/usr/local/src/nginx/3module/ngx_http_consistent_hash-master
make && make install
3.修改配置文件
进入到安装目录下的conf，修改nginx.conf
4.测试结果
进入到安装目录下的sbin
启动        ./nginx  
重新加载./nginx -s  reload
停止        ./nginx -s  stop
===添加第三方模块==================
5.一致性hash负载均衡模块
 文件名：ngx_http_consistent_hash-master.zip
github下载：https://github.com/replay/ngx_http_consistent_hash
解压到某个目录：
make clean 
./configure --prefix=/usr/local/src/nginx/installdir-nginx --user=www --group=www --with-http_ssl_module --with-http_stub_status_module --add-module=/usr/local/src/nginx/3module/ngx_http_consistent_hash-master
make && make install

6.被负载均衡的服务健康检测模块(未完成)
  A。安装nginx_upstream_check_module 下载地址：https://github.com/yaoweibin/nginx_upstream_check_module
  b。进入nginx源码目录，进行打该模块的补丁
      安装打补丁的命令：yum -y install patch
	  打补丁：patch -p1 < ../3module/nginx_upstream_check_module-master/check_1.14.0+.patch
	  ./configure --add-module=/usr/local/src/nginx/3module/nginx_upstream_check_module-master
	  可以使用 make && make install  
	  也可以：如果单纯添加模块，不需要install，而是执行以下操作，将打过补丁的nginx二进制文件覆盖/usr/local/nginx/sbin/目录中的文件即可
	  　　cp /usr/local/nginx/sbin/nginx　 /usr/local/nginx/sbin/nginx.bak
      　　cp /nginx源码目录/objs/nginx   /usr/local/nginx/sbin/
  c。
            server 127.0.0.1:8181;
            server 127.0.0.1:8182;
            #http健康检查相关配置
            check interval=3000 rise=2 fall=3 timeout=3000 type=http;
            #/health/status为后端健康检查接口
            check_http_send "HEAD /health/status HTTP/1.0\r\n\r\n";
            check_http_expect_alive http_2xx http_3xx;
			
			
命令
/sbin/nginx -s reload
