package blackjack.domain.player;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardPattern.SPADE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    private static List<Card> createCards(Card firstCard, Card... remainCard) {
        final List<Card> cards = new ArrayList<>();
        cards.add(firstCard);
        cards.addAll(Arrays.stream(remainCard)
                .collect(Collectors.toList()));
        return cards;
    }

    @Test
    @DisplayName("중복된 이름들로 생성 시 예외를 발생시킨다.")
    void createExceptionByDuplication() {
        final Player firstplayer = ParticipatingPlayer
                .init("user", createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)));
        final Player secondplayer = ParticipatingPlayer
                .init("user", createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)));
        final List<Player> players = Arrays.asList(firstplayer, secondplayer);

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름 간에 중복이 있으면 안됩니다.");
    }

    @Test
    @DisplayName("현재 턴의 플레이어가 버스트될 경우 다음 플레이어로 턴을 넘긴다.")
    void drawCurrentPlayerIsBust() {
        final Player player = ParticipatingPlayer
                .init("user", createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)));
        final Players players = new Players(Collections.singletonList(player));
        players.drawCurrentPlayer(Card.of(SPADE, JACK));
        assertTrue(players.isAllTurnEnd());
    }

    @Test
    @DisplayName("모든 플레이어의 턴이 종료되었는데 드로우하려고하면 예외가 발생해야 한다.")
    void drawCurrentPlayerExceptionByEndAllTurn() {
        final Player player =
                ParticipatingPlayer.init("user", createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)));
        final Players players = new Players(Collections.singletonList(player));
        players.drawCurrentPlayer(Card.of(SPADE, JACK));

        assertThatThrownBy(() -> players.drawCurrentPlayer(Card.of(SPADE, A)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모든 턴이 종료되었습니다.");
    }

    @Test
    @DisplayName("모든 턴이 종료되었을 때 턴 증가를 할 수 없다.")
    void turnToNextPlayerExceptionByEndAllTurn() {
        final Player player =
                ParticipatingPlayer.init("user", createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)));
        final Players players = new Players(Collections.singletonList(player));
        players.turnToNextPlayer();

        assertThatThrownBy(() -> players.turnToNextPlayer())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모든 턴이 종료되었습니다.");
    }

    @Test
    @DisplayName("모든 턴이 종료되었을 때 현재 플레이어 정보를 반환하려하면 예외가 발생한다.")
    void getCurrentTurnPlayerInfoExceptionByEndAllTurn() {
        final Player player = ParticipatingPlayer.init("user", createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN)));
        final Players players = new Players(Collections.singletonList(player));
        players.turnToNextPlayer();

        assertThatThrownBy(() -> players.getCurrentTurnPlayerInfo())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모든 턴이 종료되었습니다.");
    }
}