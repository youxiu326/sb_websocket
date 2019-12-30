
###### WebSocket个人小例子

收发消息:
http://localhost:8888/


vue实例：
http://localhost:8888/vue


```

Spring-Boot整合freemarker引入静态资源css、js等（转）
一、概述

springboot 默认静态资源访问的路径为：/static 或 /public 或 /resources 或 /META-INF/resources 这样的地址都必须定义在src/main/resources目录文件中，这样可以达到在项目启动时候可以自动加载为项目静态地址目录到classpath下 ，静态访问地址其实是使用 ResourceHttpRequestHandler 核心处理器加载到WebMvcConfigurerAdapter进行对addResourceHandlers方法进行覆盖.将静态访问目录进行重新定义。我们也可以实现其中方法，手动指定静态访问路径通过继承WebMvcConfigurerAdapter重写内部方法addResourceHandlers也可以达到我们想要的效果。

二、静态资源配置方案

方案1、默认采用springboot 静态资源路径在src/main/resources创建/static 或 /public 或 /resources 或 /META-INF/resources可以直接访问静态资源，默认会放到classpath目录中

方案2、通过application.properties配置spring.resources.static-locations=classpath:/img/ 指定自定义静态文件的目录位置，，多个使用逗号分隔，springboot自动失效

方案3、创建StaticController类继承WebMvcConfigurerAdapter 重写addResourceHandlers 指定静态访问资源目录



addResourceHandler 表示拦截请求，如果遇到/img请求就会找 classpath:/img/中找到对应资源的位置，找到图片，如果没有找到就返回404错误

addResourceLocations 访问本地资源内容对应的映射路径


```
