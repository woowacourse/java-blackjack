package domain;

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
        Hand hand = new Hand(playingCards);

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
        Hand hand = new Hand(playingCards);

        // When
        boolean isBurst = hand.isBurst();

        // Then
        assertThat(isBurst).isTrue();
    }
}
