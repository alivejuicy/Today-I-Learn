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


---

## Model 1
![](https://velog.velcdn.com/images/alivejuicy/post/6d8a6076-f6a4-4da7-8593-a88c748460d2/image.png)

**Model 1** 방식은 **Controller 영역에 View 영역을 같이 구현**하는 방식이며, 사용자의 요청을 JSP가 전부 처리한다. 요청을 받은 JSP는 
JavaBean Service Class를 사용하여 웹 브라우저 사용자가 요청한 작업을 처리하고 그 결과를 출력하는 방식이다.
결과적으로 View와 Controller의 역할을 JSP가 모두 담당해야 하기 때문에 코드와 구조가 복잡해질 가능성이 높다. 이러한 점을 개선하기 위해 
`Model 2` 아키텍처가 등장한다.

## Model 2
![](https://velog.velcdn.com/images/alivejuicy/post/e6b314af-8651-4652-bde1-cd5850774f4d/image.png)
**Model 2** 방식은 웹 브라우저 사용자의 요청을 서블릿이 받고 서블릿은 해당 요청으로 View로 보여줄 것인지 Model로 보낼 것인지를 판단하여 전송한다. 또한 `HTML` Source와 `JAVA` Source를 분리해놓았기 때문에 Model 1 방식에 비해 확장시키기도 쉽고 유지보수 또한 쉬운 특징이 있다. 이 방식을 이해하면서 Spring MVC를 시작해보자.

---
# Spring MVC?
`Spring MVC`는 위에 정리한 **MVC Pattern**을 따르는 Servlet 기반의 프레임워크라 할 수 있다. `Model 2` 아키텍처를 적용하여 만들어졌으며 유연한 아키텍처 구성 및 큰 규모의 시스템을 개발할 때 적합한 프레임워크이다.

---
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
