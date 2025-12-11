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
>- \<input type="checkbox" id="open" th:field="*{open}" class="form-check-input"/>
>  - 생성 결과 :
>    - \<input type="checkbox" id="open" class="form-check-input" name="open" value="true">
>    - \<input type="hidden" name="_open" value="on"/>
>      - 타임리프를 사용하면 체크 박스의 히든 필드와 관련된 부분도 함께 해결해줌 HTML 생성 결과를 보면 히든 필드 부분이 자동으로 완성되어 있음
>- 타임리프의 체크 확인
>  - checked="checked"
>    - 체크 박스에서 판매 여부를 선택해서 저장하면, 조회시에 checked 속성이 추가된 것을 확인할 수 있음, 이러한 부분은 개발자가 직접 처리하려면 상당히 if문를 써야하므로 번거러움, 타임리프의 th:field를 사용하면 값이 true인 경우 체크를 자동으로 처리해줌
>- **@ModelAttribute의 특별한 사용법**
>  - 등록 폼, 상세화면, 수정 폼에서 모두 서울, 부산, 제주라는 체크 박스를 반복해서 보여주어야 함
>  - 이렇게 하려면 각각의 컨트롤러에서 model.addAttribute(...)을 사용해서 체크 박스를 구성하는 데이터를 반복해서 넣어주어야 하는데
>  - @ModelAttribute는 이렇게 컨트롤러에 있는 별도의 메서드에 적용 가능
>    - 해당 방식을 사용하면 해당 컨트롤러를 요청할 때 regions에서 반환한 값이 자동으로 모델 (= model)에 담기게 됨
>  - th:for="${#ids.prev('regions')}"