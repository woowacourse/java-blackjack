package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.TrumpNumber;
import blackjack.model.card.TrumpSymbol;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("Dealer가 정상적으로 생성되는지 확인한다.")
    @Test
    void construct_Dealer() {
        Player liver = new Dealer();

        assertThat(liver).isInstanceOf(Dealer.class);
    }

    @DisplayName("카드 점수의 합이 17점 이상이면 true를 반환한다.")
    @Test
    void isImpossibleHit_true() {
        Dealer dealer = new Dealer();
        dealer.receive(new Card(TrumpNumber.NINE, TrumpSymbol.CLOVER));
        dealer.receive(new Card(TrumpNumber.JACK, TrumpSymbol.CLOVER));

        assertThat(dealer.isImpossibleHit()).isTrue();
    }
}
