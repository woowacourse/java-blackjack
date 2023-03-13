# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

---
## 기능목록
참여자 목록을 받는다

딜러와 참여자들이 카드를 갖는다 (두장)

딜러는 한장 참여자들은 두장 카드를 보여준다

참여자들이 카드를 더 받을지 정하도록 한다 (y/n) 입력받기
받은 카드를 보여준다

플레이어들 모두가 n을 입력할때까지 반복한다

딜러카드의 합이 16이면(일단 에이스는 11인걸로..) 카드를 추가로 받고, 17 이상이면 추가로 받지 않는다.

딜러와 참가자들의 카드의 합을 계산하여 보여준다 (에이스를 판단해야한다...)

최종승패를 보여준다

--- 
## 도메인
- [x] 문양
- [x] 카드값

- [x] 카드
  - [x] 문양을 가진다.
  - [x] 값을 가진다.
  - [x] 자신의 문양을 반환한다.
  - [x] 자신의 값을 반환한다.

- [x] 덱
  - [x] 중복되지 않는 카드들을 가진다.
  - [x] 카드를 무작위로 섞는다.
  - [x] 어느 카드까지 뽑았는지 저장한다.
  - [x] 현재 인덱스가 가르키는 카드를 뽑아서 반환한다.
  - [x] 덱의 모든 카드가 소진되었을 때 덱을 다시 섞는다.

- 플레이어
  - [x] 다른 핸드와 비교하여 결과를 반환한다.

- [x] 핸드
  - [x] 핸드는 생성될 때 2장의 카드들을 갖는다.
  - [x] 받은 카드들의 점수를 반환한다.
  - [x] 핸드에 카드를 추가할 수 있다.
  - [x] 다른 핸드와 비교하여 결과를 반환한다.

- [x] 딜러 (플레이어 상속)
  - [x] 카드의 합이 16이하 카드를 추가로 받을 수 있고, 17 이상이면 추가로 받을 수 없다.
  
- [x] 게스트 (플레이어 상속)
  - [x] 이름을 갖는다
    - [x] 앞뒤 공백은 제거한다
    - [x] 공백을 제거한 이름은 1글자 이상, 15글자 이하여야 한다.
  - [x] 베팅 금액을 가진다.

- [x] 게스트들 
  - [x] 참여자의 목록을 가진다
  - [x] 참여자들은 1명이상 10명 이하다
  - [x] 참여자들의 이름은 중복될 수 없다.
  
- [x] 게임 결과
  - [x] 게스트가 블랙잭일 경우 딜러가 블랙잭이 아니면 승리한다.
  - [x] 게스트가 블랙잭이고 딜러도 블랙잭이면 무승부이다.
  - [x] 게스트의 점수가 21을 초과할 경우 게스트가 패배한다.
  - [x] 딜러의 점수가 21을 초과할 경우 게스트의 점수가 21 이하일 경우 게스트가 승리한다.
  - [x] 두 플레이어의 점수가 21을 초과하지 않을 경우 점수가 높은 사람이 승리하고, 같을 경우 무승부

- [x] 베팅머니
  - [x] 게임 결과에 따른 수익을 계산한다.
    - [x] 승리하면 베팅 금액만큼 수익을 얻는다.
    - [x] 패배하면 베팅 금액만큼 수익을 잃는다.
    - [x] 무승부는 수익이 없다.
    - [x] 게스트가 블랙잭으로 이길 경우 베팅 금액의 1.5배만큼 수익을 얻는다.
  - [x] 베팅머니의 최소금액은 1000이다.
