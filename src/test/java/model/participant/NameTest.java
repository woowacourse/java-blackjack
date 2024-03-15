package model.participant;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @DisplayName("플레이어 이름이 빈 값이면 예외 발생")
    @Test
    void createPlayerNameWithEmptyName() {
        assertThatThrownBy(() -> Name.createPlayerName(""))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어 이름이 빈 값이 아니고, 딜러의 이름과 다르면 객체 생성 성공")
    @ParameterizedTest
    @ValueSource(strings = {"jojo", "lily", "조조"})
    void createPlayerNameWithNotEmptyAndNotDealerName(String name) {
        assertThatCode(() -> Name.createPlayerName(name))
            .doesNotThrowAnyException();
    }

    @DisplayName("딜러 이름 생성 성공")
    @Test
    void createDealerName() {
        assertThatCode(Name::createDealerName).doesNotThrowAnyException();
    }
}
