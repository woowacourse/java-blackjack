package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class DeckTest {
    private Deck emptyDeck(){
        Deck deck = Deck.of(new ArrayList<>(TrumpCard.ALL_CARD));
        for (int i = 0; i < 52; i++) {
            deck.deal();
        }
        return deck;
    }

    @Test
    void 전체_카드_수가_52장보다_적으면_예외_발생한다() {
        List<TrumpCard> cards = new ArrayList<>();
        cards.add(TrumpCard.of(Suit.SPADE, Rank.ACE));
        assertThatThrownBy(() -> Deck.of(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("전체 카드 수는 52장이어야 합니다.");
    }

    @Test
    void 전체_카드_수가_52장보다_많으면_예외_발생한다() {
        List<TrumpCard> cards = new ArrayList<>(TrumpCard.ALL_CARD);
        cards.add(TrumpCard.of(Suit.SPADE, Rank.ACE));

        assertThatThrownBy(() -> Deck.of(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("전체 카드 수는 52장이어야 합니다.");
    }

    @Test
    void 중복된_카드가_존재하면_예외_발생한다() {
        List<TrumpCard> cards = new ArrayList<>(TrumpCard.ALL_CARD);
        cards.removeFirst();
        cards.add(TrumpCard.of(Suit.CLOVER, Rank.KING));

        assertThatThrownBy(() -> Deck.of(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드는 중복되면 안됩니다.");
    }

    @Test
    void 카드를_지급할_때_카드가_없으면_예외_발생한다() {
        Deck deck = emptyDeck();

        assertThatThrownBy(deck::deal)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("덱에 카드가 없습니다.");
    }

    @Test
    void 카드를_지급하면_전체_카드_수가_줄어든다() {
        Deck deck = Deck.of(new ArrayList<>(TrumpCard.ALL_CARD));

        deck.deal();

        assertThat(deck.countCards()).isNotNull();
    }

    @Test
    void 게임을_시작하면_카드를_셔플한다() {
        Deck deck = Deck.create(Collections::reverse);
        TrumpCard first = deck.deal();
        assertThat(first).isEqualTo(TrumpCard.of(Suit.CLOVER, Rank.KING));
    }
}