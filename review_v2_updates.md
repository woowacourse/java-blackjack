# PR Review v2 - 수정 사항

---

## ✅ 리팩토링 할 것 목록

- [x] **1. 도메인 특화 단어 사용**
  - WINNING_SCORE_BOUNDARY -> BLACKJACK_SCORE
- [x] **2. 승패판정 책임 분리 -> 새로운 객체 도입**
  - 객체지향은 현실을 복사하는 게 아니라, 현실의 은유를 빌려서 역할과 책임을 분리하는 것이다.
  - `Participants`와 `Dealer` 수정할 것.
  - [x] `GameResult`에 `PUSH`(동점), `BUST`(21 초과) 추가
  - [x] `GameResult`에 `opposite()` 메서드 추가
    - `WIN → LOSE`, `LOSE → WIN`, `PUSH → PUSH`, `BUST → WIN`
  - [x] `GameJudge` 클래스 생성: `judge(int dealerScore, int userScore) → GameResult`
    - 유저 > 21 → `BUST`
    - 유저 == 딜러 → `PUSH`
    - 기존 WIN/LOSE 분기 유지
  - [x] `Dealer`에서 `judgeUserWin()`, `judgeDealerResult()` 제거
  - [x] `Participants.calculateUserResults()`: `gameJudge.judge(dealer.getScore(), user.getScore())` 사용
  - [x] `Participants.calculateDealerResults()`: `gameJudge.judge(...).opposite()`로 집계 (`initEnumMap()` 수정 불필요)
  - [x] `OutputView.printWinningResults()`: PUSH, BUST 출력 처리 추가
- [x] **3. `Name` 원시값 포장 검증 테스트 위치 확인**
  - 지금 다른 곳에서 validation 하고 있는 것을 위치 수정할 것.
- [x] **4. 의미없는 주석 제거**
  - `BlackjackGameTest`의 68번줄 
- [x] **5. 도메인의 책임은 데이터를 관리하고 반환하는 것.**
  - 뷰를 위한 로직은 오직 뷰에서만 처리하도록 수정.
  - domain 패키지 내의 객체들이 view를 import 하는 것은 의존성 역전임.
  - [x] `User`: `getFormatedAskGetExtraCard()` 제거, `view.Message` import 제거
  - [x] `BlackjackGame`: `make*Display()` 메서드 전부 제거, `view.Message` import 제거
  - [x] `Card`: `getDisplayName()` 제거 → `getRankName()`, `getSuitName()` 으로 분리
  - [x] `Hand`: `getCardsDisplay()`, `getFinalDisplay()`, `getFirstCardDisplay()` 제거 → `getCards()` (List<Card>) 반환
  - [x] `Participant`: `getCardsDisplay()`, `getFinalDisplay()` 제거 → `getCards()`, `getScore()`만 유지
  - [x] `Participants`: 포맷팅 메서드 전부 제거 (`makeOneUserCard`, `formatDealerDisplay`, `formatUserDisplays`, `addScoreToUserHand`, `getUserCardsDisplays` 등) → `getUsers()`, `getDealer()` 같은 순수 데이터 반환 메서드로 교체
  - [x] `OutputView`: 위에서 제거한 모든 포맷팅 로직을 여기서 담당
- [x] **6. `suit`의 변수명에 class 이름이 들어가는 것 수정.**
- [ ] **7. 게임의 흐름은 도메인에서, 입출력이 필요한 경우만 Application으로**
  - ex) readParticipants나 shuffleCards의 경우, read는 inputView에서 처리하고 shuffle은 Game 또는 deck 자체가 진행.
- [x] **8. 입력에 trim() 설정하도록 변경**
- [ ] **9. 모든 파일 포멧팅 최적화**
  - `cmd + opt + L`
- [x] **10. validator 없애고, inputView에서 객체가 없는 입력값 확인하도록 변경**
