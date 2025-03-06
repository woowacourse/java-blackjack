package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class CardTest {

    @Test
    void 랭크와_문양으로_카드를_만들수있다() {
        Card card = new Card(Rank.JACK, Shape.SPADE);
        assertThat(card).isNotNull();
    }

    @ParameterizedTest
    @CsvSource({"JACK", "QUEEN", "KING"})
    void JQK이면_숫자가_10이다(Rank rank) {
        Card card = new Card(rank, Shape.SPADE);
        assertThat(card.getNumber()).isEqualTo(10);
    }

    @ParameterizedTest
    @CsvSource({
        "ACE,1", "TWO,2", "THREE,3", "FOUR,4", "FIVE,5",
        "SIX,6", "SEVEN,7", "EIGHT,8", "NINE,9", "TEN,10"
    })
    void 숫자이면_그대로_숫자이다(Rank rank, int expectedNumber) {
        Card card = new Card(rank, Shape.SPADE);
        assertThat(card.getNumber()).isEqualTo(expectedNumber);
    }
}
