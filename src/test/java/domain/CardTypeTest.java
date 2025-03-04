package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class CardTypeTest {
    @ParameterizedTest
    @EnumSource(CardType.class)
    void 카드_타입_생성_테스트(CardType cardType) {
        Assertions.assertThat(cardType).isInstanceOf(CardType.class);
    }
}
