package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Deck;
import blackjack.domain.card.RandomDeck;
import blackjack.exception.InvalidPlayerCountException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @DisplayName("플레이어가 1명 미만이면 예외가 발생한다.")
    @Test
    void invalidPlayerCountSmallerThanMinimum() {
        //given
        Deck deck = new RandomDeck();

        //when & then
        assertThatThrownBy(() -> new Players(new ArrayList<>(), deck))
                .isInstanceOf(InvalidPlayerCountException.class);
    }

    @DisplayName("플레이어가 8명 초과이면 예외가 발생한다.")
    @Test
    void invalidPlayerCountBiggerThanMaximum() {
        //given
        Deck deck = new RandomDeck();
        List<Name> players = List.of(new Name("mason"),
                new Name("loki"),
                new Name("ato"),
                new Name("zansu"),
                new Name("polar"),
                new Name("hogi"),
                new Name("takan"),
                new Name("sando"),
                new Name("gamza"),
                new Name("pobi")
        );

        //when & then
        assertThatThrownBy(() -> new Players(players, deck))
                .isInstanceOf(InvalidPlayerCountException.class);
    }
}
