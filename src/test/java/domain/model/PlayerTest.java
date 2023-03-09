package domain.model;

import static org.junit.jupiter.api.Assertions.*;

import domain.type.Denomination;
import domain.type.Suit;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("21 이하의 점수에서의 canReceiveCard 테스트")
    public void testScore21receivable() {
        //given
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.SPADE, Denomination.NINE));
        cardList.add(new Card(Suit.DIAMOND, Denomination.NINE));
        Cards cards = new Cards(cardList);
        Player player = new Player(cards, "player");

        //when
        player.addCard(new Card(Suit.SPADE, Denomination.THREE));

        //then
        assertTrue(player.canReceiveCard());
    }

    @Test
    @DisplayName("22 이상의 점수에서의 canReceiveCard 테스트")
    public void testScore22NotReceivable() {
        //given
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.SPADE, Denomination.NINE));
        cardList.add(new Card(Suit.DIAMOND, Denomination.NINE));
        Cards cards = new Cards(cardList);
        Player player = new Player(cards, "player");

        //when
        player.addCard(new Card(Suit.SPADE, Denomination.FOUR));

        //then
        assertFalse(player.canReceiveCard());
    }
}
