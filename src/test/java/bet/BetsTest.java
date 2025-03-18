package bet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import player.Participant;

class BetsTest {

    @Test
    void 참여자의_베팅_금액을_추가한다() {
        // given
        final int amount = 10000;
        Participant participant = new Participant("시소");
        Bets bets = new Bets();

        // when & then
        Assertions.assertThatNoException()
                .isThrownBy(() -> bets.addBet(participant, amount));
    }

    @Test
    void 참여자가_존재하지_않는데_베팅한다면_예외가_발생한다() {
        // given
        final int amount = 10000;
        Participant participant = new Participant("시소");
        Participant noSuchParticipant = new Participant("omg");

        Bets bets = new Bets();
        bets.addBet(participant, amount);

        // when & then
        Assertions.assertThatThrownBy(() -> bets.getBet(noSuchParticipant))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 참여자의_베팅_금액을_반환한다() {
        // given
        final int amount = 10000;
        Participant participant = new Participant("시소");
        Bets bets = new Bets();
        bets.addBet(participant, amount);

        // when & then
        Assertions.assertThat(bets.getBet(participant).getAmount())
                .isEqualTo(amount);
    }
}
