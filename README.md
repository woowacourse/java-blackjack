# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

# 기능 요구 사항

## 플레이어

- [x] 플레이어는 여러명일 수 있다.
- [x] 플레이어 이름은 공백일 수 없다.
- [x] 쉼표 기준으로 이름을 분리할 수 있다.
- [x] 플레이어 수는 최소 1명 이상이다.
- [x] 플레이어 이름은 중복되어선 안된다.

## 카드

- [x] 숫자 계산은 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A로 구성된다.
- [x] 문양은 스페이드, 다이아몬드, 하트, 클로버로 구성된다.
- [x] A는 1 또는 11로 계산한다.
- [x] J, Q, K는 각각 10으로 계산한다.

## 게임

- [x] 게임 시작 시 인원 당 받는 덱의 수는 2장이다.
- [x] 덱은 랜덤으로 생성된다.
- 진행 조건
    - [ ] 한 플레이어가 더이상 카드를 받지 않거나 21을 초과하면 다음 플레이어로 턴이 넘어간다.
    - [ ] 카드의 합이 21을 넘지 않을 경우 얼마든지 카드를 뽑을 수 있다.
    - [ ] 딜러의 카드 합이 16이하이면 1장을 추가로 받고, 17이상이면 받지 않는다.
- 승리 조건
    - [ ] 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
    - [ ] 플레이어는 자신의 숫자 합이 딜러의 숫자의 합보다 크면 승리한다.
    - [ ] 플레이어는 자신의 숫자 합이 21을 초과할 경우 패배한다.
    - [ ] 딜러의 합과 플레이어의 합이 모두 21이면 무승부이다.
    - [ ] 딜러의 합과 플레이어의 합이 모두 21이 아니며 동점일 경우 플레이어는 패배한다.
