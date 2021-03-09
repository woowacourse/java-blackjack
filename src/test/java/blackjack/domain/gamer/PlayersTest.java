package blackjack.domain.gamer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import blackjack.domain.card.CardManager;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("Players 객체 생성")
    @Test
    void create() {
        CardManager cardManager = CardManager.create();
        assertThatCode(() ->
                new Players(
                        Arrays.asList(
                                new Player("joanne", 1000, cardManager.giveFirstHand()),
                                new Player("pk", 1000, cardManager.giveFirstHand())
                        )
                )
        ).doesNotThrowAnyException();
    }
}
