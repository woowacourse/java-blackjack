package blackjack.domain.player;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.MockDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.HoldCards;

public class PlayerTest {

    @Nested
    @DisplayName("drawCard는")
    class DrawCard {

        @Test
        @DisplayName("Card를 자신의 패에 추가한다.")
        void addCard() {
            MockDeck mockDeck = new MockDeck(List.of(Card.of(CardPattern.CLOVER, CardNumber.JACK),
                Card.of(CardPattern.CLOVER, CardNumber.JACK), Card.of(CardPattern.CLOVER, CardNumber.JACK)));
            Player player = new Player(new Name("roma"), HoldCards.drawTwoCards(mockDeck));
            player.drawCard(mockDeck);
            Assertions.assertThat(player.getTotalNumber()).isEqualTo(30);
        }
    }

    @Nested
    @DisplayName("isBust는")
    class IsBust {

        @ParameterizedTest
        @CsvSource(value = {"ACE|false", "TWO|true"}, delimiter = '|')
        @DisplayName("패의 합이 21이 넘는지 유무를 알려준다.")
        void returnFalse(CardNumber cardNumber, boolean expected) {
            MockDeck mockDeck = new MockDeck(List.of(Card.of(CardPattern.CLOVER, CardNumber.JACK)
                , Card.of(CardPattern.CLOVER, CardNumber.KING),
                Card.of(CardPattern.CLOVER, cardNumber)));
            Player player = new Player(new Name("roma"), HoldCards.drawTwoCards(mockDeck));
            player.drawCard(mockDeck);

            Assertions.assertThat(player.isBust()).isEqualTo(expected);
        }
    }
}
