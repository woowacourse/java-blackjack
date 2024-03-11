package dealer;

import card.CardDeck;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Players;

class DealerTest {

    private static final int MIN_DEALER_SCORE = 16;

    @DisplayName("최소 점수를 넘을때까지 카드를 받는다.")
    @Test
    void getExtraCardIfNotOverMinScore() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.firstCardSettings());

        dealer.getExtraCard(cardDeck);

        Assertions.assertTrue(dealer.getCardScore() > MIN_DEALER_SCORE);
    }

    @DisplayName("딜러 시점의 우승 정보를 가져온다")
    @Test
    void getDealerWinningResult() {
        Players players = Players.from(List.of("pola", "poll"), new CardDeck());
        Dealer dealer = new Dealer(new ArrayList<>());

        org.assertj.core.api.Assertions.assertThat(dealer.getWinningResult(players).winningCount()).isEqualTo(0);
    }
}
