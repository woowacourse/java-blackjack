package domain;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardGiverTest {

    @DisplayName("카드를 2장 배분한다")
    @Test
    void test1() {
        // given
        RandomGenerator<Card> randomGenerator = new TestRandomGenerator();
        CardGiver cardGiver = new CardGiver(randomGenerator);

        Card expectedCard1 = new Card(CardNumberType.SIX, CardType.CLOVER);
        Card expectedCard2 = new Card(CardNumberType.FIVE, CardType.SPACE);

        //when
        Cards cards = cardGiver.giveDefault();

        //then
        assertThat(cards).containsExactly(expectedCard1, expectedCard2);
    }
}
