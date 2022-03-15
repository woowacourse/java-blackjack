package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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
    void get(Denomination d1, Denomination d2, Denomination d3, int expectedPoint) {
        List<Card> rawCards = List.of(
                Card.of(d1, Suit.SPADE),
                Card.of(d2, Suit.SPADE),
                Card.of(d3, Suit.SPADE)
        );
        assertThat(Point.of(rawCards).get())
                .isEqualTo(expectedPoint);
    }
}
