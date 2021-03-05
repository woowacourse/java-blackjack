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
                                new Player("joanne", deck.makeInitialHands()),
                                new Player("pk", deck.makeInitialHands())
                        ),
                        new Dealer(deck.makeInitialHands())
                )
        ).doesNotThrowAnyException();
    }
}
