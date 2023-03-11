package domain.betting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Hand;
import domain.participant.Name;
import domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingMoneyByPlayerTest {

    @Test
    @DisplayName("플레이어의 배팅 금액을 찾아서 반환한다.")
    void findBettingMoneyByPlayer() {
        // given
        Player player = new Player(
                new Name("seongha"),
                new Hand(List.of(new Card(Suit.SPADE, Denomination.SIX), new Card(Suit.DIAMOND, Denomination.TEN))));
        BettingMoney bettingMoney = new BettingMoney(10000);

        // when
        BettingMoneyByPlayer bettingMoneyByPlayer = new BettingMoneyByPlayer();
        bettingMoneyByPlayer.putPlayerBettingMoney(player, bettingMoney);

        // then
        Assertions.assertThat(bettingMoneyByPlayer.findBettingMoneyByPlayer(player)).isEqualTo(bettingMoney);
    }
}
