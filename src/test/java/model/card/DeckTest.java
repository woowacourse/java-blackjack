package model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void 블랙잭_덱은_비어있을_수_없다() {
        // when & then
        assertThatThrownBy(() -> new Deck(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 블랙잭_덱은_중복된_카드가_존재할_수_없다() {
        // given
        Card aceSpade = new Card(CardShape.SPADE, CardValue.ACE);

        // when & then
        assertThatThrownBy(() -> new Deck(List.of(aceSpade, aceSpade)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 덱에_존재하는_마지막_카드를_나눠줄_수_있다() {
        // given
        Card firstCard = new Card(CardShape.SPADE, CardValue.ACE);
        Card secondCard = new Card(CardShape.SPADE, CardValue.TWO);
        Deck deck = new Deck(List.of(firstCard, secondCard));

        // when
        Card firstDrawCard = deck.draw();
        Card secondDrawCard = deck.draw();

        // then
        assertThat(firstDrawCard).isEqualTo(secondCard);
        assertThat(secondDrawCard).isEqualTo(firstCard);
    }

    @Test
    void 덱에_존재하는_카드가_없을_때_나눠준다면_예외가_발생한다() {
        // given
        Deck deck = new Deck(List.of(new Card(CardShape.SPADE, CardValue.ACE)));
        deck.draw();

        // when & then
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalArgumentException.class);
    }
}