package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.Suit;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    void 덱에서_카드를_한_장_뽑는다() {
        // given
        final int cardCount = 1;
        final Card card1 = new Card(Suit.DIAMOND, Rank.ACE);
        final Card card2 = new Card(Suit.CLOVER, Rank.KING);
        final Card card3 = new Card(Suit.HEART, Rank.JACK);
        final Card card4 = new Card(Suit.CLOVER, Rank.THREE);
        Deck deck = new Deck(List.of(card1, card2, card3, card4));

        // when
        List<Card> cards = deck.drawCards(cardCount);

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(cards.size()).isEqualTo(cardCount);
            softAssertions.assertThat(cards.contains(card4)).isTrue();
        });
    }

    @Test
    void 덱에서_카드를_두_장_뽑는다() {
        // given
        final int cardCount = 2;
        final Card card1 = new Card(Suit.DIAMOND, Rank.ACE);
        final Card card2 = new Card(Suit.CLOVER, Rank.KING);
        final Card card3 = new Card(Suit.HEART, Rank.JACK);
        final Card card4 = new Card(Suit.CLOVER, Rank.THREE);
        Deck deck = new Deck(List.of(card1, card2, card3, card4));

        // when
        List<Card> cards = deck.drawCards(cardCount);

        // then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(cards.size()).isEqualTo(cardCount);
            softAssertions.assertThat(cards.contains(card3)).isTrue();
            softAssertions.assertThat(cards.contains(card4)).isTrue();
        });
    }

    @Test
    void 덱에_남은_카드_수가_뽑으려는_카드_수보다_적으면_예외가_발생한다() {
        // given
        Deck deck = new Deck(List.of(
                new Card(Suit.HEART, Rank.ACE)
        ));

        // when & then
        Assertions.assertThatThrownBy(() -> deck.drawCards(2))
                .isInstanceOf(IllegalStateException.class);
    }
}
