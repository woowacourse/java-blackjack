package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HandGenerator;
import blackjack.domain.card.Number;
import blackjack.domain.card.RandomDeck;
import blackjack.exception.InvalidPlayerCountException;
import java.util.ArrayList;
import java.util.List;

import blackjack.testutil.CustomDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @DisplayName("입력된 플레이어 이름이 하나 미만이면 예외가 발생한다.")
    @Test
    void invalidPlayerCountTest() {
        Deck deck = RandomDeck.getInstance();
        HandGenerator handGenerator = new HandGenerator(deck);

        assertThatThrownBy(() -> new Players(new ArrayList<>(), handGenerator))
                .isInstanceOf(InvalidPlayerCountException.class);
    }

    @DisplayName("입력된 플레이어 이름이 하나 이상이면 예외가 발생하지 않는다.")
    @Test
    void validPlayerCountTest() {
        List<Name> playerNames = List.of(new Name("Alice"), new Name("Bob"));
        Deck deck = new CustomDeck(List.of(Number.ACE, Number.FOUR, Number.QUEEN, Number.EIGHT));
        HandGenerator handGenerator = new HandGenerator(deck);

        assertThatCode(() -> new Players(playerNames, handGenerator))
                .doesNotThrowAnyException();
    }
}
