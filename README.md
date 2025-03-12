# java-blackjack

블랙잭 미션 저장소

## 기능 요구 사항

- [x] 참여자 이름 입력
    - [x] 입출력 요구사항
        - [x] 쉼표를 기준으로 분리한다.

```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason
```

- [ ] 배팅 금액 입력
    - [ ] 입출력 요구사항
        - [ ] 플레이어별로 배팅할 금액을 입력 받는다.

```
pobi의 배팅 금액은?
10000

jason의 배팅 금액은?
20000
```

- [x] 카드 지급
    - [x] 플레이어는 카드 두 장을 지급 받는다.
    - [x] 카드는 4가지 문양을 가질 수 있다.
    - [x] 숫자는 A~K까지 가질 수 있다.
        - [x] Ace는 1 또는 11로 계산할 수 있다.
        - [x] J, Q, K는 각각 10으로 계산한다.
    - [x] 카드는 한 벌만 사용하며, 위에서부터 뽑는다.
    - [x] 입출력 요구사항
        - [x] 플레이어별로 지급받은 카드를 출력한다.
        - [x] 딜러는 지급받은 카드 중 마지막 카드만 출력한다.

```
딜러와 pobi, jason에게 2장을 나누었습니다.
딜러카드: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드
```

- [x] 플레이어 카드 추가 지급
    - [x] 카드를 추가로 지급받아 버스트될 경우 더이상 카드를 추가로 지급받을 수 없다.
    - [x] 입출력 요구사항
        - [x] 플레이어별로 n이 입력될 때까지 카드를 추가로 지급받는다.
        - [x] 카드를 추가로 지급받아 버스트될 경우
            - [x] 카드를 보여주고 버스트되었음을 안내한다.
            - [x] 추가 지급 질문을 출력하지 않는다.

```
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드
```

- [x] 딜러 카드 추가 지급
    - [x] 딜러는 카드 숫자 합이 16 이하이면 카드를 추가로 받는다.
        - [x] 16을 넘길 때까지 추가로 받는 카드 장수에는 제한이 없다.
        - [x] Ace는 버스트되지 않는 한 11로 계산한다.
        - [x] Ace를 11로 계산하여 버스트된다면 1로 계산한다.

```
딜러는 16이하라 한장의 카드를 더 받았습니다.
```

- [x] 라운드 결과 안내
    - [x] 딜러와 각 플레이어들이 가진 카드 숫자 합을 계산한다.
        - [x] Ace는 버스트되지 않는 한 11로 계산한다.
        - [x] Ace를 11로 계산하여 버스트된다면 1로 계산한다.
    - [x] 입출력 요구사항
        - [x] 딜러와 각 플레이어의 모든 카드를 공개한다.
        - [x] 카드 숫자 합도 함께 출력한다.

```
딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17
```

- [x] 최종 승패
    - [x] 상대방만 버스트했다면 카드 숫자 합에 상관없이 승리한다.
    - [x] 양쪽 다 버스트하지 않았다면 카드 숫자 합을 비교하여 더 높은 쪽이 승리한다.
    - [x] 동점일 경우
        - [x] 블랙잭(Ace + J or Q or K)은 21을 이긴다.
        - [x] 둘 다 블랙잭이면 무승부로 처리한다.
        - [x] 블랙잭이 없다면 무승부로 처리한다.

- [ ] 최종 수익
    - [x] 딜러가 승리할 경우 플레이어가 베팅한 금액을 수익으로 계산한다
    - [x] 플레이어가 블랙잭 없이 승리할 경우 베팅 금액의 1배를 수익으로 계산한다
    - [x] 플레이어가 블랙잭으로 승리할 경우 베팅 금액의 1.5배를 수익으로 계산한다
    - [x] 딜러가 패배할 경우 수익을 플레이어의 베팅 금액을 차감한 값으로 계산한다
    - [x] 플레이어가 패배할 경우 수익을 0에서 베팅 금액을 차감한 값으로 계산한다
    - [ ] 무승부일 경우 수익을 0으로 계산한다
    - [ ] 입출력 요구사항
        - [ ] 딜러와 플레이어들의 최종 수익을 출력한다.

```
## 최종 수익
딜러: 10000
pobi: 10000 
jason: -20000
```

## 실행 결과

```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

pobi의 배팅 금액은?
10000

jason의 배팅 금액은?
20000

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

## 최종 수익
딜러: 10000
pobi: 10000 
jason: -20000
```
