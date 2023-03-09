package domain.model;

import static org.junit.jupiter.api.Assertions.*;

import domain.type.Denomination;
import domain.type.Suit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("21 이하의 점수에서의 canReceiveCard 테스트")
    public void testScore21receivable() {
        //given
        Cards cards = Cards.of(
            new Card(Suit.SPADE, Denomination.NINE),
            new Card(Suit.DIAMOND, Denomination.NINE));
        Player player = new Player("player", cards);

        //when
        player.addCard(new Card(Suit.SPADE, Denomination.THREE));

        //then
        assertTrue(player.canReceiveCard());
    }

    @Test
    @DisplayName("22 이상의 점수에서의 canReceiveCard 테스트")
    public void testScore22NotReceivable() {
        //given
        Cards cards = Cards.of(
            new Card(Suit.SPADE, Denomination.NINE),
            new Card(Suit.DIAMOND, Denomination.NINE));
        Player player = new Player("player", cards);
        //when
        player.addCard(new Card(Suit.SPADE, Denomination.FOUR));

        //then
        assertFalse(player.canReceiveCard());
    }
}
