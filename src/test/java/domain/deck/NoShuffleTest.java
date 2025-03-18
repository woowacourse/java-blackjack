package domain.deck;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.TrumpCard;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NoShuffleTest {

    @Nested
    class ValidCases {

        @DisplayName("카드를 섞지 않는다.")
        @Test
        void shuffle() {
            // given
            NoShuffle noShuffle = new NoShuffle();
            List<TrumpCard> originalCards = Arrays.asList(TrumpCard.values());

            List<TrumpCard> firstShuffleCards = Arrays.asList(TrumpCard.values());
            List<TrumpCard> secondShuffleCards = Arrays.asList(TrumpCard.values());

            // when
            noShuffle.shuffle(firstShuffleCards);
            noShuffle.shuffle(secondShuffleCards);

            // then
            assertSoftly(softly -> {
                softly.assertThat(firstShuffleCards).isEqualTo(originalCards);
                softly.assertThat(secondShuffleCards).isEqualTo(originalCards);
                softly.assertThat(firstShuffleCards).isEqualTo(secondShuffleCards);
            });
        }
    }
}
