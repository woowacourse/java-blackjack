package model.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @DisplayName("플레이어 이름이 딜러의 이름이면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"딜러", "dealer", "Dealer"})
    void createPlayerNameWithDealerName(String name) {
        assertThatThrownBy(() -> Name.createPlayerName(name))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
