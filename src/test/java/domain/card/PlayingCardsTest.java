package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayingCardsTest {
    @Test
    @DisplayName("카드 묶음 생성 테스트")
    void playingCards_create() {
        // given
        PlayingCards playingCards = new PlayingCards();
        playingCards.addCard(PlayingCard.of(Suit.CLUBS, Denomination.FIVE));
        playingCards.addCard(PlayingCard.of(Suit.HEARTS, Denomination.SEVEN));

        // when
        List<PlayingCard> rawPlayingCard = playingCards.getPlayingCards();

        // then
        assertThat(rawPlayingCard).contains(PlayingCard.of(Suit.CLUBS, Denomination.FIVE));
    }

    @Test
    @DisplayName("카드 묶음 컬렉션 수정 시도시 UOE 발생")
    void playingCardsShouldBeUnmodifiable() {
        // given
        PlayingCards playingCards = new PlayingCards();

        // when
        List<PlayingCard> unmodifiablePlayingCards = playingCards.getPlayingCards();

        // then
        assertThatThrownBy(
                () -> unmodifiablePlayingCards.add(PlayingCard.of(Suit.DIAMONDS, Denomination.JACK)))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
