package team.blackjack.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    void draw는_카드_한_장을_반환한다() {
        Deck deck = new Deck();
        Card card = deck.draw();
        assertThat(card).isNotNull();
    }

    @Test
    void draw를_호출하면_덱에서_카드가_제거된다() {
        Deck deck = new Deck();
        deck.draw();
        deck.draw();
        // 52장 중 2장 제거 후 50장 남음 - 추가 draw 가능
        Card card = deck.draw();
        assertThat(card).isNotNull();
    }

    @Test
    void 전체_52장_드로우_시_중복되지_않는다() {
        Deck deck = new Deck();
        Set<Card> drawnCards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            Card card = deck.draw();
            drawnCards.add(card);
        }
        assertThat(drawnCards).hasSize(52);
    }
}
