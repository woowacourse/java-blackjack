# java-blackjack

블랙잭 미션 저장소

### 학습목표
- 클린 코드하기

### 개인 목표
- 확실하게 TDD 파기!
- 객체 책임 구분 잘하기!

### 페어 교대 시간
- 20분 (너무 의식하지 않는 선에서 )

### 기능 흐름

1. [ ] 딜러가 참가자 이름 입력 받는다.
2. [ ] 게임을 시작하면 딜러와 각각의 플레이어는 두 장의 카드를 지급받는다.
    1. 덱은 딜러에게 2장의 카드를 지급한다.
    2. 덱은 플레이어들에게 2장의 카드를 지급한다.
3. [ ] 딜러와 참가자들의 카드를 출력한다.
    1. 딜러는 첫 번째 카드만 출력한다.
    2. 참가자들의 카드를 출력한다.
4. [ ] 참가자 순으로  “카드 더받기”를 진행한다.
    1. 참가자의 카드 합이 21점이 넘는지 물어봅니다.
    2. 만약, 참가자의 카드가 21점을 넘지 않는다면, 뽑을지 여부를 입력받습니다.
    3. 뽑을지 여부가 y라면, 참가자는 덱으로부터 카드를 한 장 뽑습니다.
    4. 위의 과정을, 모든 참가자가 n라고 하거나, 누구도 카드 합 21점 이하에 해당하지 않은 경우까지 반복합니다.
    - 카드 더 받기는 참가자에게 카드를 더 받을지 요청받습니다.
    - y라면 카드를 뽑고, 전체 카드 목록을 출력합니다.
    - n이라면 다음 참가자의 “카드 더 받기”가 진행됩니다.
    - 21을 초과하지 않았다면 “카드 더 받기”를 진행할 수 있다.
5. [ ] “카드 더 받기”가 종료된 후, 딜러 카드의 합이 16 이하라면, 딜러는 카드를 한 장 뽑는다.
    1. 딜러에게 카드 합이 16 이하인지 물어본다.
    2. 만약, 16 이하라면, 딜러는 카드를 한 장 뽑는다.
6. [ ] 딜러의 카드합이 17이상이면 카드를 더 뽑을 수 없다.
7. [ ] 딜러의 카드들과 카드 합을 출력한다.
8. [ ] 참가자들의 카드들과 카드 합을 출력한다.
9. [ ] 최종 승패 결과를 출력한다.

### 예외 사항들
- [ ] 카드가 부족하기 때문에, 참가자의 인원 수에 제한을 둔다.
- [ ] Deck에 동일한 카드가 들어오면, 예외가 발생한다.
- [ ] Deck에 뽑을 카드가 존재하지 않는다면, 예외가 발생한다.


## **실행 결과**

```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

딜러와 pobi, jason에게 2장을 나누었습니다.
딜러카드: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 승패
딜러: 1승 1패
pobi: 승
jason: 패
```
