# Practical_DDD
Practical DDD in Enterprise 를 보면서 스스로 정리하는 레포 입니다!

시작 날짜 2022.06.01 (투표하고왔습니다ㅎ)



## 기본적인 프로젝트 구조 파악하기

> DDD는 MSA(Micro Service Architecture)에 알맞은 개발방법론으로,
>
> 각각의 Domain을 기준으로 디렉토리를 나눕니다. 

​	Practical DDD 에서는 `CargoTracker` 를 만드는 서비스로 예제가 구성되어있습니다.



Cargotracker는 크게 4가지 도메인으로 나눌 수 있습니다.

- Cargotracker
  - Booking(예약)
  - Handling(집하처리, 상품 도착 등)
  - routing(경로 설정)
  - tracking(현재위치 추적)

​	위 4가지를 기준으로,  다음 사진처럼 프로젝트가 구성됩니다.

---

![image-20220604134318657](README.assets/image-20220604134318657.png)

​	확인해보시면, 기존에 우리가 알던 Spring MVC와는 꽤나 다른 모습을 볼 수 있습니다.

​	각각의 프로젝트 안의 구조를 살펴보면 이 것 또한 4가지로 구성되어있습니다.

- Application
- Domain
- InfraStructure
- Interfaces

---

하나씩 살펴보겠습니다. 

### 1. Interface ( 인터페이스 )

인터페이스는 주로 두 가지의 역할을 합니다.

> - 외부로부터 들어오는 프로토콜의 종류를 협의합니다 e.g) REST, Web, Socket, FTP ..
>
> - View를 제공하는 adaptor 역할을 합니다. (Browser View, Mobile View ..)

보시다 보면, Spring MVC에서 Controller와 비슷한 역할을 수행한다고 생각이 됩니다!




### 2. Application ( 어플리케이션 )

어플리케이션은 꽤나 많은 역할을 수행합니다. 

> - 앞의 인터페이스의 Input 역할을 하고, Repository에서 돌려주는 결과값의 Output 역할을 합니다.
> - Commands(명령), Queries(조회), Events(다른 도메인 이벤트), [Saga](https://microservices.io/patterns/data/saga.html) (패턴)
> - Transaction의 시작, 조정, 종료
> - Dto(Data Transfer Object) 역할
> - 다른 BC(Bounded Context) 호출

<img src="README.assets/image-20220604153543106.png" alt="image-20220604153543106" style="zoom:50%;" />



### 3. Domain ( 도메인 )

이 디렉토리는 Bounded Context의 Domain 모델들을 가지고 있습니다. 다음 목록들이 **Bounded Context를 구성하는 주요 Classes** 입니다.

> - Aggregates
> - Entities
> - Value Objects
> - Command, Query(Domain Rules)



### 4. Infrastructure 

이 디렉토리는 여러 데이터베이스 (RDB, NOSQL, EVENT, ... ) 에서 데이터를 가져오는 역할을 합니다. 우리가 앞으로 자주 사용할 JPA 도 이 곳에 설정됩니다.  
