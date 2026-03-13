package domain.pariticipant;

import domain.card.Card;
import domain.card.CardSuit;
import domain.result.MatchCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import test_util.TestUtil;

import java.util.List;
import java.util.stream.Stream;

import static constant.BlackjackConstant.DEALER_NAME;
import static domain.card.CardRank.*;
import static domain.card.CardSuit.*;
import static domain.result.MatchCase.*;
import static org.assertj.core.api.Assertions.assertThat;
import static test_util.TestUtil.*;

class PlayerTest {

    @ParameterizedTest
    @MethodSource("베팅_결과_계산_테스트_케이스")
    @DisplayName("플레이어의 베팅 결과를 계산한다.")
    public void calculateBettingProfit_success(MatchCase matchCase, List<Card> cards, int amount, int result) throws Exception {
        // given
        Player pobi = createPlayer("pobi", cards, amount);

        // when
        long profit = pobi.calculateBettingProfit(matchCase);

        // then
        assertThat(profit).isEqualTo(result);
    }

    private static Stream<Arguments> 베팅_결과_계산_테스트_케이스() {
        return Stream.of(
                Arguments.of(WIN,
                        List.of(createCard(CLUB, NINE), createCard(HEART, ACE)), 10000, 10000), // 승리
                Arguments.of(WIN,
                        List.of(createCard(CLUB, ACE), createCard(HEART, KING)), 10000, 15000), // 블랙잭 승리
                Arguments.of(DRAW,
                        List.of(createCard(CLUB, NINE), createCard(HEART, ACE)), 10000, 0), // 무승부
                Arguments.of(LOSE,
                        List.of(createCard(CLUB, NINE), createCard(HEART, ACE)), 10000, -10000) // 패배
        );
    }

    @ParameterizedTest
    @MethodSource("게임_비김_여부_반환")
    @DisplayName("플레이어가 게임에서 비겼는지 여부를 반환한다.")
    public void is_draw(Player player, Dealer dealer, boolean result) throws Exception {

        // when
        boolean draw = player.isDraw(dealer);

        // then
        assertThat(draw).isEqualTo(result);
    }

    private static Stream<Arguments> 게임_비김_여부_반환() {
        return Stream.of(
                Arguments.of(
                        createPlayer("pobi",
                                List.of(createCard(HEART, TWO), createCard(SPADE, EIGHT), createCard(CLUB, TEN))),
                        creteDealer(DEALER_NAME,
                                List.of(createCard(DIAMOND, THREE), createCard(CLUB, NINE), createCard(DIAMOND, EIGHT))),
                        true),
                Arguments.of(
                        createPlayer("pobi",
                                List.of(createCard(HEART, TWO), createCard(SPADE, EIGHT), createCard(CLUB, ACE))),
                        creteDealer(DEALER_NAME,
                                List.of(createCard(DIAMOND, THREE), createCard(CLUB, NINE), createCard(DIAMOND, EIGHT))),
                        false)
                );
    }

    @ParameterizedTest
    @MethodSource("게임_짐_여부_반환")
    @DisplayName("플레이어가 게임에서 졌는지 여부를 반환한다.")
    public void is_lose(Player player, Dealer dealer, boolean result) throws Exception {

        // when
        boolean draw = player.isLose(dealer);

        // then
        assertThat(draw).isEqualTo(result);
    }

    private static Stream<Arguments> 게임_짐_여부_반환() {
        return Stream.of(
                Arguments.of(
                        createPlayer("pobi",
                                List.of(createCard(HEART, TWO), createCard(SPADE, EIGHT), createCard(CLUB, SEVEN))),
                        creteDealer(DEALER_NAME,
                                List.of(createCard(DIAMOND, THREE), createCard(CLUB, NINE), createCard(DIAMOND, EIGHT))),
                        true),
                Arguments.of(
                        createPlayer("pobi",
                                List.of(createCard(HEART, TWO), createCard(SPADE, EIGHT), createCard(CLUB, ACE))),
                        creteDealer(DEALER_NAME,
                                List.of(createCard(DIAMOND, THREE), createCard(CLUB, NINE), createCard(DIAMOND, EIGHT))),
                        false)
        );
    }

}
