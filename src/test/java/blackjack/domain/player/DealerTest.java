package blackjack.domain.player;

import blackjack.domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.fixture.FixedSequenceDeck.generateDeck;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("현재 점수가 16점 이하인 경우 참을 반환한다")
    void needMoreCardWhenTrue(){
        Deck deck = generateDeck(new Card(Symbol.HEART, Denomination.SIX), new Card(Symbol.SPADE, Denomination.JACK));
        Dealer dealer = new Dealer(deck.initialDraw());

        assertThat(dealer.doesNeedToDraw()).isTrue();
    }

    @Test
    @DisplayName("현재 점수가 16점 초과인 경우 거짓을 반환한다")
    void needMoreCardWhenFalse() {
        Deck deck = generateDeck(new Card(Symbol.HEART,Denomination.NINE), new Card(Symbol.SPADE,Denomination.EIGHT));
        Dealer dealer = new Dealer(deck.initialDraw());

        assertThat(dealer.doesNeedToDraw()).isFalse();
    }

    @Test
    @DisplayName("첫 공개카드는 한 장을 반환한다")
    void testOpenCards() {
        Deck deck = generateDeck(new Card(Symbol.HEART,Denomination.NINE), new Card(Symbol.SPADE,Denomination.EIGHT));
        Dealer dealer = new Dealer(deck.initialDraw());

        assertThat(dealer.openCards().size()).isEqualTo(1);
    }
}
