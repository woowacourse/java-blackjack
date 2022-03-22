package blackjack.domain.user;

import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static blackjack.fixture.CardBundleFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayersTest {

    private static final String playerName = "기론";


    @Test
    @DisplayName("유저가 이름을 입력한만큼 참가자들 생성되는지 테스트")
    void joinTest() {
        Map<String, Money> input = new LinkedHashMap<>();
        Map<String, Cards> playerCards = new LinkedHashMap<>();
        input.put(playerName, Money.from(1000));
        input.put("열음", Money.from(5000));
        playerCards.put(playerName, _16_CARDS);
        playerCards.put("열음", _15_INIT_CARDS);

        Players players = Players.create(input, playerCards);
        assertThat(players.size()).isEqualTo(input.size());
    }

    @ParameterizedTest
    @MethodSource("calculateTest")
    @DisplayName("딜러의 점수가 15일때, " +
            "플레이어가 블랙잭으로 우승하면 1.5배, " +
            "그냥 우승은 1배," +
            " 패배는 -1배, " +
            "무승부는 0을 얻는다." +
            " 딜러의 수익과 플레이어의 수익의 합은 0이다.")
    void calculateTest(Map<String, Cards> cards, Map<String, Double> playerProfit) {
        Map<String, Money> input = new LinkedHashMap<>();
        input.put(playerName, Money.from(1000));

        Players players = Players.create(input, cards);
        Map<String, Double> statistics = players.getStatistics(new Dealer(_15_INIT_CARDS));
        assertAll(
                () -> assertThat(statistics.get(playerName)).isEqualTo(playerProfit.get(playerName)),
                () -> assertThat(statistics.get("딜러")).isEqualTo(-playerProfit.get(playerName))

        );
    }

    private static Stream<Arguments> calculateTest() {
        return Stream.of(
                Arguments.of(Map.of(playerName, JACK_ACE_BLACKJACK), Map.of(playerName, 1500D)),
                Arguments.of(Map.of(playerName, _16_CARDS), Map.of(playerName, 1000D)),
                Arguments.of(Map.of(playerName, _5_CARDS), Map.of(playerName, -1000D)),
                Arguments.of(Map.of(playerName, _15_INIT_CARDS), Map.of(playerName, 0D))
        );

    }


}
