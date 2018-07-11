总结都来自于这个链接https://segmentfault.com/a/1190000004976222
首先普及常识
1、http协议是 客户端-服务端的超文本传输协议
2、https和http不同之处有四点
一、https协议需要到ca申请证书，一般免费证书很少，需要交费。
二、http是超文本传输协议，信息是明文传输，https 则是具有安全性的ssl加密传输协议。
三、http和https使用的是完全不同的连接方式，用的端口也不一样，前者是80，后者是443。
四、http的连接很简单，是无状态的；HTTPS协议是由SSL+HTTP协议构建的可进行加密传输、身份认证的网络协议，比http协议安全。
现在开始处理nginx的https：
TLS或传输层安全( transport layer security)，它的前身是SSL(安全套接字层secure sockets layer)，是Web协议用来包裹在一个受保护，加密封装正常通道。
采用这种技术，服务器和客户端之间可以安全地进行交互，而不用担心消息将被拦截和读取。证书系统帮助用户在核实它们与连接站点的身份。
步骤1：Create the SSL Certificate
1、创建保存证书和key的目录及创建证书
  /etc/dockernginx/ssl
  openssl req -x509 -nodes -days 36500 -newkey rsa:2048 -keyout /etc/dockernginx/ssl/nginx.key -out /etc/dockernginx/ssl/nginx.crt
2、配置nginx配置文件，注意：配置文件里的证书路径是 host对应容器的挂载路径。
    listen       80;
    listen       443 ssl;  https 默认是指定使用 443端口的，如果需要指定别的端口，比如websocket的特殊端口 9090  9093 ，只需要 listen 9093 ssl;即可。   
    server_name  localhost;
    ssl_certificate      /etc/nginx/ssl/nginx.crt;
    ssl_certificate_key  /etc/nginx/ssl/nginx.key;
3、重启即可。
    