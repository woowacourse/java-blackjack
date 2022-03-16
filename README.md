# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 실행 결과

```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

딜러와 pobi, jason에게 2장의 나누었습니다.
딜러: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드
//
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 승패
딜러: 1승 1패
pobi: 승
jason: 패
```

## 블랙잭 1단계 기능 요구 사항

### 입력

- [x] 게임에 참여할 사람의 이름을 입력받는다.
    - [x] null이나 empty일 수 없다.
    - [x] 컴마로 구분한다.
    - [x] 참여인원은 최소 2명 ~ 최대 8명이다.
    - [x] 이름은 서로 중복될 수 없다.
- [x] 각 참여자는 카드를 더 받을지 선택한다.
    - [x] 더 받으려면 y, 받지 않으려면 n을 입력한다.
        - [x] 그 이외의 입력 값이 들어오면 예외를 발생한다.

### 출력

- [x] 딜러 및 참여자의 카드 정보를 출력한다.
- [x] 딜러가 한장의 카드를 더 받았는지에 대한 정보를 출력한다.
- [x] 딜러 및 참여자의 각 점수를 출력한다.
- [x] 딜러 및 참여자의 최종 승패를 출력한다.

### 점수 계산

- [x] 참여자의 숫자 합이 21을 초과하면 패배한다.
- [x] 딜러는 두 장의 숫자 합이 16 이하면 한장의 카드를 받는다.
- [x] 딜러는 두 장의 숫자 합이 17 이상이면 추가로 카드를 받을 수 없다.
- [x] 라운드가 끝나면 참여자들의 점수를 계산한다.
    - [x] 점수가 21에 가장 근접한 자가 승리한다.
    - [x] 딜러와 참여자는 상대보다 점수가 크면 승리한다.
    - [x] 딜러와 참여자의 점수가 같으면 딜러가 승리한다.
    - [x] 21을 초과하면 패배한다.
    - [x] 딜러와 참여자 점수 모두 21을 초과하면 딜러가 승리한다.
    - [x] 딜러와 참여자 점수를 비교하여 최종 승패를 계산한다.
    - [x] 딜러와 참가자의 점수가 동일할 때, 딜러가 이긴 것으로 처리한다.

### 카드 정보

- [x] 종류
    - 하트
    - 스페이드
    - 클로버
    - 다이아몬드
- [x] 점수
    - 숫자 : 2 ~ 10점
    - 알파벳 : K, Q, J는 10점으로 통일한다.
    - A : 1, 11을 유리한 쪽으로 선택한다.
- [x] 배분
    - [x] 초기 딜러와 참여자는 2장씩 배분받는다.
    - [x] 딜러는 최대 1장을 더 배분받을 수 있다.
    - [x] 참여자는 1장씩 추가로 배분받을 수 있다.

<br>

## 블랙잭 2단계 기능 요구 사항

### 입력

- [ ] 참가자들의 배팅 금액을 입력받는다.
    - [ ] 금액은 숫자이어야 한다.
    - [ ] 금액은 1000원 이상이어야 한다.

### 배팅 금액

- [ ] 딜러는 참가자들의 배팅 합 만큼 가지고 있다.
- [ ] 참가자는 승리하면 배팅한 금액 만큼 수익을 얻을 수 있다. (21이하이고 딜러보다 점수 합이 높으면)
- [ ] 참가자는 블랙잭인 경우 배팅 금액의 1.5배를 수익으로 얻을 수 있다.
- [ ] 참가자와 딜러 모두 블랙잭인 경우 참가자는 배팅한 금액만 수익으로 얻는다.
- [ ] 참가자는 bust 인 경우 배팅 금액을 잃는다. (무조건 딜러가 이긴다.)

### 출력

- [ ] 딜러와 참가자의 최종 수익을 출력한다.
