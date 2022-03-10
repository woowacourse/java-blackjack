package domain.player;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class GamblersTest {
    @Test
    @DisplayName("Gamblers 생성 테스트")
    void createGamblers() {
        // given
        List<Gambler> gamblers = List.of(
                new Gambler("포비"),
                new Gambler("리차드"),
                new Gambler("돌범")
        );

        // when & then
        assertThatCode(() -> new Gamblers(gamblers))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "생성자 매개변수 : {0}")
    @NullAndEmptySource
    @DisplayName("Null 또는 Empty 로 겜블러 생성시 IAE 발생")
    void createGamblersWithNullOrEmptyShouldFail(List<Gambler> gamblers) {
        assertThatThrownBy(() -> new Gamblers(gamblers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 겜블러 목록을 확인해주세요");
    }
}
