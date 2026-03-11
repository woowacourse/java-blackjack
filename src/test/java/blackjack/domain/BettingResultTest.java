package blackjack.domain;

import static blackjack.domain.card.Emblem.CLOVER;
import static blackjack.domain.card.Emblem.HEART;
import static blackjack.domain.card.Grade.ACE;
import static blackjack.domain.card.Grade.EIGHT;
import static blackjack.domain.card.Grade.FIVE;
import static blackjack.domain.card.Grade.FOUR;
import static blackjack.domain.card.Grade.JACK;
import static blackjack.domain.card.Grade.KING;
import static blackjack.domain.card.Grade.NINE;
import static blackjack.domain.card.Grade.QUEEN;
import static blackjack.domain.card.Grade.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Emblem;
import blackjack.domain.card.Grade;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Hand;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BettingResultTest {

    @ParameterizedTest
    @MethodSource("bettingResults")
    void 베팅_결과를_판단한다(Dealer dealer, Player player, BettingResult expectedResult) {
        // when
        BettingResult bettingResult = BettingResult.judge(dealer, player);
        // then
        assertThat(bettingResult).isEqualTo(expectedResult);
    }

    static Stream<Arguments> bettingResults() {
        return Stream.of(
                Arguments.of(
                        createDealer(createCard(CLOVER, ACE), createCard(HEART, EIGHT)),
                        createPlayer(createCard(CLOVER, ACE), createCard(HEART, JACK)),
                        BettingResult.BLACKJACK_WIN
                ),
                Arguments.of(
                        createDealer(createCard(CLOVER, FIVE), createCard(HEART, EIGHT), createCard(HEART, NINE)),
                        createPlayer(createCard(CLOVER, ACE), createCard(HEART, JACK)),
                        BettingResult.BLACKJACK_WIN
                ),
                Arguments.of(
                        createDealer(createCard(CLOVER, ACE), createCard(HEART, EIGHT)),
                        createPlayer(createCard(CLOVER, TWO), createCard(HEART, JACK), createCard(HEART, JACK)),
                        BettingResult.LOSE
                ),
                Arguments.of(
                        createDealer(createCard(CLOVER, FIVE), createCard(HEART, EIGHT), createCard(HEART, NINE)),
                        createPlayer(createCard(CLOVER, TWO), createCard(HEART, JACK), createCard(HEART, JACK)),
                        BettingResult.LOSE
                ),
                Arguments.of(
                        createDealer(createCard(CLOVER, ACE), createCard(HEART, QUEEN)),
                        createPlayer(createCard(CLOVER, ACE), createCard(HEART, JACK)),
                        BettingResult.PUSH
                ),
                Arguments.of(
                        createDealer(createCard(CLOVER, KING), createCard(CLOVER, FOUR), createCard(HEART, EIGHT)),
                        createPlayer(createCard(CLOVER, TWO), createCard(HEART, JACK)),
                        BettingResult.WIN
                ),
                Arguments.of(
                        createDealer(createCard(CLOVER, KING), createCard(CLOVER, TWO), createCard(HEART, EIGHT)),
                        createPlayer(createCard(CLOVER, TWO), createCard(HEART, KING), createCard(HEART, NINE)),
                        BettingResult.WIN
                )
        );
    }

    private static Card createCard(Emblem emblem, Grade grade) {
        return new Card(emblem, grade);
    }

    private static Dealer createDealer(Card... cards) {
        return new Dealer(new Hand(List.of(cards)));
    }

    private static Player createPlayer(Card... cards) {
        return new Player("러키", new Hand(List.of(cards)));
    }
}
