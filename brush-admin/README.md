<p>能够成功登陆和注册，并且能够完整的验证权限</p>
<p>使用@EnableGlobalMethodSecurity(prePostEnabled =true)开启注解</p>
<p>使用@PreAuthorize("hasRole('ROLE_ADMIN')")注解权限</p>
<p>使用的sql放在common下</p>
<p>登陆url<a href="http://localhost:8080/login">http://localhost:8080/login</a></p>
<p>注册url<a href="http://localhost:8080/signup">http://localhost:8080/signup</a></p> 