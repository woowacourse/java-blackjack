package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.cards.Cards;
import blackjack.domain.card.element.Denomination;
import blackjack.domain.card.element.Suit;
import blackjack.domain.result.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PointTest {

    @ParameterizedTest
    @CsvSource(value = {
            "ACE,FIVE,ACE,17",
            "JACK,FIVE,JACK,25",
            "ACE,ACE,ACE,13",
            "JACK,QUEEN,KING,30",
            "ACE,TEN,TEN,21",
    })

    @DisplayName("올바른 포인트 계산되는지 테스트")
    void setExpectedPointTest(Denomination d1, Denomination d2, Denomination d3, int expectedPoint) {
        Cards cards = new Cards();
        cards.add(Card.of(d1, Suit.SPADE));
        cards.add(Card.of(d2, Suit.SPADE));
        cards.add(Card.of(d3, Suit.SPADE));

        assertThat(Point.fromCards(cards).get())
                .isEqualTo(expectedPoint);
    }
}
