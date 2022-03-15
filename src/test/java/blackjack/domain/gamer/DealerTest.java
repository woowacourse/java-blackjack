package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Deck;

class DealerTest {

    private Deck deck;

    @BeforeEach
    void init() {
        deck = Deck.create();
    }

    @Test
    @DisplayName("카드의 합이 16 이하여서 뽑을 수 있는지 확인")
    void canDrawTest() {
        Dealer dealer = new Dealer(deck.drawStartingCards());

        assertThat(dealer.canDraw()).isTrue();
    }
}