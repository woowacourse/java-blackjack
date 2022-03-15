package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.ManualDeckGenerator;
import blackjack.domain.result.MatchStatus;

public class PlayersTest {

    private final ManualDeckGenerator manualCardStrategy = new ManualDeckGenerator();

    @DisplayName("플레이어 이름은 서로 중복될 수 없다.")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("blackjack.domain.participant.provider.PlayersTestProvider#provideForPlayerNameDuplicatedExceptionTest")
    void playerNameDuplicatedExceptionTest(final List<String> playerNames) {
        final Deck deck = generateDeck(Collections.emptyList());
        assertThatThrownBy(() -> Players.readyToPlay(playerNames, deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어명은 중복될 수 없습니다.");
    }

    @DisplayName("플레이어는 8명 이하여야 합니다.")
    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("blackjack.domain.participant.provider.PlayersTestProvider#provideForPlayerCountTooManyExceptionTest")
    void playerCountTooManyExceptionTest(final List<String> playerNames) {
        final Deck deck = generateDeck(Collections.emptyList());
        assertThatThrownBy(() -> Players.readyToPlay(playerNames, deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어는 8명 이하여야 합니다.");
    }

    @DisplayName("각 플레이어는 딜러와의 승패를 계산할 수 있어야 한다.")
    @MethodSource("blackjack.domain.participant.provider.PlayersTestProvider#provideForJudgeWinnersTest")
    @ParameterizedTest
    void judgeWinnersTest(final List<String> names,
                          final List<Card> initializedCards,
                          final Map<String, MatchStatus> expectedResultOfPlayers) {
        final Deck deck = generateDeck(initializedCards);
        final Dealer dealer = Dealer.readyToPlay(deck);
        final Players players = Players.readyToPlay(names, deck);

        final Map<String, MatchStatus> actualResultOfPlayers = players.judgeWinners(dealer).getResultOfPlayers();
        assertThat(actualResultOfPlayers).isEqualTo(expectedResultOfPlayers);
    }

    private Deck generateDeck(final List<Card> initializedCards) {
        manualCardStrategy.initCards(initializedCards);
        return Deck.generate(manualCardStrategy);
    }

}
