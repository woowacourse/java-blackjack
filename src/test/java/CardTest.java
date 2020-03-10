import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest {

    @ParameterizedTest
    @CsvSource(value = {"스페이드,5","다이아몬드,10","하트,A","클로버,K"})
    void 카드_생성_테스트(String type, String symbol) {
        Card card = new Card(type, symbol);

        Assertions.assertThat(card).hasFieldOrPropertyWithValue("type",type);
        Assertions.assertThat(card).hasFieldOrPropertyWithValue("symbol",symbol);
    }

    @ParameterizedTest
    @CsvSource(value = {"스페이드,five",",11","하트하트,A","클로버,", "다이아,아무거나"})
    void 잘못된_생성값_예외처리(String type, String symbol) {
        Assertions.assertThatThrownBy(() -> new Card(type, symbol))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 값이 들어왔습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"스페이드,5","다이아몬드,10","하트,A","클로버,K"})
    void 카드_비교_테스트(String type, String symbol) {
        Card card1 = new Card(type, symbol);
        Card card2 = new Card(type, symbol);

        Assertions.assertThat(card1.equals(card2)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"스페이드,5,5", "다이아몬드,10,10", "하트,A,1", "클로버,K,10"})
    void 카드_숫자_반환_테스트(String type, String symbol, int expected) {
        Card card = new Card(type, symbol);
        int result = card.getValue();
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
