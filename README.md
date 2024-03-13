# 🃏 우테코 레벨1 - 블랙잭

우테코 레벨 1 블랙잭은 게임에 참여할 사람들을 입력받아 딜러와 대결하는 프로그램 입니다.

☀️ **썬, 눈에 거슬리는 모든 것들을 지적해주세요!**
🌞 **태양을 볼 때 눈이 찌뿌려질 정도 이상으로도 좋아요. 부릅뜨고 보겠습니다.**
🌅 **`step1` 에서도 피드백 덕분에 코드를 많이 갈아엎으며 저만의 철학을 만들 수 있었습니다.**
🟡 **그럼 이번 `step2` 도 파이팅 입니다!!**

---

## 📝 기능 요구 사항

### 😎 플레이어

- [x] 플레이어는 여러명일 수 있다.
- [x] 플레이어 이름은 공백일 수 없다.
- [x] 쉼표 기준으로 이름을 분리할 수 있다.
- [x] 플레이어 수는 최소 1명 이상이다.
- [x] 플레이어 이름은 중복되어선 안된다.

### 🃏 카드

- [x] 숫자 계산은 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A로 구성된다.
- [x] 문양은 스페이드, 다이아몬드, 하트, 클로버로 구성된다.
- [x] A는 1 또는 11로 계산한다.
- [x] J, Q, K는 각각 10으로 계산한다.

### 🎲 게임

- [x] 게임 시작 시 인원 당 받는 덱의 수는 2장이다.
- [x] 덱은 랜덤으로 생성된다.
- 진행 조건
    - [x] 한 플레이어가 더이상 카드를 받지 않거나 21을 초과하면 다음 플레이어로 턴이 넘어간다.
    - [x] 카드의 합이 21을 넘지 않을 경우 얼마든지 카드를 뽑을 수 있다.
    - [x] 딜러의 카드 합이 16이하이면 1장을 추가로 받고, 17이상이면 받지 않는다.
- 종료 조건
    - [x] 플레이어가 bust 된다.
    - [x] 플레이어가 블랙잭이 된다.
    - [x] 플레이어가 N을 입력한다.
- 승패 조건
    - [x] 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다.
    - [x] 플레이어는 자신의 숫자 합이 딜러의 숫자의 합보다 크면 승리한다.
    - [x] 처음 두 장의 카드 합이 21일 경우 블랙잭이 되며 베팅 금액의 1.5 배를 딜러에게 받는다.
    - [x] 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
    - [x] 딜러와 플레이어 모두 블랙직이 아니고, 플레이어 카드 합이 높을 경우 승리해 베팅 금액을 받는다.
    - [x] 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

### 👛 지갑

- [x] 딜러가 소유하고 있으며 플레이어의 이름과 함께 돈을 담아둔다.
- [x] 플레이어의 승패가 결졍난 후 베팅 금액을 통해 수익을 계산한다.
- [x] 플레이어의 수익을 통해 딜러의 수익 또한 계산한다.

---

## 📋 프로젝트 구조

### 📦 패키지

