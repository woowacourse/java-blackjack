package domain.pariticipant;

import domain.card.Card;
import domain.result.MatchCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static domain.card.CardRank.*;
import static domain.card.CardSuit.CLUB;
import static domain.card.CardSuit.HEART;
import static domain.result.MatchCase.*;
import static org.assertj.core.api.Assertions.assertThat;
import static test_util.TestUtil.createCard;
import static test_util.TestUtil.createPlayer;

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

}
