package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayingCardTest {

    @Test
    @DisplayName("카드 인스턴스 생성 확인")
    public void createCard() {
        Suit suit = Suit.SPADE;
        Denomination denomination = Denomination.FOUR;
        PlayingCard playingCard = new PlayingCard(suit, denomination);

        assertThat(playingCard).isInstanceOf(PlayingCard.class);
    }
}
