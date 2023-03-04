package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CardsTest {

    @Test
    void 카드뭉치_안에는_같은_카드가_두개일_수_없다() {
        List<Card> cards = List.of(new Card("A하트", 11), new Card("A하트", 11));
        assertThatThrownBy(() -> new Cards(cards))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 카드뭉치는_가진_카드의_총합을_반환한다() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("A하트", 11));
        cards.add(new Card("3하트", 3));
        Cards actual = new Cards(cards);

        assertThat(actual.sumOfCards()).isEqualTo(14);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "A하트,11,false",
            "A클로버,11,true"
    })
    void 카드뭉치는_카드를_추가할_경우_성공하면_True를_실패하면_False를_반환한다(String cardName, int cardValue, boolean expected) {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("A하트", 11));
        cards.add(new Card("3하트", 3));
        Cards actual = new Cards(cards);

        assertThat(actual.addCard(new Card(cardName, cardValue))).isEqualTo(expected);
    }

    @Test
    void 블랙잭인지_확인할_수_있다() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("A하트", 11));
        cards.add(new Card("10하트", 10));
        Cards actual = new Cards(cards);

        assertThat(actual.isBlackJack()).isTrue();
    }

    @Test
    void 카드의_수가_홀수일_때_총합을_반환한다() {
        List<Card> cards = new ArrayList<>();

        // 21 이 넘을 경우 A 는 1로 대체된다
        cards.add(new Card("2하트", 2));
        cards.add(new Card("3하트", 3));
        cards.add(new Card("10하트", 10));
        Cards actual = new Cards(cards);

        assertThat(actual.sumOfCards()).isEqualTo(15);
    }

    @Test
    void 카드의_수가_홀수일_때_총합을_반환하고_21을_넘을경우_A의값은_1로_대체된다() {
        List<Card> cards = new ArrayList<>();

        // 21 이 넘을 경우 A 는 1로 대체된다
        cards.add(new Card("A하트", 11));
        cards.add(new Card("2하트", 2));
        cards.add(new Card("10하트", 10));
        Cards actual = new Cards(cards);

        assertThat(actual.sumOfCards()).isEqualTo(13);
    }
}
