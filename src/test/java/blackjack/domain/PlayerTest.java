package blackjack.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.MockDeck;

public class PlayerTest {

    @Nested
    @DisplayName("drawCard는")
    class DrawCard {

        @Test
        @DisplayName("Card를 자신의 패에 추가한다.")
        void addCard() {
            Player player = new Player(new Name("roma"));
            player.drawCard(new MockDeck(List.of(Card.of(CardPattern.CLOVER, CardNumber.JACK))));
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
            MockDeck mockDeck = new MockDeck(List.of(Card.of(CardPattern.CLOVER, CardNumber.JACK)
                , Card.of(CardPattern.CLOVER, CardNumber.KING), Card.of(CardPattern.CLOVER, cardNumber)));
            player.drawCard(mockDeck);
            player.drawCard(mockDeck);
            player.drawCard(mockDeck);

            Assertions.assertThat(player.isBust()).isEqualTo(expected);
        }
    }
}
