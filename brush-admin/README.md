<h1>12月6日</h1>
<p>能够成功登陆和注册，并且能够完整的验证权限</p>
<p>使用@EnableGlobalMethodSecurity(prePostEnabled =true)开启注解</p>
<p>使用@PreAuthorize("hasRole('ROLE_ADMIN')")注解权限</p>
<p>使用的sql放在common下</p>
<p>登陆url<a href="http://localhost:8080/login">http://localhost:8080/login</a></p>
<p>注册url<a href="http://localhost:8080/signup">http://localhost:8080/signup</a></p> 
<h1>12月7日</h1>
<p>改为远程mysql和redis</p>
<p>更改mybatis-plus依赖</p>

 ```
<dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.0.2</version>
            <exclusions>
                <exclusion>
                    <artifactId>tomcat-jdbc</artifactId>
                    <groupId>org.apache.tomcat</groupId>
                </exclusion>
            </exclusions>
 </dependency>
 ```
 <p>然鹅还是没有com.baomidou.mybatisplus.annotations.IdType这个包</p>
 
 <h1>12月8日</h1>
 <p>加入了后台html开源模版，可运行项目查看</p>
 <p>今天周六就搞这么多了</p>
<h1>12月10日</h1>
<p>使用cep0连接池依赖为</p>

```
<dependency>
     <groupId>com.mchange</groupId>
     <artifactId>c3p0</artifactId>
     <version>0.9.5.2</version>
</dependency>
```
<p>这个模版的css好复杂！！！！！！！</p>
<h1>12月12日</h1>
<p>打个酱油，修复下只能获取一个权限的问题</p>
