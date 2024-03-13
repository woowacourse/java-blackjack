package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @DisplayName("딜러 카드 패의 총 합이 16 이하라면 Hit이다.")
    @Test
    void dealerHitTest() {
        // given
        Dealer dealer = Dealer.from(
                new ArrayList<>(List.of(new Card(Suit.HEART, Rank.TEN), new Card(Suit.HEART, Rank.SIX))));

        // when & then
        assertThat(dealer.hasHitScore()).isTrue();
    }

    @DisplayName("딜러 카드 패의 총 합이 16 초과라면 Stand이다.")
    @Test
    void dealerStandTest() {
        // given
        Dealer dealer = Dealer.from(
                new ArrayList<>(List.of(new Card(Suit.HEART, Rank.TEN), new Card(Suit.HEART, Rank.SEVEN))));

        // when & then
        assertThat(dealer.hasHitScore()).isFalse();
    }
}
