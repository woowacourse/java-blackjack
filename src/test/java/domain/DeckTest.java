package domain;

import domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    @DisplayName("NoShuffleStrategy를 사용하면 카드가 순서대로 나온다.")
    void NoShuffleStrategy_카드_순서() {
        // given
        Deck deck = new Deck(new NoShuffleStrategy());

        // when
        Card firstCard = deck.drawCard();

        // then
        assertEquals(Rank.TWO, firstCard.getRank());
        assertEquals(Suit.HEART, firstCard.getSuit());
    }

    @Test
    @DisplayName("카드가 52장 모두 소진되어 더 이상 카드를 뽑을 수 없다면 에러가 나온다.")
    void 카드모두_소진_시_에러() {
        // given
        Deck deck = new Deck(new NoShuffleStrategy());
        Set<Card> cards = new HashSet<>();

        // when
        for (int i = 0; i < 52; i++) {
            Card card = deck.drawCard();
            cards.add(card);
        }

        // then
        assertEquals(52, cards.size());
        assertThatThrownBy(deck::drawCard)
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("더 이상 카드가 존재하지 않습니다.");
    }
}
