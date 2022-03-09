package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RuleTest {

    @DisplayName("에이스가 없는 경우의 점수를 계산한다.")
    @Test
    public void testCalculateDefaultCondition() {
        //given
        Rule rule = new Rule();

        List<Card> cards = List.of(
                new Card(Suit.CLOVER, Denomination.FIVE),
                new Card(Suit.HEART, Denomination.KING)
        );

        //when
        int score = rule.sumPoint(cards);

        //then
        Assertions.assertThat(score).isEqualTo(15);
    }

    @DisplayName("에이스가 포함된 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithAce() {
        //given
        Rule rule = new Rule();

        List<Card> cards = List.of(
                new Card(Suit.CLOVER, Denomination.FIVE),
                new Card(Suit.DIAMOND, Denomination.ACE)
        );

        //when
        int score = rule.sumPoint(cards);

        //then
        Assertions.assertThat(score).isEqualTo(16);
    }

    @DisplayName("에이스가 여러개 있는 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithMultipleAce() {
        //given
        Rule rule = new Rule();

        List<Card> cards = List.of(
                new Card(Suit.HEART, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.SPADE, Denomination.ACE),
                new Card(Suit.DIAMOND, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.KING)
        );

        //when
        int score = rule.sumPoint(cards);

        //then
        Assertions.assertThat(score).isEqualTo(14);
    }

    @DisplayName("에이스가 여러개 있는 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithMultipleAce2() {
        //given
        Rule rule = new Rule();

        List<Card> cards = List.of(
                new Card(Suit.SPADE, Denomination.ACE),
                new Card(Suit.DIAMOND, Denomination.ACE),
                new Card(Suit.CLOVER, Denomination.NINE)
        );

        //when
        int score = rule.sumPoint(cards);

        //then
        Assertions.assertThat(score).isEqualTo(21);
    }
}
