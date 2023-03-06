# java-blackjack

블랙잭 미션 저장소

### 1단계 피드백 사항 및 추가 수정사항
- [x] 클래스, 변수, 메소드명 담고 있는 의미를 잘 나타내도록 수정
- [x] Card 관리 List -> Queue 변경
  - [x] 카드 다떨어지면 우선 에러처리
- [x] 카드 뽑는 기능 Collections.shuffle() 사용
- [x] Player 생성 로직 PlayerFactory 에 위임 -> Players 정적 팩토리 메소드로 구현
- [x] Player 이름 중복, Player 수 validate 하는 로직 Players 에 이관
- [x] 딜러, 플레이어 출력 로직 중복 제거
  - [x] 딜러 getName() 메소드 "딜러"로 하드코딩 되어 있는 것 수정
- [x] 카드를 더 받을지 말지 로직 enum 으로 구현
- [x] 플레이어의 의사(카드를 더 받을지 말지) + 더 받을 수 있는 상태를 판단하는 로직은 Player 에게 위임 
    - 플레이어 의사 판단은 controller 에 있게 함
- [x] GameResult 딜러 결과 HashMap -> enumMap 변경 

### 요구 사항

### 입력

- [x] Player 이름
    - [x] 중복 확인
    - [x] Blank 검증
    - [x] Player 수 1~7 명
- [x] 카드 더 받을지 여부
    - [x] y, n 이 아닐 경우

### 출력

- [x] 처음 할당받은 카드들 정보
    - [x] 딜러는 1장만
- [x] 매 턴마다 현재 가지고 있는 카드 출력
- [x] 게임 종료 후 최종 결과 출력
- [x] 최종 승패 출력

### 기능 목록

- [x] 52장의 카드를 담은 리스트를 생성한다 (Trump)
- [x] 카드를 뽑는다 (random 생성된 Index 번호)
- [x] 카드를 나눠준다(1장씩)
- [x] 모든 참가자들에게 2개씩 카드를 나눠준다
- [x] 카드를 더 받을지 여부에 따라서 카드를 추가로 받는다
- [x] 숫자 계산
    - [x] 카드 숫자를 기본
    - [x] King, Queen, Jack : 10
    - [x] Ace
        - [x] 기본 11점
        - [x] 총합이 21 초과가 되면 1로 계산한다 (21이 안넘을 때까지 하나씩 1로 계산)
- [x] 승패 계산
- [x] Player
    - [x] 1 개 받는다
    - 반복 (n 이 들어오거나 점수 합이 22 이상이 될때까지)
        - [x] 현재 가지고 있는 카드의 점수를 계산해서 더 받을 수 있는지 여부를 확인
        - [x] 점수의 합이 22 미만이면 더 받을지의 여부를 입력받는다
        - [x] y 면 카드를 새로 한장 받는다
    - [x] n 이면 차례가 끝난다
- [x] Dealer
    - [x] 딜러가 16 이하면 카드를 계속 받을 수 있는지 여부를 확인
- [x] Player 와 Dealer 중복되는 코드 제거
  - [x] Participant 클래스 상속

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
