package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PlayerTest {

    private static Stream<Arguments> getTestPlayer() {
        Map<Name, Batting> playerNamesAndBattings = new HashMap<>();
        playerNamesAndBattings.put(new Name("pobi"), Batting.from(1.0));
        Player testPlayer = new Players(playerNamesAndBattings)
                .getPlayers()
                .get(0);
        return Stream.of(arguments(testPlayer));
    }

    @DisplayName("플레이어가 카드 추가를 승낙하면, 카드패에 한장이 추가된다")
    @ParameterizedTest
    @MethodSource("getTestPlayer")
    void should_AddCard_When_PlayerAcceptDraw(Player testPlayer) {
        Deck deck = Deck.createShuffledDeck();
        testPlayer.draw(() -> Boolean.TRUE, deck);
        assertThat(testPlayer.getHandsCards()).hasSize(1);
    }

    @DisplayName("플레이어가 카드 추가를 거부하면, 카드는 추가되지 않는다")
    @ParameterizedTest
    @MethodSource("getTestPlayer")
    void should_NotAddCard_When_PlayerRejectDraw(Player testPlayer) {
        Deck deck = Deck.createShuffledDeck();
        testPlayer.draw(() -> Boolean.FALSE, deck);
        assertThat(testPlayer.getHandsCards()).isEmpty();
    }

    @DisplayName("플레이어가 카드 추가를 거부하면, 플레이어가 FINISHED 상태임 전한다")
    @ParameterizedTest
    @MethodSource("getTestPlayer")
    void should_getFinishedState_When_PlayerReject_Draw(Player testPlayer) {
        Deck deck = Deck.createShuffledDeck();
        PlayerState playerState = testPlayer.draw(() -> Boolean.FALSE, deck);
        assertThat(playerState).isEqualTo(PlayerState.FINISHED);
    }

    @DisplayName("플레이어가 버스트 상태일 때, 플레이어가 FINISHED 상태임을 전한다")
    @ParameterizedTest
    @MethodSource("getTestPlayer")
    void should_getFinishedState_When_PlayerBust(Player testPlayer) {
        Deck deck = Deck.createShuffledDeck();
        testPlayer.addCard(Card.create(0)); // spade ace -1
        testPlayer.addCard(Card.create(9)); // spade 10 - 10
        testPlayer.addCard(Card.create(10)); // spade J - 10

        PlayerState playerState = testPlayer.draw(() -> Boolean.TRUE, deck);

        assertThat(playerState).isEqualTo(PlayerState.FINISHED);
    }

    @DisplayName("플레이어가 카드를 더 받을 수 있을 때, 플레이어가 RUNNING 상태임을 전한다")
    @ParameterizedTest
    @MethodSource("getTestPlayer")
    void should_getRunningState_When_PlayerCanDrawMoreCard(Player testPlayer) {
        Deck deck = Deck.createShuffledDeck();

        PlayerState playerState = testPlayer.draw(() -> Boolean.TRUE, deck);

        assertThat(playerState).isEqualTo(PlayerState.RUNNING);
    }
}
