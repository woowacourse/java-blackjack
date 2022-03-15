package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayingCardsTest {

    private List<PlayingCard> playingCards;

    @BeforeEach
    public void setUp() {
        playingCards = PlayingCards.getPlayingCards();
    }

    @Test
    @DisplayName("Cards 반환 확인")
    public void checkCardReturn() {
        assertThat(playingCards.size()).isEqualTo(52);
    }
}
