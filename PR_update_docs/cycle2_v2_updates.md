# cycle 2 - PR Review v2 - 수정 사항

---

## ✅ 리팩토링 할 것 목록

- [ ] **1. `Application`의 Map 객체 동등성 확인**
- [ ] **2. `BlackjackGame`의 주석 지우기**
- [ ] **3. `blackjackGame`의 protected field를 private으로 수정할 것.**
  - [ ] 이를 위해 blackjackGame의 prepare() 구조를 생성자로 옮겨야 함.
- [ ] **4. `GameJudge`의 judge 함수를 dealer와 user 객체 자체를 받는 형식으로 수정**
- [ ] **5. `Participants`의 Dealer 제거**
  - [ ] dealer는 BlackjackGame에서 직접 관리하도록 수정
- [ ] **6. `GameSummary`의 생성자 수정**
  - [ ] map 두개를 받는 구조로 짜여있는데, 하나만 있어도 User을 순회할 수 있음.
- [ ] **7. `DeckTest`를 exception을 종료 조건으로 설정하지 말고, 직접 사이즈를 테스트하는 형식으로 수정.**
- [ ] **8. `Participant`의 checkBust 함수 간소화**
- [ ] **9. `Participants`의 saverUsers를 bets만 받아 객체 생성하도록 수정.**
- [ ] **10. 뷰가 도메인 객체를 만들고 있는 부분 수정.**
  - [ ] inputView는 이름을 파싱하고 List<String>를 반환하고, Application에서 객체 생성
  - [ ] `Names`라는 일급 컬렉션을 하나 만들어서 객체 생성과 검증 단계를 한번에 진행. 
