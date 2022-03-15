# 인터넷과 HTTP 

웹 개발 로드맵에 따라 먼저, 웹의 가장 큰 구조를 이루는 인터넷과 HTTP의 정의 및 통신 방식에 대해 알아보고자 합니다.

## 인터넷
>컴퓨터와 컴퓨터간의 연결을 **인터넷**이라 부를 수 있습니다.
>연결 이후 사용자들은 소통과 통신을 주 목적으로 발전되었다고 볼 수 있습니다.

## HTTP
>웹 서버와 클라이언트 간에 문서 / 데이터를 교환하기 위한 통신 규약(프로토콜)을 의미합니다. 즉, HTML 문서와 같은 자원을 처리하는 프로토콜로 웹 서버와 클라이언트 간 데이터 교환의 기초가 됩니다.
### 1. 통신

http 통신 방식이란 쉽게 말해 '약속'이라 생각할 수 있습니다. 통신 방법은 4가지가 있는데
**GET, POST, PUT, DELETE**로 나눌 수 있습니다.

**Get**은 클라이언트에서 서버로부터 데이터를 요청(Request)하는 작업입니다. [Select]

**Post**는 데이터를 추가하는 의미입니다. 데이터의 가짓수는 10개가 될 수도, 1개가 될 수도 있으며 클라이언트에서 서버로 Post를 Request하면 서버에서 데이터를 [Insert]합니다.

**Put**은 데이터를 수정하는 의미입니다. [Update]

**Delete**는 데이터를 삭제하는 의미입니다. [Delete]

***

### 2. Stateless, Stateful
클라이언트의 상태 정보를 서버가 저장하는 지/안 하는지의 개념으로 구분할 수 있습니다.

**Stateless** : HTTP,UDP 통신 방식에서 사용됩니다.
>Stateless 구조는 서버의 응답이 클라이언트와의 세션 state와 독립적입니다. 그렇기 때문에 세션 관리에 관한 부분은 **클라이언트**에 책임이 있습니다.
client와의 세션 정보를 기억할 필요가 없으므로, 이러한 정보를 서버에 저장하지 않습니다. 대신 필요에 따라 외부 DB에 저장하여 관리 할 수 있습니다.


**Stateful** - 채팅
> Server와 Client간 세션의 'State(상태)'에 기반하여 Client에 response를 보냅니다. 이를 위해 세션 '상태'를 포함한 Client와의 세션 정보를 server에 저장하게 됩니다. 

***

http Header와 http Body

**Header** : 확장자 및 MIME 타입 형식이 담겨있거나, 순서와 주소 등의 정보가 담겨있다.

**Body** : 실질적 데이터 Value

HTTP 통신 방식은 두 가지가 있습니다.

패킷 스위칭 : 클라이언트마다 데이터를 write 할 때 패킷 단위로 서버에 넘긴다. 여기서 각 데이터들은 헤더에 각 클라언트의 정보, MIME 타입이 기재된다.

서킷 스위칭 : 클라이언트마다 서버에 각각 연결되어 데이터를 Write한다. 속도는 빠르지만 유지보수비용이 높다.

#### 여기서 MIME Type이란?
>각 HTTP 헤더에 명시해야할 데이터들의 형식 체계를 뜻한다.

***

### Postman을 활용한 Http 통신/요청 방식 확인하기

@GetMapping ,@PostMapping, @PustMapping, @DeleteMapping

가상 DB로 Member 클래스 선언 후 예제 아이디, 이메일, 사용자명을 선언한다.

Postman에서 Get 요청 :

쿼리 스트링 방식으로 요청하면 서버는 요청된 값을 확인할 수 있다.

>get메소드를 통하여 어떤 값을 요청했는지

Post 요청: 쿼리 스트링으로 데이터를 요청하는 것이 아니라 "Body"라는 곳에 담아 요청해야한다. 어노테이션 @RequestBody를 사용한다.
@RequestBody는 추가된 데이터를 확인 할 수 있다. Put 요청도 마찬가지이다.

```java
package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답 @Controller /  데이터를 응답하는 컨트롤 @RestController

@RestController 
public class HttpControllerTest {
	
	//select 요청, 인터넷 브라우저 요청은 Get요청으로만 할 수 있음을 기억하자.
	@GetMapping("/http/get")
	
	public String getTest(Member m) {
		return "get 요청 = "+ m.getId()+","+ m.getEmail()+","+m.getUsername();
	}

	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청"+ m.getId()+","+ m.getEmail()+","+m.getUsername();
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청"+ m.getId()+","+ m.getEmail()+","+m.getUsername();
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}

```
