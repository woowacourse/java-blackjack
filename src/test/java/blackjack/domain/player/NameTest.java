package blackjack.domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @Test
    @DisplayName("플레이어이름 객체 생성 성공 테스트")
    public void createNameTest() {
        assertThat(Name.of("jack").getName())
                .isEqualTo("jack");
    }
}
