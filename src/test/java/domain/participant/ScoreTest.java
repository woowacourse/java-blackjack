package domain.participant;

import domain.playingcard.PlayingCard;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.playingcard.PlayingCardShape.DIAMOND;
import static domain.playingcard.PlayingCardShape.HEART;
import static domain.playingcard.PlayingCardValue.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScoreTest {

    @Test
    void 카드_목록을_입력하면_카드_숫자_합계를_반환한다() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(DIAMOND, SEVEN), new PlayingCard(HEART, TWO));

        // When
        Score score = Score.of(playingCards);

        // Then
        assertThat(score.value()).isEqualTo(9);
    }

    @Test
    void ACE가_포함된_손패의_합을_21에서_뺀_값이_10이상이면_ACE_한_장을_11로_계산한다() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(DIAMOND, SEVEN), new PlayingCard(HEART, ACE));

        // When
        Score score = Score.of(playingCards);

        // Then
        assertThat(score.value()).isEqualTo(18);
    }

    @Test
    void ACE가_포함된_손패의_합을_21에서_뺀_값이_10_미만이면_ACE를_모두_1로_계산한다() {
        // Given
        List<PlayingCard> playingCards = List.of(
                new PlayingCard(DIAMOND, SEVEN),
                new PlayingCard(DIAMOND, KING),
                new PlayingCard(DIAMOND, ACE)
        );

        // When
        Score score = Score.of(playingCards);

        // Then
        assertThat(score.value()).isEqualTo(18);
    }
}
