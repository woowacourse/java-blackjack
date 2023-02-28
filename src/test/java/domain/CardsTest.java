package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardsTest {

    @Test
    void calculateScore_메서드_테스트() {
        //given, when
        List<Card> cards = List.of(new Card(Suit.CLOVER, Denomination.FIVE),
                new Card(Suit.HEART, Denomination.QUEEN));

        //then
        int expectedScore = 15;
        Assertions.assertThat(new Cards(cards).calculateScore()).isEqualTo(expectedScore);
    }
}