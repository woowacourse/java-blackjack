package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Deck;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessages;

class PlayersTest {

    @Test
    @DisplayName("입력 받은 플레이어 이름만큼 Player 객체가 잘 만들어졌는지 확인한다.")
    void playersCreateTest() {
        List<String> names = Arrays.asList("kun", "runa");
        Players players = Players.of(names);

        assertThat(players.getPlayers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("입력 받은 플레이어 이름이 쉼표만 있을 경우 에러를 발생시킨다.")
    void playersCommaErrorTest() {
        List<String> names = Arrays.asList("kun", "", "", "runa");

        assertThatThrownBy(() -> Players.of(names))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessages.EMPTY_NAME_ERROR);
    }

    @Test
    @DisplayName("첫 턴에서 모든 참가자가 카드를 두개씩 뽑는지 확인")
    void initialTurnTest() {
        Players players = Players.of(Arrays.asList("runa", "kun"));
        Deck deck = Deck.initDeck(Card.values());

        players.runInitialTurn(deck);
        int actual = (int) players.getPlayers().stream()
            .filter(participant -> participant.getCards().size() == 2)
            .count();

        assertThat(actual).isEqualTo(2);
    }

    @Test
    @DisplayName("Players의 이름이 잘 변환이 되는지 확인한다.")
    void toNamesTest() {
        Players players = Players.of(Arrays.asList("kun", "runa"));
        List<String> playerNames = players.toNames();

        assertThat(playerNames.get(0)).isEqualTo("kun");
        assertThat(playerNames.get(1)).isEqualTo("runa");
    }
}