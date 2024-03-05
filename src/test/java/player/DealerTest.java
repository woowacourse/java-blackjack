package player;

import card.Card;
import card.Deck;
import card.Number;
import card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러는 17 이상이 될 때까지 카드를 계속 뽑는다.")
    void dealerDrawTest() {
        // given
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(
                new Card(Symbol.HEART, Number.JACK),
                new Card(Symbol.DIAMOND, Number.SIX),
                new Card(Symbol.CLOVER, Number.THREE),
                new Card(Symbol.CLOVER, Number.FIVE)
        );
        Deck deck = new Deck(cards);
        // when
        dealer.drawCard(deck);
        // then
        assertThat(dealer.calculateScore()).isEqualTo(19);
    }
}
