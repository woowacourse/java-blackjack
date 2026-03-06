package fixture;

import java.util.List;
import java.util.stream.Stream;
import model.Card;
import model.CardShape;
import model.CardValue;
import org.junit.jupiter.params.provider.Arguments;

public class CardsTestFixture {

    public static Stream<Arguments> 카드_목록_별_점수_정보_제공() {
        return Stream.of(
                // ACE(1) + JACK(10) = 11
                Arguments.of(
                        List.of(new Card(CardShape.SPADE, CardValue.ACE),
                                new Card(CardShape.CLOVER, CardValue.JACK)),
                        11
                ),
                // THREE(3) + SEVEN(7) + KING(10) + ACE(1) = 21
                Arguments.of(
                        List.of(new Card(CardShape.HEART, CardValue.THREE),
                                new Card(CardShape.DIAMOND, CardValue.SEVEN),
                                new Card(CardShape.SPADE, CardValue.JACK),
                                new Card(CardShape.SPADE, CardValue.ACE)
                        ),
                        21
                )
        );
    }
}
