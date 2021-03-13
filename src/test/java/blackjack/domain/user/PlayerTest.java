package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @DisplayName("draw시 정상적으로 카드가 추가되는지")
    @Test
    public void askDraw_additionalCard() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.FIVE, Suit.CLUBS));
        cards.add(new Card(Denomination.EIGHT, Suit.DIAMONDS));
        Player player = Player.create("pobi");

        player.initialHands(cards);
        player.draw(deck.pickSingleCard());

        assertThat(player.getCards().size()).isEqualTo(3);
    }
}
