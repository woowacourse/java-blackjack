# java-blackjack
블랙잭 미션 저장소

# 의문점
1. 테스트 코드 작성시, 테스트하고 싶은 메소드가 아닌 다른 메소드에 의존해도 괜찮은지?
```angular2html
@DisplayName("카드를 랜덤으로 1장 생성하여 배분한다")
    @Test
    void test4() {
        //given
        RandomGenerator<Card> randomGenerator = new TestRandomGenerator();
        GivenCards testGivenCards = GivenCards.createEmpty();
        testGivenCards.addUnique(new Card(CardNumberType.FIVE, CardType.SPACE)); // 이 부분
        CardGiver cardGiver = new CardGiver(randomGenerator, testGivenCards);
        //when

        //then
    }
```

# 학습 목표
- 도메인은 TDD로 구현하기
- 최대한 가독성이 좋은 코드를 작성하기(클린 코드)

# 기능 요구사항 정의

### 1. 게임에 참여할 사람의 이름을 입력받는다
#### 입력 뷰 객체
- [ ] 게임에 참여할 사람의 이름을 입력받는다
#### 입력 파싱 객체
- [ ] 이름은 쉼표 기준으로 구분한다
#### 입력 검증 객체 (커스텀 요구사항)
- [ ] 최대 참여 인원 수는 4명이다
- [ ] 이름 제한은 5글자 이내이다

### 2. 게임 참여자와 딜러에게 카드 2장을 랜덤으로 나눠준다
#### 카드 불변 객체
- [X] 숫자와 모양이 같다면 같은 객체이다
#### 카드 랜덤 생성 객체
- [X] 카드를 랜덤으로 1장 생성한다
#### 카드 배분 객체, 카드 묶음 객체(일급 컬렉션)
- [X] 카드를 2장 배분한다
  - [X] 랜덤으로 생성한 카드를 저장한다
    - [X] 랜덤으로 생성한 카드가 나눠준 카드와 중복되는지 검증한다
#### 참여자 저장소 객체
- [X] 딜러의 객체를 기본적으로 가지고 있다
  - [X] 딜러의 이름은 "딜러"이다
- [X] 참여자 전원의 객체를 저장한다
- [X] 딜러와 참여자 이름으로 참여자 객체를 조회한다
#### 컨트롤러 객체
- [ ] 딜러의 카드는 1장만 뷰에 전달한다
- [ ] 참여자에게 배분된 카드는 전부 뷰에 전달한다

#### 출력 객체
- [ ] 딜러와 참여자의 이름과 각각 배분된 카드를 출력한다

### 3. 게임 참여자에게 카드를 추가로 배분한다
#### 참여자 저장소 객체
- [X] 딜러를 제외한 모든 참여자를 조회한다
#### 컨트롤러 객체
- [ ] 모든 참여자에게 카드 추가 배분 여부를 묻는다
#### 입력 객체
- [ ] 참여자에게 카드 추가 배분 여부를 입력받는다

y일 경우
#### 카드 묶음 객체
- [X] 기존 카드의 합이 21이 넘는다면 예외를 발생시킨다

#### 컨트롤러 객체
- [ ] 기존 카드의 합이 21이 넘지 않는지 검증한다
  - [ ] 예외가 발생할 경우, 다음 참여자로 넘어간다

#### 카드 랜덤 생성 객체, 카드 배분 객체
- [X] 카드를 랜덤으로 1장 생성하여 배분한다
    - [X] 랜덤으로 생성한 카드가 나눠준 카드와 중복되는지 검증한다

#### 카드 묶음 객체
- [ ] 새로 배분된 카드를 저장한다

#### 출력 객체
- [ ] 해당 참여자의 업데이트된 카드 정보를 모두 출력한다

n일 경우
#### 출력 객체
- [ ] 해당 참여자의 기존 카드 정보를 모두 출력한다

#### 컨트롤러 객체
- [ ] 다음 참여자로 넘어간다

### 4. 딜러의 카드를 추가로 배분한다
#### 카드 묶음 객체
- [ ] 카드의 합계가 16이하인지 확인한다

#### 카드 랜덤 생성 객체, 카드 배분 객체
- [ ] 카드 합계가 16이하일 경우 카드를 랜덤으로 1장 생성하여 배분한다
  - [ ] 랜덤으로 생성한 카드가 나눠준 카드와 중복되는지 검증한다 

#### 출력 객체
- [ ] 카드 합계가 16이하일 경우 카드 추가 메시지를 출력한다
- [ ] 카드 합계가 17이상일 경우 카드를 추가하지 않았다는 메시지를 출력한다

### 5. 딜러와 모든 참여자의 결과를 출력한다
#### 카드 묶음 객체
- [ ] 카드의 합을 구한다
  - [ ] 만약 ACE가 포함된 경우, 나머지 카드의 합이 11 이하이면 ACE를 10으로 간주한다
  - [ ] 만약 ACE가 포함된 경우, 나머지 카드의 합이 12 이상이면 ACE를 1로 간주한다 

#### 참여자 저장소 객체
- [ ] 딜러와 모든 참여자의 정보를 조회한다

#### 출력 객체
- [ ] 모든 참여자의 정보를 출력한다

### 6. 최종 승패를 구한다
#### 승패 판단 객체
- [ ] 딜러와 참여자의 카드 합을 비교해 참여자의 승패 여부를 결정한다

#### 출력 객체
- [ ] 딜러의 승패 결과를 출력한다
- [ ] 모든 참여자의 승패 결과를 출력한다
