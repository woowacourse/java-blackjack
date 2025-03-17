package bet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import player.Participant;
import player.Player;

class BetsTest {

    @Test
    void 참여자의_베팅_금액을_추가한다() {
        // given
        final int amount = 10000;
        Player player = new Participant("시소");
        Bets bets = new Bets();

        // when & then
        Assertions.assertThatNoException()
                .isThrownBy(() -> bets.addBet(player, amount));
    }

    @Test
    void 참여자가_존재하지_않는데_베팅한다면_예외가_발생한다() {
        // given
        final int amount = 10000;
        Player player = new Participant("시소");
        Player noSuchPlayer = new Participant("omg");

        Bets bets = new Bets();
        bets.addBet(player, amount);

        // when & then
        Assertions.assertThatThrownBy(() -> bets.getBet(noSuchPlayer))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 참여자의_베팅_금액을_반환한다() {
        // given
        final int amount = 10000;
        Player player = new Participant("시소");
        Bets bets = new Bets();
        bets.addBet(player, amount);

        // when & then
        Assertions.assertThat(bets.getBet(player).getAmount())
                .isEqualTo(amount);
    }
}
