package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayingCardsTest {

    private PlayingCards playingCards;

    @BeforeEach
    public void setUp() {
        playingCards = new PlayingCards();
    }

    @Test
    @DisplayName("카드 객체 생성 확인")
    public void createDeck() {
        assertThat(playingCards).isInstanceOf(PlayingCards.class);
    }

    @Test
    @DisplayName("cards에 card 더하는 로직 확인")
    public void checkAddCard() {
        playingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.EIGHT));
        PlayingCards comparedPlayingCards = new PlayingCards();
        comparedPlayingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.EIGHT));

        assertThat(playingCards).isEqualTo(comparedPlayingCards);
    }

    @Test
    @DisplayName("보유한 card 2개일 때 점수 합산")
    public void checkSumDeckCardsPoint() {
        playingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.EIGHT));
        playingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.TWO));
        int sumPoint = playingCards.sumPoints();

        assertThat(sumPoint).isEqualTo(10);
    }

    @Test
    @DisplayName("보유한 card 3개일 때 점수 합산")
    public void checkSumDeckThreeCardsPoint() {
        playingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.EIGHT));
        playingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.TWO));
        playingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.JACK));
        int sumPoint = playingCards.sumPoints();

        assertThat(sumPoint).isEqualTo(20);
    }

    @Test
    @DisplayName("ace 4개 일때 점수 확인")
    public void checkPointsForFourAces() {
        playingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.ACE));
        playingCards.addCard(new PlayingCard(Suit.CLUB, Denomination.ACE));
        playingCards.addCard(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
        playingCards.addCard(new PlayingCard(Suit.HEART, Denomination.ACE));
        int sumPoint = playingCards.sumPoints();

        assertThat(sumPoint).isEqualTo(14);
    }

    @Test
    @DisplayName("ace 3개 일때 13점 점수 확인")
    public void checkPointsForThreeAces() {
        playingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.ACE));
        playingCards.addCard(new PlayingCard(Suit.CLUB, Denomination.ACE));
        playingCards.addCard(new PlayingCard(Suit.DIAMOND, Denomination.ACE));
        playingCards.addCard(new PlayingCard(Suit.DIAMOND, Denomination.TWO));
        int sumPoint = playingCards.sumPoints();

        assertThat(sumPoint).isEqualTo(15);
    }

    @Test
    @DisplayName("ace 2개 일때 12점 점수 확인")
    public void checkPointsForTwoAces() {
        playingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.ACE));
        playingCards.addCard(new PlayingCard(Suit.CLUB, Denomination.ACE));
        playingCards.addCard(new PlayingCard(Suit.DIAMOND, Denomination.NINE));
        int sumPoint = playingCards.sumPoints();

        assertThat(sumPoint).isEqualTo(21);
    }

    @Test
    @DisplayName("ace 2개 일때 2점 점수 확인")
    public void checkPointsForTwoAcesOverLimit() {
        playingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.ACE));
        playingCards.addCard(new PlayingCard(Suit.CLUB, Denomination.ACE));
        playingCards.addCard(new PlayingCard(Suit.DIAMOND, Denomination.JACK));
        int sumPoint = playingCards.sumPoints();

        assertThat(sumPoint).isEqualTo(12);
    }
}
