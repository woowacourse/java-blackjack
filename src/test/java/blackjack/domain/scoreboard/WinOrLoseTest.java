package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;
import blackjack.domain.card.painting.Suit;
import blackjack.domain.card.painting.Symbol;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;
import blackjack.domain.user.User;
import blackjack.domain.user.status.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static blackjack.domain.scoreboard.WinOrLose.*;
import static blackjack.domain.user.status.Status.*;
import static org.assertj.core.api.Assertions.assertThat;

class WinOrLoseTest {
    @DisplayName("상태 조건에 따라 승무패가 결정되는지 확인")
    @ParameterizedTest
    @MethodSource
    void winOrLoseByStatusTest(User user, Dealer dealer, WinOrLose expectedResult) {
        //given
        //when
        //then
        assertThat(WinOrLose.decideWinOrLose(user, dealer)).isEqualTo(expectedResult);
    }

    static Stream<Arguments> winOrLoseByStatusTest() {
        return Stream.of(
                Arguments.of(
                        changeStatus(new User("웨지", 0), PLAYING), changeStatus(new Dealer(), BURST), WIN
                ),
                Arguments.of(
                        changeStatus(new User("웨지", 0), BURST), changeStatus(new Dealer(), PLAYING), LOSE
                ),
                Arguments.of(
                        changeStatus(new User("웨지", 0), BURST), changeStatus(new Dealer(), BURST), DRAW
                )
        );
    }

    static Participant changeStatus(Participant participant, Status status) {
        if (status == BURST) {
            participant.drawCards(Arrays.asList(new Card(Suit.DIAMOND, Symbol.QUEEN), new Card(Suit.DIAMOND, Symbol.KING)));
            participant.drawCard(new Card(Suit.DIAMOND, Symbol.TWO));
        }

        if (status == BLACKJACK) {
            participant.drawCards(Arrays.asList(new Card(Suit.DIAMOND, Symbol.QUEEN), new Card(Suit.DIAMOND, Symbol.ACE)));
        }

        if (status == PLAYING) {
            participant.drawCards(Arrays.asList(new Card(Suit.DIAMOND, Symbol.QUEEN), new Card(Suit.DIAMOND, Symbol.THREE)));
        }

        return participant;
    }

    @DisplayName("상태 조건이 PLAYING일 때, 점수 비교로 승무패가 결정되는지 확인")
    @ParameterizedTest
    @MethodSource
    void winOrLoseByScoreTest(User user, Dealer dealer, WinOrLose expectedResult) {
        //given
        //when
        //then
        assertThat(WinOrLose.decideWinOrLose(user, dealer)).isEqualTo(expectedResult);
    }

    static Stream<Arguments> winOrLoseByScoreTest() {
        return Stream.of(
                Arguments.of(
                        setScoreTwenty(new User("웨지", 0)), setScoreEleven(new Dealer()), WIN
                ),
                Arguments.of(
                        setScoreEleven(new User("웨지", 0)), setScoreEleven(new Dealer()), DRAW
                ),
                Arguments.of(
                        setScoreEleven(new User("웨지", 0)), setScoreTwenty(new Dealer()), LOSE
                )
        );
    }

    static Participant setScoreTwenty(Participant participant) {
        participant.drawCards(Arrays.asList(new Card(Suit.DIAMOND, Symbol.QUEEN), new Card(Suit.DIAMOND, Symbol.JACK)));
        return participant;
    }

    static Participant setScoreEleven(Participant participant) {
        participant.drawCards(Arrays.asList(new Card(Suit.DIAMOND, Symbol.SEVEN), new Card(Suit.DIAMOND, Symbol.FOUR)));
        return participant;
    }

    @DisplayName("승무패 받아 반대 결과를 반환하는지 확인")
    @Test
    void oppositeTest() {
        //given
        //when
        //then
        assertThat(WinOrLose.opposite(WIN)).isEqualTo(LOSE);
        assertThat(WinOrLose.opposite(DRAW)).isEqualTo(DRAW);
        assertThat(WinOrLose.opposite(LOSE)).isEqualTo(WIN);
    }
}