package domain.gameplaying;

import static org.junit.jupiter.api.Assertions.*;

import domain.CardInfo;
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
    @MethodSource("cloverCards")
    @DisplayName("카드 정보는 라벨과 문양을 가져야 한다.")
    void 카드_정보_반환(Card provided) {
        String cardLabel = provided.rank().label();
        String cardMark = provided.cardMark().description();

        CardInfo expected = new CardInfo(cardLabel, cardMark);
        CardInfo actual = provided.info();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("aceCards")
    @DisplayName("A 카드인 지 반환할 수 있어야 한다.")
    void A카드_확인 (Card provided) {
        String cardLabel = provided.rank().label();
        String cardMark = provided.cardMark().description();

        CardInfo expected = new CardInfo(cardLabel, cardMark);
        CardInfo actual = provided.info();

        assertEquals(expected, actual);
    }

    private static Stream<CardRank> cardRanks() {
        return Stream.of(CardRank.values());
    }

    private static Stream<Card> cloverCards() {
        return Stream.of(
                new Card(CardRank.TWO, CardMark.CLOVER),
                new Card(CardRank.EIGHT, CardMark.CLOVER),
                new Card(CardRank.KING, CardMark.CLOVER),
                new Card(CardRank.ACE, CardMark.CLOVER)
        );
    }

    private static Stream<Card> aceCards() {
        return Stream.of(
                new Card(CardRank.ACE, CardMark.DIAMOND),
                new Card(CardRank.ACE, CardMark.SPADE),
                new Card(CardRank.ACE, CardMark.HEART),
                new Card(CardRank.ACE, CardMark.CLOVER)
        );
    }
}
