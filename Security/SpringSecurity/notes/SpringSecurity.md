# Spring Security

## 目录

* [1. Quickstart](#1-quickstart)
* [2. Basic Auth](#2-basic-auth)
* [3. Users Roles and Authorities](#3-users-roles-and-authorities)
* [4. Permission Based Authentication](#4-permission-based-authentication)
* [5. Cross-site request forgery (CSRF)](#5-cross-site-request-forgery--csrf-)
* [6. Form Based Authentication](#6-form-based-authentication)
* [7. Database Authentication](#7-database-authentication)
* [8. JWT](#8-jwt)
* [参考资料](#----)

 

## 1. Quickstart

添加依赖

**pom.xml**

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```



编写 `controller`

**HelloController.java**

```java
@RestController
public class HelloController {

    @RequestMapping("/")
    public String sayHello() {
        return "Hello!";
    }
    
}
```

配置端口为 9090

```yaml
server:
  port: 9090
```

当 `url` 输入以下网址时

<div align="center"> <img src="image-20200729101107448.png" width="20%"/> </div><br>

页面变成了

<div align="center"> <img src="image-20200729101213621.png" width="70%"/> </div><br>



可见，我们的接口没有赤裸裸地暴露供第三方随意访问了，`spring security` 框架给我们加了一层保护

用户名默认为 `user`

密码从控制台可以获得

<div align="center"> <img src="image-20200729101512541.png" width="70%"/> </div><br>

成功跳转

<div align="center"> <img src="image-20200729101705261.png" width="60%"/> </div><br>



⚠️注意：

`login` 之后默认跳转到 `/` 路径



## 2. Basic Auth

配置 `security`

⚠️注意：一定要加 `@Configuration` ！

**ApplicationSecurityConfig.java**

```java
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
```

继承了 `WebSecurityConfigurerAdapter`，我们重写了 `configure` 方法（参数为 `HttpSecurity`）

解释一下编写思路（编写时会有代码提示）

对发过来的 `http request`

1. 授权请求
2. 所有请求
3. 都要验证
4. 和
5. 使用 `http basic` 验证方式


<div align="center"> <img src="image-20200729103511124.png" width="60%"/> </div><br>

改一下 `controller`

**HelloController.java**

```java
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello!";
    }

}
```



成功访问


<div align="center"> <img src="image-20200729112220291.png" width="90%"/> </div><br>

## 3. Users Roles and Authorities

### 3.1 User service

在安全领域

一个用户包括一般包括以下信息：

- username
- password
- role
- authorities

- ...



`Spring security` 默认的用户是 `user`

```java
/**
  * 配置用户信息
  * 
  * @return 
  */
@Override
@Bean
protected UserDetailsService userDetailsService() {
  UserDetails userDetails = User.builder()
    .username("ceezyyy")
    .password("123")
    .roles("admin")
    .build();

  return new InMemoryUserDetailsManager(userDetails);

}
```



解释（具体查看源码）：

- User：用户类
- UserDetails：用户信息类
- InMemoryUserDetailsManager：用户信息保存在内存



这里有一个小技巧，方法返回值是 `UserDetailsService`

是一个接口，点击左边绿色图标可以查看其实现类

<div align="center"> <img src="image-20200729115735148.png" width="70%"/> </div><br>



总的来说，用户信息配置类通过工厂模式创建了一个用户信息对象，并保存在内存中



### 3.2 Password

作为一个企业级安全框架，是决不允许密码以明文形式存储

`Spring security` 为我们提供了一个利器：`PasswordEncoder`

**PasswordEncoder.class**

```java
public interface PasswordEncoder {
    String encode(CharSequence var1);

    boolean matches(CharSequence var1, String var2);

    default boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
```

采用第三种加密方式：

<div align="center"> <img src="image-20200729142305771.png" width="70%"/> </div><br>

**PasswordConfig.java**

```java
@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
```

**ApplicationSecurityConfig.java**

```java
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 配置用户信息
     *
     * @return
     */
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
                .username("ceezyyy")
                .password(passwordEncoder.encode("123"))
                .roles("admin")
                .build();

        return new InMemoryUserDetailsManager(userDetails);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/index")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
```



<div align="center"> <img src="image-20200729143042853.png" width="40%"/> </div><br>

debug 一下，发现明文密码 “123” 已经加密


成功访问

<div align="center"> <img src="image-20200729142739165.png" width="50%"/> </div><br>



### 3.3 Roles and Permissions

模拟两个角色：

- admin
- visitor

两个角色对应着不同的权限





<div align="center"> <img src="roles.jpg" width="50%"/> </div><br>

 











## 4. Permission Based Authentication

























## 5. Cross-site request forgery (CSRF)































## 6. Form Based Authentication





















## 7. Database Authentication



























## 8. JWT









## 9. Conclusion

1. `Springboot` 与其他框架整合时，配置类：
   - 一定要加上 `@Configuration` 注解
   - 加上 `@EnableXXX` 注解
2. 多看源码
3. 工厂模式很常用










## 参考资料

- [Spring Security | FULL COURSE](https://www.youtube.com/watch?v=her_7pa0vrg)

　