<table>
    <tr>
        <th>Package</th>
        <th>Class</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>
            <img src="https://raw.githubusercontent.com/mallowigi/iconGenerator/master/assets/icons/folders/controllers.svg?sanitize=true"/>
            <b> controller</b>
        </td>
        <td><b>BlackjackController</b></td>
        <td>입력을 받아 계산하고 출력 해주는 전체 진행 담당 컨트롤러</td>
    </tr>
    <tr>
        <td rowspan="7">
            <img src="https://raw.githubusercontent.com/mallowigi/iconGenerator/master/assets/icons/folders/home.svg?sanitize=true"/>
            <b> domain / card</b>
        </td>
        <td><b>Card</b></td>
        <td>모든 트럼프 카드가 있는 상수</td>
    </tr>
    <tr>
        <td><b>CardScore</b></td>
        <td>카드 점수에 대한 상수</td>
    </tr>
    <tr>
        <td><b>CardSuit</b></td>
        <td>카드 문양에 대한 상수</td>
    </tr>
    <tr>
        <td><b>Deck</b></td>
        <td>모든 카드를 한장 씩 가지며 뽑아쓰는 일급 컬렉션</td>
    </tr>
    <tr>
        <td><b>Hand</b></td>
        <td>딜러와 플레이어의 손 안에 있는 패를 의미하는 있는 일글 컬렉션</td>
    </tr>
    <tr>
        <td><b>RandomShuffleStrategy</b></td>
        <td>카드 덱을 랜덤으로 섞어 줄 구현체</td>
    </tr>
    <tr>
        <td><b>ShuffleStrategy</b></td>
        <td>랜덤하게 섞는 것에 대한 전략 인터페이스</td>
    </tr>
    <tr>
        <td rowspan="4">
            <img src="https://raw.githubusercontent.com/mallowigi/iconGenerator/master/assets/icons/folders/home.svg?sanitize=true"/>
            <b> domain / gamer</b>
        </td>
        <td><b>Dealer</b></td>
        <td>딜러 클래스</td>
    </tr>
    <tr>
        <td><b>Gamer</b></td>
        <td>게임 참여자 추상 클래스</td>
    </tr>
    <tr>
        <td><b>Player</b></td>
        <td>플레이어 클래스</td>
    </tr>
    <tr>
        <td><b>Players</b></td>
        <td>참여하는 모든 플레이어를 가지는 일급컬렉션</td>
    </tr>
    <tr>
        <td rowspan="3">
            <img src="https://raw.githubusercontent.com/mallowigi/iconGenerator/master/assets/icons/folders/home.svg?sanitize=true"/>
            <b> domain / money</b>
        </td>
        <td><b>Money</b></td>
        <td>사용자의 베팅 금액을 저장해두는 클래스</td>
    </tr>
    <tr>
        <td><b>MoneyRate</b></td>
        <td>게임 결과에 따라 금액에 곱해지는 비율 상수</td>
    </tr>
    <tr>
        <td><b>Wallet</b></td>
        <td>딜러가 플레이어의 베팅 금액을 가지고 있을 클래스</td>
    </tr>
    <tr>
        <td rowspan="5">
            <img src="https://raw.githubusercontent.com/mallowigi/iconGenerator/master/assets/icons/folders/views.svg?sanitize=true"/>
            <b> view</b>
        </td>
        <td><b>CardScoreName</b></td>
        <td>카드 점수를 출력 문구로 변환해주는 상수</td>
    </tr>
    <tr>
        <td><b>CardSymbolName</b></td>
        <td>카드 문양을 출력 문구로 변환해주는 상수</td>
    </tr>
    <tr>
        <td><b>InputView</b></td>
        <td>전체적인 출력을 담당하는 뷰</td>
    </tr>
    <tr>
        <td><b>OutputView</b></td>
        <td>전체적인 입력을 담당하는 뷰</td>
    </tr>
    <tr>
        <td><b>PlayerCommand</b></td>
        <td>사용자의 입력 명령어 상수</td>
    </tr>
</table>

---

## 🎓 `step1` 을 통해 배운 것 들

### 1. 스트림과 복사

`List.copyOf(...)` 를 사용하면 내부적으로 복사를 하지만 불변객체로 만들어 버리기 때문에 카드 덱으로 사용하기 어려운 점이 있었습니다.

이로 인해 가변 리스트로 바꾸는 방법으로 `new ArrayList<>(...)` 을 사용했었습니다.

하지만 아래 코드를 통해 스트림의 `.collect()` 함수로 바로 가변 객체로 만들 수 있음을 알게 되었습니다.

``` java
Arrays.stream(Card.values())
    .collect(Collectors.toList());
```

### 2. 컨벤션을 위해 불필요한 컨트롤러 생성

기존에 `indent 1` 이라는 컨벤션을 지키기 위해 불필요한 `CommandController` 를 만들었고 `body` 가 비어있는 `while` 문, 항상 `flase` 를 리턴하는 함수 등을 작성하였습니다.

이로 인해 무한 반복이 될 것 같은 위험 요소로 보이기도 하여서 이럴 떄는 꼭 컨벤션을 위해 이렇게 까지 할 필요 없겠다고 생각되었습니다.

**Before**

``` java
while (commandController.runUntilCanHit(InputView.readPlayerCommand(player.getName()))) ;
```

**After**

``` java
private void requestHitOrStand(Player player) {
    PlayerCommand playerCommand;
    do {
        playerCommand = requestUntilValid(() ->
                PlayerCommand.from(InputView.readPlayerCommand(player.getName())));

        if (playerCommand == PlayerCommand.STAND) {
            printIfPlayerOnlyDeal(player);
            break;
        }
        hitAndPrint(player);
    } while (player.canContinue());
}
```

