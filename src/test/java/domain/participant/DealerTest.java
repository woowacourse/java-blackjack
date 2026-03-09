package domain.participant;

import domain.card.Card;
import domain.card.TrumpNumber;
import domain.card.TrumpSuit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    private final Dealer dealer = new Dealer(new HandCards(List.of(new Card(TrumpSuit.HEART, TrumpNumber.JACK), new Card(TrumpSuit.HEART, TrumpNumber.EIGHT)))); // 18점

    @Test
    void 딜러_전적_계산_테스트() {
        final int WIN_NUM = 2;
        final int DRAW_NUM = 1;
        final int LOSS_NUM = 3;

        List<Integer> playerScores = new ArrayList<>(List.of(14, 17, 19, 21, 20, 18));

        for (int playerScore : playerScores) {
            dealer.finalizeResult(playerScore);
        }

        assertThat(dealer.getRecord().get(WinStatus.WIN)).isEqualTo(WIN_NUM);
        assertThat(dealer.getRecord().get(WinStatus.DRAW)).isEqualTo(DRAW_NUM);
        assertThat(dealer.getRecord().get(WinStatus.LOSS)).isEqualTo(LOSS_NUM);
    }
}