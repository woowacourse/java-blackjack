package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

class DealerTest {

    private Deck deck;

    @BeforeEach
    void init() {
        deck = Deck.create();
    }

    @Test
    @DisplayName("카드의 합이 16 이하여서 뽑을 수 있는지 확인")
    void canDrawTest() {
        Cards cards = new Cards(
            List.of(new Card(Denomination.TWO, Suit.CLUBS), new Card(Denomination.THREE, Suit.CLUBS)));
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.canDraw()).isTrue();
    }
}