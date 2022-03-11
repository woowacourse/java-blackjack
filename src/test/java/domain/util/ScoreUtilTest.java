package domain.util;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.JACK;
import static domain.card.Denomination.SEVEN;
import static domain.card.Suit.DIAMONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.PlayingCard;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreUtilTest {
    @Test
    @DisplayName("점수 계산 테스트")
    void scoreResultTest() {
        // given
        List<PlayingCard> cards = List.of(
                PlayingCard.of(DIAMONDS, SEVEN),
                PlayingCard.of(DIAMONDS, JACK),
                PlayingCard.of(DIAMONDS, ACE)
        );

        // when
        int seventeen = ScoreUtil.getScore(List.of(cards.get(0), cards.get(1)));
        int blackjack = ScoreUtil.getScore(List.of(cards.get(1), cards.get(2)));
        int twelve = ScoreUtil.getScore(List.of(cards.get(2), cards.get(2)));
        int nineteen = ScoreUtil.getScore(List.of(cards.get(0), cards.get(2), cards.get(2)));

        // then
        assertAll(
                () -> assertThat(seventeen == 17).isTrue(),
                () -> assertThat(blackjack == 21).isTrue(),
                () -> assertThat(twelve == 12).isTrue(),
                () -> assertThat(nineteen == 19).isTrue()
        );
    }
}
