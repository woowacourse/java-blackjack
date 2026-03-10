import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class HandTest {
    public static Stream<Arguments> getScore() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(Rank.ACE, Suit.SPADE), new Card(Rank.KING, Suit.SPADE)),
                        21)
        );
    }
}