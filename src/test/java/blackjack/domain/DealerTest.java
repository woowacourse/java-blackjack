package blackjack.domain;

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
        player = new Player("pobi");
        sixSpade = Card.of(CardNumber.SIX, CardShape.SPADE);
        sevenSpade = Card.of(CardNumber.SEVEN, CardShape.SPADE);
        queenSpade = Card.of(CardNumber.QUEEN, CardShape.SPADE);
    }

    @DisplayName("딜러의 카드 총 합이 16 이하일 경우 True 를 반환하는지 확인한다.")
    @Test
    void is_received_true() {
        dealer.receiveInitCards(List.of(sixSpade, queenSpade));

        assertThat(dealer.isReceived()).isTrue();
    }

    @DisplayName("딜러의 카드 총 합이 16 초과일 경우 False 를 반환하는지 확인한다.")
    @Test
    void is_received_false() {
        dealer.receiveInitCards(List.of(sevenSpade, queenSpade));

        assertThat(dealer.isReceived()).isFalse();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 큰 경우를 확인한다.")
    @Test
    void is_lower_score_true() {
        player.receiveInitCards(List.of(sixSpade, sixSpade));
        dealer.receiveInitCards(List.of(sevenSpade, sevenSpade));

        assertThat(dealer.isLowerScore(player)).isFalse();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 같은 경우를 확인한다.")
    @Test
    void is_lower_score_same() {
        player.receiveInitCards(List.of(sixSpade, sevenSpade));
        dealer.receiveInitCards(List.of(sixSpade, sevenSpade));

        assertThat(dealer.isLowerScore(player)).isFalse();
    }

    @DisplayName("딜러의 카드 총 합이 플레이어보다 작은 경우를 확인한다.")
    @Test
    void is_lower_score_false() {
        player.receiveInitCards(List.of(sevenSpade, sevenSpade));
        dealer.receiveInitCards(List.of(sixSpade, sixSpade));

        assertThat(dealer.isLowerScore(player)).isTrue();
    }
}
