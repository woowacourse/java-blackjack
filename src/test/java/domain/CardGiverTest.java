package domain;

import static org.assertj.core.api.Assertions.*;
import static util.ExceptionConstants.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.ExceptionConstants;

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

    @DisplayName("배분할 카드가 2장 미만일 시 예외가 발생한다")
    @Test
    void test19() {
        // given
        RandomGenerator<Card> randomGenerator = new TestRandomGenerator();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 51; i++) {
            cards.add(new Card(CardNumberType.ACE, CardType.DIAMOND));
        }
        GivenCards givenCards = GivenCards.create(cards);
        CardGiver cardGiver = new CardGiver(randomGenerator, givenCards);
        //when & then
        assertThatThrownBy(
                cardGiver::giveDefault
        ).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(ERROR_HEADER);
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
