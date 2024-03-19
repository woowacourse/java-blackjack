package model.participant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.card.Card;
import model.card.Emblem;
import model.card.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 카드 덱이 Bust되지 않았으면 hit할 수 있다.")
    void canHit_ShouldReturnTrue_WhenCardDeckIsNotBust() {
        Participant player = new Player(new Name("name"));

        player.hitCard(Card.from(Number.TEN, Emblem.SPADE));
        player.hitCard(Card.from(Number.NINE, Emblem.HEART));
        player.hitCard(Card.from(Number.TWO, Emblem.HEART));

        assertTrue(player.canHit());
    }

    @Test
    @DisplayName("플레이어는 카드 덱이 Bust되면 hit할 수 없다.")
    void canHit_ShouldReturnFalse_WhenCardDeckIsBust() {
        Participant player = new Player(new Name("name"));

        player.hitCard(Card.from(Number.TEN, Emblem.SPADE));
        player.hitCard(Card.from(Number.NINE, Emblem.HEART));
        player.hitCard(Card.from(Number.THREE, Emblem.HEART));

        assertFalse(player.canHit());
    }


    @Test
    @DisplayName("플레이어의 초기 2장의 카드가 블랙잭인지 판별한다.")
    void isInitBlackjack_ShouldReturnTrue_WhenHandValueIs21DeckSizeIs2() {
        Player player = new Player(new Name("프람"));
        player.hitCard(Card.from(Number.ACE, Emblem.CLUB));
        player.hitCard(Card.from(Number.TEN, Emblem.HEART));

        assertTrue(player.isInitBlackjack());
    }

}
