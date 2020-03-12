package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    public static final String PLAYER_NAME = "DD";
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
    @DisplayName("딜러 이름 테스트")
    void dealer_Name() {
        User dealer = new Dealer(notBustHand1);
        assertThat(dealer.toString()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("이름을 불러오는 지 테스트")
    void name_Test() {
        Player player = new Player(PLAYER_NAME, notBustHand1);
        assertThat(player.toString()).isEqualTo(PLAYER_NAME);
    }

    @Test
    void both_isBust_Test() {
        Player player = new Player(PLAYER_NAME, bustHand1);
        Dealer dealer = new Dealer(bustHand2);
        assertThat(dealer.compareScore(player) == Result.DRAW).isTrue();
    }

    @Test
    void Dealer_isBust_Test() {
        Player player = new Player(PLAYER_NAME, notBustHand1);
        Dealer dealer = new Dealer(bustHand1);
        assertThat(dealer.compareScore(player) == Result.WIN).isTrue();
    }

    @Test
    void isBust_Player_Test() {
        Player player = new Player(PLAYER_NAME, bustHand1);
        assertThat(player.isBust()).isTrue();
    }
}
