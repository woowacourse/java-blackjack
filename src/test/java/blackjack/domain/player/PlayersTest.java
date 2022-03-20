package blackjack.domain.player;


import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.generator.RandomCardDeckGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    @ParameterizedTest
    @MethodSource("providePlayerNames")
    @DisplayName("플레이어의 수가 1 ~ 8 명이 아니면, 예외를 발생한다.")
    void checkPlayerCountToPlayGame(List<String> playerNames) {
        final CardDeck cardDeck = new CardDeck(new RandomCardDeckGenerator());

        assertThatThrownBy(() -> Players.createByName(playerNames, cardDeck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임을 하기 위한 플레이어 수는 1명이상 8명이하로 입력해주세요.");
    }

    static Stream<Arguments> providePlayerNames() {
        return Stream.of(Arguments.of(
                List.of(),
                List.of("slow1", "slow2", "slow3", "slow4", "slow5", "slow6", "slow7", "slow8", "slow9")
        ));
    }

    @Test
    @DisplayName("플레이어의 이름이 중복이면, 예외를 발생한다.")
    void checkDuplicatePlayerNames() {
        final CardDeck cardDeck = new CardDeck(new RandomCardDeckGenerator());

        final List<String> playerNames = new ArrayList<>(List.of("slow", "slow", "pobi"));

        assertThatThrownBy(() -> Players.createByName(playerNames, cardDeck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 플레이어의 이름이 있습니다.");
    }

    @Test
    @DisplayName("배팅 금액, 이름이 모두 정상 입력된 경우, Players 를 정상 생성한다.")
    void createPlayers() {
        final CardDeck cardDeck = new CardDeck(new RandomCardDeckGenerator());
        final Map<String, Integer> players = Map.ofEntries(
                Map.entry("slow", 1000),
                Map.entry("pobi", 2000)
        );

        assertThatCode(() -> Players.createByNameAndBettingMoney(players, cardDeck))
                .doesNotThrowAnyException();
    }
}
