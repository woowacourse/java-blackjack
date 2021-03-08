package blackjack.domain.gamer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import blackjack.domain.card.CardManager;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamersTest {

    @DisplayName("Players 객체 생성")
    @Test
    void create() {
        CardManager cardManager = CardManager.create();
        assertThatCode(() ->
                new Gamers(
                        Arrays.asList(
                                new Player("joanne", cardManager.giveFirstHand()),
                                new Player("pk", cardManager.giveFirstHand())
                        )
                )
        ).doesNotThrowAnyException();
    }
}
