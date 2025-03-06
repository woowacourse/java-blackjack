# java-blackjack

## 기능목록

### 이름 입력

- [x] 이름들을 입력받는다.
- 예외
    - [x] 중복된 이름인 경우

### 카드 2장씩 분배 후 공개

- [X] 참가자별로 모두 2장씩 분배한다.
- [X] 딜러는 첫 번째 카드만 공개한다.
- [X] 플레이어는 두 장 모두 공개한다.

### 참가자 카드 추가 분배

- [x] 플레이어의 카드 총합이 21미만인 경우, 한 장 더 받을 지 물어본다.
    - [x] y 의 경우, 한 장 더 분배한다.
        - [x] 지금까지 받은 카드를 공개한다.
    - [x] n 의 경우, 턴을 종료한다.
- [x] 딜러는 카드의 합을 계산한다.
    - [x] 16 이하의 경우, 한 장 더 분배한다.
    - [x] 17 이상의 경우, 턴을 종료한다.

### 참가자 카드 공개

- [ ] 참가자 모두의 카드를 공개한다.
- [ ] 참가자가 지닌 카드의 합을 계산하여 공개한다.

### 승패 결과 출력

- [ ] 딜러와 각 플레이어 사이의 승패를 계산한다.
- [ ] 딜러는 `0승 0패` 형식으로 결과를 출력한다.
- [ ] 플레이어는 `승` or `패` 형식으로 결과를 출력한다.