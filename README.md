
## 기능 요구 사항
(🫸🏻 : 추가된 요구사항으로 우선순위를 뒤로 미룸.)

- 입출력
  - [x] 게임에 참여할 사람의 이름을 입력한다.
    - [x] 이름들은 쉼표(",")로 구분하여 입력해야한다.
    - [x] 입력값은 쉼표로 시작하거나 끝날 수 없다.🫸🏻

  - [ ] 플레이어마다 배팅 금액을 입력한다.
    - [ ] 배팅 금액은 숫자만 입력할 수 있다.

  - [x] 처음 받은 카드를 출력한다.
    - [x] 딜러는 첫 번째로 받은 카드 한장만 출력한다.
    - [x] 플레이어는 받은 모든 카드를 출력한다.

  - [x] 플레이어가 카드를 더 뽑을지 입력한다.
    - [x] 입력은 대소문자 구분없이 y("예") 또는 n("아니오")을 입력받는다.
    - [x] y 또는 n 외의 값은 입력할 수 없다.🫸🏻
    - [x] 카드를 더 뽑았을 경우 합계가 21을 초과한다면 "버스트" 임으로 더 이상 묻지 않고, 버스트라고 출력한다.

  - [x] 플레이어가 카드를 뽑을 때마다 들고있는 카드들을 출력한다.
  
  - [x] 딜러가 추가로 카드를 받을 때 마다 카드를 받았다고 출력한다.

  - [x] 딜러와 플레이어의 전체 카드 목록과 결과를 출력한다.

  - [ ] 최종 수익을 출력한다.
    - [ ] 딜러의 최종 수익을 출력한다.
    - [ ] 플레이어의 최종 수익을 출력한다.

  - [x] ~~최종 승패를 출력한다.~~
    - [x] ~~딜러에 대한 승패를 출력한다.~~
    - [x] ~~플레이어에 대한 승패를 출력한다.~~

    <br>

- 카드 (Card)
  - [x] 문양을 가지고 있다.
    - [x] 문양은 ``하트, 스페이스, 클로버. 다이아몬드``가 있다.
  - [x] 번호를 가지고 있다.
    - [x] ``2 ~ 10``범위의 수와 ``Q, J, K (10), A (1 또는 11)``의 수가 있다.
    - [x] 에이스 카드는 1(하드 핸드) 또는 11(소프트 핸드)로 계산한다.

<br>

- 카드 덱 (Card Deck)
  - [x] 블랙잭 룰에 따라 6세트의 카드를 덱으로 사용한다.
    - [x] 하나의 카드 팩는 52장의 카드로 구성되어 있다.
  - [x] 맨 위의 카드를 뽑을 수 있다.
    - [x] 카드의 순서는 섞여있다.
    - [x] 맨 위에서 부터 한 장씩 뽑는다. 

<br>

- 블랙잭 게임 (BlackJackGame)
  - [x] 처음에 게이머들에게 카드를 2장씩 준다.
  - [x] 게이머에게 카드를 준다.
    - [x] 게이머가 더 이상 카드를 받을 수 없다면 카드를 주지 않는다.

<br>

- 딜러 (Dealer)
  - [x] 카드를 뽑는다.
  - [x] 카드를 더 받을 수 있는지 판단한다.
  - [x] 카드의 점수를 알 수 있다.
  - [x] "버스트" 인지를 판단한다.

<br>

- 플레이어 (Player)
  - [x] 플레이어는 2명에서 8명까지만 참가 가능하다.🫸🏻
  - [x] 플레이어 이름은 중복될 수 없다.🫸🏻
  - [x] 플레이어는 "딜러"를 이름으로 사용할 수 없다.🫸🏻
  - [x] 카드를 뽑는다.
  - [x] 카드의 점수를 알 수 있다.
  - [x] 카드를 더 받을 수 있는지 판단한다.
  - [x] "버스트" 인지를 판단한다.

<br>

- 핸즈 (Hands)
  - [x] 카드를 뽑아서 저장한다.
  - [x] 카드 합계를 계산할 수 있다.
  - [x] 카드 합계가 21을 초과하는지 아닌지를 판단한다.

<br>

- 플레이어 이름(Name)
  - [x] 이름의 길이는 1이상 5이하이어야 한다.🫸🏻
  - [x] 공백을 이름으로 가질 수 없다.🫸🏻
  - [x] 이름의 앞 뒤 공백은 제거 된다.🫸🏻

<br>

- 심판 (Referee)
  - [x] 딜러와 플레이어 사이의 최종 승패를 결정한다. 
    - [x] 점수 합계가 21점 이하면서 가장 가까운 사람이 승리한다.
    - [x] "딜러가 버스트인 경우"
      - [x] 플레이어가 버스트라면 딜러가 이긴다.
      - [x] 플레이어가 버스트가 아니라면 딜러가 진다.
    - [x] "딜러가 블랙잭인 경우"
      - [x] 플레이어가 블랙잭이 아니라면 딜러가 이긴다.
      - [x] 플레이어도 블랙잭이라면 무승부이다.
    - [x] "딜러가 일반 숫자(버스트도 아니고, 블랙잭도 아닌)인 경우"
      - [x] 플레이어가 블랙잭이라면 딜러가 진다.
      - [x] 플레이어가 버스트라면 딜러가 이긴다.
      - [x] 플레이어와 딜러 모두 일반 점수인 경우, 21점에 가까운 사람이 이긴다.

<br>

- 게임 결과 (Result)
  - [ ] 블랙잭으로 승리하면 베팅 금액의 1.5배를 받는다.
    - [ ] 승리하면 배팅한 금액만큼 받는다. (1배)
    - [ ] 패배하면 배팅한 금액만큼 잃는다. (-1배)
    - [ ] 무승부이면 배팅한 금액만큼 돌려 받는다. (수익 = 0)

<br>

- 금액 (Money)
  - [ ] 베팅 금액은 5000원 이상, 50만원 이하이다.🫸🏻

<br>

- 배팅(Betting)
  - [ ] 플레이어에 대한 배팅 금액이 있다.
  - [ ] 플레이어의 결과에 따른 배팅 수익을 계산할 수 있다.
  - [ ] 딜러는 게임에서 플레이어에게 얻고 잃은 금액으로 수익을 계산한다.

