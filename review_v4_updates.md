# PR Review v4 - 수정 사항

---

## ✅ 리팩토링 할 것 목록

- [x] **1. `GameSummary`의 `getDealerLoseCount`를 GameResult에서**
  - 로직이 수정됨에 따라 해당 코드가 삭제
- [x] **2. `Application`에서 do while문**
    ```
    private void askCardToPlayer(User user) {
       while (wantExtraCard(user)) {
          dealMoreCard(user);
       } 
    }
    ```
  - 이 경우, wantExtraCard를 application에 코드를 두고, outputView의 출력 기능 또한 그곳에 두면 구현이 가능할 듯 하다.
- [x] **3. bust가 아닌 이상 무한정 hit가 가능하게 수정**
