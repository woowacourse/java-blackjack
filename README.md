# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

# 기능 요구사항

블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
- **플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.**
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다. 
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. **단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.**
- 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다. 
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. **딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.**

## 실행 프로세스

---

1. 게임에 참여할 사람의 이름 입력
2. **플레이어들의 베팅 금액 입력** 
3. 딜러와 플레이어에게 2장씩 카드를 나눠줌 
4. 각 플레이어에게 카드 더 받을건지 물어봄 

   4-1. 플레이어가 "n"를 입력할 때까지 반복 
5. 딜러의 카드가 16 초과가 될 때까지 카드를 더 받음 
6. 딜러와 플레이어 카드 출력 
7. 최종 ~~승패~~ 수익 출력

## 기능 목록

---

- 참가자 (플레이어 + 딜러)
  - [x] 카드를 받아서 가지고 있음
  - [x] 모든 카드의 숫자 합을 반환하는 기능
- 플레이어
  - [x] validation: 플레이어 이름은 중복 불가
  - [x] validation: 플레이어 이름의 길이 1글자 이상 5글자 이하
  - [x] 쉼표 기준으로 분리
  - [x] validation: 플레이어의 수는 최소 1명 최대 7명
  - [x] 카드를 받음
    - [x] 초기 카드 2장을 받음
    - [x] 추가 카드를 받음
    - [x] 각 턴마다 카드 숫자가 21 초과 인지 확인
- 딜러
  - [x] 카드들 받음
    - [x] 초기 카드 2장을 받음
    - [x] 추가 카드를 받음
    - [x] 각 턴마다 카드 숫자가 16 초과 인지 확인
- 게임
  - [x] 참가자들에게 초기 카드 배분
  - [x] 참가자들에게 각 턴마다 카드 배분
  - [x] 딜러와 플레이어의 결과를 비교하여 승패 판단
- 카드
  - [x] 카드 숫자(Rank) 반환
- 덱
  - [x] 모든 카드를 가짐
- ACE
  - 모든 카드의 합이 
    - 21보다 작으면 11로 적용
    - 21보다 크면 1로 적용
- (2단계) 배팅 금액
  - validation: 양의 정수인지
- (2단계) 상태에 따른 수익 배분
  - BlackJack
    - 플레이어: 1.5배 
    - 플레이어 + 딜러: 1배
  - Bust
    - 플레이어: 0배
    - 딜러: 1배
  - Stay (=Not BlackJack && Not Bust) 
    - 플레이어 > 딜러: 1배
    - 플레이어 < 딜러: 0배
- (2단계) 플레이어 결과에 따른 수익 배분
  - Win
    - BlackJack: 1.5배
    - Stay: 1배
  - Lose
    - 0배
  - Push
    - 동시 BlackJack: 1배
    - Stay: 1배
