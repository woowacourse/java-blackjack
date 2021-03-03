package blackjack.domain.gamer;

import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

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
