package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

class DealerResultsTest {

    @Test
    void 아직_저장되지_않은_플레이어에_대한_결과일_경우_예외가_발생하지_않는다() {
        // given
        Dealer dealer = new Dealer(new Hand());
        dealer.takeCard(new Card(CardSuit.CLUB, CardRank.ACE));
        DealerResult dealerResult = new DealerResult(GameResultType.TIE, new Score(dealer));

        DealerResults dealerResults = new DealerResults();

        Player player = new Player("히로", new Hand(), new BetAmount(1_000));

        // when
        assertThatCode(() -> dealerResults.add(player, dealerResult))
                .doesNotThrowAnyException();
    }

    @Test
    void 이미_저장된_플레이어에_대한_결과일_경우_예외가_발생한다() {
        // given
        Dealer dealer = new Dealer(new Hand());
        dealer.takeCard(new Card(CardSuit.CLUB, CardRank.ACE));
        DealerResult dealerResult = new DealerResult(GameResultType.TIE, new Score(dealer));

        DealerResults dealerResults = new DealerResults();

        Player player = new Player("히로", new Hand(), new BetAmount(1_000));
        dealerResults.add(player, dealerResult);

        // when
        assertThatThrownBy(() -> dealerResults.add(player, dealerResult))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 플레이어에_해당하는_결과를_반환한다() {
        // given
        Dealer dealer = new Dealer(new Hand());
        dealer.takeCard(new Card(CardSuit.CLUB, CardRank.ACE));
        DealerResult dealerResult = new DealerResult(GameResultType.TIE, new Score(dealer));

        DealerResults dealerResults = new DealerResults();

        Player player = new Player("히로", new Hand(), new BetAmount(1_000));
        dealerResults.add(player, dealerResult);

        // when
        DealerResult foundDealerResult = dealerResults.findResultByPlayer(player);

        // then
        assertThat(foundDealerResult).isEqualTo(dealerResult);
    }

    @Test
    void 존재하지_않는_플레이어에_대한_결과를_찾을_경우_예외를_던진다() {
        // given
        Dealer dealer = new Dealer(new Hand());
        dealer.takeCard(new Card(CardSuit.CLUB, CardRank.ACE));
        DealerResult dealerResult = new DealerResult(GameResultType.TIE, new Score(dealer));

        DealerResults dealerResults = new DealerResults();

        Player player = new Player("히로", new Hand(), new BetAmount(1_000));

        // when & then
        assertThatThrownBy(() -> dealerResults.findResultByPlayer(player))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
