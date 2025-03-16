package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @DisplayName("카드뭉치의 카드를 드로우 할 수 있다.")
    @Test
    void extractCard() {
        //given
        List<Card> cards = new ArrayList<>();
        Card card = new Card(Symbol.COLVER, Rank.FIVE);
        cards.add(card);

        Deck deck = Deck.from(cards);

        //when
        Card actual = deck.extractCard();

        //then
        assertThat(actual).isEqualTo(card);
    }

}
