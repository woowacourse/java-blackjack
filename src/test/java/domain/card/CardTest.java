package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class CardTest {

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

    @Test
    void 모든_조합의_카드를_리스트로_얻을_수_있다() {
        List<Card> allCards = Card.allCards();
        assertThat(allCards).containsExactly(Card.values());
    }
}
