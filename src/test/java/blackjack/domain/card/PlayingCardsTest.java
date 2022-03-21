package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.Fixtures;

import static org.assertj.core.api.Assertions.assertThat;

class PlayingCardsTest {

    private PlayingCards playingCards;

    @BeforeEach
    void setUp() {
        playingCards = new PlayingCards();
    }

    @Test
    @DisplayName("카드 객체 생성 확인")
    void createDeck() {
        assertThat(playingCards).isInstanceOf(PlayingCards.class);
    }

    @Test
    @DisplayName("cards에 card 더하는 로직 확인")
    void checkAddCard() {
        playingCards.addCard(Fixtures.SPADE_ACE);
        PlayingCards comparedPlayingCards = new PlayingCards();
        comparedPlayingCards.addCard(Fixtures.SPADE_ACE);

        assertThat(playingCards).isEqualTo(comparedPlayingCards);
    }

    @Test
    @DisplayName("보유한 card 2개일 때 점수 합산")
    void checkSumDeckCardsPoint() {
        playingCards.addCard(Fixtures.SPADE_ACE);
        playingCards.addCard(Fixtures.SPADE_TWO);
        int sumPoint = playingCards.calculatePoints();

        assertThat(sumPoint).isEqualTo(13);
    }

    @Test
    @DisplayName("보유한 card 3개일 때 점수 합산")
    void checkSumDeckThreeCardsPoint() {
        playingCards.addCard(Fixtures.SPADE_ACE);
        playingCards.addCard(Fixtures.SPADE_TWO);
        playingCards.addCard(Fixtures.SPADE_JACK);
        int sumPoint = playingCards.calculatePoints();

        assertThat(sumPoint).isEqualTo(13);
    }

    @Test
    @DisplayName("ace 4개 일때, 점수 확인")
    void checkPointsForFourAces() {
        playingCards.addCard(Fixtures.SPADE_ACE);
        playingCards.addCard(Fixtures.CLUB_ACE);
        playingCards.addCard(Fixtures.DIAMOND_ACE);
        playingCards.addCard(Fixtures.HEART_ACE);
        int sumPoint = playingCards.calculatePoints();

        assertThat(sumPoint).isEqualTo(14);
    }

    @Test
    @DisplayName("ace 3개 일때, 점수 확인")
    void checkPointsForThreeAces() {
        playingCards.addCard(Fixtures.SPADE_ACE);
        playingCards.addCard(Fixtures.CLUB_ACE);
        playingCards.addCard(Fixtures.DIAMOND_ACE);
        playingCards.addCard(Fixtures.SPADE_TWO);
        int sumPoint = playingCards.calculatePoints();

        assertThat(sumPoint).isEqualTo(15);
    }

    @Test
    @DisplayName("ace 2개 일때 12점 점수 확인")
    void checkPointsForTwoAces() {
        playingCards.addCard(Fixtures.CLUB_ACE);
        playingCards.addCard(Fixtures.DIAMOND_ACE);
        playingCards.addCard(Fixtures.SPADE_NINE);
        int sumPoint = playingCards.calculatePoints();

        assertThat(sumPoint).isEqualTo(21);
    }

    @Test
    @DisplayName("ace 2개 일때 2점 점수 확인")
    void checkPointsForTwoAcesIfBust() {
        playingCards.addCard(Fixtures.DIAMOND_ACE);
        playingCards.addCard(Fixtures.CLUB_ACE);
        playingCards.addCard(Fixtures.SPADE_JACK);
        int sumPoint = playingCards.calculatePoints();

        assertThat(sumPoint).isEqualTo(12);
    }
}
