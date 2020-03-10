package card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardFactoryTest {
    @ParameterizedTest
    @CsvSource(value = {"SPADE,FIVE","DIAMOND,TEN","HEART,ACE","CLUB,KING"})
    void 카드_전달_테스트(String type, String symbol) {
        Card card1 = CardFactory.of(Type.valueOf(type), Symbol.valueOf(symbol));
        Card card2 = CardFactory.of(Type.valueOf(type),Symbol.valueOf(symbol));

        Assertions.assertThat(card1 == card2).isTrue();
    }
}
