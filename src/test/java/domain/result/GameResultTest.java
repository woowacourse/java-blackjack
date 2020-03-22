package domain.result;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayersInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    private PlayersInfo playersInfo;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        List<String> names = Arrays.asList("win", "lose", "draw", "blackjack");
        Map<String, Integer> playerInfo = names.stream()
                .collect(Collectors.toMap(Function.identity(), name -> 1000,
                        (e1, e2) -> {
                            throw new AssertionError("중복된 키가 있습니다.");
                        },
                        LinkedHashMap::new));

        playersInfo = PlayersInfo.of(playerInfo);
        dealer = Dealer.appoint();
        dealer.draw(new Card(Symbol.SPADE, Type.SIX));

        playersInfo.getPlayers()
                .get(0)
                .draw(new Card(Symbol.SPADE, Type.SEVEN));
        playersInfo.getPlayers()
                .get(1)
                .draw(new Card(Symbol.HEART, Type.FIVE));
        playersInfo.getPlayers()
                .get(2)
                .draw(new Card(Symbol.CLOVER, Type.SIX));
        playersInfo.getPlayers()
                .get(3)
                .draw(new Card(Symbol.SPADE, Type.ACE));
        playersInfo.getPlayers()
                .get(3)
                .draw(new Card(Symbol.CLOVER, Type.KING));
    }

    @ParameterizedTest
    @DisplayName("플레이어들 수익")
    @MethodSource("createIndexAndProfitOfPlayers")
    void getProfitOfPlayers(int index, int expected) {
        Map<Player, Integer> profitOfPlayers;
        profitOfPlayers = GameResult.of(dealer, playersInfo).getProfitOfPlayers();
        Player player = playersInfo.getPlayers().get(index);

        assertThat(profitOfPlayers.get(player)).isEqualTo(expected);
    }

    private static Stream<Arguments> createIndexAndProfitOfPlayers() {
        return Stream.of(
                Arguments.of(0, 1000),
                Arguments.of(1, -1000),
                Arguments.of(2, 0),
                Arguments.of(3, 1500)
        );
    }

    @Test
    @DisplayName("딜러 최종 수익")
    void getProfitOfDealer() {
        GameResult gameResult = GameResult.of(dealer, playersInfo);

        assertThat(gameResult.getProfitOfDealer()).isEqualTo(-1500);
    }
}