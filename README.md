## 블랙잭 요구사항
- 유저
  - [x] 유저는 이름을 가질 수 있다.
  - [x] [ERROR] 이름이 null이 들어오는 경우 예외가 발생한다.
  - [x] [ERROR] 이름이 공백이 들어오는 경우 예외가 발생한다.
  - [x] 유저는 배팅금액을 가질 수 있다.
  - [x] [ERROR] 배팅금액이 0이하가 들어오는 경우 예외가 발생한다.

- 카드
  - 카드 패턴
    - [x] 카드 패턴은 총 4개이다. (스페이드, 클로버, 다이아, 하트)
  - 카드 숫자
    - [x] 카드 숫자는 총 13개이다.
    - [x] A는 상황에 따라 11로 계산될 수 있다.
    - [x] 현재 상황에서 가장 좋은 수의 합을 계산할 수 있다.
    - [x] 현재 상황에서 가장 큰 수의 합을 계산할 수 있다.
  - 카드셋
    - [x] 카드합이 21이 초과하면 버스트다.
    - [x] 카드합이 21이고 카드가 두장이면 블랙잭이다

- 카드 덱
  - [x] 카드 덱은 총 52개로 구성되어있다.
  - [x] [ERROR] 카드 덱이 52개가 들어오지 않는 경우 예외가 발생한다.
  - [x] 카드를 한장 반환할 수 있다.
  - [x] [ERROR] 카드가 없는데 카드를 반환하려고하는 경우 예외가 발생한다.

- 게임
  - [ ] 유저는 처음 2장의 카드를 나눠받는다
  - [x] 유저는 hit으로 카드를 받을 수 있다.
  - [ ] 유저는 카드 받기를 중단할 수 있다.
  - [ ] [ERROR] 유저가 이미 종료된 상태인데 hit을 하려는 경우 예외가 발생한다.
  - [ ] 카드를 받았는데 Bust가 된다면 (21초과) 유저의 상태는 Bust로 변경된다.

- 게임 결과
  - 진행 중
    - [x] hit을 할 수 있다
    - [x] stay하여 종료할 수 있다.
    - [x] 종료 여부는 false이다.
    - [x] [ERROR] 진행 상태는 입력 카드 스코어가 21을 넘을 경우 예외가 발생한다.
    - [x] [ERROR] 진행 상태는 수익률을 계산하려고하는 경우 예외가 발생한다. 
  - 완료
    - [x] [ERROR] hit을 하는 경우 예외가 발생한다
    - [x] [ERROR] stay를 하는 경우 예외가 발생한다.
    - [x] 종료 여부는 true이다.
    - 다른 카드의 결과와 확인하여 이익률을 반환할 수 있다.
      - 현재 Blackjack
        - 상대도 Blackjack인 경우 0을 반환한다.
        - 나머지의 경우 1.5를 반환한다.
      - 현재 Bust
        - -1을 반환한다.
      - 현재 Stand
        - 상대가 Blackjack인 경우 -1을 반환한다.
        - 상대보다 21에 가까울 경우 1을 반환한다.
        - 상대와 수가 같은 경우 0을 반환한다.
        - 상대보다 21에 멀 경우 -1을 반환한다.
