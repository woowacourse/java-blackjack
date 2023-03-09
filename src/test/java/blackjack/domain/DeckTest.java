package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Deck;
import blackjack.util.CardPickerGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("랜덤의 카드를 뽑아서 비교")
    void create() {
        //give
        Deck deck = Deck.create(new TestCardPickerGenerator(0));
        Card card = new Card(CardNumber.ACE, CardSuit.SPADE);

        //when
        Card result = deck.pick();

        //then
        assertThat(result).isEqualTo(card);
    }

    class TestCardPickerGenerator implements CardPickerGenerator {

        int randomIndex;

        TestCardPickerGenerator(int randomIndex) {
            this.randomIndex = randomIndex;
        }

        @Override
        public int generator(final int number) {
            return randomIndex;
        }
    }
}
