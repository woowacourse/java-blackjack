package domain.player.participant;

import domain.area.CardArea;
import domain.card.Card;
import domain.card.CardShape;
import domain.player.Name;
import domain.player.dealer.Dealer;
import domain.player.dealer.DealerResult;
import domain.player.participant.Participant;
import domain.player.participant.ParticipantResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.card.CardShape.*;
import static domain.card.CardValue.NINE;
import static domain.card.CardValue.TEN;
import static domain.player.participant.ParticipantResult.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ParticipantResult 의")
class ParticipantResultTest {

    CardArea participantCardArea;

    CardArea dealerCardArea;

    @BeforeEach
    void initCardArea() {
        participantCardArea = new CardArea(new Card(CLOVER, TEN), new Card(CLOVER, NINE));
        dealerCardArea = new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, NINE));
    }

    @Test
    @DisplayName("matchBetween() : 참여자가 burst 일 경우에는 참여자가 무조건 게임에서 진다.")
    void test_matchBetween_burst_participant_must_lose_participant() throws Exception {
        //given
        participantCardArea.addCard(new Card(SPADE, TEN));
        final Participant participant = new Participant(new Name("name"), participantCardArea);
        final Dealer dealer = new Dealer(dealerCardArea);

        //when
        final ParticipantResult participantResult = matchBetween(participant, dealer);

        //then
        assertEquals(LOSER, participantResult);
    }

    @Test
    @DisplayName("matchBetween() : 딜러는 burst 이면서 참여자가 burst 가 아니면 참여자가 무조건 게임에서 이긴다.")
    void test_matchBetween_burst_dealer_must_lose_dealer() throws Exception {
        //given
        final Participant participant = new Participant(new Name("name"), participantCardArea);
        final Dealer dealer = new Dealer(dealerCardArea);
        dealerCardArea.addCard(new Card(SPADE, TEN));

        //when
        final ParticipantResult participantResult = matchBetween(participant, dealer);

        //then
        assertEquals(WINNER, participantResult);
    }

    @ParameterizedTest
    @MethodSource("makeBothNotBurst")
    @DisplayName("matchBetween() : 딜러, 참여자 모두 버스트가 아닐 때 점수가 높은 쪽이 이기고, 같으면 무승부이다.")
    void test_matchBetween_not_burst_win_higher_score_or_draw_same_score(
            final CardArea participantCardArea, final CardArea dealerCardArea,
            final ParticipantResult participantResult) throws Exception {

        //given
        final Participant participant = new Participant(new Name("name"), participantCardArea);
        final Dealer dealer = new Dealer(dealerCardArea);

        //when & then
        assertEquals(ParticipantResult.matchBetween(participant, dealer), participantResult);
    }

    static Stream<Arguments> makeBothNotBurst() {

        //무승부
        final CardArea participantDrawCardArea = new CardArea(
                new Card(CardShape.SPADE, TEN),
                new Card(CardShape.DIAMOND, TEN)
        );

        final CardArea dealerDrawCardArea = new CardArea(
                new Card(HEART, TEN),
                new Card(CLOVER, TEN)
        );

        //참여자가 이길 경우
        final CardArea participantWinCardArea = new CardArea(
                new Card(CardShape.SPADE, TEN),
                new Card(CardShape.DIAMOND, TEN)
        );

        final CardArea dealerLoseCardArea = new CardArea(
                new Card(HEART, TEN),
                new Card(CLOVER, NINE)
        );

        //딜러가 이길 경우
        final CardArea participantLoseCardArea = new CardArea(
                new Card(CardShape.SPADE, TEN),
                new Card(CardShape.DIAMOND, NINE)
        );

        final CardArea dealerWinCardArea = new CardArea(
                new Card(HEART, TEN),
                new Card(CLOVER, TEN)
        );

        return Stream.of(
                Arguments.of(participantDrawCardArea, dealerDrawCardArea, DRAWER),
                Arguments.of(participantWinCardArea, dealerLoseCardArea, WINNER),
                Arguments.of(participantLoseCardArea, dealerWinCardArea, LOSER)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "WINNER -> LOSER",
            "DRAWER -> DRAWER",
            "LOSER -> WINNER"
    }, delimiterString = " -> ")
    @DisplayName("convertToDealerResult() : 무승부를 제외하고 딜러의 결과는 참여자의 결과에 대해 반대이다.")
    void test_convertToDealerResult_opposite_DealerResult_And_ParticipantResult(
            final ParticipantResult participantResult, final DealerResult dealerResult) throws Exception {

        //when & then
        assertEquals(participantResult.convertToDealerResult(), dealerResult);
    }
}
