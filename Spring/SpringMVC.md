# MVC란?
![](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/MVC-Process.svg/800px-MVC-Process.svg.png)

MVC란 `Model-View-Controller`의 약자로 애플리케이션을 세 가지 역할로 구분한 개발 방법론이다. 간단하게 정의하자면
사용자(User)가 Controller를 조작하면 Controller는 Model을 통해 데이터를 가져오고 그 데이터를 바탕으로 View를 통해 시각적 표현을 제어하여 사용자에게 전달하게 되는 디자인 패턴이다.

>이러한 패턴을 성공적으로 사용하면, 사용자 인터페이스로부터 **비즈니스 로직**을 분리하여 애플리케이션의 시작적 요소나 그 이면에서 실행되는 비즈니스 로직을 서로 영향 없이 쉽게 고칠 수 있는 애플리케이션을 만들 수 있게 된다.

**모델**은 View 렌더링에 필요한 데이터 혹은 로직의 처리를 맡는 부분이다. 일반적으로 **POJO(Plain Old Java Object)** 로 구성된다.
모델은 뷰나 컨트롤러에 대해서 어떤 정보도 알지 말아야한다.

**뷰**는 사용자가 보는 화면을 위한 디스플레이 데이터 또는 **프레젠테이션**을 맡는 부분으로 Model 데이터의 `렌더링`을 담당한다.
뷰는 받은 데이터를 화면에 표시해주는 역할만을 수행해야하며 모델에게 전달받은 데이터를 별도로 저장하지 않아야 한다.

**컨트롤러**는 뷰와 모델의 중간에서 View와 Model에 대한 사용자의 입력이나 요청에 대해 비즈니스 로직 처리 후 그 결과를 Model에 담아 View에 전달하는 것을 맡는 부분이며 Servlet이 담당한다.

그래서 모델이나 뷰는 서로의 존재를 모르지만 컨트롤러는 이들을 중재하기 위해 모델과 뷰의 정보에 대해 알고있다.

이 MVC 패턴의 방식으로는 두 가지가 있다.




