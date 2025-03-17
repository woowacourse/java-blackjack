package model.bettings;

import static org.assertj.core.api.Assertions.assertThat;

import model.cards.Hand;
import model.participants.Dealer;
import model.participants.Name;
import model.participants.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {
    @DisplayName("딜러와 플레이어 핸드에 대해 판정 결과를 생성한다")
    @Test
    void evaluateResult() {
        Dealer dealer = new Dealer();
        dealer.initializeDealerWithHand();
        Hand dealerHand = dealer.getHand();

        Player player = new Player(new Name("player"), new Wager(1000), dealer.produceHand());
        Hand playerHand = player.getHand();

        assertThat(Result.evaluateResult(true, dealerHand, playerHand)).isNotNull();
    }
}
