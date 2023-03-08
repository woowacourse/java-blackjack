package domain.model;

import static org.junit.jupiter.api.Assertions.*;

import domain.type.Denomination;
import domain.type.Suit;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("21 이하의 점수에서의 canReceiveCard 테스트")
    public void testScore21receivable() {
        //given
        Set<Card> cardSet = new HashSet<>();
        cardSet.add(new Card(Suit.SPADE, Denomination.NINE));
        cardSet.add(new Card(Suit.DIAMOND, Denomination.NINE));
        Cards cards = new Cards(cardSet);
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
        Set<Card> cardSet = new HashSet<>();
        cardSet.add(new Card(Suit.SPADE, Denomination.TEN));
        cardSet.add(new Card(Suit.DIAMOND, Denomination.TEN));
        Cards cards = new Cards(cardSet);
        Player player = new Player(cards, "player");

        //when
        player.addCard(new Card(Suit.SPADE, Denomination.TWO));

        //then
        assertFalse(player.canReceiveCard());
    }
}
