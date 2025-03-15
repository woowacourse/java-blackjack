package Blackjack.domain.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest {

    @ParameterizedTest
    @CsvSource({"ACE,1", "TWO,2", "THREE,3", "FOUR,4", "FIVE,5", "SIX,6", "SEVEN,7", "EIGHT,8", "NINE,9", "TEN,10",
            "JACK,10", "QUEEN,10", "KING,10"})
    @DisplayName("카드의 점수 계산 기능 테스트")
    void calculateCardScoreTest(String rankName, int rankScore) {
        // given
        Card card = new Card(Rank.valueOf(rankName), Suit.CLOVER);
        // when & then
        assertEquals(rankScore, card.rank().getValue());
    }
}
