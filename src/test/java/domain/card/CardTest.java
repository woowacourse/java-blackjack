package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
public class CardTest {

    @ParameterizedTest
    @CsvSource(value = {"SPADE,FIVE", "DIAMOND,TEN", "HEART,ACE", "CLUB,KING"})
    void 카드_생성_테스트(String type, String symbol) {
        Card card = new Card(Type.valueOf(type), Symbol.valueOf(symbol));

        Assertions.assertThat(card).hasFieldOrPropertyWithValue("type", Type.valueOf(type));
        Assertions.assertThat(card).hasFieldOrPropertyWithValue("symbol", Symbol.valueOf(symbol));
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE,FIVE", "DIAMOND,TEN", "HEART,ACE", "CLUB,KING"})
    void 카드_비교_테스트(String typeValue, String symbolValue) {
        Type type = Type.valueOf(typeValue);
        Symbol symbol = Symbol.valueOf(symbolValue);
        Card card = new Card(type, symbol);

        Assertions.assertThat(card.isSameWith(type, symbol)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE,FIVE,5", "DIAMOND,TEN,10", "HEART,ACE,1", "CLUB,KING,10"})
    void 카드_숫자_반환_테스트(String type, String symbol, int expected) {
        Card card = new Card(Type.valueOf(type), Symbol.valueOf(symbol));
        int result = card.getValue();
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE,FIVE,5스페이드", "DIAMOND,TEN,10다이아몬드", "HEART,ACE,A하트", "CLUB,KING,K클로버"})
    void 카드_이름_반환_테스트(String type, String symbol, String expected) {
        Card card = new Card(Type.valueOf(type), Symbol.valueOf(symbol));

        Assertions.assertThat(card.getName()).isEqualTo(expected);
    }
}
