package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("플레이어를 생성한다.")
    void createPlayerTest() {
        Assertions.assertDoesNotThrow(() -> new Player("pobi"));
    }

    @Test
    @DisplayName("플레이어가 카드를 받는다.")
    void receiveCard() {
        Player player = new Player("pobi");
        Card card = new Card(CardNumber.ACE, CardPattern.SPADE);

        player.addCard(card);

        assertThat(player.getCardsSum()).isEqualTo(11);
    }

    @Test
    @DisplayName("카드값의 합이 21 초과 여부를 확인 할 수 있다.")
    void checkOver21Test() {
        Player player = new Player("pobi");
        Card card1 = new Card(CardNumber.KING, CardPattern.SPADE);
        Card card2 = new Card(CardNumber.KING, CardPattern.DIAMOND);
        Card card3 = new Card(CardNumber.KING, CardPattern.DIAMOND);
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        assertThat(player.isOverBlackJack()).isTrue();
    }

    @Test
    @DisplayName("카드가 2장이고, 합이 21인 경우 블랙잭이다.")
    void isBlackJack() {
        Player player = new Player("pobi");
        Card card1 = new Card(CardNumber.KING, CardPattern.SPADE);
        Card card2 = new Card(CardNumber.ACE, CardPattern.DIAMOND);
        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.isBlackJack()).isTrue();
    }

}
