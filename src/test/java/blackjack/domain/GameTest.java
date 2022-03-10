package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Status;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GameTest {

    @Test
    @DisplayName("게임을 초기화 한다.")
    void initGame() {
        // give
        Game game = new Game(CardFactory.createNoShuffle(), List.of("pobi", "rick"));

        // when
        final int actual = game.getParticipantCount();

        // then
        assertThat(actual).isEqualTo(3);
    }

    @ParameterizedTest
    @CsvSource(value = {"pobi,rick:46", "pobi,rick,json:44"}, delimiter = ':')
    @DisplayName("모든 참가자에게 카드를 2장씩 분배한다.")
    void initParticipants(String names, int expected) {
        // give
        Game game = new Game(CardFactory.createNoShuffle(), List.of(names.split(",")));

        // when
        game.init();
        int actual = game.getRemainAmount();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"HIT:51", "STAY:52"}, delimiter = ':')
    @DisplayName("상태가 HIT이면 플레이어가 카드를 1장 뽑는다.")
    void drawCard_HIT(Status status, int expected) {
        // give
        final String name = "pobi";
        Game game = new Game(CardFactory.createNoShuffle(), List.of(name));
        final List<Player> players = game.getPlayers();

        // when
        game.drawPlayerCard(players.get(0), status);
        final int actual = game.getRemainAmount();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 턴을 진행한다")
    void drawDealerCard() {
        // give
        Game game = new Game(CardFactory.createNoShuffle(), List.of("pobi"));

        // when
        game.drawDealerCard();
        int actual = game.getDealerScore();

        // then
        assertThat(actual).isEqualTo(20);
    }
}
