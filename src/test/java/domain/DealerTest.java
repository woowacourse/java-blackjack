package domain;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.player.Dealer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러가 상대 플레이어보다 점수가 높으면 승이 추가된다.")
    void givenDealerWinningFromPlayer_thenAddWinning() {
        dealer.addCard(new Card(Shape.DIAMOND, Number.KING));
        Dealer anotherPlayer = new Dealer();
        anotherPlayer.addCard(new Card(Shape.DIAMOND, Number.NINE));

        dealer.battle(anotherPlayer);

        assertThat(dealer.getWinningCount()).isEqualTo(1);
        assertThat(dealer.getLoseCount()).isEqualTo(0);
        assertThat(dealer.getDrawCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러가 상대 플레이어보다 점수가 낮으면 패가 추가된다.")
    void givenDealerLosingFromPlayer_thenAddLose() {
        dealer.addCard(new Card(Shape.DIAMOND, Number.NINE));
        Dealer anotherPlayer = new Dealer();
        anotherPlayer.addCard(new Card(Shape.DIAMOND, Number.ACE));

        dealer.battle(anotherPlayer);

        assertThat(dealer.getWinningCount()).isEqualTo(0);
        assertThat(dealer.getLoseCount()).isEqualTo(1);
        assertThat(dealer.getDrawCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러가 상대 플레이어와 점수가 같으면 무승부가 추가된다.")
    void givenDealerDrawingWithPlayer_thenAddDraw() {
        dealer.addCard(new Card(Shape.DIAMOND, Number.NINE));
        Dealer anotherPlayer = new Dealer();
        anotherPlayer.addCard(new Card(Shape.DIAMOND, Number.NINE));

        dealer.battle(anotherPlayer);

        assertThat(dealer.getWinningCount()).isEqualTo(0);
        assertThat(dealer.getLoseCount()).isEqualTo(0);
        assertThat(dealer.getDrawCount()).isEqualTo(1);
    }
}