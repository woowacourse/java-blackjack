package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Hand;
import blackjack.domain.MatchResult;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import blackjack.dto.DealerGameResult;
import blackjack.dto.PlayerGameResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러가 정상적으로 생성된다.")
    @Test
    void createDealer() {
        Dealer dealer = Dealer.from();

        assertThat(dealer.getNickname()).isEqualTo("딜러");
        assertThat(dealer.isDealer()).isTrue();
    }

    @DisplayName("딜러는 총합이 16점 이하일 때 카드를 받는다.")
    @Test
    void isDealerDraw() {
        Dealer dealer = Dealer.from();
        Hand cards16 = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SIX, Suit.HEART)
        ));

        dealer.receiveCard(cards16.getCards());
        assertThat(dealer.isDealerDraw()).isTrue();
    }

    @DisplayName("딜러는 총합이 16점 초과일 때 카드를 받지 않는다.")
    @Test
    void isDealerNotDraw() {
        Dealer dealer = Dealer.from();
        Hand cards17 = Hand.from(List.of(
            new Card(Rank.TEN, Suit.SPADE),
            new Card(Rank.SEVEN, Suit.HEART)
        ));

        dealer.receiveCard(cards17.getCards());
        assertThat(dealer.isDealerDraw()).isFalse();
    }

    @DisplayName("딜러의 첫 번째 카드 이름을 반환한다.")
    @Test
    void getFirstCard() {
        Dealer dealer = Dealer.from();
        Hand cards = Hand.from(List.of(
            new Card(Rank.ACE, Suit.SPADE),
            new Card(Rank.TEN, Suit.HEART)
        ));

        dealer.receiveCard(cards.getCards());

        assertThat(dealer.getFirstCard()).isEqualTo("A스페이드");
    }

    @Test
    @DisplayName("딜러의 수익률 계산은 참가자들 수익의 음수값이다.")
    void calculateDealerProfitResult() {
        // given
        Dealer dealer = Dealer.from();
        PlayerGameResult winningResult = new PlayerGameResult("boye", MatchResult.WIN, 10000);
        PlayerGameResult tieResult = new PlayerGameResult("sumin", MatchResult.TIE, 0);
        PlayerGameResult losingResult = new PlayerGameResult("zzang", MatchResult.LOSE, -5000);
        List<PlayerGameResult> playerGameResults = List.of(
            winningResult,
            tieResult,
            losingResult
        );

        // when
        DealerGameResult dealerGameResult = dealer.calculateDealerProfitResult(playerGameResults);

        // then
        assertThat(dealerGameResult.profit()).isEqualTo(-5000);
    }
}
