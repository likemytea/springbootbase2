1、开通一个ecs，你要在安全组里边配置他的端口号的，否则外网不能访问的。
docker run --name registry1  --net=multi-host-network -p 8761:8761   -v /opt/server/registry/:/opt/server/registry/  img-registry
docker run --name nginx1 --net=multi-host-network -v /opt/server/nginx/config:/etc/nginx/conf.d -v /opt/server/nginx/www/:/data/www/ -p 80:80 -p 443:443 -p 9093:9093  -d nginx

docker run -d  --name feign1 --privileged=true --net=multi-host-network  -p 8765:8765 -v /var/log/applog:/var/log/applog  -v /opt/proj/feign/:/usr/liuxing:ro liuxingjavabase3

docker run -d  --name userservice1 --privileged=true --net=multi-host-network  -p 8766:8766 -v /var/log/applog:/var/log/applog  -v /opt/proj/userservice/:/usr/liuxing:ro liuxingjavabase3

docker run -d  --name myzuul1 --privileged=true --net=multi-host-network  -p 8770:8770 -v /var/log/applog:/var/log/applog  -v /opt/proj/myzuul/:/usr/liuxing:ro liuxingjavabase3


docker run --name nginx1 --privileged=true -d --net=multi-host-network -p 80:80 -v /var/nginx/webroot:/usr/share/nginx/html:ro -v /var/nginx/logs:/var/log/nginx -v /etc/nginx:/etc/nginx:ro nginx