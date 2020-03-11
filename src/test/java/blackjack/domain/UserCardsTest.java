package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserCardsTest {
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("카드가 한 장도 없는 상태에서 초기화됐을 경우")
    void emptyHandTest(List<Card> cards) {
        assertThatThrownBy(() -> new UserCards(cards))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("카드가 없습니다");
    }
}
