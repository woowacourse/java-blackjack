package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suit;

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
        List<Card> cards = List.of(
            new Card(Suit.SPADE, Denomination.NINE),
            new Card(Suit.DIAMOND, Denomination.NINE));
        Player player = Player.from("player");
        cards.forEach(player::addCard);

        //when
        player.addCard(new Card(Suit.SPADE, Denomination.FOUR));

        //then
        assertFalse(player.canReceiveCard());
    }

    @Test
    @DisplayName("첫 손패 21점에서의 isBlackJack 테스트")
    public void testFirstCards21IsBlackJack() {
        //given
        List<Card> cards = List.of(
            new Card(Suit.SPADE, Denomination.ACE),
            new Card(Suit.SPADE, Denomination.TEN));
        Player player = Player.from("player");

        //when
        cards.forEach(player::addCard);

        // then
        assertTrue(player.isBlackJack());
    }

    @Test
    @DisplayName("첫 손패 이후 추가 카드까지 21점에서의 isBlackJack 테스트")
    public void testScore21WithAdditionalCardsIsBlackJack() {
        //given
        List<Card> cards = List.of(
            new Card(Suit.SPADE, Denomination.ACE),
            new Card(Suit.SPADE, Denomination.NINE));
        Player player = Player.from("player");
        cards.forEach(player::addCard);

        //when
        Card thirdCard = new Card(Suit.SPADE, Denomination.ACE);
        player.addCard(thirdCard);

        // then
        assertFalse(player.isBlackJack());
    }
}
