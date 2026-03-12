package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void null인_카드를_받으면_생성시_예외_발생한다() {
        List<TrumpCard> cards = null;

        assertThatThrownBy(() -> Deck.of(cards))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("cards 는 null 이 올 수 없습니다.");
    }

    @Test
    void 전체_카드_수가_52장이_아니면_예외_발생한다() {
        List<TrumpCard> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(TrumpCard.of(suit, rank));
            }
        }
        cards.add(TrumpCard.of(Suit.SPADE, Rank.ACE));

        assertThatThrownBy(() -> Deck.of(cards))
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

        assertThatThrownBy(() -> Deck.of(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드는 중복되면 안됩니다.");
    }

    @Test
    void 카드를_지급할_때_카드가_없으면_예외_발생한다() {
        List<TrumpCard> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(TrumpCard.of(suit, rank));
            }
        }
        Deck deck = Deck.of(cards);
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }

        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱에 카드가 없습니다.");
    }

    @Test
    void 카드를_지급하면_전체_카드_수가_줄어든다() {
        List<TrumpCard> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(TrumpCard.of(suit, rank));
            }
        }
        Deck deck = Deck.of(cards);
        TrumpCard draw = deck.draw();

        assertThat(draw).isNotNull();
    }

    @Test
    void 게임을_시작하면_카드를_셔플한다() {
        Deck deck = Deck.create(Collections::reverse);
        TrumpCard first = deck.draw();
        assertThat(first).isEqualTo(TrumpCard.of(Suit.CLOVER, Rank.KING));
    }

    @Test
    void 초반에_카드를_2장씩_한번에_지급한다() {
        List<TrumpCard> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(TrumpCard.of(suit, rank));
            }
        }
        Deck deck = Deck.of(cards);
        List<TrumpCard> drawnCards = deck.drawInitialCards();

        assertThat(drawnCards).hasSize(2);
    }
}