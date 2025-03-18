package domain.calculatestrategy;

import static org.assertj.core.api.Assertions.assertThat;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerStrategyTest {

    @DisplayName("딜러의 카드 합을 계산한다.")
    @Test
    void 딜러의_카드_합을_계산한다() {

        // given
        final DealerStrategy dealerStrategy = new DealerStrategy();
        final List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, Shape.SPADE));
        cards.add(new Card(Rank.FIVE, Shape.SPADE));
        cards.add(new Card(Rank.ACE, Shape.HEART));

        // when
        final int sumOfRank = dealerStrategy.calculateSum(cards);

        // then
        assertThat(sumOfRank).isEqualTo(17);
    }

}
