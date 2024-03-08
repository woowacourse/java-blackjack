package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HandGenerator;
import blackjack.domain.card.Number;
import blackjack.testutil.CustomDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerIteratorTest {
    private static final String VALID_NAME = "감자";
    @DisplayName("현재 플레이어를 올바르게 반환한다.")
    @Test
    void getPlayerTest() {
        Players players = createPlayers(List.of(Number.JACK, Number.QUEEN));
        Player expectedPlayer = getPlayerByIndex(players, 0);
        PlayerIterator playerIterator = new PlayerIterator(players);
        assertThat(playerIterator.getPlayer()).isEqualTo(expectedPlayer);
    }

    @DisplayName("현재 플레이어가 Hit이 불가능해지면 순서를 증가시킨다.")
    @Test
    void updateByCurrentPlayerHitTest() {
        Players players = createPlayers(List.of(Number.JACK, Number.QUEEN, Number.ACE, Number.EIGHT));
        Player expectedPlayer = getPlayerByIndex(players, 1);
        PlayerIterator playerIterator = new PlayerIterator(players);
        bustFirstPlayer(players);
        playerIterator.update(false);
        assertThat(playerIterator.getPlayer()).isEqualTo(expectedPlayer);
    }

    @DisplayName("모든 플레이어를 순회하면 hasNext()가 거짓을 반환한다.")
    @Test
    void hasNextTest() {
        Players players = createPlayers(List.of(Number.ACE, Number.JACK, Number.FIVE, Number.SIX));
        PlayerIterator playerIterator = new PlayerIterator(players);
        playerIterator.update(false);
        playerIterator.update(false);
        assertFalse(playerIterator.hasNext());
    }

    private static void bustFirstPlayer(Players players) {
        Player firstPlayer = getPlayerByIndex(players, 0);
        firstPlayer.addCard(new CustomDeck(List.of(Number.EIGHT)));
    }

    private static Players createPlayers(List<Number> numbers) {
        List<Name> playerNames = IntStream.range(0, numbers.size() / 2)
                .mapToObj((number) -> new Name(VALID_NAME))
                .toList();
        Deck deck = new CustomDeck(numbers);
        HandGenerator handGenerator = new HandGenerator(deck);
        return new Players(playerNames, handGenerator);
    }

    private static Player getPlayerByIndex(Players players, int index) {
        return players.getValues().get(index);
    }
}
