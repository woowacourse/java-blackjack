package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlayerTest {

    @Nested
    @DisplayName("drawCard는")
    class DrawCard {

        @Test
        @DisplayName("Card를 자신의 패에 추가한다.")
        void addCard() {
            Player player = new Player(new Name("roma"));
            player.drawCard(Card.of(CardPattern.CLOVER, CardNumber.J));
            Assertions.assertThat(player.getTotalNumber()).isEqualTo(10);
        }
    }

    @Nested
    @DisplayName("isBust는")
    class IsBust {

        @ParameterizedTest
        @CsvSource(value = {"ACE|false", "TWO|true"}, delimiter = '|')
        @DisplayName("패의 합이 21이 넘는지 유무를 알려준다.")
        void returnFalse(CardNumber cardNumber, boolean expected) {
            Player player = new Player(new Name("roma"));
            player.drawCard(Card.of(CardPattern.CLOVER, CardNumber.J));
            player.drawCard(Card.of(CardPattern.CLOVER, CardNumber.K));
            player.drawCard(Card.of(CardPattern.CLOVER, cardNumber));

            Assertions.assertThat(player.isBust()).isEqualTo(expected);
        }
    }
}
