package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    private Dealer dealer;
    private Player player;
    private Card sixSpade;
    private Card sevenSpade;
    private Card queenSpade;

    @BeforeEach
    void before() {
        dealer = new Dealer();
        dealer.bet(1000);
        player = new Player("pobi");
        player.bet(1000);
        sixSpade = Card.of(Denomination.SIX, Suit.SPADE);
        sevenSpade = Card.of(Denomination.SEVEN, Suit.SPADE);
        queenSpade = Card.of(Denomination.QUEEN, Suit.SPADE);
    }

    @DisplayName("딜러의 카드 총 합이 16 이하일 경우 True 를 반환하는지 확인한다.")
    @Test
    void is_received_true() {
        dealer.deal(List.of(sixSpade, queenSpade));

        assertThat(dealer.isDrawable()).isTrue();
    }

    @DisplayName("딜러의 카드 총 합이 16 초과일 경우 False 를 반환하는지 확인한다.")
    @Test
    void is_received_false() {
        dealer.deal(List.of(sevenSpade, queenSpade));

        assertThat(dealer.isDrawable()).isFalse();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 큰 경우를 확인한다.")
    @Test
    void is_lower_score_true() {
        player.deal(List.of(sixSpade, sixSpade));
        dealer.deal(List.of(sevenSpade, sevenSpade));

        assertThat(dealer.isLowerScore(player)).isFalse();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 같은 경우를 확인한다.")
    @Test
    void is_lower_score_same() {
        player.deal(List.of(sixSpade, sevenSpade));
        dealer.deal(List.of(sixSpade, sevenSpade));

        assertThat(dealer.isLowerScore(player)).isFalse();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 작은 경우를 확인한다.")
    @Test
    void is_lower_score_false() {
        player.deal(List.of(sevenSpade, sevenSpade));
        dealer.deal(List.of(sixSpade, sixSpade));

        assertThat(dealer.isLowerScore(player)).isTrue();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 큰 경우를 확인한다.")
    @Test
    void is_higher_score_true() {
        player.deal(List.of(sixSpade, sixSpade));
        dealer.deal(List.of(sevenSpade, sevenSpade));

        assertThat(dealer.isHigherScore(player)).isTrue();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 같은 경우를 확인한다.")
    @Test
    void is_higher_score_same() {
        player.deal(List.of(sixSpade, sevenSpade));
        dealer.deal(List.of(sixSpade, sevenSpade));

        assertThat(dealer.isHigherScore(player)).isFalse();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 작은 경우를 확인한다.")
    @Test
    void is_higher_score_false() {
        player.deal(List.of(sevenSpade, sevenSpade));
        dealer.deal(List.of(sixSpade, sixSpade));

        assertThat(dealer.isHigherScore(player)).isFalse();
    }
}
