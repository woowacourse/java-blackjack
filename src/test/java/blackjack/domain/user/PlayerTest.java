package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("draw시 정상적으로 카드가 추가되는지")
    @Test
    public void askDraw_additionalCard() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.FIVE, Suit.CLUBS));
        cards.add(new Card(Denomination.EIGHT, Suit.DIAMONDS));

        String name = "pobi";
        int money = 10000;
        Player player = Player.create(name, money);

        player.initialHands(cards);
        player.draw(deck.pickSingleCard());

        assertThat(player.getCards().size()).isEqualTo(3);
    }
}
