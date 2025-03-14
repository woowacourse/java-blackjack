package domain;

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
        assertThat(bettingRound.getPlayerBetAmount(player)).isEqualTo(10000);
    }
}
