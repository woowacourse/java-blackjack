# java-blackjack
블랙잭 게임 미션 저장소

## 기능 요구사항
### Domain(TDD)
- [x] Card
  - [x] 미리 분배할 카드들을 캐싱 한다.
      - [x] 카드들을 섞는다.
  - [x] Suit 와 Number 상태값을 가진다.
  
- [x] Suit, Face
  - [x] enum을 사용하여 카드에서 사용하는 정보를 관리한다. 

- [x] Cards (유저가 가지고 있는 카드 묶음)
  - [x] 현재 스코어를 계산한다.
  - [x] 21점이 넘으면, Bust 임을 확인한다.
  
- [ ] Player
  - [ ] Challenger
    - [x] Cards 상태값을 가진다.
    - [x] Name, isBust 상태값을 가진다.
    - [x] 초기 카드를 받는다. (2장)
    - [ ] 카드를 추가로 받는다.(수동) 
    - [x] Bust 여부 계산한다.
  - [ ] Dealer
    - [x] Cards 상태값을 가진다.
    - [x] 초기 카드를 받는다. (2장)
    - [ ] 카드를 추가로 받는다.(자동)
    - [x] Player와의 승자 판별한다.
    - [x] Bust 여부 계산한다.


- [ ] Players (게임에 참여한 플레이어 그룹)

- [ ] Name

### Controller, Service, View
- [ ] InputView (String 으로 받는다)
- [ ] OutputView
  