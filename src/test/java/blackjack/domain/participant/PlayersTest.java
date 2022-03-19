package blackjack.domain.participant;

import static blackjack.Fixture.SPADE_FIVE;
import static blackjack.Fixture.SPADE_TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.ManualDeckGenerator;
import blackjack.domain.card.generator.RandomDeckGenerator;

public class PlayersTest {

    private final ManualDeckGenerator manualDeckGenerator = new ManualDeckGenerator();

    private Deck generateDeck(final List<Card> initializedCards) {
        manualDeckGenerator.initCards(initializedCards);
        return Deck.generate(manualDeckGenerator);
    }

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

    @DisplayName("플레이어 이름으로 플레이어를 찾을 수 있어야 한다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.participant.provider.PlayersTestProvider#provideForPlayerNameNotExistExceptionTest")
    void playerNameNotExistExceptionTest(final List<String> playerNames, final String notExistedPlayerName) {
        final Deck deck = Deck.generate(new RandomDeckGenerator());
        final Players players = Players.readyToPlay(playerNames, deck);

        assertThatThrownBy(() -> players.getPlayerCards(notExistedPlayerName))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("플레이어를 찾을 수 없습니다.");
    }

    @DisplayName("턴이 종료되지 않은 플레이어가 존재하는지 확인할 수 있어야 한다.")
    @Test
    void isAnyPlayerNotFinishedTest() {
        final ManualDeckGenerator manualDeckGenerator = new ManualDeckGenerator();
        manualDeckGenerator.initCards(List.of(SPADE_TEN, SPADE_FIVE));
        final Deck deck = Deck.generate(manualDeckGenerator);
        final Players players = Players.readyToPlay(List.of("player"), deck);

        assertThat(players.isAnyPlayerNotFinished()).isTrue();
    }

    @DisplayName("플레이어 이름으로 해당 플레이어가 보유한 카드 패를 확인할 수 있어야 한다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.participant.provider.PlayersTestProvider#provideForGetPlayerCardsTest")
    void getPlayerCardsTest(final List<Card> expectedCards, final String playerName) {
        final Deck deck = generateDeck(expectedCards);
        final Players players = Players.readyToPlay(List.of(playerName), deck);

        final List<Card> actualCards = players.getPlayerCards(playerName);
        assertThat(actualCards).isEqualTo(expectedCards);
    }

}
