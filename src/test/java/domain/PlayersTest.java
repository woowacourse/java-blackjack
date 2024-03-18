package domain;

import static domain.BetAmountFixture.betAmount10_000;
import static domain.HandsTestFixture.bustHands;
import static domain.HandsTestFixture.noBustHands;

import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("참가자 중 버스트 되지 않은 참가자가 있다면 isAllBust가 False를 반환한다.")
    void isAllBustFalse() {
        //given
        final Player bustPlayer = new Player(new Name("레디"), bustHands, betAmount10_000);
        final Player noBustPlayer = new Player(new Name("제제"), noBustHands, betAmount10_000);
        final Players players = new Players(List.of(bustPlayer, noBustPlayer));

        //when && then
        Assertions.assertThat(players.isAllBust()).isFalse();
    }

    @Test
    @DisplayName("모든 참가자가 버스트되면 isAllBust가 True를 반환한다.")
    void isAllBustTrue() {
        //given
        final Player bustPlayer1 = new Player(new Name("레디"), bustHands, betAmount10_000);
        final Player bustPlayer2 = new Player(new Name("제제"), bustHands, betAmount10_000);
        final Player bustPlayer3 = new Player(new Name("수달"), bustHands, betAmount10_000);
        final Player bustPlayer4 = new Player(new Name("피케이"), bustHands, betAmount10_000);

        Players players = new Players(List.of(bustPlayer1, bustPlayer2, bustPlayer3, bustPlayer4));

        //when && then
        Assertions.assertThat(players.isAllBust()).isTrue();
    }
}
