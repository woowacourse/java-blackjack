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

    @DisplayName("y 입력시 카드 추가 후 HIT")
    @Test
    public void askDraw_additionalCard() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.FIVE, Suit.CLUBS));
        cards.add(new Card(Denomination.EIGHT, Suit.DIAMONDS));
        Player player = Player.create("pobi");
        player.initialHands(cards);

        player.askDraw("y", deck);
        assertThat(player.getCards().size()).isEqualTo(3);
    }

    @DisplayName("n 입력시 HandStatus.STAY 로 번경")
    @Test
    public void askDraw_convertToStay() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Denomination.FIVE, Suit.CLUBS));
        cards.add(new Card(Denomination.EIGHT, Suit.DIAMONDS));
        Player player = Player.create("pobi");
        player.initialHands(cards);

        player.askDraw("n", deck);
        assertThat(player.getCards().size()).isEqualTo(2);
    }
}