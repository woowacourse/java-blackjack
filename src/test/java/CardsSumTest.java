import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import java.util.List;

import blackjack.domain.Rank;
import blackjack.domain.Shape;
import org.junit.jupiter.api.Test;

public class CardsSumTest {

    @Test
    void cards_sum_without_ace() {
        Cards cards = new Cards(List.of(
                new Card(Rank.TWO, Shape.HEART),
                new Card(Rank.THREE, Shape.SPADE),
                new Card(Rank.JACK, Shape.DIAMOND),
                new Card(Rank.QUEEN, Shape.CLOVER)));
        assertThat(cards.sumScore()).isEqualTo(25);
    }

    @Test
    void cards_sum_with_ace() {
        Cards cards1 = new Cards(List.of(
                new Card(Rank.TWO, Shape.HEART),
                new Card(Rank.THREE, Shape.SPADE),
                new Card(Rank.ACE, Shape.DIAMOND),
                new Card(Rank.JACK, Shape.CLOVER),
                new Card(Rank.QUEEN, Shape.HEART)));
        Cards cards2 = new Cards(List.of(
                new Card(Rank.TWO, Shape.HEART),
                new Card(Rank.THREE, Shape.SPADE),
                new Card(Rank.ACE, Shape.DIAMOND)));
        Cards cards3 = new Cards(List.of(
                new Card(Rank.TWO, Shape.HEART),
                new Card(Rank.THREE, Shape.SPADE),
                new Card(Rank.ACE, Shape.DIAMOND),
                new Card(Rank.ACE, Shape.CLOVER)));
        assertThat(cards1.sumScore()).isEqualTo(26);
        assertThat(cards2.sumScore()).isEqualTo(16);
        assertThat(cards3.sumScore()).isEqualTo(17);
    }
}
