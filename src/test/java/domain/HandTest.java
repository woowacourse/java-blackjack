package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.PlayingCardShape.CLOVER;
import static domain.PlayingCardShape.DIAMOND;
import static domain.PlayingCardValue.FOUR;
import static domain.PlayingCardValue.SEVEN;
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
}
