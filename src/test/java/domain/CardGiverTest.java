package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardGiverTest {

    static class TestRandomGenerator implements RandomGenerator<Card> {
        Card expectedCard1 = new Card(CardNumberType.FIVE, CardType.SPACE);
        Card expectedCard2 = new Card(CardNumberType.FIVE, CardType.SPACE);
        Card expectedCard3 = new Card(CardNumberType.SIX, CardType.CLOVER);
        List<Card> testCards = List.of(expectedCard1, expectedCard2, expectedCard3);
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

        Card expectedCard1 = new Card(CardNumberType.FIVE, CardType.SPACE);
        Card expectedCard2 = new Card(CardNumberType.SIX, CardType.CLOVER);

        //when
        Cards cards = cardGiver.giveDefault();

        //then
        assertThat(cards.getCards()).containsExactly(expectedCard1, expectedCard2);
    }

    @DisplayName("카드를 랜덤으로 1장 생성하여 배분한다")
    @Test
    void test4() {
        //given
        RandomGenerator<Card> randomGenerator = new TestRandomGenerator();
        GivenCards testGivenCards = GivenCards.createEmpty();
        testGivenCards.addUnique(new Card(CardNumberType.FIVE, CardType.SPACE));
        CardGiver cardGiver = new CardGiver(randomGenerator, testGivenCards);
        //when
        Card card = cardGiver.giveOne();
        //then
        assertThat(card).isEqualTo(new Card(CardNumberType.SIX, CardType.CLOVER));
    }
}
