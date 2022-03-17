package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.fixture.FixedSequenceDeck.generateDeck;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("현재 점수가 16점 이하인 경우 참을 반환한다")
    void needMoreCardWhenTrue(){
        Deck deck = generateDeck(new Card(Symbol.HEART, Denomination.SIX), new Card(Symbol.SPADE, Denomination.JACK));
        Dealer dealer = new Dealer(deck.initialDraw());

        assertThat(dealer.canDraw()).isTrue();
    }

    @Test
    @DisplayName("현재 점수가 16점 초과인 경우 거짓을 반환한다")
    void needMoreCardWhenFalse() {
        Deck deck = generateDeck(new Card(Symbol.HEART,Denomination.NINE), new Card(Symbol.SPADE,Denomination.EIGHT));
        Dealer dealer = new Dealer(deck.initialDraw());

        assertThat(dealer.canDraw()).isFalse();
    }

    @Test
    @DisplayName("첫 공개카드는 한 장을 반환한다")
    void testOpenCards() {
        Deck deck = generateDeck(new Card(Symbol.HEART,Denomination.NINE), new Card(Symbol.SPADE,Denomination.EIGHT));
        Dealer dealer = new Dealer(deck.initialDraw());

        assertThat(dealer.openCards().size()).isEqualTo(1);
    }
}
