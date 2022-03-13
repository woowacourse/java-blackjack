package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import blackjack.domain.participant.Dealer;
import blackjack.dto.DealerResultDto;
import blackjack.dto.PlayerResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("추가 카드가 필요한 경우 참을 반환한다")
    void needMoreCardWhenTrue() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.HEART, Denomination.TWO));

        assertThat(dealer.isAbleToAddCard()).isTrue();
    }

    @Test
    @DisplayName("추가 카드가 필요없는 경우 거짓을 반환한다")
    void needMoreCardWhenFalse() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Symbol.HEART, Denomination.NINE));
        dealer.addCard(new Card(Symbol.SPADE, Denomination.NINE));

        assertThat(dealer.isAbleToAddCard()).isFalse();
    }

    @Test
    @DisplayName("플레이어의 승리는 딜러의 패배로, 플레이어의 패배는 딜러의 승리로 계산한다.")
    void calculateTotalResult() {
        List<PlayerResultDto> playersResult = new ArrayList<>();
        playersResult.add(new PlayerResultDto("a", true));
        playersResult.add(new PlayerResultDto("b", true));
        playersResult.add(new PlayerResultDto("c", false));

        DealerResultDto dealerResult = new Dealer().computeResult(playersResult);

        assertThat(dealerResult.getWinCount()).isEqualTo(1);
        assertThat(dealerResult.getLoseCount()).isEqualTo(2);
    }
}
