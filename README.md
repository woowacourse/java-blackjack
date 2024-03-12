## 기능 요구사항

### 게임

- [x] 각 플레이어 및 딜러에게 두 장의 카드를 준다.
- [x] 카드팩을 생성한다.
- [ ] 모든 카드가 사용되면 새로운 카드팩을 생성한다.

### 카드

- [x] A부터 K까지의 숫자 카드를 갖는다.
- [x] A는 1 또는 11로 계산한다.
- [x] J, Q, K는 각각 10으로 계산한다.

### 카드팩생성기

- [x] 각각 다른 카드 52장을 생성한다.
- [x] 랜덤으로 카드를 배치한다.

### 카드팩

- [x] 카드를 한 장 반환한다.
- [x] 모든 카드가 사용됐는지 체크한다.

### 플레이어들

- [x] 중복된 이름을 가질 수 없다.
- [x] 최소 1명 최대 8명까지 참여할 수 있다.

### 플레이어

- [x] 점수가 21 이하인지 초과인지 알려준다.
- [x] 카드를 한 장 받는다.

### 플레이어 이름

- [x] 최소 1자 최대 20자 길이를 갖는다.
- [x] 플레이어의 이름은 "딜러"가 될 수 없다.

### 딜러

- [x] 점수가 16 이하인지 알려준다.
- [x] 점수가 21 초과이면 즉시 패배한다.
- [x] 카드를 한 장 받는다.

### 점수

- [x] 카드의 합에 10을 더했을 때 21 이하인 경우 A는 11로 계산한다.
- [x] 카드의 합에 10을 더했을 때 21 초과인 경우 A는 1로 계산한다.
- [x] 지금까지 뽑은 카드 숫자의 합을 저장한다.
- [x] 지금까지 뽑은 카드 숫자의 합이 21 미만인지 알려준다.

### 게임 결과

- [x] 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이긴다.
- [x] 점수를 비교하여 승패를 알려준다.

### 입력

- [x] 플레이어의 이름을 입력받는다.
- [x] 카드를 더 뽑을지 말지 입력받는다. (y 또는 n)

### 출력

- [x] 딜러는 첫번째 카드, 각 플레이어는 두 장의 카드 모두를 출력한다.
- [x] 플레이어가 갖고 있는 카드를 출력한다.
- [x] 딜러가 카드를 추가로 받았다는 메시지를 출력한다.
- [x] 각 플레이어별로 갖고 있는 카드와 숫자의 합을 출력한다.
- [x] 각 플레이어별로 승패를 출력한다.
