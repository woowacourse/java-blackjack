# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 기능 목록
- 참가자의 이름을 입력 받는다.
  - 쉼표를 기준으로 분리한다.
  - 이름은 공백일 수 없다. 
  - 이름에는 특수문자가 들어갈 수 없다.러
  - 참가자의 이름은 `딜러`일 수 없다.
  - 참가자는 1명 이상 8명이하여야한다.


- 각 참가자의 베팅 금액을 입력받는다.
  - 베팅 금액은 양의 정수여야한다.
  - 최소 베팅 금액은 1000원이다.
  - 베팅 금액은 1000원으로 나누어 떨어져야한다.


- 딜러와 각 참가자들에게 카드를 2장씩 나눠준다.
- 각 참가자들에게 카드를 1장 더 받을 지 물어본다. 
  - 플레이어의 점수가 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
  - y : 카드를 1장 준다. 다시 또 물어본다.
  - n : 카드를 안 준다. 끝낸다.
  - 참가자의 점수가 21 초과일 경우 바로 버스트 당하게 된다.
  

- 딜러의 점수가 16이상이 될 때까지 카드를 준다.
- 딜러와 각 참가자의 점수를 계산한다.
  - 카드의 숫자 계산은 카드 숫자를 기본으로 한다.
    - Ace는 1 또는 11로 계산할 수 있다.
    - King, Queen, Jack은 각각 10으로 계산한다.


- 딜러와 각 참가자의 수익을 가린다.
  - 플레이어의 점수가 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
  - 참가자
    - 블랙잭 승 : 처음 카드 두 장의 합이 21인 경우, 베팅 금액의 1.5배를 받는다.
    - 승 : 21을 초과하지 않으면서 딜러보다 점수가 높을 경우, 딜러가 버스트 당할 경우, 베팅 금액을 받는다.
    - 무승부 : 21을 초과하지 않으면서 딜러와 점수가 같을 경우, 딜러와 참가자가 모두 블랙잭인 경우, 베팅 금액을 돌려받는다.
    - 패 : 버스트 당할 경우, 21을 초과하지 않으면서 딜러보다 점수가 낮을 경우, 딜러가 블랙잭이고 참가자가 블랙잭이 아닐 경우, 베팅 금액을 잃는다.
    
## 블랙잭 카드
- 카드는 52장으로 다음과 같다.
  ![스크린샷 2022-03-08 오후 2 30 43](https://user-images.githubusercontent.com/45311765/157176620-4eb06947-3a8e-4969-9f56-d162f18f25c4.png)
