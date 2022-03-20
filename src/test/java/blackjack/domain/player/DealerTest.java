package blackjack.domain.player;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.fixture.CardFixture.*;
import static blackjack.domain.fixture.FixedSequenceDeck.generateDeck;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("현재 점수가 16점 이하인 경우 참을 반환한다")
    void needMoreCardWhenTrue(){
        Deck deck = generateDeck(SPADE_JACK, SPADE_TWO);
        Dealer dealer = new Dealer(deck.initialDraw());

        assertThat(dealer.canDraw()).isTrue();
    }

    @Test
    @DisplayName("현재 점수가 16점 초과인 경우 거짓을 반환한다")
    void needMoreCardWhenFalse() {
        Deck deck = generateDeck(SPADE_JACK, SPADE_NINE);
        Dealer dealer = new Dealer(deck.initialDraw());

        assertThat(dealer.canDraw()).isFalse();
    }

    @Test
    @DisplayName("첫 공개카드는 한 장을 반환한다")
    void testOpenCards() {
        Deck deck = generateDeck(DUMMY_CARD, DUMMY_CARD);
        Dealer dealer = new Dealer(deck.initialDraw());

        assertThat(dealer.openCards().size()).isEqualTo(1);
    }
}
