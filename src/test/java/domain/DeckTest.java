package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("덱테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DeckTest {

    @Test
    void 카드_두장을_분배한다() {
        List<Card> twoCards = Deck.provideTwoCards();

        assertAll(
            () -> assertThat(twoCards).hasSize(2),
            () -> assertThat(twoCards.get(0)).isNotEqualTo(twoCards.get(1))
        );
    }

}
