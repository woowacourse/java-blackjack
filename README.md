# java-blackjack

블랙잭 미션 저장소

## 기능 요구사항 해석

### 요구사항

- 블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다. 
- **플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.**
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
- **단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.**
- **처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.**
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
- **딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.**
- **게임을 완료한 후 각 참가자들별로 최종 수익을 출력한다.**

### 해석

- 플레이어 이름은 공백으로 입력할 수 없다.
- 플레이어는 입력 순서대로 출력한다.
- 플레이어는 최소1명, 최대 8명이다.
- 플레이어의 숫자가 21을 넘는 경우 카드를 받을 수 없다.
- 플레이어에게 카드를 더 받을지 물어볼 때, Ace는 1로 계산한다.
- 딜러와 플레이어의 카드 합계가 같을 경우 무승부로 한다.
- 플레이어 혹은 딜러가 `Bust` 되는 경우
  - 플레이어가 `Bust`되는 경우 배팅 금액을 모두 잃는다 -> 딜러 승
  - 딜러가 `Bust`되는 경우 `Bust`되지 않은 플레이어들은 베팅 금액을 받는다. -> 플레이어 승
- 플레이어와 딜러가 모두 `Bust`되지 않는 경우
  - 플레이어의 처음 두장의 카드가 `Blackjack`인 경우 배팅 금액의 1.5배를 딜러에게 받는다 -> 플레이어 승
  - 플레이어와 딜러가 모두 `Blackjack`인 경우 배팅 금액을 돌려받는다. -> 무승부


## 배팅 금액

- [x] 배팅 금액이 양수가 아닌 경우 예외가 발생한다.

## 수익

- [x] 비율을 통해 수익을 판단한다.

## 이름

- [x] 게임에 참여할 사람의 이름 생성
- [x] 공백이 입력된 경우 예외 발생
- [x] 플레이어의 이름이 중복되는 경우 예외 발생

## 게임 참가자

- 이름
- 카드 패
  - [x] 카드를 패에 넣는다.
  - [x] 카드의 점수 합을 구한다.
    - [x] Ace를 1 또는 11로 계산해서 21에 더 가까운 값으로 결과를 정한다.
- [x] 카드를 더 받을 수 있는지 판단한다.
- [x] 카드의 합계로 `Bust`를 판단한다.
- [x] 카드의 합계로 `Blackjack`를 판단한다.


### 플레이어

- [x] 플레이어는 최소1명, 최대 8명이다.
  - [x] 플레이어가 최소 1명, 최대 8명이 아닌 경우 예외 발생
- [x] `Bust`와 `Blackjack`이 아닌 경우 카드를 더 받을 수 있다.

### 딜러

- 카드 덱
  - [x] 카드를 카드덱에서 뽑는다.
  - [x] 카드를 섞는다.
- [x] 게임 참가자에게 카드를 1장씩 나누어준다.
- [x] 딜러의 카드 합계가 16 이하면 카드를 한 장 더 받는다.

## 게임 결과

- [x] 플레이어와 딜러의 점수를 비교해 승패무를 정한다.

## 게임 결과 상태

- [x] 게임 결과 상태에 따라 수익을 변경한다.

## 블랙잭 게임

- [x] 게임 시작 시 딜러와 플레이어에게 카드를 2장씩 나누어준다.
- [ ] 딜러와 플레이어의 최종 수익을 도출한다.

## 카드

- [x] 카드를 맨 위에서 뽑는다.
- [x] 카드를 섞는다.
- [x] 카드의 합을 구한다.
- [x] 에이스가 포함되었는지 알 수 있다. 

## 입력

- [x] 게임에 참여할 사람의 이름 입력 (쉼표를 기준으로 분리)
  - [x] 공백이 입력된 경우 예외 발생
- [x] 배팅 금액을 입력
  - [x] 숫자가 아닌 경우 예외가 발생
- [x] 플레이어에게 카드를 더 받을지에 대해 입력을 받는다.
  - [x] 'y'를 선택한 플레이어에게 카드를 주고, 다시 물어본다.

## 출력

- [x] 카드를 나눈 결과를 출력한다.
  - [x] 딜러의 카드 두 장 중 한 장만 공개한다.
- [x] 카드를 받을 때마다 해당 플레이어의 카드를 모두 출력한다.
- [x] 딜러의 추가 카드를 받았다면 해당 내용을 출력한다.
- [x] 카드 계산 결과를 출력한다.
- [ ] 최종 수익을 출력한다.
