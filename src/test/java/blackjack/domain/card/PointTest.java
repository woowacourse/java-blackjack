package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.cardelement.Denomination;
import blackjack.domain.card.cardelement.Suit;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class PointTest {
    
    @ParameterizedTest
    @CsvSource(value = {
            "ACE,FIVE,ACE,17",
            "JACK,FIVE,JACK,25",
            "ACE,ACE,ACE,13",
            "JACK,QUEEN,KING,30",
            "ACE,TEN,TEN,21",
    })
    
    void 올바른_포인트_계산되는지_검사(Denomination d1, Denomination d2, Denomination d3, int expectedPoint) {
        List<Card> rawCards = List.of(
                Card.of(d1, Suit.SPADE),
                Card.of(d2, Suit.SPADE),
                Card.of(d3, Suit.SPADE)
        );
        assertThat(Point.from(rawCards).get()).isEqualTo(expectedPoint);
    }
}
