# 우아한테크코스 7기 BE LEVEL1 MISSION 3 - 블랙잭

## 커스텀 기능 요구사항

- 플레이어 명수는 최소 1명, 최대 6명 까지이다.
- 플레이어의 이름은 최소 2글자, 최대 5글자로 제한한다.
- 플레이어의 이름에 공백을 포함할 수 없다.
- 플레이어의 베팅 금액은 최소 천원 이상이여야 한다.
- 플레이어의 배팅 금액은 10만원을 넘을 수 없다.


- 딜러와 플레이어는 동일한 방식으로 에이스 카드를 계산한다.
    - 에이스 카드를 포함한 경우, 21을 넘지 않는 선에서 가능한 큰 값이 되도록 계산한다.


- 예외가 발생하는 경우, 예외 메시지를 출력하고 다시 입력 받는다.

## 게임 시작

#### 참가 플레이어 등록
- [x] 게임에 참여할 사람의 이름을 입력받는다.
    - [x] 올바르지 않은 이름의 형식이 들어오면 예외를 발생시킨다.
    - [x] 플레이어 명수가 1명 이상, 6명 이하가 아닌 경우 예외를 발생시킨다.
    - [x] 이름은 중복 불가하며, `딜러`라는 이름을 사용할 수 없다.

#### 플레이어 베팅
- [x] 플레이어 별 베팅 금액을 입력 받는다.
    - [x] 올바르지 않은 베팅 금액 형식이 들어 오면 예외를 발생시킨다.
    - [x] 플레이어의 베팅 금액이 1,000원 미만인 경우 예외를 발생시킨다.
    - [x] 플레이어의 베팅 금액이 100,000 초과인 경우 예외를 발생시킨다.
- [x] 카드를 생성해 덱을 구성한다.
- [x] 게임 라운드를 시작한다.
    - [x] 참여할 사람을 등록한다.

## 게임 진행

- [x] 딜러와 모든 플레이어에게 2장씩 카드를 나누어 준다.
- [x] 딜러의 카드 한 장을 출력한다.
- [x] 모든 플레이어의 카드 전체를 출력한다.

#### 플레이어 턴

- [x] 플레이어에게 추가로 한장 더 받을 지 여부를 입력받는다.
    - [x] 올바르지 않는 응답의 경우 예외를 발생시킨다.
    - [x] 플레이어가 동의할 경우 카드를 한 장 더 지급한다.
        - [x] 플레이어 카드 전체를 출력한다.
        - [x] 플레이어 카드 합을 계산한다.
        - [x] 플레이어의 카드 합이 21 초과이면 안내를 하고 다음 플레이어로 넘어간다.
        - [x] 플레이어가 동의하지 않을 때까지 위 과정을 반복한다.
    - [x] 플레이어가 동의하지 않을 경우 다음 플레이어로 넘어간다.
        - [x] 만약 처음부터 동의하지 않는 경우, 카드 전체를 출력한다.
- [x] 모든 플레이어에게 위의 과정을 반복한다.

#### 딜러 턴

- [x] 딜러의 카드 합을 계산한다.
- [x] 딜러의 카드 합이 16 이하인 경우 카드를 한 장만 더 지급한다.
    - [x] 지급 받은 경우에는 멘트를 출력한다.

## 게임 종료

#### 최종 결과 안내

- [x] 딜러와 플레이어의 카드의 합계를 계산한다.
- [x] 딜러와 플레이어의 보유 카드와 카드의 합계를 출력한다.


#### 최종 승패 판별

- [x] 최종 승패 결과를 계산한다.
    - [x] 플레이어와 딜러 둘 다 21점 이하인 경우 숫자가 큰 쪽이 승리한다.
    - [x] 점수가 같은 경우 무승부 처리한다.
    - [x] 플레이어와 딜러 둘 중 한명만 21 초과한 경우 초과한 쪽이 패배한다.
    - [x] 플레이어와 딜러 둘 다 21 초과한 경우 딜러가 승리한다.


#### 승패에 따른 수익 안내

- [ ] 플레이어나 딜러의 카드가 블랙잭인지 확인한다.
    - [ ] 플레이어만 블랙잭 인 경우 해당 플레이어의 수익을 베팅 금액의 1.5배 하여 설정한다.
    - [ ] 플레이어만 블랙잭 인 경우 딜러의 수익은 플레이어의 수익만큼 뺀다.
    - [ ] 플레이어와 딜러 모두 블랙잭인 경우 해당 플레이어와 딜러의 수익은 변동 사항이 없다. 
- [ ] 승패 결과에 따라 수익을 설정한다.


- [ ] 최종 승패에 따른 수익을 출력한다.


- [x] 프로그램을 종료한다.
