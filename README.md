# fastdfs-client-spring-boot-starter
Spring Boot Starter For FastDFS Client

***fastdfs-client: https://github.com/ykrenz/fastdfs-client***

`springboot-server参考` https://github.com/ykrenz/springboot-file-server#fastdfs 配置
fastdfs:
enabled: true
# tracker服务
tracker-servers: "192.168.24.130:22122"
# 固定分组
#  default-group: "group1"
http:
# web地址
web-servers: "http://192.168.24.130:8888"
# 访问地址是否包含group
url-have-group: true
# 开启防盗链
http-anti-steal-token: true
# 防盗链密钥
secret-key: "FastDFS1234567890"
connection:
#scoket超时
socket-timeout: 30000
#连接超时
connect-timeout: 2000
#tracker重试
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
springboot 版本 2.2.5.RELEASE
```
健康检查端点: /actuator/fastdfs

```yml
management:
  endpoints:
    web:
      exposure:
        include: 'fastdfs'
```

yml示例：

```yml
#fastdfs 配置
fastdfs:
  enabled: true
  # tracker服务
  tracker-servers: "192.168.24.130:22122"
  # 固定分组
  #  default-group: "group1"
  http:
    # web地址
    web-servers: "http://192.168.24.130:8888"
    # 访问地址是否包含group
    url-have-group: true
    # 开启防盗链
    http-anti-steal-token: true
    # 防盗链密钥
    secret-key: "FastDFS1234567890"
  connection:
    #scoket超时
    socket-timeout: 30000
    #连接超时
    connect-timeout: 2000
    #tracker重试
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