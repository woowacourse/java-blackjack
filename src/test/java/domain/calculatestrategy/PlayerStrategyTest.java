package domain.calculatestrategy;

import static org.assertj.core.api.Assertions.assertThat;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerStrategyTest {

    @DisplayName("플레이어의 카드 합을 계산한다.")
    @Test
    void 플레이어의_카드_합을_계산한다() {

        // given
        final PlayerStrategy playerStrategy = new PlayerStrategy();
        final List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.ACE, Shape.SPADE));
        cards.add(new Card(Rank.ACE, Shape.HEART));
        cards.add(new Card(Rank.ACE, Shape.CLOVER));
        cards.add(new Card(Rank.ACE, Shape.DIAMOND));

        // when
        final int sumOfRank = playerStrategy.calculateSum(cards);

        // then
        assertThat(sumOfRank).isEqualTo(14);
    }

}
