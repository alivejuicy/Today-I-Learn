# Lombok

Spring 내에서 VO 객체 생성과 프로젝트의 크기 규모와 비례하여 생각해보면 변수 , 메소드들이 기하급수적으로 늘어나게 되고 관리가 쉽지 않고, 유지보수가 힘들어집니다.
여기서 Lombok 라이브러리를 사용하면 위 문제를 해결 할 수 있게 도와주는 역할을 합니다. 

**어떻게?** => *자동으로 처리*

## `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor` 알아보기

`@Builder` 패턴은 생성자의 매개변수가 많을 때를 고려하면 유용하게 사용할 수 있습니다.
여기서 Builder 패턴이 어떻게 구현되어 있는지 살펴보면 `전체 생성자`가 필요하다는 것을 알 수 있습니다.

그렇기 때문에 `@AllArgsConstructor` 없이 `@Builder`, `@NoArgsConstructor`만 사용하여 빌드하면 에러가 발생합니다.
`전체 생성자`가 필요한 이유는 필요한 매개변수를 조립하여 객체를 생성하거나 모든 매개변수를 사용할 수도 있는 경우 두 가지를 고려해야 하기 때문입니다.

`@NoArgsConstrator` 어노테이션이나 다른 생성자가 존재하지 않을 때는 `전체 생성자`를 자동으로 만들어줍니다.
기본 생성자 또는 다른 생성자가 존재한다면 직접 `전체 생성자`를 만들어주어야 함을 기억해야 합니다. (`@AllArgsConstructor`)

## Lombok 어노테이션

자주 쓰이는 어노테이션 5가지
```
@ToString
toString() 메소드를 생성한다. @ToString(exclude={“제외값”})으로 제외시키고 싶은 값을 설정할 수 있다.
@Getter , @Setter //DTO
getter() setter() 메소드를 생성한다.
@EqualsAndHashCode
equals(), hashCode() 메소드를 생성한다.
@RequiredArgsConstructor
모든 멤버 변수를 초기화시키는 생성자를 생성한다.
@Data
위에 언급한 5가지 어노테이션 설정을 모두 포함한다.
```


### 참조:
https://github.com/wjdrbs96/Today-I-Learn/blob/master/Spring/Lombok/Builder%2C%20NoArgsConstructor%2C%20AllArgsConstructor%20%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0.md
