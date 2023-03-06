package domain.player.participant;

import domain.area.CardArea;
import domain.card.Card;
import domain.player.dealer.DealerResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static domain.card.CardShape.CLOVER;
import static domain.card.CardShape.DIAMOND;
import static domain.card.CardValue.NINE;
import static domain.card.CardValue.TEN;
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
