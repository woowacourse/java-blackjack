## 1. 기능 명세서

- [x] 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- [x] 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
- [x] 블랙잭 판단 기준은 두장의 카드의 합이 21이면 블랙잭이다.
- [x] 플레이어와 딜러 둘다 블랙잭일시 무승부이다.
- [x] 블랙잭으로 승리시 150%의 수익을 얻는다
- [x] 그냥 승리시 100% 의 수익을 얻는다.
- [x] 무승부시 수익은 없다.
- [x] 패배 혹은 버스트일때 배팅금액을 잃는다.
- [x] 게임을 완료한 후 각 플레이어별로 수익을 출력한다.

## Player

- [x] 게임을 시작하면 플레이어는 두 장의 카드를 지급 받는다.
- [x] 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
- [x] 참가 인원은 2명에서 8명이어야 한다.
- [x] 플레이어의 이름은 10글자 제한이 있고 공백은 허용한다.
- [x] 빈 이름이거나 중복된 이름은 허용하지 않는다.
- [x] 플레이어의 카드가 21을 넘을 경우 점수는 0이 된다.

## Dealer

- [x] 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.

## Money

- [x] 각 플레이어의 배팅 금액을 가진다.
- [x] 최소 배팅금액은 0원, 최대 금액은 1억원이다.

## CardDeck

- [x] 52장의 카드 덱을 6덱 가지고 있다.
- [x] 플레이어와 딜러에게 카드를 뽑아 준다.

## Card

- [x] Ace 와 2부터 10까지의 숫자가 있으며 Heart, Spade, Clover, Dia 그리고 King, Queen, Jack이 존재한다.

## Referee

- [x] 플레이어의 카드와 딜러의 카드를 받아 누가 이겼는지 계산한다.

## 입력

- [x] 플레이어의 이름을 입력받는다.
- [x] 각 플레이어마다 배팅금액을 확인한다.
- [x] 플레이어가 한장의 카드를 더 받을 지의 여부를 입력 받는다.
    - [x] 이외의 값을 입력 받을 경우 예외를 처리한다.

## 출력

- [x] 받은 패를 딜러와 플레이어의 이름과 함께 보여준다.
- [x] 턴이 끝난 후 플레이어가 카드를 받았을 경우 상태를 공개한다.
- [x] 딜러가 카드를 더 받는 경우 안내문구를 출력한다.
- [x] 게임이 끝난 후 받은 카드를 보여주고, 각 점수 결과를 보여준다.
- [x] 각 플레이어를 기준으로 수익금을 출력한다.
