package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
public class HandCardTest {

    @Test
    void 보관_중인_카드_존재여부_확인_테스트() {
        HandCard handCard = new HandCard();
        Type type = Type.valueOf("SPADE");
        Symbol symbol = Symbol.valueOf("ACE");
        handCard.add(new Card(type, symbol));

        Assertions.assertThat(handCard.isNotEmpty()).isTrue();
    }

    @Test
    void 카드_추가_테스트() {
        HandCard handCard = new HandCard();
        Type type = Type.valueOf("SPADE");
        Symbol symbol = Symbol.valueOf("ACE");
        handCard.add(new Card(type, symbol));
        type = Type.valueOf("HEART");
        symbol = Symbol.valueOf("SEVEN");
        handCard.add(new Card(type, symbol));
        type = Type.valueOf("CLUB");
        symbol = Symbol.valueOf("KING");
        handCard.add(new Card(type, symbol));

        Assertions.assertThat(handCard.get().size()).isEqualTo(3);
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE,FIVE,5", "DIAMOND,TEN,10", "HEART,ACE,11", "CLUB,KING,10"})
    void 카드_총합_계산_테스트(String type, String symbol, int expected) {
        Card card = new Card(Type.valueOf(type), Symbol.valueOf(symbol));
        HandCard handCard = new HandCard();
        handCard.add(card);

        Assertions.assertThat(handCard.getScore()).isEqualTo(expected);
    }

    @Test
    void 에이스_포함시_점수_계산_테스트() {
        HandCard handCard = new HandCard();
        Card card = new Card(Type.valueOf("SPADE"), Symbol.valueOf("ACE"));
        handCard.add(card);
        card = new Card(Type.valueOf("HEART"), Symbol.valueOf("ACE"));
        handCard.add(card);

        Assertions.assertThat(handCard.getScore()).isEqualTo(12);

        card = new Card(Type.valueOf("HEART"), Symbol.valueOf("NINE"));
        handCard.add(card);

        Assertions.assertThat(handCard.getScore()).isEqualTo(21);
    }

    @Test
    void 버스트_확인_테스트() {
        HandCard handCard = new HandCard();
        Type type = Type.valueOf("SPADE");
        Symbol symbol = Symbol.valueOf("QUEEN");
        handCard.add(new Card(type, symbol));
        type = Type.valueOf("HEART");
        symbol = Symbol.valueOf("SEVEN");
        handCard.add(new Card(type, symbol));

        Assertions.assertThat(handCard.isBust()).isFalse();

        type = Type.valueOf("CLUB");
        symbol = Symbol.valueOf("KING");
        handCard.add(new Card(type, symbol));

        Assertions.assertThat(handCard.isBust()).isTrue();
    }

    @Test
    void 블랙잭_확인_테스트() {
        HandCard handCard = new HandCard();
        Type type = Type.valueOf("SPADE");
        Symbol symbol = Symbol.valueOf("ACE");
        handCard.add(new Card(type, symbol));

        Assertions.assertThat(handCard.isBlackjack()).isFalse();

        type = Type.valueOf("HEART");
        symbol = Symbol.valueOf("KING");
        handCard.add(new Card(type, symbol));

        Assertions.assertThat(handCard.isBlackjack()).isTrue();
    }
}
