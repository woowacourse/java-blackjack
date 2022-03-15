package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
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
                Arguments.of(Result.DRAW, makeUser(CardNumber.ACE, CardNumber.KING),
                        makeDealer(CardNumber.ACE, CardNumber.KING)),
                Arguments.of(Result.DRAW,
                        makeHavingExceedMaxScorePlayer(makeUser(CardNumber.JACK, CardNumber.KING), CardNumber.THREE),
                        makeHavingExceedMaxScorePlayer(makeDealer(CardNumber.KING, CardNumber.FIVE), CardNumber.SEVEN)),
                Arguments.of(Result.WIN, makeUser(CardNumber.ACE, CardNumber.KING),
                        makeDealer(CardNumber.KING, CardNumber.JACK)),
                Arguments.of(Result.WIN, makeUser(CardNumber.ACE, CardNumber.KING),
                        makeHavingExceedMaxScorePlayer(makeDealer(CardNumber.KING, CardNumber.FIVE), CardNumber.SEVEN)),
                Arguments.of(Result.LOSE, makeUser(CardNumber.KING, CardNumber.JACK),
                        makeDealer(CardNumber.ACE, CardNumber.KING)),
                Arguments.of(Result.LOSE,
                        makeHavingExceedMaxScorePlayer(makeUser(CardNumber.KING, CardNumber.FIVE), CardNumber.SEVEN),
                        makeDealer(
                                CardNumber.ACE, CardNumber.KING))
        );
    }

    static Player makeUser(final CardNumber cardNumber1, final CardNumber cardNumber2) {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardPattern.DIAMOND, cardNumber1), new Card(CardPattern.DIAMOND, cardNumber2)));
        return new User("AAA", cards);
    }

    static Player makeDealer(final CardNumber cardNumber1, final CardNumber cardNumber2) {
        List<Card> cards = new ArrayList<>(
                List.of(new Card(CardPattern.DIAMOND, cardNumber1), new Card(CardPattern.DIAMOND, cardNumber2)));
        return new Dealer(cards);
    }

    static Player makeHavingExceedMaxScorePlayer(Player player, CardNumber cardNumber) {
        player.pickCard(new Card(CardPattern.DIAMOND, cardNumber));
        return player;
    }
}
