package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayingCardsTest {

    private PlayingCards playingCards;

    @BeforeEach
    public void setUp() {
        playingCards = new PlayingCards(new AlwaysAscNumberMachinePlaying());
    }
    @Test
    @DisplayName("Cards 객체 생성 확인")
    public void createCards() {
        assertThat(playingCards).isInstanceOf(PlayingCards.class);
    }

    @Test
    @DisplayName("Cards 반환 확인")
    public void checkCardReturn() {
        PlayingCard playingCard = playingCards.assignCard();

        assertThat(playingCard.getRank()).isEqualTo(Denomination.ACE);
    }
}
