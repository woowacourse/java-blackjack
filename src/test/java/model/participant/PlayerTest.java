package model.participant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.card.Card;
import model.card.Emblem;
import model.card.Number;
import model.participant.Name;
import model.participant.Participant;
import model.participant.Player;
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

}
