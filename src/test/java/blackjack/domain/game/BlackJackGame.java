package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackGame {

    @DisplayName("둘다 버스트인 상황에서 승자 비교")
    @Test
    void testCalculateBothAreBust(){
        Dealer dealer = new Dealer();
        Gambler gambler = new Gambler(new Name("a"));

        dealer.receiveCard(new Card(Suit.CLUB, Denomination.NINE));
        dealer.receiveCard(new Card(Suit.CLUB, Denomination.KING));
        dealer.receiveCard(new Card(Suit.CLUB, Denomination.KING));

        gambler.receiveCard(new Card(Suit.CLUB, Denomination.KING));
        gambler.receiveCard(new Card(Suit.CLUB, Denomination.KING));
        gambler.receiveCard(new Card(Suit.CLUB, Denomination.KING));

        assertThat(dealer.calculateWinOrLose(gambler)).isEqualTo(WinOrLose.DRAW);
    }

    @DisplayName("둘다 bust가 아닌 상황에서 승자 비교")
    @Test
    void testCalculate(){
        Dealer dealer = new Dealer();
        Gambler gambler = new Gambler(new Name("a"));

        dealer.receiveCard(new Card(Suit.CLUB, Denomination.NINE));
        dealer.receiveCard(new Card(Suit.CLUB, Denomination.KING));

        gambler.receiveCard(new Card(Suit.CLUB, Denomination.KING));
        gambler.receiveCard(new Card(Suit.CLUB, Denomination.KING));

        assertThat(dealer.calculateWinOrLose(gambler)).isEqualTo(WinOrLose.LOSE);
    }

    @Test
    void compareScoreTest(){
        Dealer dealer = new Dealer();
        Gambler gambler = new Gambler(new Name("a"));

        dealer.receiveCard(new Card(Suit.CLUB, Denomination.ACE));
        dealer.receiveCard(new Card(Suit.CLUB, Denomination.KING));

        gambler.receiveCard(new Card(Suit.CLUB, Denomination.KING));
        gambler.receiveCard(new Card(Suit.CLUB, Denomination.KING));

        assertThat(dealer.getCards().compareCardsScore(gambler.getCards()))
                .isEqualTo(WinOrLose.WIN);
    }
}
