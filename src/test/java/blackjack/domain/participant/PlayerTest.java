package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.gameresult.Batting;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static blackjack.domain.card.Kind.SPADE;
import static blackjack.domain.card.Value.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PlayerTest {
    private static Stream<Arguments> makeTestPlayer() {
        Player testPlayer = new Player(new Name("pobi"), Batting.from(1.0));
        return Stream.of(arguments(testPlayer));
    }

    @DisplayName("플레이어가 카드를 추가하면, 카드패에 한장이 추가된다")
    @ParameterizedTest
    @MethodSource("makeTestPlayer")
    void should_AddCard(Player testPlayer) {
        testPlayer.addCard(new Card(SPADE, ACE));
        assertThat(testPlayer.getHandsCards()).hasSize(1);
    }

    @DisplayName("플레이어가 버스트가 아니라면, 한장을 더 받을 수 있다")
    @ParameterizedTest
    @MethodSource("makeTestPlayer")
    void should_getFinishedState_When_PlayerBust(Player testPlayer) {
        testPlayer.addCard(new Card(SPADE, JACK));
        testPlayer.addCard(new Card(SPADE, QUEEN));

        assertTrue(testPlayer::canAddCard);
    }

    @DisplayName("플레이어가 버스트 상태이면 한장을 더 받을 수 없다")
    @ParameterizedTest
    @MethodSource("makeTestPlayer")
    void should_getFinishedState_When_PlayerReject_Draw(Player testPlayer) {
        testPlayer.addCard(new Card(SPADE, JACK));
        testPlayer.addCard(new Card(SPADE, QUEEN));
        testPlayer.addCard(new Card(SPADE, KING));

        assertFalse(testPlayer::canAddCard);
    }
}
