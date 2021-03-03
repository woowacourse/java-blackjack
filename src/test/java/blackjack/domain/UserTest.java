package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserTest {
    @DisplayName("A 1개 판단 테스트")
    @Test
    void aceOne() {
        User user = new User();
        Card card = new Card(CardShape.DIAMOND, CardNumber.ACE);
        user.drawCard(card);
        assertThat(user.getValue()).isEqualTo(11);
        card = new Card(CardShape.DIAMOND, CardNumber.KING);
        user.drawCard(card);
        user.drawCard(card);
        assertThat(user.getValue()).isEqualTo(21);
    }

    @DisplayName("A 2개 판단 테스트")
    @Test
    void aceTwo() {
        User user = new User();
        Card card = new Card(CardShape.DIAMOND, CardNumber.ACE);
        user.drawCard(card);
        user.drawCard(card);
        assertThat(user.getValue()).isEqualTo(12);
    }

    @DisplayName("A 3개 판단 테스트")
    @Test
    void aceThree() {
        User user = new User();
        Card card = new Card(CardShape.DIAMOND, CardNumber.ACE);
        user.drawCard(card);
        user.drawCard(card);
        user.drawCard(card);
        assertThat(user.getValue()).isEqualTo(13);
    }

    @DisplayName("플레이어는 갖고있는 카드들의 숫자 총 합이 21 이하일 때 선택 가능")
    @Test
    void canDrawCard1() {
        User user = new User();
        Card twoCard = new Card(CardShape.DIAMOND, CardNumber.TWO);
        user.drawCard(twoCard);
        assertThat(user.isCanDraw()).isTrue();
    }

    @DisplayName("플레이어는 갖고있는 카드들의 숫자 총 합이 21 이하일 때 선택 가능")
    @Test
    void canDrawCard21() {
        User user = new User();
        Card sevenCard = new Card(CardShape.DIAMOND, CardNumber.SEVEN);
        user.drawCard(sevenCard);
        user.drawCard(sevenCard);
        user.drawCard(sevenCard);
        assertThat(user.isCanDraw()).isTrue();
    }

    @DisplayName("플레이어는 갖고있는 카드들의 숫자 총 합이 21 초과일 때 선택 불가능")
    @Test
    void cannotDrawCard22() {
        User user = new User();
        Card sevenCard = new Card(CardShape.DIAMOND, CardNumber.SEVEN);
        user.drawCard(sevenCard);
        user.drawCard(sevenCard);
        Card eightCard = new Card(CardShape.DIAMOND, CardNumber.EIGHT);
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
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("승패 판단 - 승")
    @Test
    void win() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card(CardShape.DIAMOND, CardNumber.SEVEN));

        User user = new User();
        user.drawCard(new Card(CardShape.CLUB, CardNumber.EIGHT));

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("승패 판단 - 패")
    @Test
    void loss() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card(CardShape.DIAMOND, CardNumber.SEVEN));

        User user = new User();
        user.drawCard(new Card(CardShape.CLUB, CardNumber.SIX));

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }

    @DisplayName("승패 판단 - 무승부")
    @Test
    void draw() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card(CardShape.DIAMOND, CardNumber.SEVEN));

        User user = new User();
        user.drawCard(new Card(CardShape.CLUB, CardNumber.SEVEN));

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.DRAW);
    }

    @DisplayName("승패 판단 - 패(21 초과)")
    @Test
    void lossOver21() {
        Dealer dealer = new Dealer();
        dealer.drawCard(new Card(CardShape.DIAMOND, CardNumber.SEVEN));

        User user = new User();
        user.drawCard(new Card(CardShape.CLUB, CardNumber.TEN));
        user.drawCard(new Card(CardShape.CLUB, CardNumber.TEN));
        user.drawCard(new Card(CardShape.CLUB, CardNumber.TWO));

        ResultType resultType = user.getResult(dealer);
        assertThat(resultType).isEqualTo(ResultType.LOSS);
    }
}
