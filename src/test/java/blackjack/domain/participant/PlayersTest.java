package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Deck;
import blackjack.domain.HandGenerator;
import blackjack.domain.RandomDeck;
import blackjack.exception.InvalidPlayerCountException;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @DisplayName("플레이어가 한 명 미만이면 예외가 발생한다.")
    @Test
    void invalidPlayerCountTest() {
        Deck deck = RandomDeck.getInstance();
        HandGenerator handGenerator = new HandGenerator(deck);

        assertThatThrownBy(() -> new Players(new ArrayList<>(), handGenerator))
                .isInstanceOf(InvalidPlayerCountException.class);
    }
}
