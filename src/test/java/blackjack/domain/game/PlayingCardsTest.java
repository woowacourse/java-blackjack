package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.fixture.Fixture.ACE;
import static blackjack.fixture.Fixture.TEN;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayingCardsTest {

    @DisplayName("카드들의 총합이 올바르게 계산되는지 확인한다.")
    @Test
    void calculate_total() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(TEN, TEN));

        assertThat(playingCards.total()).isEqualTo(20);
    }

    @DisplayName("카드들의 총합이 21을 초과하는지 확인한다.")
    @Test
    void is_over_blackjack() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(TEN, TEN, TEN));

        assertThat(playingCards.isBust()).isTrue();
    }

    @DisplayName("카드들의 총합이 21미만인지 확인한다.")
    @Test
    void is_under_blackjack() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(TEN, TEN));

        assertThat(playingCards.isUnderBlackjack()).isTrue();
    }

    @DisplayName("카드가 블랙잭인지 확인한다.")
    @Test
    void is_blackjack() {
        PlayingCards playingCards = new PlayingCards();
        playingCards.add(List.of(ACE, TEN));

        assertThat(playingCards.isBlackjack()).isTrue();
    }
}
