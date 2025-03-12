# 우아한테크코스 7기 BE LEVEL1 MISSION 3 - 블랙잭

## 커스텀 기능 요구사항

- 플레이어 명수는 최소 1명, 최대 6명 까지이다.
- 플레이어의 이름은 최소 2글자, 최대 5글자로 제한한다.
- 플레이어의 이름에 공백을 포함할 수 없다.
- 배팅 금액은 최소 1_000원, 최대 1_000_000까지이다.
- 딜러와 플레이어는 동일한 방식으로 에이스 카드를 계산한다.
    - 에이스 카드를 포함한 경우, 21을 넘지 않는 선에서 가능한 큰 값이 되도록 계산한다.
- 예외가 발생하는 경우, 예외메시지를 출력하고 다시 입력 받는다.

## 기능 목록
### 게임 시작
- [x] 게임에 참여할 사람의 이름을 입력받는다.
    - [x] 올바르지 않은 이름의 형식이 들어오면 예외를 발생시킨다.
    - [x] 플레이어 명수가 1명 이상, 6명 이하가 아닌 경우 예외를 발생시킨다.
    - [x] 이름은 중복 불가하며, `딜러`라는 이름을 사용할 수 없다.
- [x] 카드를 생성해 덱을 구성한다.
- [x] 게임 라운드를 시작한다.
  - [x] 게임 덱을 등록한다. 
  - [x] 참여할 사람을 등록한다.
- [x] 플레이어 별 배팅 금액을 입력 받는다.
  - [x] 만약 올바르지 않은 금액의 형식이 들어오면 예외를 발생시킨다.
  - [x] 배팅 금액이 최소 1,000원 최대 1,000,000원이 아닌 경우 예외를 발생시킨다.

### 게임 진행
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

### 게임 종료
- [x] 딜러와 플레이어의 카드의 합계를 계산한다.
- [x] 딜러와 플레이어의 보유 카드와 카드의 합계를 출력한다.
- [x] 최종 승패 결과를 계산한다.
  - [x] 플레이어가 2장의 카드를 가지고 있고, 합이 21이면 블랙잭 승리다.
  - [x] 플레이어와 딜러 모두 2장의 카드에 합이 21일 시 블랙잭 무승부다.
  - [x] 플레이어와 딜러 둘 다 21 초과한 경우 딜러가 승리한다.
  - [x] 플레이어와 딜러 둘 중 한명만 21 초과한 경우 초과한 쪽이 패배한다.
  - [x] 플레이어와 딜러 둘 다 21점 이하인 경우 숫자가 큰 쪽이 승리한다.
  - [x] 점수가 같은 경우 무승부 처리한다.
- [x] 최종 수익을 계산한다.
  - [x] 블랙잭 승리한 플레이어는 원금의 1.5배에 달하는 수익을 얻는다.
  - [x] 블랙잭 무승부한 플레이어는 원금만을 회수한다.
  - [x] 일반 승리한 플레이어는 원금의 2배에 달하는 수익을 얻는다.
  - [x] 패배한 플레이어는 원금을 잃는다.
  - [x] 무승부한 플레이어는 원금만을 회수한다.
  - [x] 딜러는 모든 플레이어들의 수익/손실을 합하여 수익을 계산한다.
- [x] 최종 수익 결과를 출력한다.
- [x] 프로그램을 종료한다.
