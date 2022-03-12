# 연료 주입

## 기능 요구 사항

1. 렌터카 회사는 이동할 거리가 정해져있는 자동차를 받을 수 있다.
2. 자동차는 Sonata, Avante, K5 3종류가 존재한다.
3. 자동차 회사는 각 차량 별로 주입해야 할 연료량을 확인할 수 있는 보고서를 출력해야 한다.

### 자동차 정보

자동차 연비는 아래와 같다.

```
* Sonata : 10km/리터
* Avante : 15km/리터
* K5 : 13km/리터
```

---

# 블랙잭

## 기능 요구 사항

- [x] 게임에 참여할 플레이어의 이름을 입력 받는다.
    - 쉼표를 기준으로 분리한다.
    - [x] 플레이어명에 포함된 좌우 공백은 제거된다.
    - [x] 플레이어명이 입력되지 않으면 예외가 발생한다.
    - [x] 플레이어명이 빈 문자열인 경우 예외가 발생한다.
    - [x] 플레이어명이 중복된 경우 예외가 발생한다.
    - [x] 플레이어명이 "딜러"인 경우 예외가 발생한다.

- [x] 게임을 시작하면 딜러와 모든 플레이어에게 두 장의 카드가 지급된다.

- [x] 딜러의 패가 처음부터 21인 경우 즉시 게임을 종료한다.
    - 블랙잭인 플레이어들은 무승부로, 나머지 플레이어들은 패배한다.

- [x] 딜러와 플레이어의 카드 정보를 출력한다.
    - 딜러의 경우 두 장 중 한 장만 공개된다.
    - 플레이어의 경우 두 장 모두 공개된다.

- [x] 각 플레이어별로 카드를 더 뽑는다.
    - [x] 에이스의 경우 11로 계산하지만, 점수가 21을 초과하는 경우 1로 계산하여 진행 가능 여부를 판단한다.
    - [x] 플레이어의 현재 점수가 21을 초과한 경우, 버스트되었다는 메세지를 출력하고 카드 뽑기를 중단한다.
    - [x] 플레이어의 현재 점수가 21 이하인 경우 카드를 한 장 더 받을지의 여부를 입력받는다. (예는 y, 아니오는 n)
    - [x] y를 입력한 경우 카드 덱에서 한 장의 카드를 뽑아서 지급한다.
    - [x] n을 입력한 경우 카드 뽑기를 중단한다.
    - [x] y, n 이외의 값을 입력한 경우 예외가 발생한다.

- [x] 딜러는 패가 17 이상이 될 때까지 자동으로 카드를 계속 더 받게 된다.
    - [x] 에이스의 경우 11로 계산하지만, 점수가 21을 초과하는 경우 1로 계산하여 진행 가능 여부를 판단한다.

- [x] 딜러와 각 플레이어의 모든 패와 점수가 동시에 공개된다.
    - [x] 에이스의 경우 11로 계산하지만, 점수가 21을 초과하는 경우 1로 계산한다.
    - [x] 다만, 여러 장의 에이스가 존재하는 경우, 21을 넘지 않는 선에서 가급적 에이스를 11로 계산한다.

- [x] 게임의 결과를 출력한다.
    - [x] 각 플레이어별로 딜러와 패를 비교하여 승리, 패배, 무승부 여부를 판단한다.
        - [ ] 딜러의 패가 카드 3장 이상으로 구성된 21인 경우, 블랙잭인 플레이어는 승리한다.
        - [x] 플레이어가 버스트되는 경우, 무조건 딜러가 승리한다.
            - 플레이어와 딜러의 패 모두 버스트되는 경우, 나중에 버스트된 딜러가 승리한다.
        - [ ] 딜러가 버스트되는 경우, 아직 버스트되지 않은 모든 플레이어가 승리한다.
        - [x] 딜러와 플레이어의 패가 모두 21이하일 때 점수가 더 큰 쪽이 승리한다.
    - [x] 딜러의 경우, 모든 플레이어와의 전적을 출력한다.
    - [x] 플레이어의 경우, 딜러와의 승부 결과를 출력한다.

### 카드 정보

카드 덱은 다음과 같이 구성된다.

```
* 카드는 4가지 심볼로 구성된다. (SPADE, HEART, DIAMOND, CLOVER)
* 카드는 13가지 랭크로 구성된다. (Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King)
* 이에 따라 한 팩의 카드는 52장으로 이루어진다. 
* 게임이 시작될 때 52장의 카드는 셔플되며, 맨 위의 카드부터 한 장씩 뽑힌다. 
```

카드 랭크별 숫자 정보는 아래와 같다.

```
* Ace : 1 혹은 11 
* 2~10 : 대응되는 번호를 그대로 사용
* King, Queen, Jack : 10
```
