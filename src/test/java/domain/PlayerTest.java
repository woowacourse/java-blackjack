package domain;

import static org.assertj.core.api.Assertions.*;

import domain.deck.Card;
import domain.deck.CardNumber;
import domain.deck.CardPattern;
import domain.participants.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("플레이어를 생성한다.")
    void createPlayerTest() {
        Assertions.assertDoesNotThrow(() -> new Player("pobi",new BettingMoney(0)));
    }

    @Test
    @DisplayName("카드를 받는다.")
    void receiveCard() {
        Player player = new Player("pobi",new BettingMoney(0));
        Card card = new Card(CardNumber.ACE, CardPattern.SPADE);
        player.addCard(card);
        assertThat(player.getPlayerCards().get(0)).isEqualTo(card);
    }

    @Test
    @DisplayName("카드값의 합이 21 초과 여부를 확인 할 수 있다.")
    void checkOver21Test() {
        Player player = new Player("pobi",new BettingMoney(0));
        Card card1 = new Card(CardNumber.KING,CardPattern.SPADE);
        Card card2 = new Card(CardNumber.KING,CardPattern.DIAMOND);
        Card card3 = new Card(CardNumber.KING,CardPattern.DIAMOND);

        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        assertThat(player.isOverPlayerBlackJack()).isTrue();
    }


}
