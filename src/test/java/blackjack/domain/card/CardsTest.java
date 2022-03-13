package blackjack.domain.card;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("에이스가 없는 경우의 점수를 계산한다.")
    @Test
    public void testCalculateDefaultCondition() {
        //given
        Cards cards = new Cards();

        cards.add(new Card(CLOVER, FIVE));
        cards.add(new Card(HEART, KING));

        //when
        int score = cards.sumPoint();

        //then
        Assertions.assertThat(score).isEqualTo(15);
    }

    @DisplayName("에이스가 포함된 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithAce() {
        //given
        Cards cards = new Cards();

        cards.add(new Card(CLOVER, FIVE));
        cards.add(new Card(DIAMOND, ACE));

        //when
        int score = cards.sumPoint();

        //then
        Assertions.assertThat(score).isEqualTo(16);
    }

    @DisplayName("에이스가 여러개 있는 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithMultipleAce() {
        //given
        Cards cards = new Cards();

        cards.add(new Card(HEART, ACE));
        cards.add(new Card(CLOVER, ACE));
        cards.add(new Card(SPADE, ACE));
        cards.add(new Card(DIAMOND, ACE));
        cards.add(new Card(CLOVER, KING));

        //when
        int score = cards.sumPoint();

        //then
        Assertions.assertThat(score).isEqualTo(14);
    }

    @DisplayName("에이스가 여러개 있는 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithMultipleAce2() {
        //given
        Cards cards = new Cards();

        cards.add(new Card(SPADE, ACE));
        cards.add(new Card(DIAMOND, ACE));
        cards.add(new Card(CLOVER, NINE));

        //when
        int score = cards.sumPoint();

        //then
        Assertions.assertThat(score).isEqualTo(21);
    }

    @DisplayName("에이스가 여러개 있는 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithMultipleAce3() {
        //given
        Cards cards = new Cards();

        cards.add(new Card(SPADE, FIVE));
        cards.add(new Card(DIAMOND, FIVE));
        cards.add(new Card(CLOVER, EIGHT));
        cards.add(new Card(SPADE, ACE));
        cards.add(new Card(DIAMOND, ACE));
        cards.add(new Card(CLOVER, ACE));

        //when
        int score = cards.sumPoint();

        //then
        Assertions.assertThat(score).isEqualTo(21);
    }
}
