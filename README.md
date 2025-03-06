# 블랙잭 🎰

## 기능 구현 목록 ⚙️

### ✅ 게임 참여 참가자 등록 기능

- [ ] 게임에 참여하는 참가자 이름을 입력한다
- [ ] 참가자 이름은 쉼표(,)로 구분한다
- [x] 참가자의 목록으로 객체를 생성한다
- [x] 각 참가자 마다 기본 카드 2장을 발급한다
- [x] 딜러에게 기본 카드 2장을 발급한다
- [ ] 각 플레이어 마다 발급된 카드를 출력한다 (딜러는 1장만 출력)
- [ ] `예외처리` 이름의 길이가 0일때 예외가 발생한다 `[ERROR] 이름의 길이는 1이상입니다.`
- [ ] `예외처리` 이름이 중복되면 예외가 발생한다 `[ERROR] 이름은 중복될 수 없습니다.`

```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

딜러와 pobi, jason에게 2장을 나누었습니다.
딜러카드: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드
```

### ✅ 추가 카드 발급 기능

- [x] 참가자에게 한장의 카드를 추가 발급한다
- [ ] 참가자마다 추가 발급 여부를 입력한다
    - `y` 입력인 경우 한 장의 카드를 추가 발급한다
    - `n` 입력인 경우 발급을 멈추고 다음 참가자로 넘어간다
- [x] 플레이어가 버스트인지 판단한다
    - 카드 합계가 21을 초과하면 버스트로 판단한다
- [ ] 추가 발급된 카드를 출력한다
- [ ] `예외처리` `y` 또는 `n` 이외의 값을 입력했을 때 예외가 발생한다
- [ ] `예외처리` 카드팩에 카드가 없으면 예외가 발생한다 `[ERROR] 더 이상 카드팩에 카드가 없습니다`

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

### ✅ 딜러 카드 추가 발급 기능

- [ ] 딜러의 카드 합계가 16 이하인 경우 한 장의 카드를 추가 발급한다

```
딜러는 16이하라 한장의 카드를 더 받았습니다.
```

### ✅ 최종 집계 기능

- [x] 각 플레이어의 카드 합계를 계산한다
    - `ACE` 1 또는 11로 계산한다
- [ ] 각 플레이어의 카드와 합계를 출력한다
- [ ] 각 플레이어의 최종 승패를 판결한다

```
딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 승패
딜러: 1승 1패
pobi: 승 
jason: 패
```
