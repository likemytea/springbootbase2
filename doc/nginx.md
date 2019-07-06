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
===添加第三方一致性hash模块==================
 文件名：ngx_http_consistent_hash-master.zip
github下载：https://github.com/replay/ngx_http_consistent_hash
解压到某个目录：
make clean 
./configure --prefix=/usr/local/src/nginx/installdir-nginx --user=www --group=www --with-http_ssl_module --with-http_stub_status_module --add-module=/usr/local/src/nginx/3module/ngx_http_consistent_hash-master
make && make install

