package blackjack.domain.participant;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParticipantTest {
    @Test
    @DisplayName("참가자가 Blackjack 상태인지 확인")
    void isBlackjack() {
        List<Card> cards = Arrays.asList(Card.from("10다이아몬드"), Card.from("A다이아몬드"));
        Participant player = new Player("john", cards);
        assertTrue(player.isBlackjack());

        cards = Arrays.asList(Card.from("10하트"), Card.from("6하트"));
        Participant dealer = new Dealer(cards);
        dealer.takeCard(Card.from("5하트"));
        assertFalse(dealer.isBlackjack());
    }

    @Test
    @DisplayName("참가자가 Bust 상태인지 확인")
    void isBust() {
        List<Card> cards = Arrays.asList(Card.from("10다이아몬드"), Card.from("9다이아몬드"));
        Participant player = new Player("john", cards);
        player.takeCard(Card.from("3다이아몬드"));
        assertTrue(player.isBust());

        cards = Arrays.asList(Card.from("A하트"), Card.from("9하트"));
        Participant dealer = new Dealer(cards);
        dealer.takeCard(Card.from("2하트"));
        assertFalse(dealer.isBust());
    }

    @Test
    @DisplayName("참가자가 Hit 상태인지 확인")
    void isHit() {
        List<Card> cards = Arrays.asList(Card.from("10다이아몬드"), Card.from("9다이아몬드"));
        Participant player = new Player("john", cards);
        player.takeCard(Card.from("2다이아몬드"));
        assertTrue(player.isHit());

        cards = Arrays.asList(Card.from("A하트"), Card.from("9하트"));
        Participant dealer = new Dealer(cards);
        dealer.takeCard(Card.from("2하트"));
        assertTrue(dealer.isHit());
    }
}
