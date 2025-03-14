package domain;

import static domain.card.Rank.FIVE;
import static domain.card.Rank.NINE;
import static domain.card.Rank.TEN;
import static domain.card.Rank.TWO;
import static domain.card.Suit.CLOVER;
import static domain.card.Suit.DIAMOND;
import static domain.card.Suit.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardHand;
import domain.game.BettingRound;
import domain.participant.Player;
import fixture.CardFixture;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {

    @Test
    @DisplayName("플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다")
    void playerBettingTest() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(NINE, DIAMOND), CardFixture.of(TWO, CLOVER)));
        Player player = new Player("pobi", cardHand);
        BettingRound bettingRound = new BettingRound();
        // when
        bettingRound.bet(player, 10000);
        // then
        assertThat(bettingRound.getPlayerInitialBetAmount(player)).isEqualTo(10000);
    }

    @Test
    @DisplayName("카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다")
    void testBetIsLostIfPlayerBusts() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TEN, HEART), CardFixture.of(NINE, DIAMOND)));
        Player player = new Player("pobi", cardHand);
        BettingRound bettingRound = new BettingRound();
        // when
        bettingRound.bet(player, 10000);
        player.hit(CardFixture.of(FIVE, CLOVER));
        bettingRound.betIsLostIfPlayerBusts(player);
        // then
        assertThat(bettingRound.getPlayerFinalBetAmount(player)).isEqualTo(0);
    }
}
