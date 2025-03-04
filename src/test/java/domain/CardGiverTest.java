package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardGiverTest {

    static class TestRandomGenerator implements RandomGenerator<Card> {
        Card expectedCard1 = new Card(CardNumberType.SIX, CardType.CLOVER);
        Card expectedCard2 = new Card(CardNumberType.FIVE, CardType.SPACE);
        List<Card> testCards = List.of(expectedCard1, expectedCard2);
        int index = 0;

        @Override
        public Card generate() {
            return testCards.get(index++);
        }
    }

    @DisplayName("카드를 2장 배분한다")
    @Test
    void test1() {
        // given
        RandomGenerator<Card> randomGenerator = new TestRandomGenerator();
        CardGiver cardGiver = new CardGiver(randomGenerator, GivenCards.createEmpty());

        Card expectedCard1 = new Card(CardNumberType.SIX, CardType.CLOVER);
        Card expectedCard2 = new Card(CardNumberType.FIVE, CardType.SPACE);

        //when
        Cards cards = cardGiver.giveDefault();

        //then
        assertThat(cards.cards()).containsExactly(expectedCard1, expectedCard2);
    }


}
