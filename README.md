# fastdfs-client-spring-boot-starter
Spring Boot Starter For FastDFS Client

***基于fastdfs-client: https://github.com/yinkren/fastdfs-client***

```
springboot 版本 2.2.5.RELEASE
```

yml示例：

```yml
fastdfs:
  tracker-servers: "10.10.10.110:22122"
  group-name: "group1"
  http:
    web-server-url: "http://10.10.10.110:8888"
    web-server-url-has-group: false
    http-anti-steal-token: false
    charset: "UTF-8"
    secret-key: "FastDFS1234567890"
  connection:
    socket-timeout: 30000
    connect-timeout: 2000
    retry-after-second: 30
    pool:
      max-wait-millis: 5000
      max-total-per-key: 500
      max-idle-per-key: 100
      min-idle-per-key: 10
      min-evictable-idle-time-millis: 1800000
      soft-min-evictable-idle-time-millis: 60000
      test-on-borrow: true
```