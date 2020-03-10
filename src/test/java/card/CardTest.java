package card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest {

    @ParameterizedTest
    @CsvSource(value = {"SPADE,FIVE","DIAMOND,TEN","HEART,ACE","CLUB,KING"})
    void 카드_생성_테스트(String type, String symbol) {
        Card card = new Card(Type.valueOf(type), Symbol.valueOf(symbol));

        Assertions.assertThat(card).hasFieldOrPropertyWithValue("type",Type.valueOf(type));
        Assertions.assertThat(card).hasFieldOrPropertyWithValue("symbol",Symbol.valueOf(symbol));
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE,FIVE","DIAMOND,TEN","HEART,ACE","CLUB,KING"})
    void 카드_비교_테스트(String type, String symbol) {
        Card card1 = new Card(Type.valueOf(type), Symbol.valueOf(symbol));
        Card card2 = new Card(Type.valueOf(type), Symbol.valueOf(symbol));

        Assertions.assertThat(card1.equals(card2)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE,FIVE,5","DIAMOND,TEN,10","HEART,ACE,1","CLUB,KING,10"})
    void 카드_숫자_반환_테스트(String type, String symbol, int expected) {
        Card card = new Card(Type.valueOf(type), Symbol.valueOf(symbol));
        int result = card.getValue();
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
