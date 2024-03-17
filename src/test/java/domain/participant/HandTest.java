package domain.participant;

import domain.playingcard.Deck;
import domain.playingcard.PlayingCard;
import domain.playingcard.PlayingCards;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.playingcard.PlayingCardShape.*;
import static domain.playingcard.PlayingCardValue.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class HandTest {

    @DisplayName("손패 인스턴스가 생성된다.")
    @Test
    void createHandTest() {
        // When
        Hand hand = Hand.init(Deck.init(PlayingCards.getValue()));

        // Then
        assertThat(hand).isNotNull();
    }

    @DisplayName("손패에 있는 카드의 숫자합을 반환한다.")
    @Test
    void getCardsNumberSumTest() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(CLOVER, FOUR), new PlayingCard(DIAMOND, SEVEN));
        Hand hand = new Hand(playingCards);

        // When
        Score totalScore = hand.getTotalScore();

        // Then
        assertThat(totalScore.value()).isEqualTo(11);
    }

    @Test
    void ACE가_포함된_손패의_합을_21에서_뺀_값이_10이상이면_ACE_한_장을_11로_계산한다() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(DIAMOND, SEVEN), new PlayingCard(HEART, ACE));
        Hand hand = new Hand(playingCards);

        // When
        Score score = hand.getTotalScore();

        // Then
        assertThat(score.value()).isEqualTo(18);
    }

    @Test
    void ACE가_포함된_손패의_합을_21에서_뺀_값이_10_미만이면_ACE를_모두_1로_계산한다() {
        // Given
        List<PlayingCard> playingCards = List.of(
                new PlayingCard(DIAMOND, SEVEN),
                new PlayingCard(DIAMOND, KING),
                new PlayingCard(DIAMOND, ACE)
        );
        Hand hand = new Hand(playingCards);

        // When
        Score score = hand.getTotalScore();

        // Then
        assertThat(score.value()).isEqualTo(18);
    }

    @DisplayName("손패에 새로운 카드를 추가한다.")
    @Test
    void addCardTest() {
        // Given
        PlayingCard card = new PlayingCard(DIAMOND, NINE);
        Hand hand = Hand.init(Deck.init(PlayingCards.getValue()));
        List<PlayingCard> playingCards = hand.getPlayingCards();

        // When
        hand.addCard(card);

        // Then
        assertThat(playingCards).contains(card);
    }

    @DisplayName("손패가 블랙잭이면 true를 반환한다.")
    @Test
    void isBlackJackTest() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(DIAMOND, KING), new PlayingCard(SPADE, ACE));
        Hand hand = new Hand(playingCards);

        // When
        boolean isBlackJack = hand.isBlackJack();

        // Then
        Assertions.assertThat(isBlackJack).isTrue();
    }

    @DisplayName("손패합의 상태가 버스트면 true를 반환한다.")
    @Test
    void isBustTest() {
        // Given
        List<PlayingCard> playingCards = List.of(
                new PlayingCard(DIAMOND, KING),
                new PlayingCard(SPADE, QUEEN),
                new PlayingCard(HEART, QUEEN)
        );
        Hand hand = new Hand(playingCards);

        // When
        boolean isBust = hand.isBust();

        // Then
        Assertions.assertThat(isBust).isTrue();
    }

    @Test
    void 손패의_합이_입력된_정수보다_작거나_같으면_true를_반환한다() {
        // Given
        List<PlayingCard> playingCards = List.of(
                new PlayingCard(SPADE, QUEEN),
                new PlayingCard(HEART, QUEEN)
        );
        Hand hand = new Hand(playingCards);
        int target = 20;

        // When
        boolean isTotalScoreLessOrEqual = hand.isTotalScoreLessOrEqual(target);

        // Then
        assertThat(isTotalScoreLessOrEqual).isTrue();
    }
}
