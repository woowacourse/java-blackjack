package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardsTest {

    @Test
    void calculateScore_메서드_테스트() {
        //given, when
        List<Card> cards = List.of(new Card(Suit.CLOVER, Denomination.FIVE),
                new Card(Suit.HEART, Denomination.QUEEN));

        //then
        int expectedScore = 15;
        assertThat(new Cards(cards).calculateScore()).isEqualTo(expectedScore);
    }

    @Test
    void A가_나오면_11_혹은_1중에_고른다() {
        //given, when
        List<Card> cards = List.of(new Card(Suit.CLOVER, Denomination.QUEEN),
                new Card(Suit.HEART, Denomination.QUEEN), new Card(Suit.HEART, Denomination.ACE));

        //then
        int expectedScore = 21;
        assertThat(new Cards(cards).calculateScore()).isEqualTo(expectedScore);
    }

    @Test
    void 정상_생성_테스트() {
        //given, when, then
        Assertions.assertDoesNotThrow(() ->
                new Cards(
                        List.of(new Card(Suit.CLOVER, Denomination.QUEEN),
                                new Card(Suit.HEART, Denomination.QUEEN),
                                new Card(Suit.HEART, Denomination.ACE))));
    }
}
