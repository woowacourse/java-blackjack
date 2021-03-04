# java-blackjack
블랙잭 게임 미션 저장소

- [x] 게임에 참여할 이름 입력 
  - [x] 쉼표 기준 분리 
  - 예외 상황
     - [x] 빈 문자열 입력

- [x] Deck
  - [x] 52장의 모든 카드를 가지고 있고 한장씩 나누어주는 역할
  
- [x] 딜러와 플레이들에게 카드 나누기
    - [x] 2장씩 카드 랜덤 분배
    - [x] 딜러와 플레이어 카드 출력 
      - 예외 상황
        - [x] 딜러의 카드는 한장만 공개 (첫번째 카드만 공개)

- [x] Hit or Stay
  - [x] 각 플레이를 돌면서 카드 추가 여부 입력 받기
    - 예외 상황
      - [x] y 또는 n 이 아닌 경우 예외 처리 
  - [x] 딜러는 16 이하라면 카드 한 장 추가

- [x] 카드 합산 결과 출력
  - [x] 딜러와 플레이어의 카드 현황 + 합산 결과 출력

- [ ] 승패 결과 츨력

## 추가 고려 사항 
- [ ] 플레이어 참여 인원 제한

## 패키지 구조 
1. domain 
  - User 추상 클래스 : 각 딜러 및 플레이어 관련 객체 
    - Dealer 클래스 -> extends User
    - Player 클래스 -> extends User
  - Card : 카드 객체
    - Denomination enum 클래스
    - Suit enum 클래스
  - Game : 전체 블랙잭 게임 관리 객체 
2. view
  - InputView
  - OutputView
3. controller

# 연료 주입 미션 
- [x] RentCompany 객체 생성
    - [x] 팩토리 메소드 사용
    - [x] 자동차 생성 (이동 거리 포함)
    - [x] 리포트 출력
- [x] K5, Avante, Sonata 객체 생성 
- [x] Car 추상 클래스 생성 

## 우아한테크코스 코드리뷰
* [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)