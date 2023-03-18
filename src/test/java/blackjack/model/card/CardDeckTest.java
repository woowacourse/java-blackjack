package blackjack.model.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.model.CardFixtures.CLUB_EIGHT;
import static blackjack.model.CardFixtures.CLUB_JACK;
import static org.assertj.core.api.Assertions.assertThat;

class CardDeckTest {

    @Test
    @DisplayName("카드를 뽑는다.")
    void pick_card_from_deck() {
        //given
        Card card1 = CLUB_EIGHT;
        Card card2 = CLUB_JACK;
        List<Card> cards = List.of(card1, card2);
        CardDeck cardDeck = new CardDeck(cards);

        // when
        Card pickedCard = cardDeck.pick();

        //then
        assertThat(pickedCard).isEqualTo(card2);
    }

}
