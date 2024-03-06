# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 기능 목록

### 덱

- [x] 생성 시점에서 카드를 섞는다.
- [x] 카드를 뽑을 수 있다. 

### 플레이어들

- [ ] 승패를 계산해서 반환한다.
    - [ ] 딜러는 모든 플레이어와 결과를 계산한다.
    - [ ] 플레이어는 딜러와의 결과만을 계산한다. 
    - [ ] 딜러가 burst되었을 경우
      - [ ] 21이하 참가자는 모든 1승
      - [x] burst된 참가자는 패로 기록한다.
    - [ ] 딜러가 21 이하
      - [x] 참가자는 숫자를 비교하여 21에 가까운 사람이 승리한다.
      - [x] 딜러와 참가자의 수가 같은 경우, 패로 기록한다.
      - [x] burst된 참가자는 패로 기록한다.

### 딜러
- [x] 참가자가 카드를 뽑는다.
- [x] 딜러의 손패는 한장만 반환한다.
- [x] 딜러는 16점 이하면 카드를 추가 지급한다.

### 플레이어

- [x] 참가자가 카드를 뽑는다.
- [x] 참가자가 뽑을 수 있는 상태인지 확인한다.

### 카드 손패

- [x] 들고 있는 카드의 전체 점수를 반환한다.
- [x] 카드는 점수를 반환한다.
    - [x] Ace는 1 또는 11점으로 계산한다.
    - [x] J,Q,K는 10으로 계산한다.
    - [x] Ace는 21보다 클 경우에는 1로, 그렇지 않으면 11로 계산한다.

### 카드

- [x] 카드의 기호와 숫자를 반환한다.

### 입출력

- [ ] 참가자 이름을 입력 받는다.
    - [ ] 각 참가자는 ,로 구분한다.
- [ ] 딜러의 카드는 한 장만 공개한다.
- [ ] 게임 완료 후 승패를 출력한다.
- [ ] 카드 분배 결과를 출력한다.
- [ ] 카드를 추가 지급 받을지 물어본다.
    - [ ] 응답은 y/n로만 받을 수 있다.

# Git commit 메세지

접두어로 `docs`, `test`, `feat`, `fix`, `refactor`, `chore` 사용
example feat: 사용자 입력 후 도메인 사용
