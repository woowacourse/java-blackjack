# :sparkling_heart: java-attendance 프로젝트 소개

웹 백엔드 7기 레벨1 블랙잭 미션을 구현한 프로젝트입니다.

# :dart: 구현 기능 목록

## :rocket: 플레이어 등록

### 입력

- [x] 등록할 플레이어 닉네임 입력

    ```
    게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
    pobi,jason
    ```

### 로직

- [x] 입력된 닉네임으로 플레이어 등록
- [x] [예외처리] 참여할 플레이어가 10명을 초과하는 경우
    ```
    적절하지 않은 플레이어 수 입니다.
    ```
- [x] [예외처리] 닉네임이 중복될 경우
    ```
    중복 플레이어는 허용하지 않습니다.
    ```

## :rocket: 초기 카드 분배

### 로직

- [x] 딜러에게 2장의 초기 카드 분배
- [x] 플레이어에게 2장의 초기 카드 분배

### 출력

- [x] 초기 카드가 분배된 딜러와 플레이어의 핸드 출력
- [x] 딜러의 경우 첫번째 카드만 출력
    ```
    딜러와 pobi, jason에게 2장을 나누었습니다.
    딜러카드: 3다이아몬드
    pobi카드: 2하트, 8스페이드
    jason카드: 7클로버, K스페이드
    ```

## :rocket: 플레이어턴 진행

### 입력

- [x] 히트 여부를 입력받는다.
- [x] y가 입력되면 히트, 나머지는 스탠드로 간주한다.
    ```
    pobi는 한장의 카드를 더 받겠습니까?(예는 y, 나머지는 아니오)
    y
    ```

### 로직

- [x] 플레이어 핸드의 카드 점수를 계산할 수 있다.
    - ACE 카드 : 버스트 이전에는 11점, 버스트 이후에는 11점으로 간주 (버스트는 21점을 의미)
    - 1 - 10의 카드 : 각각 1 - 10점으로 간주
    - JACK, QUEEN, KING : 10점으로 간주
- [x] 핸드의 카드점수를 바탕으로 히트 여부를 판단할 수 있다.
- [x] 히트를 진행할 경우 플레이어에게 1장의 카드를 추가한다.
- [x] 히트가 가능한 선에서, 플레이어가 원하는만큼 반복적으로 히트 가능하다.

### 출력

- [x] 히트를 진행했을 경우 플레이어의 변경된 핸드를 출력한다.
    ```
    pobi카드: 2하트, 8스페이드, A클로버
    ```

## :rocket: 딜러턴 진행

### 로직

- [x] 딜러 핸드의 카드 점수를 계산할 수 있다.
    - ACE 카드 : 버스트 이전에는 11점, 버스트 이후에는 11점으로 간주 (버스트는 21점을 의미)
    - 1 - 10의 카드 : 각각 1 - 10점으로 간주
    - JACK, QUEEN, KING : 10점으로 간주
- [x] 딜러 핸드의 카드점수가 17이상이 될때까지 반복적으로 딜러에게 카드를 추가한다.

### 출력

- [x] 딜러턴이 완료되면 딜러가 추가로 받은 카드수를 출력한다.
    ```
    딜러는 16이하라 한장의 카드를 더 받았습니다.
    ```

## :rocket: 최종 핸드 출력

### 출력

- [x] 딜러와 플레이어의 최종 핸드를 최종 카드점수와 함께 출력한다.
    ```
    딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
    pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
    jason카드: 7클로버, K스페이드 - 결과: 17
    ```

## :rocket: 최종 승패 계산

### 로직

- [x] 최종 승패를 계산할 수 있다.
    - 플레이어가 버스트일 경우 딜러 승
    - 딜러가 버스트일 경우 플레이어 승
    - 플레이어와 딜러가 버스트일 경우 무
    - 그외에 플레이어와 딜러 중 21에 가까운 쪽이 승
- [x] 딜러의 승패 상태를 계산할 수 있다.

### 출력

- [x] 최종 승패 결과를 출력할 수 있다.
    ```
    ## 최종 승패
    딜러: 1승 1패
    pobi: 승
    jason: 패
    ```

# :dart: 전체 실행 흐름

```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

딜러와 pobi, jason에게 2장을 나누었습니다.
딜러카드: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 승패
딜러: 1승 1패
pobi: 승 
jason: 패
```