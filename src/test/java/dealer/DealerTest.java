package dealer;

import card.Card;
import card.CardDeck;
import card.CardNumber;
import card.CardPattern;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import random.RandomGenerator;

class DealerTest {

    private static class RandomCardIndexShufflerMock implements RandomGenerator {

        private final int number;

        public RandomCardIndexShufflerMock(int number) {
            this.number = number;
        }

        @Override
        public int getRandomNumberInRange(int minNumber, int maxNumber) {
            return number;
        }
    }

    @DisplayName("랜덤으로 셔플 후 뽑힌 카드를 준다.")
    @Test
    void giveCard() {
        Dealer dealer = new Dealer(new RandomCardIndexShufflerMock(5), new CardDeck());

        Card card = dealer.giveCard();

        Assertions.assertThat(card).isEqualTo(new Card(CardNumber.SIX, CardPattern.SPADE_PATTERN));
    }

}