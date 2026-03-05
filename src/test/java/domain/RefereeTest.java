package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Emblem;
import domain.card.Grade;
import org.junit.jupiter.api.Test;

class RefereeTest {

    @Test
    void 딜러와_플레이어의_승패를_비교한다() {
        // given
        Participant dealer = new Participant(new Name("dealer"), new Hand());
        Participant player = new Participant(new Name("player"), new Hand());

        dealer.receiveCard(new Card(Emblem.CLOVER, Grade.TEN));
        player.receiveCard(new Card(Emblem.CLOVER, Grade.ACE));

        Participants players = new Participants();
        players.add(player);

        Referee referee = new Referee();

        // when
        GameStatistics statistics = referee.judge(dealer, players);

        // then
        assertThat(statistics.findGameResultByPlayer(player)).isEqualTo(GameResult.WIN);
    }
}
