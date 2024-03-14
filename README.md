# [블랙잭] 구현 기능 목록

## 블랙잭 용어

- 카드
    - Denomination(끗수): 2,3,4,5,6,7,8,9,10,J,Q,K,A
    - Suit(슈트): 스페이드, 하트, 다이아몬드, 클럽
    - Hand(핸드)(=Cards): 카드 패 뭉치
- 액션
    - Hit(힛): 카드를 더 받겠다는 의미
    - Stand(스탠드): 카드를 그만 받겠다는 의미

- 결과
    - BlackJack(블랙잭): 첫 카드 두 장의 합이 21인 상황
        - 플레이어와 딜러가 둘다 블랙잭이면 비긴다
    - Bust(버스트): 숫자 카드의 합이 21이 넘어간 상황
        - 플레이어가 버스트면 즉시 패배한다
        - 딜러가 버스트면 남은 플레이어들이 승리한다
    - Push(푸시): 딜러 카드와 플레이어 카드의 합이 동점인 상황
        - 푸시면 무승부다

## 비즈니스 기능

1. [테이블 참가] 입력 받은 이름의 참여자들을 등록한다 ✅
    - 참여자 수는 1명 이상이다 ✅
    - 참여자 이름은 한 글자 이상이다 ✅
    - 참여자 이름은 중복될 수 없다 ✅
    - 참여자 모두 베팅 금액을 가진다 ✅
2. [카드 딜링] 딜러와 참여자들에게 카드를 2장씩 나누어 준다 ✅
    - 카드는 끗수와 슈트의 조합이다 ✅
    - 카드는 4개의 덱에서 뽑는다(하나의 덱은 52장의 카드로 이루어진다) ✅
3. [블랙잭]
    - 플레이어가 블랙잭이면 베팅 금액의 1.5 배를 딜러에게 받는다 🆕
    - 딜러와 플레이어가 동시에 블랙잭이면 플레이어는 베팅한 금액을 돌려받는다 🆕
4. [플레이어 액션] 각 참여자들이 카드의 합을 맞추기 위해 카드를 더 받거나 그만 받는다 ✅
    - 21을 넘지 않을 경우 얼마든지 카드를 계속 뽑을 수 있다 ✅
    - 카드를 그만 받겠다는 의사 표현 이후 카드를 더 받을 수 없다 ✅
    - 버스트면 베팅 금액을 모두 잃게 된다 🆕
5. [딜러 액션] 딜러는 카드의 합이 17점 이상이 될 때까지 카드를 받는다 ✅
    - 이 때 버스트가 되면 남은 플레이어(버스트가 아닌 플레이어)가 베팅 금액만큼 금액을 더 받는다 🆕
6. [게임 결과] 딜러와 플레이어들의 카드 합을 비교하여 승패를 가리고 수익을 구한다 ✅
    - 카드 합 계산은 카드 숫자를 기본으로 한다 ✅
    - Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다 ✅
    - 플레이어가 지면 베팅 금액을 잃는다 ✅
    - 플레이어가 승리하면 베팅 금액만큼 금액을 더 받는다 ✅
    - 무승부면 플레이어는 베팅 금액을 돌려받는다 🆕
    - 수익은 얻거나 잃은 베팅 금액이다 ✅

## 입출력 기능

### 입력

1. 게임 참여자 이름을 쉼표(,)를 기준으로 구분해 입력한다 ✅
2. 각 참여자마다 베팅 금액을 입력한다 ✅
3. 각 참여자마다 Hit 혹은 Stand 여부를 y 혹은 n으로 입력한다 ✅

### 출력

1. [카드 딜링 결과]
    - 딜러는 카드를 한 장만 출력한다 ✅
    - 플레이어는 카드를 두 장 모두 출력한다 ✅
2. [플레이어 액션 결과] 카드를 더 받은 경우만 카드 목록을 출력한다 ✅
3. [딜러 액션 결과] 몇 번 카드를 더 받았는지 출력한다 ✅
4. [게임 결과]
    - 플레이어와 딜러의 최종 카드 목록과 카드 합을 출력한다 ✅
    - 플레이어와 딜러의 최종 수익을 출력한다 ✅

# 부록

## 페어와의 약속

- 변수에 final 붙이는 기준을 어떻게 정할까?
    - 불변 여부가 변경하지 않는다는 사실이 자명할 때 -> final을 붙임
        - controller에서 view와 model에서 받은 것엔 final 붙이기
        - 매개변수를 함수 내에서 건들지 않을 땐 final 붙이기
            - 함수를 호출하는 외부에선 함수한테 매개변수로 넘겨준 값이 변경되는지 알지 못하기 때문에 (변경되는 걸 기대하지 않을 수 있기에)
            - 매개변수가 함수 내에서 변경되는 걸 지양한다
    - 기능 변경으로 나중엔 불변/가변 여부가 변경될 수 있을 때 -> final 을 안붙임
        - 일반적인 지역 변수를 사용할 때 final 붙이지 않기

- 생성자 테스트 언제 작성할까?
    - 생성자에서 검증 로직이 있고 검증에 실패했을 때 예외를 던진다면 올바른 값이 들어갔을 때 예외가 발생하지 않는지 확인
    - 생성자에서 값을 변경하는 로직이 있을 때 값이 의도한 대로 저장이 되었는지 확인
        - ex) card 생성 로직에는 랜덤한 숫자를 enum(끗수, 슈트)으로 변경한다

- dto model 매핑 로직은 어디에 작성할까?
    - model에 작성할 경우 model이 dto의 구조를 아는 것이다 -> dto가 변경됐을 때 model이 변경되어야 한다
    - dto가 더 자주 변경될 것이라 판단하여, dto가 model의 구조를 아는 것이 낫다
