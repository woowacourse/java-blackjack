package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class CardsTest {
    @Test
    @DisplayName("create()는 호출하면 52장의 카드 뭉치를 생성한다.")
    void create_whenCall_thenSuccess() {
        assertThatCode(() -> Cards.create())
                .doesNotThrowAnyException();

        assertThat(Cards.create())
                .isExactlyInstanceOf(Cards.class);
    }
}
