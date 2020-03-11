import domain.HandCard;
import domain.card.Card;
import domain.card.CardFactory;
import domain.card.Symbol;
import domain.card.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
public class HandCardTest {
    @Test
    void 카드_추가_테스트() {
        HandCard handCard = new HandCard();
        Type type = Type.valueOf("SPADE");
        Symbol symbol = Symbol.valueOf("ACE");
        handCard.add(CardFactory.of(type, symbol));

        Assertions.assertThat(handCard.getNames()).isEqualTo("A스페이드");
    }

    @Test
    void 카드_이름_출력_테스트() {
        HandCard handCard = new HandCard();
        Type type = Type.valueOf("SPADE");
        Symbol symbol = Symbol.valueOf("ACE");
        handCard.add(CardFactory.of(type, symbol));
        type = Type.valueOf("HEART");
        symbol = Symbol.valueOf("SEVEN");
        handCard.add(CardFactory.of(type, symbol));
        type = Type.valueOf("CLUB");
        symbol = Symbol.valueOf("KING");
        handCard.add(CardFactory.of(type, symbol));

        Assertions.assertThat(handCard.getNames()).isEqualTo("A스페이드,7하트,K클로버");
    }

    @ParameterizedTest
    @CsvSource(value = {"SPADE,FIVE,5", "DIAMOND,TEN,10", "HEART,ACE,11", "CLUB,KING,10"})
    void 점수_계산_테스트(String type, String symbol, int expected) {
        Card card = CardFactory.of(Type.valueOf(type), Symbol.valueOf(symbol));
        HandCard handCard = new HandCard();
        handCard.add(card);

        Assertions.assertThat(handCard.getScore()).isEqualTo(expected);
    }

    @Test
    void 에이스_점수_계산_테스트() {
        HandCard handCard = new HandCard();
        Card card = CardFactory.of(Type.valueOf("SPADE"), Symbol.valueOf("ACE"));
        handCard.add(card);
        card = CardFactory.of(Type.valueOf("HEART"), Symbol.valueOf("ACE"));
        handCard.add(card);
        Assertions.assertThat(handCard.getScore()).isEqualTo(12);

        card = CardFactory.of(Type.valueOf("HEART"), Symbol.valueOf("NINE"));
        handCard.add(card);

        Assertions.assertThat(handCard.getScore()).isEqualTo(21);
    }
}
