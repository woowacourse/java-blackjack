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

- [ ] 플레이어 이름 입력 받기
    - [ ] 플레이어는 1~7명까지 가능
    - [ ] 이름은 공백일 수 없음
    - [ ] 쉼표(,)를 기준으로 이름 분리

### 카드 분배

- [ ] 게임 시작 시 카드 분배
    - [ ] 플레이어와 딜러 모두 두 장의 카드 받음
    - [ ] 딜러는 한 장의 카드만 공개
    - [ ] 플레이어는 두 장의 카드 모두 공개

### 플레이어 턴

- [ ] 각 플레이어에게 카드를 더 받을지 여부 확인
    - [ ] y/n 입력으로 결정
    - [ ] 'y' 입력 시 카드 추가 지급
    - [ ] 카드를 더 받지 않을 때까지 반복
    - [ ] 21 초과 시 안내 문구 출력 후 다음 플레이어로 진행
    - [ ] 첫 요청 시 Y/N 상관없이 카드 출력
    - [ ] 이후 요청은 Y인 경우에만 카드 출력

### 딜러 턴

- [ ] 딜러의 카드 합이 16 이하인 경우 추가 카드 지급
    - [ ] 추가 카드를 받은 경우 출력
    - [ ] 17 이상인 경우 추가 카드 없이 진행 (출력 없음)

### 결과 계산

- [ ] 모든 참가자의 카드와 점수 합계 출력
    - [ ] ACE 카드는 1 또는 11로 계산 (21에 가깝게)
    - [ ] K, Q, J는 10으로 계산

### 승패 결정

- [ ] 딜러와 각 플레이어 간 승패 결정
    - [ ] 21에 가까운 쪽이 승리
    - [ ] 같은 값일 경우 무승부
    - [ ] 각 참가자의 최종 승패 결과 출력

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

## CARD SUIT

- [x] 하트, 다이아몬드, 클로버, 스페이드 문양을 가진다.

## CARD RANK

- [x] 2~10까지의 숫자와 KING, QUEEN, JACK, ACE를 가지고 있다.
- [x] 숫자 만큼의 해당 값으로 간주한다.
- [x] KING, QUEEN, JACK의 경우엔 10이라는 값으로 간주한다.
- [x] ACE는 1 또는 11의 값으로 간주할 수 있다.

## CARD

- [x] 카드는 CARD SUIT와 CARD RANK를 가지고 있다.
- [ ] 카드의 값을 반환할 수 있다.

## DECK

- [x] 52장의 카드를 가져야 한다.
- [x] 카드들은 중복될 수 없다.
- [x] 카드를 섞을 수 있도록 한다.
- [x] 카드를 한 장씩 뽑을 수 있다.
- [x] 만약 카드를 모두 소진한다면 예외를 반환한다.

## HAND

- [ ] 카드를 가진다.
- [ ] 가능한 카드의 모든 합을 반환한다.

## PLAYER

- [ ] 이름과 카드들을 가진다.
- [ ] 플레이어는 카드를 두 장 가진다.
- [ ] 카드 총합 한계값 여부

## DEALER

- [ ] 딜러는 카드 두 장을 가지며 마지막 카드 하나에 대해서만 출력한다.
- [ ] 딜러는 조건에 따라 카드를 더 받을 수 있다.

## RULES

- [ ] 게임에 관한 규칙을 책임진다.

## GAME RESULT

- [ ] 승, 무, 패를 가진다.

## PLAYERS RESULT

- [ ] 각 플레이어들의 승패 현황을 알 수 있다.

## DEALER RESULT

- [ ] 딜러의 총 승, 무, 패의 카운트를 알 수 있다.
