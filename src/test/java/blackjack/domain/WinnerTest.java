package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinnerTest {

    private Dealer dealer;
    private Player player;
    private Winner winner;
    private Card twoSpade;
    private Card threeSpade;
    private Card queenSpade;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        player = new Player("pobi");
        winner = new Winner();
        twoSpade = Card.of(CardNumber.TWO, CardShape.SPADE);
        threeSpade = Card.of(CardNumber.THREE, CardShape.SPADE);
        queenSpade = Card.of(CardNumber.QUEEN, CardShape.SPADE);
    }

    @DisplayName("플레이어의 카드합이 21을 넘길 경우 승자에 포함되지 않는 것을 확인한다.")
    @Test
    void bust_player() {
        dealer.drawInitCards(List.of(twoSpade, twoSpade));
        player.drawInitCards(List.of(queenSpade, queenSpade, twoSpade));

        winner.compare(dealer, player);

        assertThat(winner.contains(player)).isFalse();
    }

    @DisplayName("플레이어의 값이 클 경우 비교하여 승자를 확인한다.")
    @Test
    void winner_player() {
        dealer.drawInitCards(List.of(twoSpade, twoSpade));
        player.drawInitCards(List.of(twoSpade, threeSpade));

        winner.compare(dealer, player);

        assertThat(winner.contains(player)).isTrue();
    }

    @DisplayName("플레이어의 값이 적을 경우 비교하여 승자를 확인한다.")
    @Test
    void winner_dealer() {
        dealer.drawInitCards(List.of(twoSpade, threeSpade));
        player.drawInitCards(List.of(twoSpade, twoSpade));

        winner.compare(dealer, player);

        assertThat(winner.contains(player)).isFalse();
    }

    @DisplayName("딜러의 값과 플레이어의 값이 같을 경우 승자를 확인한다.")
    @Test
    void equals_score() {
        dealer.drawInitCards(List.of(twoSpade, twoSpade));
        player.drawInitCards(List.of(twoSpade, twoSpade));

        winner.compare(dealer, player);

        assertThat(winner.contains(player)).isFalse();
    }

    @DisplayName("플레이어가 21을 초과하지 않았을 때 딜러가 21을 초과할 경우 승자를 확인한다.")
    @Test
    void bust_dealer() {
        dealer.drawInitCards(List.of(queenSpade, queenSpade, queenSpade));
        player.drawInitCards(List.of(twoSpade, twoSpade, twoSpade));

        winner.compare(dealer, player);

        assertThat(winner.contains(player)).isTrue();
    }
}