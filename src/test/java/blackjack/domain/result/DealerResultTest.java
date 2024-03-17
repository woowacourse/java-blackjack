package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.player.Name;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerResultTest {
    private Name dealerName;

    @BeforeEach
    void setUp() {
        dealerName = new Name("딜러");
    }

    @Test
    @DisplayName("딜러 이름과 게임 플레이어 결과 리스트를 통해 딜러 결과를 생성한다.")
    void create_with_gamePlayerResultList() {
        final Name name1 = new Name("초롱");
        final PrizeMoney prizeMoney1 = new PrizeMoney(10000);
        final GamePlayerResult gamePlayerResult1 = new GamePlayerResult(name1, prizeMoney1);

        final Name name2 = new Name("조이썬");
        final PrizeMoney prizeMoney2 = new PrizeMoney(15000);
        final GamePlayerResult gamePlayerResult2 = new GamePlayerResult(name2, prizeMoney2);

        assertThatCode(() -> {
            final var sut = DealerResult.of(dealerName, List.of(gamePlayerResult1, gamePlayerResult2));
            assertThat(sut.getPrizeMoney()).isEqualTo(-25000);
        }).doesNotThrowAnyException();
    }
}
