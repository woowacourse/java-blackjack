package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.profit.ProfitDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("추가 카드가 필요한 경우 참을 반환한다")
    void isHittableWhenTrue() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.HEART, Denomination.TWO));

        assertThat(dealer.isHittable()).isTrue();
    }

    @Test
    @DisplayName("추가 카드가 필요없는 경우 거짓을 반환한다")
    void isHittableWhenFalse() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.HEART, Denomination.NINE));
        dealer.addCard(new Card(Symbol.SPADE, Denomination.NINE));

        assertThat(dealer.isHittable()).isFalse();
    }

    @Test
    @DisplayName("딜러의 최종 수익은 플레이어들의 수익 합에 대한 보수이다")
    void computeProfit() {
        List<ProfitDto> profitOfPlayers = new ArrayList<>();
        profitOfPlayers.add(new ProfitDto(new Player("pobi"), 1000));
        profitOfPlayers.add(new ProfitDto(new Player("woni"), 2000));
        profitOfPlayers.add(new ProfitDto(new Player("jason"), -5000));

        assertThat(new Dealer().computeProfit(profitOfPlayers).getProfit()).isEqualTo(2000);
    }
}
