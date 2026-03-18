package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Emblem;
import domain.card.Grade;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void 카드의_합이_21이초과되면_카드를_받지_않는다() {
        // given
        List<Card> testCard = List.of(
                new Card(Emblem.HEART, Grade.JACK), new Card(Emblem.DIAMOND, Grade.SEVEN),
                new Card(Emblem.SPADE, Grade.SEVEN), new Card(Emblem.HEART, Grade.SEVEN),
                new Card(Emblem.DIAMOND, Grade.QUEEN), new Card(Emblem.HEART, Grade.KING),
                new Card(Emblem.CLOVER, Grade.NINE), new Card(Emblem.SPADE, Grade.EIGHT)
        );

        Player player = new Player("토리", 1000);
        Deck deck = new Deck(new FixShuffleStrategy(testCard));
        player.initHand(deck);
        player.playTurn(deck);

        // when
        player.playTurn(deck);
        player.playTurn(deck);
        player.playTurn(deck);
        
        // then
        int expected = 24;
        Assertions.assertEquals(expected, player.getScore());
    }

}
