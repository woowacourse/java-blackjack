package domain.deck;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.TrumpCard;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DefaultShuffleTest {

    @Nested
    class ValidCases {

        @DisplayName("카드를 섞는다.")
        @Test
        void shuffle() {
            // given
            DefaultShuffle defaultShuffle = new DefaultShuffle();
            List<TrumpCard> originalCards = Arrays.asList(TrumpCard.values());

            List<TrumpCard> firstShuffleCards = Arrays.asList(TrumpCard.values());
            List<TrumpCard> secondShuffleCards = Arrays.asList(TrumpCard.values());

            // when
            defaultShuffle.shuffle(firstShuffleCards);
            defaultShuffle.shuffle(secondShuffleCards);

            // then
            assertSoftly(softly -> {
                softly.assertThat(firstShuffleCards).isNotEqualTo(originalCards);
                softly.assertThat(secondShuffleCards).isNotEqualTo(originalCards);
                softly.assertThat(firstShuffleCards).isNotEqualTo(secondShuffleCards);
            });
        }
    }
}
