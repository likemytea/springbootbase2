1、开通一个ecs，你要在安全组里边配置他的端口号的，否则外网不能访问的。
docker run --name registry1  --net=multi-host-network -p 8761:8761   -v /opt/server/registry/:/opt/server/registry/  img-registry
docker run --name nginx1 --net=multi-host-network -v /opt/server/nginx/config:/etc/nginx/conf.d -v /opt/server/nginx/www/:/data/www/ -p 80:80 -p 443:443 -p 9093:9093  -d nginx