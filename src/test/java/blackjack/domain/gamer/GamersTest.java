package blackjack.domain.gamer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import blackjack.domain.card.Deck;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamersTest {

    @DisplayName("Players 객체 생성")
    @Test
    void create() {
        Deck deck = Deck.create();
        assertThatCode(() ->
                new Gamers(
                        Arrays.asList(
                                new Player("joanne", deck.giveFirstHand()),
                                new Player("pk", deck.giveFirstHand())
                        ),
                        new Dealer(deck.giveFirstHand())
                )
        ).doesNotThrowAnyException();
    }
}
