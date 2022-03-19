# java-blackjack


## 목표

### 공통 목표

- 상속과 인터페이스 잘 구현하기
- 가독성 개선
    - 좋은 네이밍
    - 함수의 매개변수를 최대한 줄이기
- 객체간의 의존관계 줄이기
- 프로그램 구현 전체과정을 TDD로 진행하기
- 알고있는 지식을 함께 공유하기
- 코드가 깨지지 않는 점진적인 리팩토링 스킬 키우기
- static으로 써야 할 것과 instance로 써야할 것 잘 구분하기

### 리버

- 정적 팩토리 메서드 활용하기
- Getter사용을 줄이기

### 포키

- 현실 도메인을 잊고, 프로그램 내의 객체 역할에 집중해보기
- 내 생각에 빠져서 미션의 큰 흐름을 놓치지 않기
- 내가 아는 것 뿐 아니라 다른 지식도 적극적으로 받아들이기

---

## 기능 목록

### 참가자 등록

- [X]  참가자의 이름을 입력받는다.

```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason
```

- [x] 입력받은 참가자의 이름을 검증한다.
  - [x] [예외] 입력 값이 공백이거나 없을 때
  - [x] [예외] 중복된 참가자 이름이 있을 때
  - [x] [예외] 참가자가 이름이 8개를 넘을 때

### 참가자들 배팅
- [x] 참가자의 배팅 금액을 입력받는다. 
```
pobi의 배팅 금액은?
10000
```
- [x] 입력 받은 배팅 금액을 검증한다.
    - [x] [예외] 배팅 금액이 정수가 아닐 떄
    - [x] [예외] 배팅 금액이 음수이거나 0일 때

### 최초 카드 분배

- [x]  딜러와 참가자들에게 카드를 2장씩 랜덤하게 분배한다.
    - [x]  같은 카드가 두 번 분배될 수 없다.
- [x]  딜러와 참가자들에게 카드가 분배되었음을 출력한다.

```
딜러와 pobi, jason에게 2장의 카드를 나누었습니다.
```

- [x]  분배된 카드를 출력한다.
    - [ ]  딜러의 카드는 한 장만 출력한다.

```
딜러: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드
```

### 추가 카드 뽑기-턴이 넘어가기 전 까지 반복한다.

#### 턴이 넘어가는 조건(n을 입력하는 경우,카드 합산결과가21이상인 경우)

- [x]  참가자에게 카드 추가 여부를 입력받는다.

```
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
```

- [x]  입력받은 카드 추가 여부를 검증한다.
  - [x][예외]y 또는 n이 아닐 때
- [x] y 이면, 랜덤하게 카드를 한 장 추가한다.
    - [x]  참가자가 가지고 있는 카드를 출력한다.

```
pobi: 2하트, 8스페이드, A클로버
```

- [x]  n 이고 다음 참가자가 있으면 다음 참가자로 넘어간다.
- [x]  n 이고 다음 참가자가 없으면 딜러 카드 추가로 넘어간다.

### 딜러 카드 추가

- [x]  딜러의 카드 점수를 합산한다.
    - [x]  합산 결과가 16 이하이면 랜덤하게 카드를 1장 추가한다.
    - [x]  합산 결과가 17 이상이면 추가하지 않는다.
- [x]  딜러의 카드 추가 여부를 출력한다.

```
딜러: 2하트, 8스페이드, A클로버
```

### 게임 종료-최종 결과

- [ ]  딜러와 참가자들의 카드를 최종적으로 합산한다.
- [ ]  딜러, 참가자들이 가지고 있는 카드와, 합산 결과를 출력한다.

```
딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17
```

- [ ]  딜러와 각 참가자들에 대한 승패를 판정한다.
    - [ ]  참가자의 카드 합산 결과가 21을 초과하면 무조건 패배한다.
    - [ ]  딜러와 참가자 중 21에 더 가까운 사람이 승리한다.
    - [ ]  딜러와 참가자의 점수가 동점일 경우 무승부로 한다.
- [ ]  딜러와 각 참가자들의 최종 수익을 출력한다.

```
## 최종 수익
딜러: 10000
pobi: 10000 
jason: -20000
```

---
