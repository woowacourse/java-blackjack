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
        List<Card> cards = List.of(
                new Card(Suit.CLOVER, Denomination.FIVE),
                new Card(Suit.HEART, Denomination.KING)
        );
        Rule rule = new Rule();
        //when
        int score = rule.calculate(cards);
        //then
        Assertions.assertThat(score).isEqualTo(15);
    }
}