### 3. `Wildcard Import` 피하기

아래와 같이 3개 이상 같은 클래스나 상수를 가져올 경우 자동으로 와일드카드로 바뀌게 되었는데 `Google Style Guide` 에서도 컨벤션으로 명시되어 있으며 이에 따라 `Inteliij` 에서의 자동 변환
기능을 중지하였습니다.

- 같은 이름을 가진 다른 패키지의 클래스가 존재할 경우 충돌 우려
- 협업시에 어떤 클래스가 실제로 `import` 되어있는지 파악하기 어려움

``` java
import static blackjack.domain.card.CardScore.*;
import static blackjack.domain.card.CardSymbol.*;
```

### 4. `BeforeEach` `AfterEach` 피하기

테스트의 독립성을 지키기 위해 이는 테스트 코드에서 `BeforeEach` `AfterEach` 사용은 왠만하면 피하는 것이 좋을 것 같다고 학습하였습니다.

- 테스트 케이스가 어떤 순서로 실행되는지 동작에 대한 이해에 혼란을 겪을 수 있으므로 결국 디버깅이 어려워 질 수도 있음

---

## 🎯 `step2` 에 도전해 보았던 것들

### 1. 랜덤 전략

이젠 `racingcar` 과제에서 랜덤성에 대해 공부를 많이 하였는데 결국 인터페이스를 활용한 전략패턴으로 랜덤을 부여하는 동작을 주입하는 것으로 생각이 정리되었습니다.

`step1` 에서는 `cards` 라는 객체가 있었고 테스트 코드에서는 해당 객체를 `new` 로 생성할 때 `Method Override` 를 통한 카드 주입 방법을 사용했었습니다.

해당 방법으 통해 어떠한 로직도 건드리지 않고 단순 테스트 코드에만 작성을 할 수 있었는데요, 이 방법도 결국 `step2` 에서 각 플레이어 마다 다른 카드를 주는 것에 한계를 느끼게 되었습니다.

``` java
public interface ShuffleStrategy {
    List<Card> shuffle(List<Card> cards);
}

// Production
public Deck(ShuffleStrategy shuffleStrategy) {
    cards = new ArrayList<>(shuffleStrategy.shuffle(Card.getAll()));
}

// Test
Deck deck = new Deck(cards -> List.of(
    new Card(CLUB, ACE),
    new Card(CLUB, FIVE)
));
```

위 방법을 통해 테스트 코드를 위해 따로 `ShuffleStrategy` 에 대한 고정된 카드 반환 역할을 하는 구현체를 만들지 않고도 생성자 호출 시점에서 람다를 넘겨 카드를 주입할 수 있다는 장점이 있었습니다!

딜러, 플레이어1, 플레이어2 모두 같은 `Deck` 을 공유하며 카드를 뽑기 때문에 이렇게 `Deck` 을 만들어 카드 7장 정도를 넣어두면 순서대로 원하는 카드를 뽑도록 할 수 있어서 테스트에도 용이하였습니다.

### 2. 역할 재구성

`step1` 에서는 컨트롤러가 `Deck` 을 들고 `Dealer` 와 `Players` 에게 카드를 주는 식으로 구성하였습니다.

메세지 관점으로 보았을 때

- 플레이어는 베팅할 때 딜러에게 전달한다.
- 플레이어는 자신의 패의 점수를 계산한다.
- 각자의 패는 카드 덱에서 한 장을 뽑아 가져온다.
- 딜러는 플레이어의 패를 보고 수익을 계산한다.

등으로 프로젝트 구조가 많이 수정되게 되었고 제일 큰 변화로는 최종 컨트롤러에서의 시작점이 아래 코드와 같이 `Deck, Dealer, Players` 를 생성하고 `베팅 > 첫카드 > 게임진행 > 결과출력`
과정에서
게임에 있어서 메인 커뮤니케이션을 하는 객체들인 `Dealer` 와 `Players` 간의 협력이 잘 드러났다고 볼 수 있었습니다.

``` java
public void run() {
    Deck deck = createDeckWithRandomShuffle();
    Dealer dealer = new Dealer(deck);
    Players players = requestPlayers(deck);

    requestBetting(dealer, players);
    processDeal(dealer, players);
    processHitOrStand(dealer, players);
    printResult(dealer, players);
}
```