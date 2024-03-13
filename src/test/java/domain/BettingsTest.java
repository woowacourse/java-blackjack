package domain;

import static domain.HandsTestFixture.blackJack;
import static domain.HandsTestFixture.sum20Size3;
import static domain.HandsTestFixture.sum21Size2;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
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
        final BetAmount betAmount = new BetAmount(10_000);

        // when
        bettings.save(player, betAmount);

        // then
        Assertions.assertThat(bettings.findBy(player)).isEqualTo(new BetAmount(10_000));
    }

    @DisplayName("처음 2장의 카드가 블랙잭이고 딜러의 카드가 블랙잭이 아니면 배팅 금액의 1.5배를 반환한다.")
    @Test
    void blackJack() {
        // given
        final Player player = new Player(new Name("제제"), blackJack);
        final BetAmount betAmount = new BetAmount(10_000);
        final Dealer dealer = new Dealer(CardDeck.generate(), sum20Size3);

        // when
        bettings.save(player, betAmount);
        final BetAmount newBetAmount = bettings.calculate(player, dealer);

        // then
        Assertions.assertThat(newBetAmount).isEqualTo(new BetAmount((int) (10_000 * 1.5))); //TODO int 괜찮은지 확인하기 ??
    }
}
