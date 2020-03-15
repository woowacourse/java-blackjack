package model;

import org.junit.jupiter.api.Test;

import static model.UserTest.PLAYER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    static CardHand bustHand1 = new CardHand();
    static CardHand bustHand2 = new CardHand();
    static CardHand notBustHand1 = new CardHand();

    static {
        bustHand1.addCard(new Card(Symbol.KING, Type.DIAMOND));
        bustHand1.addCard(new Card(Symbol.QUEEN, Type.DIAMOND));
        bustHand1.addCard(new Card(Symbol.JACK, Type.DIAMOND));
        bustHand2.addCard(new Card(Symbol.NINE, Type.DIAMOND));
        bustHand2.addCard(new Card(Symbol.KING, Type.DIAMOND));
        bustHand2.addCard(new Card(Symbol.KING, Type.DIAMOND));
        notBustHand1.addCard(new Card(Symbol.KING, Type.DIAMOND));
        notBustHand1.addCard(new Card(Symbol.JACK, Type.DIAMOND));
    }

    @Test
    void both_isBust_Test() {
        Player player = new Player(PLAYER_NAME, bustHand1);
        Dealer dealer = new Dealer(bustHand2);
        assertThat(Result.compete(dealer, player) == Result.DRAW).isTrue();
    }

    @Test
    void Dealer_isBust_Test() {
        Player player = new Player(PLAYER_NAME, notBustHand1);
        Dealer dealer = new Dealer(bustHand1);
        assertThat(Result.compete(dealer, player) == Result.WIN).isTrue();
    }
}
