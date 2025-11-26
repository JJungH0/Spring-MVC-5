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