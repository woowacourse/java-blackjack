package domain.card;

import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckGeneratorTest {

    @DisplayName("CardDeck 생성에 성공한다.")
    @Test
    void create_success() {
        // when && then
        assertThatNoException().isThrownBy(() -> CardDeckGenerator.create());
    }

    //TODO: 추가적인 테스트에 관한 고민
}
