# ♠️블랙잭 게임 기능 명세서♠️

### 카드 (Card)

- 카드는 모양을 갖는다.
- 카드는 문자를 갖는다.
- 카드는 문자에 따라 숫자로 계산된다.

### 문양 (Suit)

- 문양은 하트, 스페이드, 다이아몬드, 클로버가 있다.

### 문자 (Rank)

- 문자는 숫자 또는 영어 글자이다.
- 숫자는 2부터 10까지의 정수이다.
- 영어 글자는 Ace(A), King(K), Queen(Q), Jack(J) 가 있다.
- 문자는 숫자로 계산된다.
    - 숫자가 적힌 카드는 그 숫자로 계산한다.
    - K, Q, J은 각각 10으로 계산한다.
    - A는 1 또는 11로 계산할 수 있다.

### 카드덱 (CardDeck)

- 카드덱은 카드로 구성된다.
- 카드덱의 카드는 4가지 문양과 13개의 문자가 조합된 52장의 카드로 구성된다.
- 카드덱에서 한장을 뽑을 수 있다.

### 플레이어 (Player)

- 플레이어는 손패를 갖는다.
    - 플레이어는 최초의 손패를 받는다.
    - 플레이어는 카드를 손패에 추가할 수 있다.
- 플레이어는 이름을 갖는다.
    - 플레이어의 이름은 딜러의 이름과 같을 수 없다.
- 플레이어는 손패의 합을 계산한다.
    - Ace는 버스트 되지 않는 선에서 11로 계산한다.
- 플레이어는 더 받을 수 있는지 없는지를 안다.
    - 손패의 합이 21 미만이면 더 받을 수 있다.
- 플레이어는 버스트인지를 안다.
    - 손패의 합이 21 초과이면 버스트이다.
- 플레이어는 베팅을 한다.

### 딜러 (Dealer)

- 딜러는 카드덱을 갖는다.
    - 딜러는 카드덱을 섞는다.
    - 딜러는 카드덱으로부터 카드를 꺼낼 수 있다.
- 딜러는 이름을 갖는다.
- 딜러는 손패를 갖는다.
- 딜러는 손패의 합을 계산한다.
    - Ace는 버스트 되지 않는 선에서 11로 계산한다.
- 딜러는 더 받을 수 있는지 없는지를 안다.
    - 손패의 합이 17 미만이면 더 받을 수 있다.
- 딜러는 버스트인지를 안다.
    - 손패의 합이 21 초과이면 버스트이다.

### 게임 (BlackJackGame)

- 참가자들의 손패를 초기화한다.
    - 딜러가 카드를 2장씩을 뽑는다.
    - 카드 2장을 참가자들의 손패로 준다.
- 플레이어가 히트하게 한다.
- 딜러가 히트하게 한다.
- 게임의 결과를 반환한다.

### 승패 (WinLoss)

- 딜러 승, 플레이어 승, 무승부가 있다.
- 승패는 딜러와 플레이어 한 명을 비교하여 결정한다.
    - 둘 다 버스트되면, 딜러가 이긴다.
    - 둘 중 하나만 버스트되면, 상대가 이긴다.
    - 둘 다 버스트되지 않으면, 21에 더 가까운 사람이 이긴다.
    - 둘 다 버스트되지 않으면서 점수가 동일하면, 비긴다.
    - 둘 다 블랙잭이면, 비긴다.

### 배팅 (BettingPot)

- 딜러가 승리할 경우, 플레이어의 배팅 금액은 딜러에게 넘어간다.
- 플레이어가 승리할 경우, 배팅한 금액을 다시 받고, 그와 동일한 금액을 딜러로부터 추가로 받는다.
- 플레이어가 블랙잭으로 승리할 경우, 배팅 금액을 돌려받고, 배팅 금액의 1.5배를 딜러로부터 추가로 받는다.
- 비길 경우, 플레이어는 자신이 배팅한 금액을 돌려받는다.

### 입력 (InputView)

- 플레이어의 이름 입력 받는다.
    - 이름은 ,로 구분한다.
    - 이름은 1~5글자 이다.
    - 이름은 "딜러"가 될 수 없다.
    - 플레이어의 수는 최대 10명까지 허용한다.
- 플레이어의 배팅 금액을 입력받는다.
    - 배팅 금액은 숫자만 허용한다.
    - 0이나 음수는 허용하지 않는다.
- 플레이어의 hit, stand 여부를 입력 받는다.
    - 입력은 y와 n만 허용한다.

### 출력 (OutputView)

- 최초 손패를 분배했다는 메세지를 출력한다.
- 최초 손패 분배 결과를 출력한다.
    - 이때, 딜러는 한 장만 보여준다.
- 플레이어가 hit하여, 손 패가 변경되면 손패를 출력한다.
- 플레이어가 hit 하지 않았더라도, 최초의 stand에 대해서는 손패를 출력한다.
- 딜러는 hit을 할 때 마다, 한 장을 받았는 메세지를 출력한다.
- 모든 참가자의 순서가 끝나면, 최종 손패와 결과를 출력한다.
- 모든 참가자의 최종 수익을 출력한다.
