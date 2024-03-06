package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.PlayingCardShape.DIAMOND;
import static domain.PlayingCardValue.EIGHT;
import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @DisplayName("딜러 인스턴스를 생성한다.")
    @Test
    void createDealerTest() {
        // When
        Dealer dealer = Dealer.init();

        // Then
        assertThat(dealer).isNotNull();
    }

    @DisplayName("딜러의 손패 합이 16이하이면 true를 반환한다.")
    @Test
    void isDrawableTest() {
        // Given
        List<PlayingCard> playingCards = List.of(new PlayingCard(DIAMOND, EIGHT));
        Hand hand = new Hand(playingCards);
        Dealer dealer = new Dealer(hand);

        // When
        boolean isDrawable = dealer.isDrawable();

        // Then
        assertThat(isDrawable).isTrue();
    }
}
