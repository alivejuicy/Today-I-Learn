# Spring Container

스프링 컨테이너(Container)란 스프링 프레임워크의 핵심이며 스프링 빈의 생명 주기를 관리하고 Spring 프레임워크의 특징인 `IoC`와 `DI`(의존성 주입)을 제공해주는 역할을 한다.

스프링 컨테이너에 대해 자세히 학습하기 전에 스프링 전체 구조를 이해하고 다뤄보자.

### 스프링의 전체적인 구조
![](https://velog.velcdn.com/images/alivejuicy/post/afc53b63-8721-4f85-a70e-d39e1adc92f4/image.png)


### Spring Core (⭐)

> Spring Core는 `Spring Container`을 의미합니다. **Core**라는 말 그대로 Container는 Spring Framework의 핵심이며 
Bean Factory라는 IoC 패턴을 적용하여 객체구성 부터 의존성 처리의 역할을 할 수 있다.

 

### Spring Context

> Spring Context는 Spring Framework의 
context 정보들을 제공하는 설정 파일입니다. 
Spring Context에는 JNDI, EJB, Validation, Scheduiling, Internaliztaion 등 **엔터프라이즈 서비스**들을 포함하고 있습니다.

 

### Spring AOP

> Spring AOP module은 Spring Framework에서 관점지향 프로그래밍을 할 수 있고 AOP를 적용 할수 있게 도와주는 Module입니다. 

 

### Spring DAO

> DAO란 **Data Access Object**의 약자로 Database Data에 접근하는 객체입니다. Spring JDBC DAO는 
추상 레이어를 지원함으로써 코딩이나 예외처리 하는 부분을 간편화 시켜 일관된 방법으로 코드를 짤 수 있게 도와줍니다.

 

### Spring ORM

> ORM이란 **Object relational mapping**의 약자로 간단하게 객체와의 관계 설정을 하는 것입니다. Spring에서는 
Ibatis, Hibernate, JDO 등 인기있는 객체 관계형 도구(OR도구)를 사용 할 수 있도록 지원합니다.

 

### Spring Web

> Spirng에서 Web context module은 Application module에 내장되어 있고 Web기반의 응용프로그램에 대한 Context를 제공하여 일반적인 Web Application 개발에 필요한 기본적인 기능을 지원한다. 그로인해 Jakarta Structs 와의 통합을 지원

 

### Spring MVC (⭐)

> Spring에서는 MVC에서는 
`Model2` 구조로 Apllication을 만들 수 있도록 지원한다. MVC (Model-View-Controller) 프레임 워크는 
웹 응용 프로그램을 작성하기 위한 완전한 기능을 갖춘 MVC Pattern을 구현한다.
MVC 프레임 워크는 전략 인터페이스를 통해 고급 구성이 가능하며 JSP, Velocity, Tiles, iText 및 POI를 포함한 수많은 뷰 기술을 지원한다.
(다음 학습에 Spring MVC를 중점적으로 다뤄나가보자)

***

## Spring Container
자세히 들여다보자. 

`Spring Container`는 @Configuration이 붙은 클래스를 설정(구성) 정보로 사용할 수 있다.
@Bean이 적힌 메서드를 모두 호출한 뒤, 반환된 객체를 
Spring Container에 등록한다. 이렇게 Spring Container에 등록된 객체를 `Spring Bean`이라고 한다.

Spring Container의 유형에는 `Bean Factory`와 이를 상속한 `ApplicationContext` 2가지 유형이 존재한다.


### Bean Factory Container
>`Bean Factory`는 스프링 설정 파일에 등록된 
`Bean 객체`를 **생성하고 관리**하는 기본적인 기능만을 제공한다.
Container가 구동될 때 Bean 객체를 생성하는 것이 아니라, 클라이언트의 요청에 의해서 Bean 객체가 사용되는 시점(Lazy Loading)에 객체를 생성하는 방식을 사용하고 있다.

Bean Factory는 직접 사용하는 경우는 거의 없고, 
일반적으로 Bean Factory를 상속받은 `ApplicationContext`를 `Spring Container`라고 한다. 

## ApplicationContext
Bean Factory와 마찬가지로, Bean 객체를 생성하고 관리하는 기능을 가지고 있지만 그 뿐만 아니라 `트랜잭션 관리, 메시지 기반의 다국어 처리, AOP 처리`등등 DI(Dependency Injection) 과 IoC(Inverse of Conversion) 외에도 많은 부분을 지원한다.

컨테이너가 구동되는 시점에 객체들을 생성하는 `Pre-Loading` 방식을 따른다.

스프링 컨테이너는 XML 혹은 애너테이션 기반, Java 설정 클래스의 구현체로 만들 수 있는데 다양한 형식의 설정 정보를 받을 수 있도록 유연하게 설계되어 있다.
일반적으로 Spring MVC의 환경을 제작하면 대부분 XmlWebApplicationContext가 자동으로 생성되어 사용한다.

유형은 다음과 같다.

- GenericXmlApplicationnContext 
> 파일 시스템이나 클래스 경로에 있는 XML 파일을 로딩하여 구동하는 컨테이너

- WebApplicationContext 
  - XmlWebApplicationContext
  > XML 설정 파일을 불러와 웹 기반의 스프링 애플리케이션을 개발할 때 사용하는 컨테이너
  - AnnotationConfigWebApplicationContext
> 자바 설정 파일 형태로 불러와 웹 기반의 스프링 애플리케이션을 개발할 때 사용하는 컨테이너
---

**XML 설정 방법과 자바 코드**로 설정하는 형식으로 나뉘지만 특성은 유사하다.
> XML 설정 파일에서는 `<component-scan/>` , 자바 설정 파일에서는 `@ComponentScan`을 이용해 스캔 범위를 지정하여 @Component 어노테이션이 붙은 빈으로 등록 될 준비를 마친 클래스들을 스캔하여 빈으로 등록해준다.

자바 설정 파일에서 클래스의 빈 등록을 위해서는 `@Configuration`과 `@Bean` 애너테이션이 잘 등록되어 있어야한다.
만약 빈으로 등록하기 위한 클래스 정의시 @Configuration이 빠진다면 `Bean Scope`가 singleton으로 생성되지 못 한다.


---

이렇게 스프링이 다양한 컨테이너 설정 형식을 지원할 수 있는 것은 `BeanDefinition`이라는 추상화 덕분이다. 
역할과 구현을 개념적으로 나눴다고 보면 된다.
XML 파일이나 자바 코드를 읽어서 `BeanDefinition`을 만들 수 있는데 그럼 `Spring Container`는 빈에 정의가 자바 코드인지 XML인지 알 필요 없이 오직 `BeanDefinition`만 알면 된다. 이것을 기반으로 빈 정보를 생성할 수 있다. BeanDefinition을 **빈 설정 메타 정보**라고 하는데 @Bean, bean<> 당 각각 하나씩 메타 정보가 생성된다.
<br/>

```java
new BeanDefinition(...)
```


위처럼 직접 생성해서 `Spring Container`에 등록할 수도 있다. 하지만 실무에서는 `BeanDefinition`을 직접 정의하거나 사용할 일은 거의 없으므로 Spring이 다양한 형태의 설정 정보를 `BeanDefinition`으로 __추상화__해서 사용한다는 것 정도만 알면 된다.


