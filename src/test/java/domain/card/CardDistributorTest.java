package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardDistributorTest {

    @Test
    @DisplayName("생성 확인")
    void distribute() {
        // given
        CardDistributor cardDistributor = new CardDistributor();

        // then
        assertThatNoException().isThrownBy(cardDistributor::distribute);
    }

    @Test
    @DisplayName("카드가 다 소요되면 에러가 발생한다.")
    void failed() {
        // given
        CardDistributor cardDistributor = new CardDistributor();

        // when
        for (int i = 0; i < 52; i++) {
            cardDistributor.distribute();
        }

        // then
        assertThatThrownBy(cardDistributor::distribute)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드가 모두 소요됐습니다.");
    }
}
