package domain.game;

import domain.area.CardArea;
import domain.card.Card;
import domain.player.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.card.CardShape.DIAMOND;
import static domain.card.CardValue.*;
import static domain.fixture.PlayerFixture.말랑;
import static domain.game.ParticipantResult.*;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ParticipantResult 는")
class ParticipantResultTest {

    static Stream<Arguments> participantAndDealerAndResult() {
        final CardArea cardArea16 = new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, SIX));
        final CardArea cardArea17 = new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, SEVEN));
        final CardArea cardArea19 = new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, NINE));
        final CardArea cardArea20 = new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, TEN));
        final CardArea cardArea21 = new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, ACE));

        final CardArea cardArea22 = new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, TEN));
        cardArea22.addCard(new Card(DIAMOND, TWO));

        return Stream.of(
                Arguments.of(Named.of("16", cardArea16), Named.of("17", cardArea17), LOSER),
                Arguments.of(Named.of("16", cardArea16), Named.of("22", cardArea22), WINNER),
                Arguments.of(Named.of("16", cardArea16), Named.of("21", cardArea21), LOSER),
                Arguments.of(Named.of("22", cardArea22), Named.of("22", cardArea22), LOSER),
                Arguments.of(Named.of("22", cardArea22), Named.of("19", cardArea19), LOSER),
                Arguments.of(Named.of("21", cardArea21), Named.of("21", cardArea21), DRAWER),
                Arguments.of(Named.of("21", cardArea21), Named.of("20", cardArea20), WINNER),
                Arguments.of(Named.of("16", cardArea16), Named.of("16", cardArea16), DRAWER)
        );
    }

    @ParameterizedTest(name = "참가자의 점수가 {0}, 딜러의 점수가 {1} 인 경우, 참가자는 {2} 이다")
    @MethodSource("participantAndDealerAndResult")
    void judge_시_참가자의_승리여부를_판별한다(final CardArea participantCardArea, final CardArea dealerCardArea, final ParticipantResult participantResult) {
        // when
        final ParticipantResult judge = judge(말랑(participantCardArea), new Dealer(dealerCardArea));

        // then
        assertThat(judge).isEqualTo(participantResult);
    }

    @ParameterizedTest(name = "reverse() 시 {0} 는 {1} 로 변경된다.")
    @CsvSource(value = {
            "WINNER -> LOSER",
            "LOSER -> WINNER",
            "DRAWER -> DRAWER"
    }, delimiterString = " -> ")
    void reverse_테스트(final ParticipantResult before, final ParticipantResult reversed) {
        // then
        assertThat(before.reverse()).isEqualTo(reversed);
    }
}