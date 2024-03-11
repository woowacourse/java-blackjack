package blackjack.domain.bet;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardShape.DIA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.player.bet.BetAmount;
import blackjack.domain.player.bet.BetRevenue;
import blackjack.domain.player.bet.BetStatus;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class BetStatusTest {

    static final Hands score19count2 = new Hands(List.of(new Card(TEN, DIA), new Card(NINE, DIA)));
    static final Hands score20count2 = new Hands(List.of(new Card(TEN, DIA), new Card(JACK, DIA)));
    static final Hands score21count2 = new Hands(List.of(new Card(ACE, DIA), new Card(JACK, DIA)));
    static final Hands score21count3 = new Hands(List.of(new Card(ACE, DIA), new Card(JACK, DIA), new Card(QUEEN, DIA)));
    static final Hands score22count3 = new Hands(List.of(new Card(TEN, DIA), new Card(JACK, DIA), new Card(TWO, DIA)));
    static final Hands score23count3 = new Hands(List.of(new Card(TEN, DIA), new Card(JACK, DIA), new Card(THREE, DIA)));


    @DisplayName("딜러와 참여자의 카드들로 배팅결과를 계산한다.")
    @ParameterizedTest(name = "딜러 카드 : {0}, 플레이어 카드 : {1}, 결과 : {2}")
    @MethodSource("handsAndWinStatus")
    void of(Hands dealerHands, Hands playerHands, BetStatus expected) {
        BetStatus betStatus = BetStatus.of(dealerHands, playerHands);

        assertThat(betStatus).isEqualTo(expected);
    }

    static Stream<Arguments> handsAndWinStatus() {
        return Stream.of(                                                  // 딜러(카드수) | 참여자(카드수) | 레버리지
                arguments(score20count2, score21count2, BetStatus.LUCKY),  // 생존(2)    , 블랙잭(2)     , 1.5
                arguments(score22count3, score21count2, BetStatus.LUCKY),  // 죽음(3)    , 블랙잭(2)     , 1.5
                arguments(score21count3, score21count2, BetStatus.LUCKY),  // 블랙잭(3)   , 블랙잭(2)    , 1.5
                arguments(score20count2, score21count3, BetStatus.WIN),    // 생존(2)    , 블랙잭(3)     , 1
                arguments(score23count3, score21count3, BetStatus.WIN),    // 죽음(3)    , 블랙잭(3)     , 1
                arguments(score23count3, score20count2, BetStatus.WIN),    // 죽음(3)    , 생존(2)      , 1
                arguments(score19count2, score20count2, BetStatus.WIN),    // 생존(2)    , 생존(2)      , 1
                arguments(score21count2, score20count2, BetStatus.LOSE),   // 블랙잭(2)   , 생존(2)      , -1
                arguments(score21count3, score22count3, BetStatus.LOSE),   // 블랙잭(3)   , 죽음(3)      , -1
                arguments(score21count2, score21count3, BetStatus.LOSE),   // 블랙잭(2)   , 블랙잭(3)     , -1
                arguments(score20count2, score19count2, BetStatus.LOSE),   // 생존(2)    , 생존(2)       , -1
                arguments(score20count2, score23count3, BetStatus.LOSE),   // 생존(2)    , 죽음(3)       , -1
                arguments(score22count3, score23count3, BetStatus.LOSE),   // 죽음(3)    , 죽음(3)       , -1
                arguments(score23count3, score22count3, BetStatus.LOSE),   // 죽음(3)    , 죽음(3)       , -1
                arguments(score22count3, score22count3, BetStatus.LOSE),   // 죽음(3)    , 죽음(3)       , -1
                arguments(score21count2, score21count2, BetStatus.DRAW),   // 블랙잭(2)   , 블랙잭(2)     , 0
                arguments(score21count3, score21count3, BetStatus.DRAW),   // 블랙잭(3)   , 블랙잭(3)     , 0
                arguments(score20count2, score20count2, BetStatus.DRAW)    // 생존(2)    , 생존(2)       , 0
        );
    }

    @DisplayName("배팅 결과로 플레이어 수익을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {"LUCKY,1000,1500", "WIN,1000,1000", "LOSE,1000,-1000", "DRAW,1000,0"})
    void applyLeverage(BetStatus betStatus, int betAmount, int revenue) {
        // given
        BetAmount batAmount = new BetAmount(betAmount);

        // when
        BetRevenue batRevenue = betStatus.applyLeverage(batAmount);

        // then
        assertThat(batRevenue).isEqualTo(new BetRevenue(revenue));
    }
}