## Model 1
![](https://velog.velcdn.com/images/alivejuicy/post/6d8a6076-f6a4-4da7-8593-a88c748460d2/image.png)

**Model 1** 방식은 **Controller 영역에 View 영역을 같이 구현**하는 방식이며, 사용자의 요청을 JSP가 전부 처리한다. 요청을 받은 JSP는 
JavaBean Service Class를 사용하여 웹 브라우저 사용자가 요청한 작업을 처리하고 그 결과를 출력하는 방식이다.
결과적으로 View와 Controller의 역할을 JSP가 모두 담당해야 하기 때문에 코드와 구조가 복잡해질 가능성이 높다. 이러한 점을 개선하기 위해 
`Model 2` 아키텍처가 등장한다.

## Model 2
![](https://velog.velcdn.com/images/alivejuicy/post/e6b314af-8651-4652-bde1-cd5850774f4d/image.png)
**Model 2** 방식은 웹 브라우저 사용자의 요청을 서블릿이 받고 서블릿은 해당 요청으로 View로 보여줄 것인지 Model로 보낼 것인지를 판단하여 전송한다. 또한 `HTML` Source와 `JAVA` Source를 분리해놓았기 때문에 Model 1 방식에 비해 확장시키기도 쉽고 유지보수 또한 쉬운 특징이 있다. 이 방식을 이해하면서 Spring MVC를 시작해보자.


# Spring MVC?
`Spring MVC`는 위에 정리한 **MVC Pattern**을 따르는 Servlet 기반의 프레임워크라 할 수 있다. `Model 2` 아키텍처를 적용하여 만들어졌으며 유연한 아키텍처 구성 및 큰 규모의 시스템을 개발할 때 적합한 프레임워크이다.


## Spring MVC 구조
![](https://terasolunaorg.github.io/guideline/5.0.1.RELEASE/en/_images/RequestLifecycle.png)

**MVC Pattern**을 적용한 `Spring MVC 프레임워크` 기본 처리 구조이다.
[인용 출처](https://tecoble.techcourse.co.kr/post/2021-06-25-dispatcherservlet-part-1/)
> ① DispatcherServlet으로 클라이언트의 **웹 요청(HttpServletRequest)** 가 들어온다.
* 웹 요청을 LocaleResolver, ThemeResolver, MultipartResolver 인터페이스 구현체에서 분석한다. <br/>
② 웹 요청을 `HandlerMapping`에 위임하여 해당 요청을 처리할 `Handler(Controller)`를 탐색한다. <br/>
③ 찾은 Handler를 실행할 수 있는 `HandlerAdapter`를 탐색한다. <br/>
④, ⑤ 찾은 HandlerAdapter를 사용해서 Handler의 메소드를 실행한다. 이때, Handler의 반환값은 `Model`과 `View`이다. <br/>
⑥ View 이름을 `ViewResolver`에게 전달하고, ViewResolver는 해당하는 View 객체를 반환한다.<br/>
⑦ `DispatcherServlet`은 View에게 Model을 전달하고 화면 표시를 요청한다. 이때, Model이 null이면 View를 그대로 사용한다. 반면, 값이 있으면 View에 Model 데이터를 렌더링한다.<br/>
⑧ 최종적으로 DispatcherServlet은 View 결과`(HttpServletResponse)`를 클라이언트에게 반환한다.<br/>

해당 흐름은 @Controller를 기준이다. @RestController의 경우 ⑥, ⑦ 과정이 생략된다. 즉, `ViewResolver`를 타지 않고 반환값에 알맞는 `MessageConverter`를 찾아 응답 본문을 작성한다.


한 눈에 봐서는 이해하기 어려울 수 있지만 요청과 처리 방식을 각각 분리한 구조인 것을 확인 할 수 있었고 Spring MVC 구조에서 `Dispatcher-Servlet`은 Spring MVC의 핵심 요소이다. 저번 학습에서 `Dispatch-Servlet`는 클라이언트에서 웹 요청 시, `Dispatcher-Servlet`에서 이루어지는 동작의 흐름은 `Front Controller`의 역할을 한다고 알 수 있었다.
<br/>

# WAS에서 Client의 요청 처리

### WAS(Web Applicatoin Server)
일반적인 웹서버(Web Server)는 정적인 처리만을 다룬다. 
그러나 __Web Application Server__`(Tomcat)`은  웹서버와는 다르게 DB연결, 다른 응용프로그램과 상호 작용 등 동적인 기능들을 사용할 수 있다. Tomcat은 Apache 웹서버의 일부 기능 + Web container 조합으로, 웹서버의 정적 데이터 처리 기능과 동적 데이터 처리 기능 모두를 포함하고 있다. 하나의 톰캣은 하나의 __JVM__을 가지는 특징이 있다.

![](https://velog.velcdn.com/images/alivejuicy/post/e3b94a5c-dbed-4d1e-bab6-d10f4081fb42/image.png)
### WAS(Tomcat)에서부터 Spring MVC 프레임워크의 클라이언트 요청 처리 단계 살펴보기

>
1. Client에서 WAS에 내장된 웹서버에 HttpRequest가 들어온다.
2. 웹서버에서 요청을 Servlet container로 전달한다.
3. Servlet container는 각 요청별로 thread를 생성한다. 혹여 Servlet container에 Dispatcher-Servlet 인스턴스가 존재하지 않을 경우 디스패처 서블릿을 생성하고 init() 메소드를 호출한다. 
5. thread에서는 Dispatcher-Servlet의 service() 메소드를 호출한다.
6. Dispatcher-Servlet은 `HandlerMapping`을 통해 요청을 `Mapping(=위임)`할 `Handler(Controller)`를 조회한다.
7. 조회된 `Handler(Controller)`를 실행할 수 있는 HandlerAdapter를 탐색한다.
8. 탐색된 HandlerAdapter를 통해 Mapping한 Handler를 호출하여 요청을 처리한다.
이때 Controller는 요청 처리 후 반환값은 `Model(Service & DAO)`과 `View`를 받는 것이다.
9. [이하 과정 같음](https://velog.io/@alivejuicy/Spring-Spring-MVC-1)

여기서 알 수 있는 점은 Dispatcher-Servlet은 결론적으로 Spring Framework에서 제공하는 `HandlerMapping`, `HandlerAdapter`, `ViewResolver` 같은 전략 인터페이스들의 구현체를 받아서 사용하는 것이다. 즉 요청을 받으면 필요한 공통된 처리를 한 후, 요청에 맞는 Handler를 조회 및 위임하는 과정을 거친 뒤 Handler의 실행 결과를 HttpResponse 형태로 만드는 역할을 하는 것이다. 각 인터페이스들의 정의와 구현체들을 살펴보자

### HandlerMapping 

Client의 Request URL과 매칭되어 요청을 처리할 핸들러를 선택하는 역할을 수행하는 인터페이스이다. HandlerMapping은 인터페이스이고 이것을 이용한 여러 구현체가 존재한다.
>
**BeanNameUrlHandlerMapping**
URL과 일치하는 이름을 갖는 빈의 `이름`을 컨트롤러로 매핑하는 방법.
**ControllerBeanNameHandlerMapping**
URL과 일치하는 이름을 갖는 빈의 `아이디`를 컨트롤러로 사용하는 방법.
**SimpleUrlHandlerMapping**
URL패턴에 매핑되는 지정된 컨트롤러를 사용하는 방법, 위 2가지는 하나의 Controller에 하나의 URL을 매핑하는 방법이지만 이건 하나의 Controller에 여러 URL 매핑이 가능한 방법이다.
**RequestMappingHandlerMapping**
@RequestMapping을 이용한 매핑 방법이며 주로 사용하는 전략이다. 어노테이션을 통해 URL과 컨트롤러를 매핑한다. 이 방법은 컨트롤러 내 메소드와도 매핑이 가능하다.

Handler Mapping은 1개 이상 동시에 사용이 가능하다. 1개 Mapping으로 통일하는 것이 보편적이나 2개 이상 매핑의 경우 URL이 중복 매치될 수 있다. 그럴 경우 ORDER PROPERTY를 통해 Mapping 우선 순위를 지정할 수 있다.

### HandlerAdapter

HandlerMapping이 위임한 Handler(Controller)를 호출하고 처리하는 인터페이스를 뜻한다.
Spring에서는 4가지 방식의 컨트롤러를 지원하며 각 Controller를 연결해주는 Adapter가 있어야 한다. 

> **RequestMappingHandlerAdapter**
@RequestMapping annotation을 처리한다.
**HttpRequestHandlerAdapter**
HttpRequestHandler를 처리한다.
**SimpleControllerHandlerAdapter**
컨트롤러 인터페이스를 처리한다.

### View
뷰는 MVC 아키텍처에서 Model이 가진 정보를 어떻게 표현해야 하는지에 대한 logic을 가지고 있는 Component이다.

### ViewResolver
컨트롤러에서 반환하는 View 이름에 해당하는 View를 찾아내는 인터페이스이다.
