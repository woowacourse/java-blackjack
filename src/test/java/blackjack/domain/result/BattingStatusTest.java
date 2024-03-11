package blackjack.domain.result;

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
import blackjack.domain.rule.BattingStatus;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BattingStatusTest {

    static final Hands score19count2 = new Hands(List.of(new Card(TEN, DIA), new Card(NINE, DIA)));
    static final Hands score20count2 = new Hands(List.of(new Card(TEN, DIA), new Card(JACK, DIA)));
    static final Hands score21count2 = new Hands(List.of(new Card(ACE, DIA), new Card(JACK, DIA)));
    static final Hands score21count3 = new Hands(List.of(new Card(ACE, DIA), new Card(JACK, DIA), new Card(QUEEN, DIA)));
    static final Hands score22count3 = new Hands(List.of(new Card(TEN, DIA), new Card(JACK, DIA), new Card(TWO, DIA)));
    static final Hands score23count3 = new Hands(List.of(new Card(TEN, DIA), new Card(JACK, DIA), new Card(THREE, DIA)));


    @DisplayName("딜러와 참여자의 카드들로 배팅결과를 계산한다.")
    @ParameterizedTest(name = "딜러 카드 : {0}, 플레이어 카드 : {1}, 결과 : {2}")
    @MethodSource("handsAndWinStatus")
    void of(Hands dealerHands, Hands playerHands, BattingStatus expected) {
        BattingStatus battingStatus = BattingStatus.of(dealerHands, playerHands);

        assertThat(battingStatus).isEqualTo(expected);
    }

    static Stream<Arguments> handsAndWinStatus() {
        return Stream.of(                                                  // 딜러(카드수) | 참여자(카드수) | 레버리지
                arguments(score20count2, score21count2, BattingStatus.LUCKY),  // 생존(2)    , 블랙잭(2)     , 1.5
                arguments(score22count3, score21count2, BattingStatus.LUCKY),  // 죽음(3)    , 블랙잭(2)     , 1.5
                arguments(score21count3, score21count2, BattingStatus.LUCKY),  // 블랙잭(3)   , 블랙잭(2)    , 1.5
                arguments(score20count2, score21count3, BattingStatus.WIN),    // 생존(2)    , 블랙잭(3)     , 1
                arguments(score23count3, score21count3, BattingStatus.WIN),    // 죽음(3)    , 블랙잭(3)     , 1
                arguments(score23count3, score20count2, BattingStatus.WIN),    // 죽음(3)    , 생존(2)      , 1
                arguments(score19count2, score20count2, BattingStatus.WIN),    // 생존(2)    , 생존(2)      , 1
                arguments(score21count2, score20count2, BattingStatus.LOSE),   // 블랙잭(2)   , 생존(2)      , -1
                arguments(score21count3, score22count3, BattingStatus.LOSE),   // 블랙잭(3)   , 죽음(3)      , -1
                arguments(score21count2, score21count3, BattingStatus.LOSE),   // 블랙잭(2)   , 블랙잭(3)     , -1
                arguments(score20count2, score19count2, BattingStatus.LOSE),   // 생존(2)    , 생존(2)       , -1
                arguments(score20count2, score23count3, BattingStatus.LOSE),   // 생존(2)    , 죽음(3)       , -1
                arguments(score22count3, score23count3, BattingStatus.LOSE),   // 죽음(3)    , 죽음(3)       , -1
                arguments(score23count3, score22count3, BattingStatus.LOSE),   // 죽음(3)    , 죽음(3)       , -1
                arguments(score22count3, score22count3, BattingStatus.LOSE),   // 죽음(3)    , 죽음(3)       , -1
                arguments(score21count2, score21count2, BattingStatus.DRAW),   // 블랙잭(2)   , 블랙잭(2)     , 0
                arguments(score21count3, score21count3, BattingStatus.DRAW),   // 블랙잭(3)   , 블랙잭(3)     , 0
                arguments(score20count2, score20count2, BattingStatus.DRAW)    // 생존(2)    , 생존(2)       , 0
        );
    }


}
