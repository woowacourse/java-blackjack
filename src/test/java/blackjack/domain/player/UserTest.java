package blackjack.domain.player;

import static blackjack.domain.player.UsersTest.POBI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.ResultType;
import blackjack.domain.UserDrawContinue;
import blackjack.domain.card.Card;
import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserTest {
    private static final int MIN_BETTING_MONEY_BOUND = 1_000;
    protected static final int MAX_BETTING_MONEY_BOUND = 2_000_000_000;

    @DisplayName("유저를 이름만으로 생성한 경우")
    @Test
    void onlyNameConstructor() {
        assertThatCode(() -> new User(POBI))
            .doesNotThrowAnyException();
    }

    @DisplayName("배팅 금액이 1000원 이상, 20억 이하이면, 배팅 가능")
    @Test
    void validBetting() {
        assertThatCode(() -> new User(POBI, MAX_BETTING_MONEY_BOUND))
            .doesNotThrowAnyException();
        assertThatCode(() -> new User(POBI, MIN_BETTING_MONEY_BOUND))
            .doesNotThrowAnyException();
    }

    @DisplayName("배팅 금액이 1000원 미만이면, 예외 발생")
    @Test
    void underBettingBoundException() {
        assertThatThrownBy(() -> new User(POBI, MIN_BETTING_MONEY_BOUND - 1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액이 20억 초과이면, 예외 발생")
    @Test
    void overBettingBoundException() {
        assertThatThrownBy(() -> new User(POBI, MAX_BETTING_MONEY_BOUND + 1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유저는 갖고있는 카드들의 숫자 총 합이 21 이하일 때 isCanDraw() true")
    @Test
    void canDrawCardWhen1() {
        User user = new User(POBI);
        Card twoCard = new Card(CardShapeType.DIAMOND, CardNumberType.TWO);
        user.drawOneCard(twoCard);
        assertThat(user.isCanDraw()).isTrue();
    }

    @DisplayName("유저는 갖고있는 카드들의 숫자 총 합이 21 이하일 때 isCanDraw() true")
    @Test
    void canDrawCardWhen21() {
        User user = new User(POBI);
        Card sevenCard = new Card(CardShapeType.DIAMOND, CardNumberType.SEVEN);
        user.drawOneCard(sevenCard);
        user.drawOneCard(sevenCard);
        user.drawOneCard(sevenCard);
        assertThat(user.isCanDraw()).isTrue();
    }

    @DisplayName("유저는 갖고있는 카드들의 숫자 총 합이 21 초과일 때 isCanDraw() false")
    @Test
    void cannotDrawCardWhen22() {
        User user = new User(POBI);
        Card sevenCard = new Card(CardShapeType.DIAMOND, CardNumberType.SEVEN);
        user.drawOneCard(sevenCard);
        user.drawOneCard(sevenCard);
        Card eightCard = new Card(CardShapeType.DIAMOND, CardNumberType.EIGHT);
        user.drawOneCard(eightCard);
        assertThat(user.isCanDraw()).isFalse();
    }

    @DisplayName("이름 유효성 검사 - 유효한 이름")
    @ParameterizedTest
    @ValueSource(strings = {"딜러", " jason ", " pobi"})
    void validNames(String nameInput) {
        assertThatCode(() ->
            assertThat(new User(nameInput).getName()).isEqualTo(new Name(nameInput))
        )
            .doesNotThrowAnyException();
    }

    @DisplayName("이름 유효성 검사 - 유효하지 않은 이름")
    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void invalidNames(String nameInput) {
        assertThatThrownBy(() -> new User(nameInput))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름 유효성 검사 - null 입력")
    @Test
    void nullName() {
        assertThatThrownBy(() -> new User(null))
            .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("카드 계속해서 뽑기 여부 입력 테스트")
    @Test
    void isDrawContinue() {
        User user = new User(POBI);
        UserDrawContinue userDrawContinue = new UserDrawContinue("y");
        UserDrawContinue userDrawNotContinue = new UserDrawContinue("n");

        assertThat(user.isDrawContinue(userDrawContinue)).isTrue();
        assertThat(user.isDrawStop()).isFalse();

        assertThat(user.isDrawContinue(userDrawNotContinue)).isFalse();
        assertThat(user.isDrawStop()).isTrue();
    }

    @DisplayName("승패 판단 - 딜러가 bust, 유저가 bust 일 때 = 패")
    @Test
    void dealerBustUserBust() {
        Dealer dealer = new Dealer(); // bust
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.JACK));
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.THREE));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.NINE));
        assertThat(dealer.isBlackJack()).isFalse();
        assertThat(dealer.isBust()).isTrue();
        assertThat(dealer.getScore()).isEqualTo(22);

        User user = new User(POBI); // bust
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.KING));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.FOUR));
        user.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isTrue();
        assertThat(user.getScore()).isEqualTo(22);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("승패 판단 - 딜러가 bust, 유저가 블랙잭 일 때 = 승")
    @Test
    void dealerBustUserBlackJack() {
        Dealer dealer = new Dealer(); // bust
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.JACK));
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.THREE));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.NINE));
        assertThat(dealer.isBlackJack()).isFalse();
        assertThat(dealer.isBust()).isTrue();
        assertThat(dealer.getScore()).isEqualTo(22);

        User user = new User(POBI); // 블랙잭
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.KING));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.ACE));
        assertThat(user.isBlackJack()).isTrue();
        assertThat(user.isBust()).isFalse();
        assertThat(user.getScore()).isEqualTo(21);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("승패 판단 - 딜러가 bust, 유저가 블랙잭이 아닌 21 일 때 = 승")
    @Test
    void dealerBustUserNotBlackJack21() {
        Dealer dealer = new Dealer(); // bust
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.JACK));
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.THREE));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.NINE));
        assertThat(dealer.isBlackJack()).isFalse();
        assertThat(dealer.isBust()).isTrue();
        assertThat(dealer.getScore()).isEqualTo(22);

        User user = new User(POBI); // 블랙잭이 아닌 21
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.TEN));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.FIVE));
        user.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SIX));
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
        assertThat(user.getScore()).isEqualTo(21);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("승패 판단 - 딜러가 bust, 유저가 21 미만일 때 = 승")
    @Test
    void dealerBustUserUnder21() {
        Dealer dealer = new Dealer(); // bust
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.JACK));
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.THREE));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.NINE));
        assertThat(dealer.isBlackJack()).isFalse();
        assertThat(dealer.isBust()).isTrue();
        assertThat(dealer.getScore()).isEqualTo(22);

        User user = new User(POBI); // 20
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.TEN));
        user.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.FOUR));
        user.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SIX));
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
        assertThat(user.getScore()).isEqualTo(20);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("승패 판단 - 딜러가 블랙잭, 유저가 bust 일 때 = 패")
    @Test
    void dealerBlackJackUserBust() {
        Dealer dealer = new Dealer(); // 블랙잭
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.JACK));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.ACE));
        assertThat(dealer.isBlackJack()).isTrue();
        assertThat(dealer.isBust()).isFalse();
        assertThat(dealer.getScore()).isEqualTo(21);

        User user = new User(POBI); // bust
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.KING));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.FOUR));
        user.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isTrue();
        assertThat(user.getScore()).isEqualTo(22);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("승패 판단 - 딜러가 블랙잭, 유저가 블랙잭 일 때 = 무승부")
    @Test
    void dealerBlackJackUserBlackJack() {
        Dealer dealer = new Dealer(); // 블랙잭
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.JACK));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.ACE));
        assertThat(dealer.isBlackJack()).isTrue();
        assertThat(dealer.isBust()).isFalse();
        assertThat(dealer.getScore()).isEqualTo(21);

        User user = new User(POBI); // 블랙잭
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.KING));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.ACE));
        assertThat(user.isBlackJack()).isTrue();
        assertThat(user.isBust()).isFalse();
        assertThat(user.getScore()).isEqualTo(21);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.DRAW);
    }

    @DisplayName("승패 판단 - 딜러가 블랙잭, 유저가 블랙잭이 아닌 21 일 때 = 패")
    @Test
    void dealerBlackJackUserNotBlackJack21() {
        Dealer dealer = new Dealer(); // 블랙잭
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.JACK));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.ACE));
        assertThat(dealer.isBlackJack()).isTrue();
        assertThat(dealer.isBust()).isFalse();
        assertThat(dealer.getScore()).isEqualTo(21);

        User user = new User(POBI); // 블랙잭이 아닌 21
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.TEN));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.FIVE));
        user.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SIX));
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
        assertThat(user.getScore()).isEqualTo(21);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("승패 판단 - 딜러가 블랙잭, 유저가 21 미만일 때 = 패")
    @Test
    void dealerBlackJackUserUnder21() {
        Dealer dealer = new Dealer(); // 블랙잭
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.JACK));
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.ACE));
        assertThat(dealer.isBlackJack()).isTrue();
        assertThat(dealer.isBust()).isFalse();
        assertThat(dealer.getScore()).isEqualTo(21);

        User user = new User(POBI); // 20
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.TEN));
        user.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.FOUR));
        user.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.SIX));
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
        assertThat(user.getScore()).isEqualTo(20);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("승패 판단 - 딜러가 블랙잭이 아닌 21, 유저가 bust일 때 = 패")
    @Test
    void dealerNotBlackJack21UserBust() {
        Dealer dealer = new Dealer(); // 블랙잭이 아닌 21
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.SEVEN));
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.SIX));
        assertThat(dealer.isBlackJack()).isFalse();
        assertThat(dealer.isBust()).isFalse();
        assertThat(dealer.getScore()).isEqualTo(21);

        User user = new User(POBI); // bust
        user.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.QUEEN));
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.TWO));
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.TEN));
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isTrue();
        assertThat(user.getScore()).isEqualTo(22);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("승패 판단 - 딜러가 블랙잭이 아닌 21, 유저가 블랙잭일 때 = 승")
    @Test
    void dealerNotBlackJack21UserBlackJack() {
        Dealer dealer = new Dealer(); // 블랙잭이 아닌 21
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.SEVEN));
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.SIX));
        assertThat(dealer.isBlackJack()).isFalse();
        assertThat(dealer.isBust()).isFalse();
        assertThat(dealer.getScore()).isEqualTo(21);

        User user = new User(POBI); // 블랙잭
        user.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.QUEEN));
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.ACE));
        assertThat(user.isBlackJack()).isTrue();
        assertThat(user.isBust()).isFalse();
        assertThat(user.getScore()).isEqualTo(21);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("승패 판단 - 딜러가 블랙잭이 아닌 21, 유저가 블랙잭이 아닌 21일 때 = 무승부")
    @Test
    void dealerNotBlackJack21UserNotBlackJack21() {
        Dealer dealer = new Dealer(); // 블랙잭이 아닌 21
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.SEVEN));
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.SIX));
        assertThat(dealer.isBlackJack()).isFalse();
        assertThat(dealer.isBust()).isFalse();
        assertThat(dealer.getScore()).isEqualTo(21);

        User user = new User(POBI); // 블랙잭이 아닌 21
        user.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.TEN));
        user.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.FIVE));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.SIX));
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
        assertThat(user.getScore()).isEqualTo(21);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.DRAW);
    }

    @DisplayName("승패 판단 - 딜러가 블랙잭이 아닌 21, 유저가 21미만일 때 = 패")
    @Test
    void dealerNotBlackJack21UserUnder21() {
        Dealer dealer = new Dealer(); // 블랙잭이 아닌 21
        dealer.drawOneCard(new Card(CardShapeType.SPADE, CardNumberType.EIGHT));
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.SEVEN));
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.SIX));
        assertThat(dealer.isBlackJack()).isFalse();
        assertThat(dealer.isBust()).isFalse();
        assertThat(dealer.getScore()).isEqualTo(21);

        User user = new User(POBI); // 20
        user.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.TEN));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.TEN));
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
        assertThat(user.getScore()).isEqualTo(20);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("승패 판단 - 딜러가 20, 유저가 bust일 때 = 패")
    @Test
    void dealer20UserBust() {
        Dealer dealer = new Dealer(); // 20
        dealer.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.FOUR));
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.EIGHT));
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.EIGHT));
        assertThat(dealer.isBlackJack()).isFalse();
        assertThat(dealer.isBust()).isFalse();
        assertThat(dealer.getScore()).isEqualTo(20);

        User user = new User(POBI); // bust
        user.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.KING));
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.TWO));
        user.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.TEN));
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isTrue();
        assertThat(user.getScore()).isEqualTo(22);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("승패 판단 - 딜러가 20, 유저가 블랙잭일 때 = 승")
    @Test
    void dealer20UserBlackJack() {
        Dealer dealer = new Dealer(); // 20
        dealer.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.THREE));
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.SEVEN));
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.TEN));
        assertThat(dealer.isBlackJack()).isFalse();
        assertThat(dealer.isBust()).isFalse();
        assertThat(dealer.getScore()).isEqualTo(20);

        User user = new User(POBI); // 블랙잭
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.JACK));
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.ACE));
        assertThat(user.isBlackJack()).isTrue();
        assertThat(user.isBust()).isFalse();
        assertThat(user.getScore()).isEqualTo(21);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("승패 판단 - 딜러가 20, 유저가 블랙잭이 아닌 21일 때 = 승")
    @Test
    void dealer20UserNotBlackJack21() {
        Dealer dealer = new Dealer(); // 20
        dealer.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.ACE));
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.TWO));
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.SEVEN));
        assertThat(dealer.isBlackJack()).isFalse();
        assertThat(dealer.isBust()).isFalse();
        assertThat(dealer.getScore()).isEqualTo(20);

        User user = new User(POBI); // 블랙잭이 아닌 21
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.ACE));
        user.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.ACE));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.EIGHT));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.JACK));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.ACE));
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
        assertThat(user.getScore()).isEqualTo(21);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("승패 판단 - 딜러가 20, 유저가 20일 때 = 무승부")
    @Test
    void dealer20User20() {
        Dealer dealer = new Dealer(); // 20
        dealer.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.ACE));
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.TWO));
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.SEVEN));
        assertThat(dealer.isBlackJack()).isFalse();
        assertThat(dealer.isBust()).isFalse();
        assertThat(dealer.getScore()).isEqualTo(20);

        User user = new User(POBI); // 20
        user.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.JACK));
        user.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.QUEEN));
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
        assertThat(user.getScore()).isEqualTo(20);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.DRAW);
    }

    @DisplayName("승패 판단 - 딜러가 20, 유저가 19일 때 = 패")
    @Test
    void dealer20User19() {
        Dealer dealer = new Dealer(); // 20
        dealer.drawOneCard(new Card(CardShapeType.HEART, CardNumberType.ACE));
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.TWO));
        dealer.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.SEVEN));
        assertThat(dealer.isBlackJack()).isFalse();
        assertThat(dealer.isBust()).isFalse();
        assertThat(dealer.getScore()).isEqualTo(20);

        User user = new User(POBI); // 19
        user.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.JACK));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.NINE));
        assertThat(user.isBlackJack()).isFalse();
        assertThat(user.isBust()).isFalse();
        assertThat(user.getScore()).isEqualTo(19);

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("유저가 지면, 배팅한 금액을 딜러에게 준다.")
    @ParameterizedTest
    @ValueSource(ints = {MIN_BETTING_MONEY_BOUND, 50000, 1234567, MAX_BETTING_MONEY_BOUND})
    void userLossProfit(int bettingMoney) {
        Dealer dealer = new Dealer();
        User user = new User(POBI, bettingMoney);
        user.applyResult(ResultType.LOSS, dealer);

        assertThat(user.getProfit()).isEqualTo(-bettingMoney);
        assertThat(dealer.getProfit()).isEqualTo(bettingMoney);
    }

    @DisplayName("비기면, 돈의 이동은 일어나지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {MIN_BETTING_MONEY_BOUND, 50000, 1234567, MAX_BETTING_MONEY_BOUND})
    void drawProfit(int bettingMoney) {
        Dealer dealer = new Dealer();
        User user = new User(POBI, bettingMoney);
        user.applyResult(ResultType.DRAW, dealer);

        assertThat(user.getProfit()).isEqualTo(0);
        assertThat(dealer.getProfit()).isEqualTo(0);
    }
}
