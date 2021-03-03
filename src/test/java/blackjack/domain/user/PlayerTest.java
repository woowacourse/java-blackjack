package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @DisplayName("y 입력시 카드 추가 후 HIT")
    @Test
    public void askDraw_additionalCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.FIVE, Suit.CLUBS));
        cards.add(new Card(Denomination.EIGHT, Suit.DIAMONDS));
        Player player = Player.create("pobi");
        player.initialHands(cards);

        player.askDraw("y", new Card(Denomination.SIX, Suit.HEARTS));
        assertThat(player.getCards().size()).isEqualTo(3);
        assertTrue(player.isHit());
    }

    @DisplayName("n 입력시 HandStatus.STAY 로 번경")
    @Test
    public void askDraw_convertToStay() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.FIVE, Suit.CLUBS));
        cards.add(new Card(Denomination.EIGHT, Suit.DIAMONDS));
        Player player = Player.create("pobi");
        player.initialHands(cards);

        player.askDraw("n", new Card(Denomination.SIX, Suit.HEARTS));
        assertThat(player.getCards().size()).isEqualTo(2);
        assertFalse(player.isHit());
    }
}