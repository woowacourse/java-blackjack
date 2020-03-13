package model.user;

import model.Result;
import model.card.Card;
import model.card.CardHand;
import model.card.Symbol;
import model.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    static CardHand cardHandWin = new CardHand();
    static CardHand cardHandLose = new CardHand();
    static CardHand cardHandDraw = new CardHand();

    static {
        cardHandWin.addCard(new Card(Symbol.KING, Type.CLUB));
        cardHandWin.addCard(new Card(Symbol.KING, Type.DIAMOND));
        cardHandLose.addCard(new Card(Symbol.TWO, Type.DIAMOND));
        cardHandLose.addCard(new Card(Symbol.TWO, Type.CLUB));
        cardHandDraw.addCard(new Card(Symbol.TWO, Type.CLUB));
    }

    @Test
    @DisplayName("딜러가 이기는 경우")
    void result_Dealer_Win() {
        Dealer dealer = new Dealer(cardHandLose);
        Player player = new Player("DD", cardHandWin);

        Result result = dealer.compareScore(player);
        dealer.setResult(result);

        assertThat(dealer.getResult().get(Result.LOSE)).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 지는 경우")
    void result_Dealer_Lose() {
        Dealer dealer = new Dealer(cardHandWin);
        Player player = new Player("DD", cardHandLose);

        Result result = dealer.compareScore(player);
        dealer.setResult(result);

        assertThat(dealer.getResult().get(Result.WIN)).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 비기는 경우")
    void result_Dealer_Draw() {
        Dealer dealer = new Dealer(cardHandDraw);
        Player player = new Player("DD", cardHandDraw);

        Result result = dealer.compareScore(player);
        dealer.setResult(result);

        assertThat(dealer.getResult().get(Result.DRAW)).isEqualTo(1);
    }
}