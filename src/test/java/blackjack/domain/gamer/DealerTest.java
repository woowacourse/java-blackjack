package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Cards;
import blackjack.domain.deck.Deck;

class DealerTest {

    private Deck deck;

    @BeforeEach
    void init() {
        deck = new Deck(Deck.initCards());
    }

    @Test
    @DisplayName("카드의 합이 16 이하인지 확인")
    void canDrawTest() {
        Dealer dealer = new Dealer(new Cards(deck.drawStartCards()));

        assertThat(dealer.canDraw()).isTrue();
    }

    @Test
    @DisplayName("카드의 합이 16 초과일 때 예외 처리하는지 확인")
    void canNotDrawTest() {
        Dealer dealer = new Dealer(new Cards(deck.drawStartCards()));

        assertThatThrownBy(() -> {
            while (true) {
                dealer.drawCard(deck.draw());
            }
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("카드를 뽑을 수 없습니다.");
    }
}