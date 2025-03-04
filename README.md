# java-blackjack

블랙잭 미션 저장소

# 기능 목록

- [ ] 게임이 종료되기 전까지는 프로그램이 종료되지 않는다.

## 참여자 등록

- [ ] 이름을 쉼표로 구분하여 입력할 수 있다.
    - 참여자는 최대 5명이다.
- [ ] 참여자가 5명이 넘어갈 경우 예외가 발생한다.
    - `[ERROR] 참여자는 최대 5명입니다.`
- [ ] 이름이 중복될 경우 예외가 발생한다.
    - `[ERROR] 이름은 중복될 수 없습니다.`

## 카드 지급

- [ ] 딜러와 참가자 모두에게 랜덤으로 2장의 카드를 지급할 수 있다.
- [ ] 카드 지급 결과를 출력할 수 있다.
    - 딜러의 카드는 한 장만 공개한다.
    - 참가의 카드는 모두 공개한다.
- [ ] 딜러의 점수가 21점이면 추가 지급 없이 승패를 계산한다.

## 카드 추가 지급

- [ ] 참가자는 카드를 더 받을 것인지 입력 할 수 있다.
    - 점수가 21점이 넘을 경우 자동으로 질문을 종료한다.
- [ ] 랜덤으로 카드를 추가 지급할 수 있다.
- [ ] 입력 형식이 잘못됐을 경우 예외가 발생한다.
    - `[ERROR] y 또는 n 으로 입력해주세요.`

## 딜러 카드 지급

- [ ] 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 한다.
    - 17점 이상이면 추가로 받을 수 없다.
- [ ] 딜러 카드 지급 결과를 출력할 수 있다.

## 결과 계산

- [ ] 지급 받은 카드의 점수를 합하여 결과를 계산할 수 있다.
    - 점수는 카드 숫자를 기본으로 한다.
    - Ace는 1 또는 11로 계산할 수 있다.
    - King, Queen, Jack은 각각 10으로 계산한다.
- [ ] 게임 결과를 출력할 수 있다.

## 승패 계산

- [ ] 결과를 바탕으로 각 플레이어와 딜러의 승패를 결정할 수 있다.
- [ ] 게임을 완료한 후 각 플레이어별로 승패를 출력한다.
