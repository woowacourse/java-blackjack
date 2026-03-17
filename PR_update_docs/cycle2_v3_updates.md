# cycle 2 - PR Review v3 - 수정 사항

---

## ✅ 리팩토링 할 것 목록

- [x] **1. Entry, Stream 등을 반환하거나 파라미터로 사용하기 보다는 이름 - 베팅 쌍을 표현하는 타입으로 사용하기**
  - [x] Entry를 LinkedHashMap으로 바꾸기.
  - [x] 객체의 고유성 보장을 위해서, 이름으로 찾아서 순회하는게 아니고, map을 순서대로 순회하도록 수정.
- [x] **2. GameJudge의 getScore을 쓰지 않고, 메시지를 보내도록**
  - [x] Participant에 isBust랑, hasSameScore, hasHigherScoreThan같은거로 
- [x] **3. Participants에 BlackjackGame에 있는 getResult를 옮기기**
  - [x] Participants에 judgeAll을 두고, gameJudge랑, Dealer를 param으로 넘겨 judge 로직을 여기서 수행 가능할 듯.
  - [x] Application의 72번줄 `blackjackGame.getUsers().forEach(outputView::printUserCards);`
  - [x] Application의 76번줄 `List<User> participants = blackjackGame.getUsers();`
  - [x] Application의 90번줄 `blackjackGame.getUsers().forEach(outputView::printUserFinalHand);` 또한 위임 대상인지 검토할 것.
- [x] **4. blackjackGame GameJudge를 static으로 바꾸거나, 로컬 변수로 변경**
- [x] **5. Participants에서 도메인 방어 중복으로 생성하기**
  ```java
    if (bets == null || bets.isEmpty()) {
      throw new IllegalArgumentException("[ERROR] 참가자는 최소 1명이어야 합니다.");                                                                                                                          
    }                                                                                                                                                                                                           
    if (bets.size() > MAX_PLAYER_COUNT) {
      throw new IllegalArgumentException("[ERROR] 최대 참가 인원은 16명 이하여야 합니다.");                                                                                                                   
    }
- [x] **6. 동일 이름의 사람은 동일 객체로 보도록 수정**
