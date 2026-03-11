package fixture;

import java.util.List;
import java.util.stream.Stream;
import model.Card;
import model.CardShape;
import model.CardValue;
import org.junit.jupiter.params.provider.Arguments;

public class FinalResultTestFixture {

    public static Stream<Arguments> 플레이어의_상황별_배팅금액_정보제공() {
        return Stream.of(
                // 1. 플레이어만 블랙잭 (딜러는 블랙잭이 아닌 그 외의 상황 모두)
                Arguments.of(
                        List.of(card(CardValue.TEN), card(CardValue.ACE)),
                        List.of(card(CardValue.SEVEN), card(CardValue.TEN)),
                        1000, 1500, 0
                )
        );
    }

    private static Card card(CardValue value) {
        return new Card(CardShape.SPADE, value);
    }
}
