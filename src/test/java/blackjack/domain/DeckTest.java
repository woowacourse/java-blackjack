package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void 전체_카드_수가_52장이_아니면_예외_발생한다() {
        List<TrumpCard> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(TrumpCard.of(suit, rank));
            }
        }
        cards.add(TrumpCard.of(Suit.SPADE, Rank.ACE));

        Assertions.assertThatThrownBy(() -> Deck.of(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("전체 카드 수는 52장이어야 합니다.");
    }

    @Test
    void 중복된_카드가_존재하면_예외_발생한다() {
        List<TrumpCard> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(TrumpCard.of(suit, rank));
            }
        }
        cards.removeFirst();
        cards.add(TrumpCard.of(Suit.CLOVER, Rank.KING));

        Assertions.assertThatThrownBy(() -> Deck.of(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드는 중복되면 안됩니다.");
    }
}