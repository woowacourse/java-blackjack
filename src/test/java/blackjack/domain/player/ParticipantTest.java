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

public class ParticipantTest {

    @Nested
    @DisplayName("isBust는")
    class IsBust {
        @ParameterizedTest
        @CsvSource(value = {"THREE|false", "FOUR|true"}, delimiter = '|')
        @DisplayName("가지고 있는 카드가 버스트인지 알려준다.")
        void it_return_boolean(CardNumber cardNumber, boolean expected) {
            Participant participant = new Player(new Name("player"),
                HoldCards.drawTwoCards(() -> Card.of(CardPattern.DIAMOND, CardNumber.NINE)));
            participant.drawCard(() -> Card.of(CardPattern.DIAMOND, cardNumber));
            Assertions.assertThat(participant.isBust()).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("isBlackjack는")
    class IsBlackjack {
        @Test
        @DisplayName("가지고 있는 카드가 버스트인지 알려준다.")
        void it_return_boolean() {
            MockDeck deck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.ACE),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN)));

            Participant participant = new Player(new Name("player"),
                HoldCards.drawTwoCards(deck));
            Assertions.assertThat(participant.isBlackjack()).isTrue();
        }
    }

    @Nested
    @DisplayName("getTotalNumber는")
    class GetTotalNumber {
        @Test
        @DisplayName("가지고 있는 카드의 합을 알려준다.")
        void it_return_boolean() {
            MockDeck deck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.ACE),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN)));

            Participant participant = new Player(new Name("player"),
                HoldCards.drawTwoCards(deck));
            Assertions.assertThat(participant.getTotalNumber()).isEqualTo(21);
        }
    }
}
