package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.utils.CardDeck;
import blackjack.utils.FixedCardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @Test
    @DisplayName("Dealer 16 초과하지 않은 경우")
    void isAvailableToTake1() {
        final CardDeck cardDeck = new FixedCardDeck();
        List<Card> cards = cardDeck.initCards();
        Participant dealer = new Dealer(cards);
        assertThat(dealer.isAvailableToTake()).isEqualTo(true);
    }

    @Test
    @DisplayName("Dealer 16 초과한 경우")
    void isAvailableToTake2() {
        List<Card> cards = Arrays.asList(Card.from("K다이아몬드"),
                Card.from("K다이아몬드"));
        Participant dealer = new Dealer(cards);

        assertThat(dealer.isAvailableToTake()).isEqualTo(false);
    }
}
