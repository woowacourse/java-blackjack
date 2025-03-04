# java-blackjack

블랙잭 미션 저장소

딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이긴다.
21을 초과하면 진다.

예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.

플레이어는 두 장의 카드를 지급 받으며, 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
딜러는 처음에 받은 2장의 합계가 16이하이면 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.

게임을 완료한 후 각 플레이어별로 승패를 출력한다.

1. 게임에 참여할 플레이어 이름 입력
```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason
```

2. 초기 카드 분배
```
딜러와 pobi, jason에게 2장을 나누었습니다.
딜러카드: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드
```

3. 플레이어별 카드 추가
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

4. 딜러 카드 추가
```
딜러는 16이하라 한장의 카드를 더 받았습니다.
```

5. 분배된 카드 확인
```
딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17
```

6. 승패 확인
```
## 최종 승패
딜러: 1승 1패
pobi: 승 
jason: 패
```