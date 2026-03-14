package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.trump.Card;
import blackjack.domain.trump.Denomination;
import blackjack.domain.trump.RandomSortBehavior;
import blackjack.domain.trump.Suit;
import blackjack.domain.trump.Trump;
import java.util.List;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrumpTest {

    @Test
    @DisplayName("트럼프 덱 생성 테스트")
    void 트럼프_덱_생성_테스트() {
        Trump trump = new Trump(new RandomSortBehavior());
        int expected = 13 * 4;

        assertThat(trump).extracting("deck")
            .asInstanceOf(InstanceOfAssertFactories.LIST)
            .hasSize(expected);
    }

    @Test
    @DisplayName("카드 드로우 테스트")
    void 카드_드로우_테스트() {
        List<Card> order = List.of(
            Card.valueOf(Suit.HEART, Denomination.TEN),
            Card.valueOf(Suit.SPADE, Denomination.FIVE),
            Card.valueOf(Suit.DIAMOND, Denomination.EIGHT));
        Trump trump = new Trump(new OrderedSortBehavior(order));
        List<Card> expected = List.of(
            Card.valueOf(Suit.HEART, Denomination.TEN),
            Card.valueOf(Suit.SPADE, Denomination.FIVE),
            Card.valueOf(Suit.DIAMOND, Denomination.EIGHT));

        List<Card> actual = List.of(trump.draw(), trump.draw(), trump.draw());

        assertAll(
            () -> assertThat(actual.get(0))
                .isEqualTo(expected.get(0)),
            () -> assertThat(actual.get(1))
                .isEqualTo(expected.get(1)),
            () -> assertThat(actual.get(2))
                .isEqualTo(expected.get(2))
        );
    }
}
