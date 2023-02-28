# java-blackjack

## 🃏블랙잭 미션 저장소

## 🚀프로젝트 설명

---
블랙잭 게임을 변형한 프로그램을 구현하는 프로젝트이다. 
블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임입니다.
## 📝기능 목록

---

### 플레이어
- [ ] 에이스를 1로 할지 혹은 11로 할지를 결정한다.
- [ ] 21에 가까운 숫자를 계산하기

### 딜러 (플레이어 상속)
- [ ] 딜러는 (처음 받은 2장이) 16이하의 숫자이면 무조건 카드 한장을 추가로 받아야 하고 17이상이면 추가로 받을 수 없다.
- [ ] 에이스를 1로 할지 혹은 11로 할지를 결정한다.
- [ ] 21에 가까운 숫자를 계산한다.

### 카드
- [ ] 랜덤한 카드를 제공한다.
  - [ ] 카드는 6개의 카드 뭉치를 사용한다.

### 블랙잭 게임
- [ ] 승패를 결정한다.(플레이어와 딜러간의 승패를 남긴다)

### 최종결과
- [ ] 게임의 최종 결과를 관리한다.

### 입력
- [ ] 플레이어 이름을 입력받는다.
    - [ ] delimeter는 ,로 한다.
- [ ] 카드를 추가로 받을지 말지 입력받는다.

### 출력
- [ ] 게임의 최종 결과를 출력한다.
- [ ] 딜러와 플레이어의 승/패를 출력한다.
- [ ] 플레이어의 카드 현황을 출력한다.

### 예외처리
- [ ] 사용자의 입력이 공백인지 null인지 검증한다.
  - [ ] 플레이어 이름을 입력받을 떼
  - [ ] 카드를 받는 여부를 입력받을 때
- [ ] 사용자의 이름이 한글, 영어, 숫자인지 검증한다.
- [ ] 카드를 받는 여부를 입력받을 때 y 혹은 n 인지 검증한다.

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
