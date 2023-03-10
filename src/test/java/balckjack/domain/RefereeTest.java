package balckjack.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RefereeTest {

    private CardDeck dealer20;
    private CardDeck dealer21;
    private CardDeck dealer23;
    private CardDeck dealerBlackJack;

    private List<CardDeck> players;
    private List<CardDeck> playersHaveBlackJack;

    private List<Money> moneys;

    @BeforeEach
    void init() {
        dealer20 = new CardDeck();

        dealer20.addCard(new Card(Pattern.CLOVER, Denomination.TEN));
        dealer20.addCard(new Card(Pattern.CLOVER, Denomination.JACK));

        dealer21 = new CardDeck();

        dealer21.addCard(new Card(Pattern.CLOVER, Denomination.TEN));
        dealer21.addCard(new Card(Pattern.CLOVER, Denomination.FOUR));
        dealer21.addCard(new Card(Pattern.CLOVER, Denomination.SEVEN));

        dealer23 = new CardDeck();

        dealer23.addCard(new Card(Pattern.CLOVER, Denomination.TEN));
        dealer23.addCard(new Card(Pattern.CLOVER, Denomination.FOUR));
        dealer23.addCard(new Card(Pattern.CLOVER, Denomination.JACK));

        dealerBlackJack = new CardDeck();

        dealerBlackJack.addCard(new Card(Pattern.CLOVER, Denomination.TEN));
        dealerBlackJack.addCard(new Card(Pattern.CLOVER, Denomination.ACE));

        CardDeck player1 = new CardDeck();
        CardDeck player2 = new CardDeck();
        CardDeck player3 = new CardDeck();
        CardDeck player4 = new CardDeck();
        CardDeck playerBlackJack = new CardDeck();

        player1.addCard(new Card(Pattern.SPADE, Denomination.EIGHT));
        player1.addCard(new Card(Pattern.SPADE, Denomination.ACE));
        player1.addCard(new Card(Pattern.SPADE, Denomination.TWO));

        player2.addCard(new Card(Pattern.CLOVER, Denomination.NINE));

        player3.addCard(new Card(Pattern.HEART, Denomination.TEN));
        player3.addCard(new Card(Pattern.HEART, Denomination.FOUR));
        player3.addCard(new Card(Pattern.HEART, Denomination.SIX));

        player4.addCard(new Card(Pattern.HEART, Denomination.TEN));
        player4.addCard(new Card(Pattern.DIAMOND, Denomination.TEN));
        player4.addCard(new Card(Pattern.DIAMOND, Denomination.KING));

        playerBlackJack.addCard(new Card(Pattern.SPADE, Denomination.TEN));
        playerBlackJack.addCard(new Card(Pattern.SPADE, Denomination.ACE));

        players = List.of(player1, player2, player3, player4);
        playersHaveBlackJack = List.of(playerBlackJack, player2, player3, player4);
        moneys = List.of(new Money(1000.0), new Money(1000.0), new Money(1500.0),
            new Money(2000.0));
    }

    @Test
    void testCommonResult() {
        Referee referee = new Referee(dealer20, players, moneys);
        List<Double> playerWinningMoney = referee.calculateWinningMoneys();
        Double dealerWinningMoney = referee.calculateDealerWinningMoney();
        Assertions.assertThat(playerWinningMoney).isEqualTo(List.of(1000.0, -1000.0, 0.0, -2000.0));
        Assertions.assertThat(dealerWinningMoney).isEqualTo(2000.0);
    }

    @Test
    void testDealerBustResult() {
        Referee referee = new Referee(dealer23, players, moneys);
        List<Double> playerWinningMoney = referee.calculateWinningMoneys();
        Double dealerWinningMoney = referee.calculateDealerWinningMoney();
        Assertions.assertThat(playerWinningMoney)
            .isEqualTo(List.of(1000.0, 1000.0, 1500.0, -2000.0));
        Assertions.assertThat(dealerWinningMoney).isEqualTo(-1500.0);
    }

    @Test
    void testIncludeBlackJackPlayerResult() {
        Referee referee = new Referee(dealer23, playersHaveBlackJack, moneys);
        List<Double> playerWinningMoney = referee.calculateWinningMoneys();
        Double dealerWinningMoney = referee.calculateDealerWinningMoney();
        Assertions.assertThat(playerWinningMoney)
            .isEqualTo(List.of(1500.0, 1000.0, 1500.0, -2000.0));
        Assertions.assertThat(dealerWinningMoney).isEqualTo(-2000.0);
    }

    @Test
    void testDealerBlackJackResult() {
        Referee referee = new Referee(dealerBlackJack, players, moneys);
        List<Double> playerWinningMoney = referee.calculateWinningMoneys();
        Double dealerWinningMoney = referee.calculateDealerWinningMoney();
        Assertions.assertThat(playerWinningMoney)
            .isEqualTo(List.of(-1000.0, -1000.0, -1500.0, -2000.0));
        Assertions.assertThat(dealerWinningMoney).isEqualTo(5500.0);
    }

    @Test
    void testDealerAndPlayerBlackJackResult() {
        Referee referee = new Referee(dealerBlackJack, playersHaveBlackJack, moneys);
        List<Double> playerWinningMoney = referee.calculateWinningMoneys();
        Double dealerWinningMoney = referee.calculateDealerWinningMoney();
        Assertions.assertThat(playerWinningMoney)
            .isEqualTo(List.of(0.0, -1000.0, -1500.0, -2000.0));
        Assertions.assertThat(dealerWinningMoney).isEqualTo(4500.0);
    }


}