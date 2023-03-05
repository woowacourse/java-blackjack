package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.util.CardPickerGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("랜덤의 카드를 뽑아서 비교")
    void create() {
        //give
        Cards cards = Cards.generator();
        Card card = new Card(CardNumber.ACE, CardSuit.SPADE);
        TestCardPickerGenerator testCardPickerGenerator = new TestCardPickerGenerator(0);

        //when
        Card result = cards.pick(testCardPickerGenerator);

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