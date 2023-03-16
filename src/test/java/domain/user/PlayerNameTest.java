package domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerNameTest {
    
    @Test
    @DisplayName("플레이어 이름 길이 예외 테스트")
    void validateNameLengthTest() {
        Assertions.assertThatThrownBy(() -> new PlayerName("echoecho"))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("플레이어 이름 공백 예외 테스트")
    void validateBlankTest() {
        Assertions.assertThatThrownBy(() -> new PlayerName(""))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("플레이어 딜러 이름 예외 테스트")
    void validateDealerNameTest() {
        Assertions.assertThatThrownBy(() -> new PlayerName("딜러"))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}