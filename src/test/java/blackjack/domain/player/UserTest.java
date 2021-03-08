package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.exception.validateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserTest {
    private static final String TEST_NAME = "pobi";

    @DisplayName("유저는 갖고있는 카드들의 숫자 총 합이 21 이하일 때 선택 가능")
    @Test
    void canDrawCardWhen1() {
        User user = new User(TEST_NAME);
        Card twoCard = Card.valueOf(CardShape.DIAMOND, CardNumber.TWO);
        user.drawCard(twoCard);
        assertThat(user.isCanDraw()).isTrue();
    }

    @DisplayName("유저는 갖고있는 카드들의 숫자 총 합이 21 이하일 때 선택 가능")
    @Test
    void canDrawCardWhen21() {
        User user = new User(TEST_NAME);
        Card sevenCard = Card.valueOf(CardShape.DIAMOND, CardNumber.SEVEN);
        user.drawCard(sevenCard);
        user.drawCard(sevenCard);
        user.drawCard(sevenCard);
        assertThat(user.isCanDraw()).isTrue();
    }

    @DisplayName("유저는 갖고있는 카드들의 숫자 총 합이 21 초과일 때 선택 불가능")
    @Test
    void cannotDrawCardWhen22() {
        User user = new User(TEST_NAME);
        Card sevenCard = Card.valueOf(CardShape.DIAMOND, CardNumber.SEVEN);
        user.drawCard(sevenCard);
        user.drawCard(sevenCard);
        Card eightCard = Card.valueOf(CardShape.DIAMOND, CardNumber.EIGHT);
        user.drawCard(eightCard);
        assertThat(user.isCanDraw()).isFalse();
    }

    @DisplayName("이름 유효성 검사 - 유효한 이름")
    @ParameterizedTest
    @ValueSource(strings = {"딜러", " jason ", " pobi"})
    void validNames(String nameInput) {
        assertThatCode(() -> new User(nameInput))
            .doesNotThrowAnyException();
    }

    @DisplayName("이름 유효성 검사 - 유효하지 않은 이름")
    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void invalidNames(String nameInput) {
        assertThatThrownBy(() -> new User(nameInput))
            .isInstanceOf(validateException.class);
    }

    @DisplayName("카드 계속해서 뽑기 여부 입력 테스트")
    @Test
    void isDrawContinue() {
        User user = new User(TEST_NAME);

        assertThat(user.isDrawContinue("y")).isTrue();
        assertThat(user.isDrawStop()).isFalse();

        assertThat(user.isDrawContinue("n")).isFalse();
        assertThat(user.isDrawStop()).isTrue();

        assertThat(user.isDrawContinue("y")).isTrue();
        assertThat(user.isDrawStop()).isTrue();

        assertThatThrownBy(() -> user.isDrawContinue("a"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("승패 판단 - 승")
    @Test
    void win() {
        Dealer dealer = new Dealer();
        dealer.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.SEVEN));

        User user = new User(TEST_NAME);
        user.drawCard(Card.valueOf(CardShape.CLUB, CardNumber.EIGHT));

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("승패 판단 - 패")
    @Test
    void loss() {
        Dealer dealer = new Dealer();
        dealer.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.SEVEN));

        User user = new User(TEST_NAME);
        user.drawCard(Card.valueOf(CardShape.CLUB, CardNumber.SIX));

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("승패 판단 - 무승부")
    @Test
    void draw() {
        Dealer dealer = new Dealer();
        dealer.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.SEVEN));

        User user = new User(TEST_NAME);
        user.drawCard(Card.valueOf(CardShape.CLUB, CardNumber.SEVEN));

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.DRAW);
    }

    @DisplayName("승패 판단 - 패(21 초과)")
    @Test
    void lossOver21() {
        Dealer dealer = new Dealer();
        dealer.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.SEVEN));

        User user = new User(TEST_NAME);
        user.drawCard(Card.valueOf(CardShape.CLUB, CardNumber.TEN));
        user.drawCard(Card.valueOf(CardShape.CLUB, CardNumber.TEN));
        user.drawCard(Card.valueOf(CardShape.CLUB, CardNumber.TWO));

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("딜러가 블랙잭일때 패, 무승부, 버스트")
    @Test
    void dealerBlackJackUserProfit() {
        Dealer dealer = new Dealer();
        dealer.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.ACE));
        dealer.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.JACK));

        User user = new User(TEST_NAME);
        user.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.ACE));
        user.setBetAmount("10000");
        assertThat(user.profit(dealer)).isEqualTo(-10000);

        user.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.JACK));
        assertThat(user.profit(dealer)).isEqualTo(0);

        user.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.TWO));
        assertThat(user.profit(dealer)).isEqualTo(-10000);
    }

    @DisplayName("딜러가 21일때 패, 블랙잭, 무승부, 버스트")
    @Test
    void dealer21UserProfit() {
        Dealer dealer = new Dealer();
        dealer.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.ACE));
        dealer.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.JACK));
        dealer.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.JACK));

        User user = new User(TEST_NAME);
        user.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.ACE));
        user.setBetAmount("10000");
        assertThat(user.profit(dealer)).isEqualTo(-10000);

        user.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.JACK));
        assertThat(user.profit(dealer)).isEqualTo(15000);

        user.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.JACK));
        assertThat(user.profit(dealer)).isEqualTo(0);

        user.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.JACK));
        assertThat(user.profit(dealer)).isEqualTo(-10000);
    }

    @DisplayName("딜러가 17일때 패, 무승부, 승, 블랙잭, 버스트")
    @Test
    void dealer17UserProfit() {
        Dealer dealer = new Dealer();
        dealer.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.SEVEN));
        dealer.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.JACK));

        User user = new User(TEST_NAME);
        user.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.SEVEN));
        user.setBetAmount("10000");
        assertThat(user.profit(dealer)).isEqualTo(-10000);

        user.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.JACK));
        assertThat(user.profit(dealer)).isEqualTo(0);

        user.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.ACE));
        assertThat(user.profit(dealer)).isEqualTo(10000);

        user.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.THREE));
        assertThat(user.profit(dealer)).isEqualTo(15000);

        user.drawCard(Card.valueOf(CardShape.DIAMOND, CardNumber.JACK));
        assertThat(user.profit(dealer)).isEqualTo(-10000);
    }
}