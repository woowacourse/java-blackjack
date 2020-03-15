package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.UserTest.PLAYER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    CardHand bustHand1 = new CardHand();
    CardHand bustHand2 = new CardHand();
    CardHand notBustHand1 = new CardHand();
    Deck bustDeck1;
    Deck bustDeck2;
    Deck notBustDeck1;

    @BeforeEach
    void init_field(){
        bustHand1.addCard(new Card(Symbol.KING, Type.DIAMOND));
        bustHand1.addCard(new Card(Symbol.QUEEN, Type.DIAMOND));
        bustHand1.addCard(new Card(Symbol.JACK, Type.DIAMOND));
        bustHand2.addCard(new Card(Symbol.NINE, Type.DIAMOND));
        bustHand2.addCard(new Card(Symbol.KING, Type.DIAMOND));
        bustHand2.addCard(new Card(Symbol.KING, Type.DIAMOND));
        notBustHand1.addCard(new Card(Symbol.KING, Type.DIAMOND));
        notBustHand1.addCard(new Card(Symbol.JACK, Type.DIAMOND));

        bustDeck1 = new Deck(bustHand1);
        bustDeck2 = new Deck(bustHand2);
        notBustDeck1 = new Deck(notBustHand1);
    }

    @Test
    void both_isBust_Test() {
        Player player = new Player(PLAYER_NAME, bustDeck1);
        Dealer dealer = new Dealer(bustDeck2);
        player.drawCard(bustDeck1.draw(1));
        dealer.drawCard(bustDeck2.draw(1));
        assertThat(Result.compete(dealer, player) == Result.DRAW).isTrue();
    }

    @Test
    void Dealer_isBust_Test() {
        Player player = new Player(PLAYER_NAME, notBustDeck1);
        Dealer dealer = new Dealer(bustDeck1);
        dealer.drawCard(bustDeck1.draw(1));
        assertThat(Result.compete(dealer, player) == Result.WIN).isTrue();
    }
}
