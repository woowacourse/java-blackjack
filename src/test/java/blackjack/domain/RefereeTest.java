package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Emblem;
import blackjack.domain.card.Grade;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Hand;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

class RefereeTest {

    @Test
    void 딜러와_플레이어의_승패를_비교한다() {
        // given
        Dealer dealer = new Dealer(createHand(Grade.TEN, Grade.EIGHT)
        );
        Participants participants = new Participants(dealer,
                List.of(new Player("aa", createHand(Grade.TEN, Grade.ACE))));
        Referee referee = new Referee();
        // when
        GameStatistics statistics = referee.judge(participants);
        // then
        assertAll(
                () -> assertThat(statistics.getDealerResult().get(GameResult.WIN)).isEqualTo(0),
                () -> assertThat(statistics.getDealerResult().get(GameResult.LOSE)).isEqualTo(1),
                () -> assertThat(statistics.getDealerResult().get(GameResult.DRAW)).isEqualTo(0)
        );
    }

    private Hand createHand(Grade grade1, Grade grade2) {
        return new Hand(
                List.of(new Card(Emblem.CLOVER, grade1),
                        new Card(Emblem.DIAMOND, grade2)));
    }
}
