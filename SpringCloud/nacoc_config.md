1. use nacos_config db script to initialize the db
2. edit the file in the folder /nacos/conf/application.properties
3. paste the items there

spring.datasource.platform=mysql

db.num=1
db.url.0=jdbc:mysql://host.docker.internal:3306/nacos_config?useUnicode=true&characterEncoding=utf-8&useSSL=false
db.user=root
db.password=zl7758258

4. restart the nacos docker, if use non-docker nacos installation, the db.url is a little different with 'host.docker.internal'


Using docker cluster just follow the Nacos offical document, but there are some pitfalls there.


1. after execute this command, 'docker-compose -f example/cluster-hostname.yaml up', it doesn't work, waiting a little while it will occurs
   bad handshake
2. you will enter the docker container change the config ,'/etc/mysql/conf.d/my.cnf', at the end append this 'skip_ssl' to avoid this error 'bad handshake'.
   here you need to install vi or vim.
   

the pitfalls solve by google from this site, https://www.gitmemory.com/issue/nacos-group/nacos-docker/184/841759603, not so pretty good face, but it work.
stuck me about two days, tried many ways to figure this out.