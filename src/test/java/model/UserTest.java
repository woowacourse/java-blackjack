package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    public static final String PLAYER_NAME = "DD";
    CardHand bustHand = new CardHand();
    CardHand notBustHand = new CardHand();
    Deck bustDeck;
    Deck notBustDeck;

    @BeforeEach
    void init_field() {
        bustHand.addCard(new Card(Symbol.KING, Type.DIAMOND));
        bustHand.addCard(new Card(Symbol.QUEEN, Type.DIAMOND));
        bustHand.addCard(new Card(Symbol.JACK, Type.DIAMOND));
        notBustHand.addCard(new Card(Symbol.KING, Type.DIAMOND));
        notBustHand.addCard(new Card(Symbol.JACK, Type.DIAMOND));
        bustDeck = new Deck(bustHand);
        notBustDeck = new Deck(notBustHand);
    }

    @Test
    @DisplayName("딜러 이름 테스트")
    void dealer_Name() {
        User dealer = new Dealer(notBustDeck);
        assertThat(dealer.getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("이름을 불러오는 지 테스트")
    void name_Test() {
        Player player = new Player(PLAYER_NAME, notBustDeck);
        assertThat(player.getName()).isEqualTo(PLAYER_NAME);
    }

    @Test
    @DisplayName("21을 넘는 지")
    void isBust_Player_Test() {
        Player player = new Player(PLAYER_NAME, bustDeck);
        player.drawCard(bustDeck.draw(1));
        assertThat(player.isBust()).isTrue();
    }
}
