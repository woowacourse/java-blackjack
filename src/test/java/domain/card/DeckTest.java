package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.shuffler.SequentialCardShuffler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    @DisplayName("카드를 뽑는다")
    @Test
    void pickCard() {
        Deck deck = Deck.from(new SequentialCardShuffler());

        Card card1 = deck.pickCard();
        Card card2 = deck.pickCard();

        assertThat(card1.getSuit()).isEqualTo(Suits.values()[0]);
        assertThat(card1.getDenomination()).isEqualTo(Denomination.values()[0]);

        assertThat(card2.getSuit()).isEqualTo(Suits.values()[0]);
        assertThat(card2.getDenomination()).isEqualTo(Denomination.values()[1]);
    }

    @DisplayName("더이상 뽑을 카드가 없을 때 예외를 발생한다")
    @Test
    void noCardException() {
        Deck deck = Deck.from(cards -> {
        });

        for (int i = 0; i < 13 * 4; i++) {
            deck.pickCard();
        }

        assertThatThrownBy(deck::pickCard)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("더이상 뽑을 카드가 없습니다.");
    }
}
