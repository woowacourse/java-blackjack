package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static controller.BlackJackGame.ADDITIONAL_DRAW_COUNT;
import static controller.BlackJackGame.INITIAL_DRAW_COUNT;
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
    void init_field() {
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
    @DisplayName("딜러와 플레이어 모두 21을 넘긴 경우 플레이 DRAW")
    void both_isBust_Test() {
        Player player = new Player(PLAYER_NAME, bustDeck1, INITIAL_DRAW_COUNT);
        Dealer dealer = new Dealer(bustDeck2, INITIAL_DRAW_COUNT);
        player.drawCard(bustDeck1, ADDITIONAL_DRAW_COUNT);
        dealer.drawCard(bustDeck2, ADDITIONAL_DRAW_COUNT);
        assertThat(Result.compete(dealer, player) == Result.DRAW).isTrue();
    }

    @Test
    @DisplayName("딜러만 21을 넘긴 경우 플레이어 WIN")
    void Dealer_isBust_Test() {
        Player player = new Player(PLAYER_NAME, notBustDeck1, INITIAL_DRAW_COUNT);
        Dealer dealer = new Dealer(bustDeck1, INITIAL_DRAW_COUNT);
        dealer.drawCard(bustDeck1, ADDITIONAL_DRAW_COUNT);
        assertThat(Result.compete(dealer, player) == Result.WIN).isTrue();
    }
}
