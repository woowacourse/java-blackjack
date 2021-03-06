package blackjack.domain.player;

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
    private static final String TEST_NAME = "pobi";

    @DisplayName("유저는 갖고있는 카드들의 숫자 총 합이 21 이하일 때 선택 가능")
    @Test
    void canDrawCardWhen1() {
        User user = new User(TEST_NAME);
        Card twoCard = new Card(CardShapeType.DIAMOND, CardNumberType.TWO);
        user.drawOneCard(twoCard);
        assertThat(user.isCanDraw()).isTrue();
    }

    @DisplayName("유저는 갖고있는 카드들의 숫자 총 합이 21 이하일 때 선택 가능")
    @Test
    void canDrawCardWhen21() {
        User user = new User(TEST_NAME);
        Card sevenCard = new Card(CardShapeType.DIAMOND, CardNumberType.SEVEN);
        user.drawOneCard(sevenCard);
        user.drawOneCard(sevenCard);
        user.drawOneCard(sevenCard);
        assertThat(user.isCanDraw()).isTrue();
    }

    @DisplayName("유저는 갖고있는 카드들의 숫자 총 합이 21 초과일 때 선택 불가능")
    @Test
    void cannotDrawCardWhen22() {
        User user = new User(TEST_NAME);
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
        assertThatCode(() -> new User(nameInput))
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
        User user = new User(TEST_NAME);
        UserDrawContinue userDrawContinue = new UserDrawContinue("y");
        UserDrawContinue userDrawNotContinue = new UserDrawContinue("n");

        assertThat(user.isDrawContinue(userDrawContinue)).isTrue();
        assertThat(user.isDrawStop()).isFalse();

        assertThat(user.isDrawContinue(userDrawNotContinue)).isFalse();
        assertThat(user.isDrawStop()).isTrue();
    }

    @DisplayName("승패 판단 - 승")
    @Test
    void win() {
        Dealer dealer = new Dealer();
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.SEVEN));

        User user = new User(TEST_NAME);
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.EIGHT));

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("승패 판단 - 패")
    @Test
    void loss() {
        Dealer dealer = new Dealer();
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.SEVEN));

        User user = new User(TEST_NAME);
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.SIX));

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("승패 판단 - 무승부")
    @Test
    void draw() {
        Dealer dealer = new Dealer();
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.SEVEN));

        User user = new User(TEST_NAME);
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.SEVEN));

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.DRAW);
    }

    @DisplayName("승패 판단 - 패(21 초과)")
    @Test
    void lossOver21() {
        Dealer dealer = new Dealer();
        dealer.drawOneCard(new Card(CardShapeType.DIAMOND, CardNumberType.SEVEN));

        User user = new User(TEST_NAME);
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.TEN));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.TEN));
        user.drawOneCard(new Card(CardShapeType.CLUB, CardNumberType.TWO));

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }
}
