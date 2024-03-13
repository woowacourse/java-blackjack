
## 기능 요구 사항
(🫸🏻 : 추가된 요구사항으로 우선순위를 뒤로 미룸.)

- 입출력
  - [x] 게임에 참여할 사람의 이름을 입력한다.
    - [x] 이름들은 쉼표(",")로 구분하여 입력해야한다.
    - [x] 입력값은 쉼표로 시작하거나 끝날 수 없다.🫸🏻

  - [] 플레이어의 베팅 금액을 입력한다.
    - [] 숫자만 입력 가능하다.

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

  - [x] 최종 승패를 출력한다.
    - [x] 딜러에 대한 승패를 출력한다.
    - [x] 플레이어에 대한 승패를 출력한다.

    <br>

- 카드 (Card)
  - [x] 문양을 가지고 있다.
    - [x] 문양은 ``하트, 스페이스, 클로버. 다이아몬드``가 있다.
  - [x] 번호를 가지고 있다.
    - [x] ``2 ~ 10``범위의 수와 ``Q, J, K (10), A (1 또는 11)``의 수가 있다.

<br>

- 카드 덱 (Card Deck)
  - [x] 하나의 덱은 52장의 카드로 구성되어 있다.
  - [x] 맨 위의 카드를 뽑을 수 있다.
  - [x] 블랙잭 룰에 따라 6세트의 카드덱을 가진다.
    - [x] 카드의 순서는 섞여있다.
    - [x] 맨 위에서 부터 한 장씩 뽑는다. 
    - [x] 해당 덱의 카드를 모두 뽑았는지 확인한다.

<br>

- 블랙잭 게임
  - [x] 처음에 게이머들에게 카드를 2장씩 준다.
  - [x] 게이머에게 카드를 준다.
    - [x] 게이머가 더 이상 카드를 받을 수 없다면 카드를 주지 않는다.
  - [x] 최종 수익 결과를 알려준다.

<br>

- 딜러
  - [x] 카드를 뽑는다.
  - [x] 카드를 더 받을 수 있는지 판단한다.
  - [x] 카드의 점수를 알 수 있다.
  - [x] "버스트" 인지를 판단한다.

<br>

- 플레이어
  - [x] 플레이어는 2명에서 8명까지만 참가 가능하다.🫸🏻
  - [x] 플레이어 이름은 중복될 수 없다.🫸🏻
  - [x] 플레이어는 "딜러"를 이름으로 사용할 수 없다.🫸🏻
  - [x] 카드를 뽑는다.
  - [x] 카드의 점수를 알 수 있다.
  - [x] 카드를 더 받을 수 있는지 판단한다.
  - [x] "버스트" 인지를 판단한다.

<br>

- 배팅금액
  - [x] 배팅은 최소 1,000원 최대 1,000,000만원 까지 가능하다.
  - [x] 단위는 100단위로만 배팅가능하다.

<br>

- 핸드
  - [x] 카드를 뽑아서 저장한다.
  - [x] 카드 합계를 계산할 수 있다.
  - [x] 카드 합계가 21을 초과하는지 아닌지를 판단한다.

<br>

- 플레이어 이름
  - [x] 이름의 길이는 1이상 5이하이어야 한다.🫸🏻
  - [x] 공백을 이름으로 가질 수 없다.🫸🏻
  - [x] 이름의 앞 뒤 공백은 제거 된다.🫸🏻

<br>

- 게임결과
  - 딜러와 플레이어의 승패를 결정한다.
    - 승패 결과
      - [x] 점수 합계가 21점 이하면서 가장 가까운 사람이 승리한다.
      - "플레이어가 이기는 경우"
        - [x] 플레이어가 딜러보다 점수가 높다면 승리한다.
        - [x] 딜러가 버스트인데, 플레이어가 버스트가 아니면 승리한다.
        - [x] 딜러가 블랙잭아닌데, 플레이어가 블랙잭일 때 승리한다.
      - "무승부하는 경우"
        - [x] 플레이어와 딜러 모두가 블랙잭일 때 무승부.
        - [x] 둘 다 블랙잭이 아니지만 점수가 같을 때 무승부.
      - "플레이어가 지는 경우"
        - [x] 위 경우를 제외한 경우는 플레이어가 패배한다.

<br>

- 배팅 수익
  - [x] 플레이어가 이긴다면 배팅한 금액만큼을 딜러에게 추가로 받는다. 
    `ex:) 플레이어가 1000원을 배팅하고 이겼을 경우 1000원의 추가 수익을 얻는다. (총 금액 : 2000원)`
  - [x] 플레이어가 블랙잭일 경우 배팅 금액의 1.5배를 딜러에게 추가로 받는다.
    `ex:) 플레이어가 1000원을 배팅하고 블랙잭으로 이겼을 경우 1500원의 추가 수익을 얻는다. (총 금액 : 2500원)`
  - [x] 플레이어가 진다면 배팅한 금액을 딜러에게 준다.
    `ex:) 플레이어가 1000원을 배팅하고 졌을 경우 1000원을 잃는다. (총 금액 : 0)`
  - [x] 무승부일 경우 배팅한 금액을 그대로 딜러에게 돌려받는다.
    `ex:) 플레이어가 1000원을 배팅하고 무승부일 경우 배팅금액을 그대로 돌려받는다. (총 금액 : 1000원)`
