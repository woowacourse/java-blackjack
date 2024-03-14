package domain;

import static domain.HandsTestFixture.blackJack;
import static domain.HandsTestFixture.bustHands;
import static domain.HandsTestFixture.sum20Size3;
import static domain.HandsTestFixture.sum21Size2;

import domain.bet.BetAmount;
import domain.bet.Bettings;
import domain.participant.Name;
import domain.participant.Player;
import java.util.AbstractMap;
import java.util.Map.Entry;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingsTest {

    final Bettings bettings = new Bettings();

    @DisplayName("참여자의 배팅 금액을 저장한다.")
    @Test
    void savePlayerBetAmount() {
        // given
        final Player player = new Player(new Name("제제"), sum21Size2);
        final BetAmount betAmount = BetAmount.from(10_000);

        // when
        bettings.save(player, betAmount);

        // then
        Assertions.assertThat(bettings.findBy(player).getAmount()).isEqualTo(10_000);
    }

    @DisplayName("참여자가 블랙잭이면서 승리하면 배팅 금액의 1.5배를 받는다.")
    @Test
    void winBlackJack() {
        // given
        final Player player = new Player(new Name("제제"), blackJack);
        final BetAmount betAmount = BetAmount.from(10_000);
        final Entry<Player, Result> result = new AbstractMap.SimpleEntry<>(player, Result.WIN_BLACKJACK);

        // when
        bettings.save(player, betAmount);
        final BetAmount resultBetAmount = bettings.calculateBy(result);

        // then
        Assertions.assertThat(resultBetAmount.getAmount()).isEqualTo((int) (10_000 * 1.5)); //TODO int 괜찮은지 확인하기 ??
    }

    @DisplayName("참여자가 블랙잭이 아니면서 승리하면 배팅 금액의 1배를 받는다.")
    @Test
    void win() {
        // given
        final Player player = new Player(new Name("제제"), sum20Size3);
        final BetAmount betAmount = BetAmount.from(10_000);
        final Entry<Player, Result> result = new AbstractMap.SimpleEntry<>(player, Result.WIN);

        // when
        bettings.save(player, betAmount);
        final BetAmount resultBetAmount = bettings.calculateBy(result);

        // then
        Assertions.assertThat(resultBetAmount.getAmount()).isEqualTo(10_000);
    }

    @DisplayName("참여자가 패배하면 배팅 금액을 모두 잃는다.")
    @Test
    void lose() {
        // given
        final Player player = new Player(new Name("제제"), bustHands);
        final BetAmount betAmount = BetAmount.from(10_000);
        final Entry<Player, Result> result = new AbstractMap.SimpleEntry<>(player, Result.LOSE);

        // when
        bettings.save(player, betAmount);
        final BetAmount resultBetAmount = bettings.calculateBy(result);

        // then
        Assertions.assertThat(resultBetAmount.getAmount()).isEqualTo((-1) * 10_000);
    }

    @DisplayName("무승부이면 금액을 돌려받는다.")
    @Test
    void tie() {
        final Player player = new Player(new Name("제제"), sum21Size2);
        final BetAmount betAmount = BetAmount.from(10_000);
        final Entry<Player, Result> result = new AbstractMap.SimpleEntry<>(player, Result.TIE);

        // when
        bettings.save(player, betAmount);
        final BetAmount resultBetAmount = bettings.calculateBy(result);

        // then
        Assertions.assertThat(resultBetAmount.getAmount()).isEqualTo(0);
    }
}
