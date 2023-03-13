package domain;

import static domain.card.CardInfo.A;
import static domain.card.CardInfo.TEN;
import static domain.card.CardInfo.THREE;
import static domain.card.CardInfo.TWO;
import static domain.card.Shape.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.CardInfo;
import domain.card.Cards;
import domain.card.Shape;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardsTest {

    @Test
    void 카드뭉치_안에는_같은_카드가_두개일_수_없다() {
        List<Card> cards = List.of(new Card(HEART, A), new Card(HEART, A));
        assertThatThrownBy(() -> new Cards(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 카드는 들어갈 수 없어요.");
    }

    @Test
    void 카드뭉치는_가진_카드의_총합을_반환한다() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(HEART, A));
        cards.add(new Card(HEART, THREE));
        Cards actual = new Cards(cards);

        assertThat(actual.sumOfCards()).isEqualTo(14);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "HEART,A,false",
            "CLOVER,A,true"
    })
    void 카드뭉치는_카드를_추가할_경우_성공하면_True를_실패하면_False를_반환한다(Shape shape, CardInfo cardInfo, boolean expected) {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(HEART, A));
        cards.add(new Card(HEART, THREE));
        Cards actual = new Cards(cards);

        assertThat(actual.addCard(new Card(shape, cardInfo))).isEqualTo(expected);
    }

    @Test
    void 블랙잭인지_확인할_수_있다() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(HEART, A));
        cards.add(new Card(HEART, TEN));
        Cards actual = new Cards(cards);

        assertThat(actual.isBlackJack()).isTrue();
    }

    @Test
    void 카드의_수가_홀수일_때_총합을_반환한다() {
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(HEART, TWO));
        cards.add(new Card(HEART, THREE));
        cards.add(new Card(HEART, TEN));
        Cards actual = new Cards(cards);

        assertThat(actual.sumOfCards()).isEqualTo(15);
    }

    @Test
    void 카드의_수가_홀수일_때_총합을_반환하고_21을_넘을경우_A의값은_1로_대체된다() {
        List<Card> cards = new ArrayList<>();

        // 21 이 넘을 경우 A 는 1로 대체된다
        cards.add(new Card(HEART, A));
        cards.add(new Card(HEART, TWO));
        cards.add(new Card(HEART, TEN));
        Cards actual = new Cards(cards);

        assertThat(actual.sumOfCards()).isEqualTo(13);
    }
}
