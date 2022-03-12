package blackjack.domain.game;

import static blackjack.fixture.CardBundleGenerator.generateCardBundleOf;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER5;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardBundle;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ResultStatisticsTest {

    private Player player;

    @BeforeEach
    void setUp() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER4, CLOVER5);
        player = Player.of("hudi", cardBundle);
    }

    @DisplayName("인스턴스 생성 시점에 승, 패, 무 정보는 0으로 초기화된다.")
    @ParameterizedTest(name = "[{index}] 결과 타입: {0}, 값: 0")
    @ValueSource(strings = {"WIN", "LOSE", "DRAW"})
    void of_initsResultsWithZero(String key) {
        ResultStatistics stats = ResultStatistics.of(player);

        ResultCount count = stats.getStats().get(ResultType.valueOf(key));

        assertThat(count.toInt()).isZero();
    }

    @DisplayName("incrementCountOf 메서드는 해당되는 승, 패, 무 정보를 1만큼 증가시킨다.")
    @ParameterizedTest(name = "[{index}] {0}를 1씩 증가시킬 수 있다.")
    @ValueSource(strings = {"WIN", "LOSE", "DRAW"})
    void incrementCountOf(String key) {
        ResultStatistics stats = ResultStatistics.of(player);
        for (int i = 0; i < 3; i++) {
            stats.incrementCountOf(ResultType.valueOf(key));
        }

        ResultCount count = stats.getStats().get(ResultType.valueOf(key));

        assertThat(count.toInt()).isEqualTo(3);
    }
}
