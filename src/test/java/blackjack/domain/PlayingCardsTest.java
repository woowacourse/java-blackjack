package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.blackjack.domain.Denomination;
import blackjack.blackjack.domain.PlayingCard;
import blackjack.blackjack.domain.PlayingCards;
import blackjack.blackjack.domain.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayingCardsTest {

    @Test
    @DisplayName("카드 묶음 생성 테스트")
    void playingCards_create() {
        // given
        final PlayingCards playingCards = new PlayingCards();
        playingCards.addCard(PlayingCard.of(Suit.CLUBS, Denomination.FIVE));
        playingCards.addCard(PlayingCard.of(Suit.HEARTS, Denomination.SEVEN));

        // when
        final List<PlayingCard> rawPlayingCard = playingCards.getPlayingCards();

        // then
        assertThat(rawPlayingCard).contains(PlayingCard.of(Suit.CLUBS, Denomination.FIVE));
    }
}
