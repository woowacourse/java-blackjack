package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class CardTest {

    @Test
    void 문양과_랭크로_카드를_얻을수있다() {
        Card card = Card.of(Shape.SPADE, Rank.JACK);
        assertThat(card).isEqualTo(Card.SPADE_J);
    }

    @ParameterizedTest
    @CsvSource({
        "SPADE_A,1", "SPADE_2,2", "SPADE_3,3", "DIAMOND_4,4", "DIAMOND_5,5",
        "HEART_6,6", "HEART_7,7", "CLOVER_8,8", "CLOVER_9,9", "CLOVER_10,10"
    })
    void 카드의_숫자를_알_수_있다(Card card, int expectedNumber) {
        assertThat(card.getNumber()).isEqualTo(expectedNumber);
    }

    @ParameterizedTest
    @CsvSource({"SPADE_J", "DIAMOND_Q", "HEART_K"})
    void 카드의_랭크가_JQK이면_숫자가_10이다(Card card) {
        assertThat(card.getNumber()).isEqualTo(10);
    }
}
