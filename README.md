# ♠️ Java Blackjack

자바로 구현한 블랙잭 게임 프로젝트입니다.

## 🎮 게임 규칙

블랙잭은 카드의 합이 21에 가장 가까운 사람이 이기는 게임입니다. 21을 초과하면 패배(Bust)합니다.

- 카드 값:
    - ACE: 1 또는 11 (21을 초과하지 않는 선에서 유리한 값 선택)
    - KING, QUEEN, JACK: 10
    - 숫자 카드: 카드에 표시된 숫자 값

## ✅ 기능 요구사항

### 게임 시작 및 초기화

- [x] 플레이어 이름 입력 받기
    - [x] 플레이어는 1~7명까지 가능
    - [x] 이름은 공백일 수 없음
    - [x] 쉼표(,)를 기준으로 이름 분리
- [x] 배팅 금액 입력 받기
    - [x] 배팅 금액은 양수

### 카드 분배

- [x] 게임 시작 시 카드 분배
    - [x] 플레이어와 딜러 모두 두 장의 카드 받음
    - [x] 딜러는 한 장의 카드만 공개
    - [x] 플레이어는 두 장의 카드 모두 공개

### 플레이어 턴

- [x] 각 플레이어에게 카드를 더 받을지 여부 확인
    - [x] y/n 입력으로 결정
    - [x] 'y' 입력 시 카드 추가 지급
    - [x] 카드를 더 받지 않을 때까지 반복
    - [x] 21 초과 시 안내 문구 출력 후 다음 플레이어로 진행
    - [x] 요청을 한 이후에 카드 출력

### 딜러 턴

- [x] 딜러의 카드 합이 16 이하인 경우 추가 카드 지급
    - [x] 추가 카드를 받은 경우 안내 출력
    - [x] 17 이상인 경우 추가 카드 없이 진행 (출력 없음)

### 결과 계산

- [x] 모든 참가자의 카드와 점수 합계 출력
    - [x] ACE 카드는 1 또는 11로 계산 (21에 가깝게)
    - [x] K, Q, J는 10으로 계산

### 승패 결정

- [x] 딜러와 각 플레이어 간 승패 결정
    - [x] 블랙잭일 경우 승리
    - [x] 블랙잭이 아니라면 21에 가까운 쪽이 승리한다.
    - [x] 둘 다 블랙잭이라면 무승부로 처리한다.
    - [x] 같은 값일 경우 무승부
    - [x] 각 참가자의 최종 승패 결과 출력
- [x] 게임에 승리 결과에 따라 수익을 처리
    - [x] 플레이어가 첫 두 장의 카드가 블랙잭이라면 베팅금액의 1.5배를 받는다.
    - [x] 단, 딜러와 플레이어 모두 블랙잭이라면 플레이어는 베팅 금액을 돌려 받는다.
    - [x] 딜러가 버스트된다면, 플레이어 승패와 상관없이 베팅 금액을 받는다.
    - [x] 승자는 배팅 금액을 가져간다.
    - [x] 패자는 배팅 금액을 잃는다.
    - [x] 승패가 결정되지 않는다면, 돈을 돌려 받는다.

# 수익 계산

- [x] 수익이 표시된다.
- [x] 무승부라면 0으로 출력된다.

## 🚀 실행 결과

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
