# cycle 2 - PR Review v1 - 수정 사항

---

## ✅ 리팩토링 할 것 목록

- [ ] **1. BlackjackGame의 setter 지양하기**
- [ ] **2. 블랙잭을 일반 21보다 더 높은 족보로 두어서, GameJudge에 반영하기**
- [x] **3. GameSummary의 getter 사용과, 원시값 사용 부분 수정**
  - User 자체를 key로 쓰면 됨.
- [x] **4. participants의 judge all을 BlackjackGame으로 옮길 것.**
- [ ] **5. 처음에 이름 view에서 파싱해서 넘겨주고, bet을 다 받은 후에 객체 생성하도록 수정**
  - 이렇게 하려면 Parse를 inputView에서 한다고 했을 때, 이름에 대한 validation이 Name에서 진행되고 있으니, InputView에서 Name을 만들어야 함.
    - 그런데 이렇게 하면 view가 도메인 객체를 알게 되는 구조가 됨.
  - Name을 만들지 않으면, 이름 자체에 대한 검증이 배팅이 끝나고 User 객체가 만들어지는 시점까지 밀리게 됨.
    - 즉, 잘못된 이름이 입력되어도 bet을 받기 전까지 검증이 진행되지 않음.
  - 위 방법들이 다 싫으면 Name class 자체를 없애고, validation을 input view에서 해야 함.
  - 이게 아니면, Name class를 그대로 두고, validation만 input view로 넘기거나.
    - 근데 이렇게 하면 리뷰어님이 말씀하신 대로 Name class를 만든 이유가 없음. 
- [x] **6. Bet test code 작성**
  - [x] 딜러 수익 관련해서 테스트 확장
- [x] **7. 돈 계산 BigDecimal 이용하도록 수정**
  - 부동 소수점이라 금액 계산에서 오차 발생할 수 있음.
- [x] **8. 배율 계산 로직 타입 캐스팅 수정**
