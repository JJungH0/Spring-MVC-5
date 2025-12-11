# 🌿 Spring MVC

> 출처: 본 문서는 김영한 강사님의 Spring-MVC 강의를 기반으로 하며,  개인적인 이해 및 해석을 더해 정리한 자료입니다

### **스프링 통합으로 추가되는 기능들**
>- 스프링의 SpringEL 문법 통함
>- ${@myBean.doSomething()} 처럼 스프링 빈 호출 지원
>- 편리한 폼 관리를 위한 추가 속성
>  - th: object(= 기능 강화, 폼 커맨드 객체 선택)
>  - th:field, th:errors, th:errorclass
>- 폼 컴포넌트 기능
>  - checkbox, radio button, List 등을 편리하게 사용할 수 있는 기능 지원
>- 스프링 메시지, 국제화 기능의 편리한 통합
>- 스프링의 검증, 오류 처리 통합
>- 스프링의 변환 서비스 통합 (= ConversionService)


### 입력 폼 처리
>- th:object
>  - 커맨드 객체를 지정
>  - th:object="${item}"
>    - \<from>에서 사용할 객체를 지정, 선택 변수 식 (*{...})을 적용할 수 있음
>- *{...}
>  - 선택 변수 식이라고 함, th:object에서 선택한 객체에 접근
>- th:field
>  - HTML 태그의 id, name, value 속성을 자동으로 처리
>  - th:field="*{itemName}" (= 선택 변수 식 사용)
>    - &{item.itemName}과 결과값이 같음, 앞서 th:object로 item을 선언했기 떄문에 가능
>- 랜더링 전
>  - \<input type="text" th:field="*{itemName}" />
>- 랜더링 후
>  - \<input type="text" id="itemName" name="itemName" th:value="*{itemName}" />

### 체크 박스
>- 기존 방식
>  - HTML checkbox는 선택이 안되면 클라이언트에서 서버로 값 자체를 보내지 않음
>  - 수정의 경우에는 상황에 따라서 해당 방식이 문제점이 될 수 있음
>  - 사용자가 의도적으로 체크되어 있던 값을 체크를 해제해도 저장시 아무런 값도 넘어가지 않는 상황이 발생하면 서버 구현에 따라서 값이 오지않은 것으로 판단하여 값을 변경하지 않음
>  - 해당 방식을 해결하기 위해 스프링 MVC는 히든 필드를 하나 만들어서 전송함
>- 히든 필드 방식
>  - \<input type="hidden" name="_open" value="on"/>
>  - 체크 박스 체크
>    - open=on&_open=on (= 스프링 MVC가 open에 값이 있는 것을 확인하고 true로 변환, 이때 _open은 무시)
>  - 체크 박스 미체크
>    - _open=on (= 스프링 MVC가 _open만 있는 것을 확인하고, open의 값이 체크되지 않았다고 인식)
>    - 해당 경우는 서버에서 Boolean 타입을 찍어보면 null이 아니라 false로 값이 넘어온걸로 확인 할 수있음

