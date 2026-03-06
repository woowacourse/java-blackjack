package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Emblem;
import domain.card.Grade;
import domain.participant.Hand;
import domain.participant.Name;
import domain.participant.Participant;
import org.junit.jupiter.api.Test;

class RefereeTest {

    @Test
    void 딜러와_플레이어의_승패를_비교한다() {
        // given

        Hand dealerHand = new Hand();
        Participant participant = new Participant(new Name("dealer"), dealerHand);
        Dealer dealer = new Dealer(participant);

        Hand playerHand = new Hand();
        Participant player = new Participant(new Name("player"), playerHand);

        dealerHand.receiveCard(new Card(Emblem.CLOVER, Grade.TEN));
        playerHand.receiveCard(new Card(Emblem.CLOVER, Grade.ACE));

        Participants players = new Participants();
        players.add(player);

        Referee referee = new Referee();

        // when
        GameStatistics statistics = referee.judge(dealer, players);

        // then
        assertAll(
                () -> assertThat(statistics.findGameResultBy(player)).isEqualTo(GameResult.WIN),
                () -> assertThat(statistics.getDealerResult().get(GameResult.WIN)).isEqualTo(0),
                () -> assertThat(statistics.getDealerResult().get(GameResult.LOSE)).isEqualTo(1),
                () -> assertThat(statistics.getDealerResult().get(GameResult.DRAW)).isEqualTo(0)
        );
    }
}
