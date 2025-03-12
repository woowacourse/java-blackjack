package object.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class CardSymbolTest {

    @ParameterizedTest
    @EnumSource(CardSymbol.class)
    void 카드_심볼_생성_테스트(CardSymbol cardSymbol) {
        Assertions.assertThat(cardSymbol).isInstanceOf(CardSymbol.class);
    }
}
