| 方法 | 说明 |
| --- | --- |
|antMatchers()     | 匹配请求路径|
|hasRole()    | 表示访问路径需要的权限|
|hasAnyRole() |    表示任意一个权限即可
|access() |    表示多个权限组合|
|anyRequest() |    表示除了前面定义的URL模式之外，用户必须登录后访问|
|authenticated()     | 表示开启单点登录|
|formLogin()    | 表示提供表单登录页面|
|loginProcessingUrl()    | 表示登录请求接口|
|permitAll()     | 表示和登录相关接口都不需要认证即可访问|
|csrf().disable()     | 表示关闭csrf|
|logout     | 开启注销登录配置|
|logoutUrl |    注销url|
|clearAuthentication     | 表示清除认证信息,默认true|
|invalidateHttpSession |    表示是否使Session失效，默认true|
|addLogoutHandler |    可完成一些清除工作|
|logoutSuccessHandler |    退除成功后的操作|