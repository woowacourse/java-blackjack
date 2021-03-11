package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThat(!user.isFinished()).isTrue();
    }

    @DisplayName("유저는 블랙잭일때 추가적인 뽑기 불가")
    @Test
    void canDrawCardWhenBlackjack() {
        Card aceCard = Card.valueOf(CardShape.DIAMOND, CardNumber.ACE);
        Card tenCard = Card.valueOf(CardShape.DIAMOND, CardNumber.TEN);
        User user = new User(TEST_NAME, aceCard, tenCard);
        assertThat(!user.isFinished()).isFalse();
    }

    @DisplayName("유저는 갖고있는 카드들의 숫자 총 합이 블랙잭이 아니고 21 이하일 때 선택 가능")
    @Test
    void canDrawCardWhen21() {
        Card sevenCard = Card.valueOf(CardShape.DIAMOND, CardNumber.SEVEN);
        User user = new User(TEST_NAME, sevenCard, sevenCard);
        user.drawCard(sevenCard);
        assertThat(!user.isFinished()).isTrue();
    }

    @DisplayName("유저는 갖고있는 카드들의 숫자 총 합이 21 초과일 때 선택 불가능")
    @Test
    void cannotDrawCardWhen22() {
        Card sevenCard = Card.valueOf(CardShape.DIAMOND, CardNumber.SEVEN);
        User user = new User(TEST_NAME, sevenCard, sevenCard);
        Card eightCard = Card.valueOf(CardShape.DIAMOND, CardNumber.EIGHT);
        user.drawCard(eightCard);
        assertThat(!user.isFinished()).isFalse();
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
        Card aceCard = Card.valueOf(CardShape.DIAMOND, CardNumber.ACE);
        User user = new User(TEST_NAME, aceCard, aceCard);

        assertThat(user.isDrawContinue("y")).isTrue();
        assertThat(user.isFinished()).isFalse();

        assertThat(user.isDrawContinue("n")).isFalse();
        assertThat(user.isFinished()).isTrue();

        assertThat(user.isDrawContinue("y")).isTrue();
        assertThat(user.isFinished()).isTrue();

        assertThatThrownBy(() -> user.isDrawContinue("a"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}