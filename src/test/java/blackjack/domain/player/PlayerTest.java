package blackjack.domain.player;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.MockDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.HoldCards;

public class PlayerTest {

    @Nested
    @DisplayName("canHit은")
    class CanHit {
            @Test
            @DisplayName("카드의 총합이 21보다 같거나작으면 true")
            void it_return_true() {
                MockDeck deck = new MockDeck(List.of(
                    Card.of(CardPattern.DIAMOND, CardNumber.ACE),
                    Card.of(CardPattern.DIAMOND, CardNumber.TEN)));

                Player player = new Player(new Name("player"),
                    HoldCards.drawTwoCards(deck));
                Assertions.assertThat(player.canHit()).isTrue();
            }

        @Test
        @DisplayName("카드의 총합이 21보다 크면 false")
        void it_return_false() {
            MockDeck deck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.TWO)));

            Player player = new Player(new Name("player"),
                HoldCards.drawTwoCards(deck));
            player.drawCard(deck);
            Assertions.assertThat(player.canHit()).isFalse();
        }
    }
}
