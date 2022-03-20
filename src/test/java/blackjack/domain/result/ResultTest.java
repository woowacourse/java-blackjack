package blackjack.domain.result;

import static blackjack.domain.Fixtures.ACE_DIAMOND;
import static blackjack.domain.Fixtures.ACE_HEART;
import static blackjack.domain.Fixtures.JACK_DIAMOND;
import static blackjack.domain.Fixtures.KING_DIAMOND;
import static blackjack.domain.Fixtures.SIX_DIAMOND;
import static blackjack.domain.Fixtures.TWO_DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTest {

    @ParameterizedTest
    @MethodSource("provideScoreAndResult")
    @DisplayName("점수에 따른 승무패를 계산한다.")
    void findResult(final Result result, final User user, final Dealer dealer) {
        final Result actual = Result.findResult(user, dealer);

        assertThat(actual).isEqualTo(result);
    }

    static Stream<Arguments> provideScoreAndResult() {
        return Stream.of(
                Arguments.of(Result.DRAW,
                        makeUser(ACE_DIAMOND, KING_DIAMOND),
                        makeDealer(ACE_HEART, JACK_DIAMOND)),
                Arguments.of(Result.DRAW,
                        makeBustPlayer(makeUser(JACK_DIAMOND, TWO_DIAMOND), KING_DIAMOND),
                        makeBustPlayer(makeDealer(KING_DIAMOND, SIX_DIAMOND), JACK_DIAMOND)),
                Arguments.of(Result.WIN,
                        makeUser(ACE_DIAMOND, KING_DIAMOND),
                        makeDealer(KING_DIAMOND, JACK_DIAMOND)),
                Arguments.of(Result.WIN,
                        makeUser(ACE_DIAMOND, KING_DIAMOND),
                        makeBustPlayer(makeDealer(KING_DIAMOND, TWO_DIAMOND), JACK_DIAMOND)),
                Arguments.of(Result.LOSE,
                        makeUser(KING_DIAMOND, JACK_DIAMOND),
                        makeDealer(ACE_DIAMOND, KING_DIAMOND)),
                Arguments.of(Result.LOSE,
                        makeBustPlayer(makeUser(KING_DIAMOND, TWO_DIAMOND), JACK_DIAMOND),
                        makeDealer(ACE_DIAMOND, KING_DIAMOND))
        );
    }

    static Player makeUser(final Card card1, final Card card2) {
        List<Card> cards = new ArrayList<>(
                List.of(card1, card2));
        return new User("AAA", cards);
    }

    static Player makeDealer(final Card card1, final Card card2) {
        List<Card> cards = new ArrayList<>(List.of(card1, card2));
        return new Dealer(cards);
    }

    static Player makeBustPlayer(final Player player, final Card card) {
        player.drawCard(card);
        return player;
    }
}
