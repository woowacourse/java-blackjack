package blackjack.domain;

import static blackjack.domain.CardNumber.A;
import static blackjack.domain.CardNumber.SEVEN;
import static blackjack.domain.CardNumber.TEN;
import static blackjack.domain.CardNumber.THREE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardNumberTest {

    @Test
    @DisplayName("카드 숫자 13개를 반환할 수 있다.")
    void cardNumbers() {
        final List<CardNumber> cardNumbers = CardNumber.cardNumbers();
        final int distinctCount = (int) cardNumbers.stream()
                .distinct()
                .count();
        assertThat(distinctCount).isEqualTo(13);
    }

    @Test
    @DisplayName("3 7 10은 스코어가 20이다.")
    void calculateScore_3_7_10() {
        final List<CardNumber> cardNumbers = Arrays.asList(THREE, SEVEN, TEN);
        final int result = CardNumber.calculateScore(cardNumbers);
        assertThat(result).isEqualTo(20);
    }

    @Test
    @DisplayName("a 10은 스코어가 21이다.")
    void calculateScore_A_10() {
        final List<CardNumber> cardNumbers = Arrays.asList(A, TEN);
        final int result = CardNumber.calculateScore(cardNumbers);
        assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("a a 10은 스코어가 12이다.")
    void calculateScore_A_A_10() {
        final List<CardNumber> cardNumbers = Arrays.asList(A, A, TEN);
        final int result = CardNumber.calculateScore(cardNumbers);
        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisplayName("a a 10 10은 스코어가 22이다.")
    void calculateScore_A_A_10_10() {
        final List<CardNumber> cardNumbers = Arrays.asList(A, A, TEN, TEN);
        final int result = CardNumber.calculateScore(cardNumbers);
        assertThat(result).isEqualTo(22);
    }
}