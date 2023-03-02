# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

```mermaid
graph TD
의존성그래프
BlackJackGame --> Participant
BlackJackGame --> Deck
BlackJackGame --> Dealer


Participant --> CardPocket
Dealer --> Participant
Player --> Participant

Players --> Player
Player --> Name
CardPocket --> Card

Deck --> Card
Card --> Shape
Card --> Symbol
```

# 기능구현 목록

## UI

1. 입력

- 참여할 사람을 입력한다
- List<String>으로 변환 및 반환한다
-

## 도메인

1. Deck
    - [x] 카드를 생성한다
        - [x] 총 52장
        - [x] Shape마다 13장을 출력
    - [x] 카드를 나눠준다

2. BlackJack
    - 딜러와 플레이어에게 카드를 2장씩 나눠준다.
      -- 카드를 나눠준다
3. Participant
    - 21 이상인지 확인하고
        - 21 이상이라면,
            - 블랙잭인지 확인한다 = 받을 수 없음
            - BURST인지 확인한다 = 받을 수 없음4
    - 현재 점수를 숫자 형태로 반환한다
    - 현재 카드를 반한한다
4. Player
    - 21 미만이면, 받을 수 있다는 boolean
5. Dealer
    - 16 이하이면, 받을 수 있따는 boolean
6. CardPocket
    - [x]  카드의 Symbol 점수를 계산한다.(ScoreCalculator 역할)
    - 카드반환 한다
    - [x] 카드 계산 총합 반환
7. Shape
    - [x] enum 형태로 하트, 스페이스, 클로버, 다이아를 저장한다
8. Symbol
    - [x] a=1 2-9, j,q,k =10
    - [x] value는 int 형으로 저장한다
9. Card
    - [x] Shape와 Symbol을 저장하는 자료구조
    -
10. Players
    - 플레이어 이름 중복
    - 플레이어 수 1명이상, 5명 이하
11. Name
    - isBlank 체크
    - 100자 이하 체크
