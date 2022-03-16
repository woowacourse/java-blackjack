package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayingCardTest {

    @Test
    @DisplayName("카드 인스턴스 생성 확인")
    void createCard() {
        PlayingCard playingCard = new PlayingCard(Suit.SPADE, Denomination.FOUR);

        assertThat(playingCard).isInstanceOf(PlayingCard.class);
    }

    @Test
    @DisplayName("생성된 카드가 ACE인지 확인")
    void isAce() {
        PlayingCard playingCard = new PlayingCard(Suit.SPADE, Denomination.ACE);
        assertThat(playingCard.isAce()).isTrue();
    }
}
