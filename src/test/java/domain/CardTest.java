package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {

    @ParameterizedTest
    @MethodSource("cardRanks")
    @DisplayName("카드 가치를 숫자로 변환해야 한다.")
    void 카드_가치_변환(CardRank cardRank) {
        Card card = new Card(cardRank, CardMark.CLOVER);

        int expected = cardRank.score();
        int actual = card.score();

        assertEquals(expected, actual);
    }


    @ParameterizedTest
    @MethodSource("cardInfo")
    @DisplayName("카드 정보가 정상적으로 반환되어야 한다.")
    void 카드_정보_반환(Card provided) {
        Card card = provided;

        String expected = "8클로버";
        String actual = card.info();

        assertEquals(expected, actual);
    }

    static Stream<CardRank> cardRanks() {
        return Stream.of(CardRank.values());
    }

    static Stream<Card> cardInfo() {
        return Stream.of(new Card(CardRank.EIGHT, CardMark.CLOVER));
    }
}
