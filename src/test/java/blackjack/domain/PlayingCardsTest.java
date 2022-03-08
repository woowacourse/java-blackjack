package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayingCardsTest {
    List<PlayingCard> playingCards = new ArrayList<>();

    @BeforeEach
    void setUp() {
        playingCards.add(PlayingCard.of(Suit.CLUBS, Denomination.FIVE));
        playingCards.add(PlayingCard.of(Suit.HEARTS, Denomination.SEVEN));
    }

    @Test
    @DisplayName("카드 묶음 생성 테스트")
    void playingCards_create() {
        // given
        final PlayingCards playingCards = new PlayingCards(this.playingCards);

        // when
        final List<PlayingCard> rawPlayingCard = playingCards.getPlayingCards();

        // then
        assertThat(rawPlayingCard).contains(PlayingCard.of(Suit.CLUBS, Denomination.FIVE));
    }
}
