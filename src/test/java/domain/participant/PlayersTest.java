package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Deck;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessages;

class PlayersTest {

    private Players players;
    private Deck deck;

    @BeforeEach
    void init() {
        players = Players.of(List.of("runa", "kun"), List.of(1000, 2000));
        deck = Deck.of(Card.getShuffledCardCache());
    }

    @Test
    @DisplayName("입력 받은 플레이어 이름만큼 Player 객체가 잘 만들어졌는지 확인한다.")
    void playersCreateTest() {
        assertThat(players.getPlayers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("입력 받은 플레이어 이름이 빈 이름일 경우 에러를 발생시킨다.")
    void playersCommaErrorTest() {

        assertThatThrownBy(() -> Players.of(List.of("", "runa"), List.of(1000, 2000)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessages.EMPTY_NAME_ERROR);
    }

    @Test
    @DisplayName("첫 턴에서 모든 참가자가 카드를 두개씩 뽑는지 확인")
    void initialTurnTest() {
        players.runInitialTurn(deck);
        int actual = (int) players.getPlayers().stream()
            .filter(participant -> participant.getCards().size() == 2)
            .count();

        assertThat(actual).isEqualTo(2);
    }
}