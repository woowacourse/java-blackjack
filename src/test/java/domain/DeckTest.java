package domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.card.Deck;
import org.junit.jupiter.api.Test;

class DeckTest {


    @Test
    void 덱_생성_시_사이즈가_52임을_확인한다() {
        // when
        Deck deck = Deck.initialize();

        // then
        assertThat(deck.getSize()).isEqualTo(52);
    }

    @Test
    void 카드_한_장을_뽑을_시_카드_숫자의_범위를_확인한다() {
        // given
        Deck deck = Deck.initialize();

        // when
        Card card = deck.drawOneCard();

        // then
        assertThat(card.getRankScore()).isBetween(1, 11);
    }

    @Test
    void 초기_카드를_두_장_뽑는다() {
        // given
        Deck deck = Deck.initialize();

        // when
        Cards cards = deck.drawInitialCards();

        // then
        assertThat(cards.getSize()).isEqualTo(2);
    }

}
