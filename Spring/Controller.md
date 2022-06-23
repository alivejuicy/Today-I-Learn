## @Controller, @RestController 이해

스프링 내에서 컨트롤러를 지정해주기 위한 어노테이션은 @Controller와 @RestController가 있습니다. 
Spring MVC의 컨트롤러인 `@Controller`와, RESTful 웹 서비스의 컨트롤러인 `@RestController`의 주요 차이점은 
**HTTP Response Body가 생성되는 방식에 차이가 있습니다.**

`@Controller`는 주로 View를 반환하기 위해 사용합니다.
`@RestController`는 Data를 반환하기 위해서 사용됩니다.

---
## Dispatcher-Servlet
`Servlet`이란 클라이언트의 요청을 처리, 그 결과를 반환하는 Servlet 클래스의 구현 규칙을 지킨 자바 웹 프로그래밍 기술 중 하나라고 볼 수 있습니다.

디스패치는 **"보내다"** 라는 뜻을 가지고 있습니다. 
`Dispatcher-Servlet`이란 HTTP 프로토콜로 들어오는 모든 요청을 가장 먼저 받아서 적합한 컨트롤러에 위임하는
 **Front Controller**라고 정의할 수 있습니다.

Client로부터 어떠한 요청이 오면 톰캣과 같은 **Servlet Container**가 요청을 받게 됩니다.
그리고 이 모든 요청을 `Dispatcher-Servlet`이 가장 먼저 받는 것입니다. 
결국 Client의 모든 요청을 받아서 처리해주는 컨트롤러이며, MVC 구조에서 함께 사용되고 있는 디자인 패턴이라 할 수 있습니다.

---
### @Controller에서의 View 반환 처리 과정
>1. Dispatcher-Servlet은 Client와 Controller 사이에서 
HandlerMapping을 통해 요청 과정을 Controller에 위임.
>> 2. 요청을 처리한 Controller가 ViewName을 View Resolver에 전달한다. 
>> 3. Dispatcher-Servlet은 ViewResolver를 통해 View를 받아 Client에 반환한다.

**객체(데이터)** 처리 과정에서는 `@Controller`가 데이터를 반환하기 위해 `@ResponseBody` 어노테이션을 활용해주어야 합니다. 이를 통해 `@Controller`도 JSON 형태로 데이터를 반환할 수 있는 것입니다.
>
```java
 @GetMapping(value = "/users")
    public @ResponseBody ResponseEntity<User> findUser(@RequestParam("userName") String userName){
        return ResponseEntity.ok(userService.findUser(user));
    }
```

***


### @RestController
`@RestController`는 `@Controller`에 @ResponseBody가 추가된 것입니다. REST API를 개발할 때 주로 사용하며 객체를 ResponseEntity로 감싸서 반환합니다.

```java
@GetMapping(value = "/users")
    public User findUser(@RequestParam("userName") String userName){
        return userService.findUser(user);
    }

```


***




