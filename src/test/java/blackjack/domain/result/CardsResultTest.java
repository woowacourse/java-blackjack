package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardsResultTest {

    @DisplayName("score나 count가 0이하인 경우 예외처리")
    @ParameterizedTest
    @CsvSource(value = {"0, 1", "1, 0", "0, 0", "-1, 1", "1, -1", "-1, -1"})
    void isNotNative(int score, int count) {
        assertThatThrownBy(() -> new CardsResult(score, false, count))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("1");
    }

    @DisplayName("카드 점수가 21을 넘는지 확인")
    @Test
    void isOverBlackJack() {
        assertThat((new CardsResult(21)).isBust()).isFalse();
        assertThat((new CardsResult(22)).isBust()).isTrue();
    }

    @DisplayName("카드가 블랙잭인지 확인")
    @Test
    void isBlackJack() {
        assertThat((new CardsResult(21, false, 2)).isBlackJack()).isTrue();
        assertThat((new CardsResult(11, true, 2)).isBlackJack()).isTrue();

        assertThat((new CardsResult(21)).isBlackJack()).isFalse();
        assertThat((new CardsResult(21)).isBlackJack()).isFalse();
        assertThat((new CardsResult(20, false, 2)).isBlackJack()).isFalse();
        assertThat((new CardsResult(22, false, 2)).isBlackJack()).isFalse();
    }

    @DisplayName("equals 확인")
    @Test
    void isSame() {
        CardsResult cardsResultBlackJack = new CardsResult(21, false, 2);
        CardsResult cardsResultBlackJackToo = new CardsResult(21, false, 2);
        CardsResult cardsResultBust = new CardsResult(22);
        CardsResult cardsResultBustToo = new CardsResult(22);
        CardsResult cardsResultTwentyOne = new CardsResult(21);

        assertThat(cardsResultBlackJack.equals(cardsResultBlackJackToo)).isTrue();
        assertThat(cardsResultBust.equals(cardsResultBustToo)).isTrue();
        assertThat(cardsResultBlackJack.equals(cardsResultBust)).isFalse();
        assertThat(cardsResultBlackJack.equals(cardsResultTwentyOne)).isFalse();
    }

    @DisplayName("Over확인")
    @Test
    void isOverScore() {
        CardsResult cardsResultTwentyTwo = new CardsResult(22);
        CardsResult cardsResultTwentyOne = new CardsResult(21);

        assertThat(cardsResultTwentyTwo.isMoreThanScore(cardsResultTwentyTwo)).isFalse();
        assertThat(cardsResultTwentyTwo.isMoreThanScore(cardsResultTwentyOne)).isTrue();

        assertThat(cardsResultTwentyOne.isMoreThanScore(cardsResultTwentyTwo)).isFalse();
        assertThat(cardsResultTwentyOne.isMoreThanScore(cardsResultTwentyOne)).isFalse();
    }
}
