package domain.participant;

import domain.PlayingCard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.PlayingCardShape.*;
import static domain.PlayingCardValue.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    @DisplayName("손패 인스턴스가 생성된다.")
    @Test
    void createHandTest() {
        // When
        Hand hand = Hand.init();

        // Then
        assertThat(hand).isNotNull();
    }

    @DisplayName("손패에 있는 카드의 숫자합을 반환한다.")
    @Test
    void getCardsNumberSumTest() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(CLOVER, FOUR), new PlayingCard(DIAMOND, SEVEN));
        Hand hand = Hand.init();
        playingCards.forEach(hand::addCard);

        // When
        int result = hand.getCardsNumberSum();

        // Then
        assertThat(result).isEqualTo(11);
    }

    @DisplayName("손패에 있는 카드의 합이 21을 넘으면 true반환한다.")
    @Test
    void isBurstTest() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(DIAMOND, KING), new PlayingCard(CLOVER, QUEEN), new PlayingCard(SPADE, NINE));
        Hand hand = Hand.init();
        playingCards.forEach(hand::addCard);

        // When
        boolean isBurst = hand.isBurst();

        // Then
        assertThat(isBurst).isTrue();
    }

    @DisplayName("손패에 새로운 카드를 추가한다.")
    @Test
    void addCardTest() {
        // Given
        Hand hand = Hand.init();
        int initCardNumberSum = hand.getCardsNumberSum();
        PlayingCard card = new PlayingCard(DIAMOND, NINE);

        // When
        hand.addCard(card);

        // Then
        assertThat(initCardNumberSum).isNotEqualTo(hand.getCardsNumberSum());
    }

    @DisplayName("손패가 블랙잭이면 true를 반환한다.")
    @Test
    void isBlackJackTest() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(DIAMOND, KING), new PlayingCard(CLOVER, QUEEN), new PlayingCard(SPADE, ACE));
        Hand hand = Hand.init();
        playingCards.forEach(hand::addCard);

        // When
        boolean isBlackJack = hand.isBlackJack();

        // Then
        Assertions.assertThat(isBlackJack).isTrue();
    }
}
