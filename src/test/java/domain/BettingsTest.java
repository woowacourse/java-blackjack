package domain;

import static domain.HandsTestFixture.sum21Size2;

import domain.participant.Name;
import domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingsTest {

    @DisplayName("참여자의 배팅 금액을 저장한다.")
    @Test
    void savePlayerBetAmount() {
        // given
        final Bettings bettings = new Bettings();
        final Player player = new Player(new Name("제제"), sum21Size2);
        final BetAmount betAmount = new BetAmount(10_000);

        // when
        bettings.save(player, betAmount);

        // then
        Assertions.assertThat(bettings.findBy(player)).isEqualTo(new BetAmount(10_000));
    }
}
