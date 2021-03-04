package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardFactoryTest {

    @DisplayName("덱 생성 테스트")
    @Test
    void create() {
        assertThat(CardFactory.create().size()).isEqualTo(52);
    }
}
