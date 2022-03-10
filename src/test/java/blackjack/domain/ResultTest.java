package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    @DisplayName("승리한 경우를 계산한다.")
    void findWinningResult() {
        Player dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.FIVE));

        Gamer judy = new Gamer("judy");
        judy.receiveCard(new Card(Suit.CLOVER, Denomination.SIX));

        assertThat(Result.findResult(dealer.calculateResult()
                , judy.calculateResult())).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("딜러가 기준보다 높고, 게이머가 기준보다 낮아 승리한 경우를 계산한다.")
    void findWinningResultDealerOverStandard() {
        Player dealer = new Dealer();
        receiveCardOverStandard(dealer);

        Gamer judy = new Gamer("judy");
        receiveCardBelowStandard(judy);

        assertThat(Result.findResult(dealer.calculateResult()
                , judy.calculateResult())).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("게이머만 21이 넘어 패배한 경우를 계산한다.")
    void findLosingResultOverStandard() {
        Player dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.FIVE));

        Gamer judy = new Gamer("judy");
        receiveCardOverStandard(judy);

        assertThat(Result.findResult(dealer.calculateResult()
                , judy.calculateResult())).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러와 게이머 모두 21이 넘어 게이머가 패배한 경우를 계산한다.")
    void findLosingResultDealerAndGamerOverStandard() {
        Player dealer = new Dealer();
        receiveCardOverStandard(dealer);

        Gamer judy = new Gamer("judy");
        receiveCardOverStandard(judy);

        assertThat(Result.findResult(dealer.calculateResult()
                , judy.calculateResult())).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("패배한 경우를 계산한다.")
    void findLosingResult() {
        Player dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.FIVE));

        Gamer judy = new Gamer("judy");
        judy.receiveCard(new Card(Suit.CLOVER, Denomination.FOUR));

        assertThat(Result.findResult(dealer.calculateResult()
                , judy.calculateResult())).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("무승부인 경우를 계산한다.")
    void findDrawResult() {
        Player dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.FIVE));

        Gamer judy = new Gamer("judy");
        judy.receiveCard(new Card(Suit.CLOVER, Denomination.FIVE));

        assertThat(Result.findResult(dealer.calculateResult()
                , judy.calculateResult())).isEqualTo(Result.DRAW);
    }

    private void receiveCardOverStandard(final Player player) {
        player.receiveCard(new Card(Suit.CLOVER, Denomination.JACK));
        player.receiveCard(new Card(Suit.DIAMOND, Denomination.JACK));
        player.receiveCard(new Card(Suit.SPADE, Denomination.TWO));
    }

    private void receiveCardBelowStandard(final Player player) {
        player.receiveCard(new Card(Suit.CLOVER, Denomination.JACK));
        player.receiveCard(new Card(Suit.DIAMOND, Denomination.NINE));
        player.receiveCard(new Card(Suit.SPADE, Denomination.TWO));
    }

}