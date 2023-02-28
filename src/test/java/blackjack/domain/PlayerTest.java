package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어가 카드를 정상적으로 받았는지 확인한다.")
    void receiveCardTest() {
        //given
        Player player = new Player(new PlayerName("아코"));
        Card card = new Card(CardNumber.ACE, CardSuit.DIAMOND);

        //when
        player.receiveCard(card);

        //then
        assertTrue(player.getReceivedCards().contains(card));
    }
}
