# java-blackjack

## 우리가 이번 페어프로그래밍을 통해 이루고 싶은 것

- **TDD를 좀 엄격하게 적용해보고 싶다.**
  - 호흡을 짧게, 길게 가져가는 그 변화를 느끼고 싶다.
  - 아는 것부터 개발하기.
- 메이에게 시비를 많이 걸어주세요.
  - 근데 돔푸는 좀 지켜보다가, 정 아니면 말함.

## 블랙잭
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
  - 플레이어는 A를 1 또는 11 중 원하는 숫자로 계산할 수 있다.
  - 딜러는 A가 어떻게 계산되는지 고정된다. 기본적으로 A를 11로 계산하지만, 만약 A를 11로 계산했을 때 Bust가 된다면 A를 1로 계산한다.
- 게임을 시작하면 딜러와 플레이어는 두 장의 카드를 지급 받으며, 플레이어는 모든 카드를 공개하고, 딜러는 한 장만 공개한다.
- 플레이어는 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.

## 기능 요구 사항

### 플레이어

- 이름 길이 2~8 글자 사이여야 한다.
- 1명부터 6명까지 생성 가능
  - 쉼표로 구분된다.
  - 입력 때 공백 있으면 자동으로 지운다.
  - 그 외의 인원수는 예외를 발생시킨다.
  - 이름이 똑같은 경우 예외 발생시킨다.

### 카드

- 카드는 스페이드, 하트, 다이어몬드, 클로버의 문양이 있다.
- 카드는 2 ~ 10, A, J, Q, K의 숫자가 있다.

### 덱 (Deck)

- 덱은 카드 모든 종류를 1장씩 갖고 있다. (52장)
- 덱은 위에서 카드를 한장씩 뽑을 수 있다.

### 손패 (CardHand)

- 딜러 혹은 플레이어가 손에 들고 있는 패로, 카드들을 갖는다.
- 카드를 한 장 혹은 여러장 추가할 수 있다.

### 블랙잭 손패 (BlackjackCardHand)

- 초기에 자신의 카드를 공개할 수 있다.
  - 플레이어는 2장, 딜러는 1장 공개한다.
- 자기가 가진 카드 숫자합을 알 수 있다.
- 자기가 가진 카드 숫자합이 버스트인지 알 수 있다.

### 승패 결과 (WinningStatus)

- 두 손패의 정보를 받아, 승리 / 패배 / 무승부를 결정한다.
  - 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기며, 21을 넘으면 Bust로 패배한다.
  - 만약 두 명의 카드의 합이 21로 같다면, 카드 수가 적은 쪽이 이긴다.
    - 카드 2장으로 21을 만든 경우(J/Q/K + A)를 블랙잭이라 하고, 카드 3장 이상으로 21을 만든 경우보다 우세하다.
  - 만약 두 명의 카드의 합이 같지만 21이 아니라면, 무승부이다.

### 게임 결과 (BlackjackJudge)

- 딜러와 플레이어들 사이의 승패 결과를 알 수 있다.
