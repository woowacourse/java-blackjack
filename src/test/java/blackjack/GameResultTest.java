package blackjack;

import blackjack.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    private Dealer dealer;
    private User user;
    private GameResult gameResult;

    @BeforeEach
    void setUp() {
        dealer = Dealer.getDealer();
        user = new User("bossdog");
        gameResult = new GameResult();
    }

    @DisplayName("딜러와 유저의 최종 상태에 따른 결과 계산")
    @Test
    void calculateGameResult() {
        dealer.setStatus(Status.BLACKJACK);
        user.setStatus(Status.BLACKJACK);

        PlayerResult playerResult = gameResult.calculatePlayerResult(dealer, user);
        assertThat(playerResult).isEqualTo(PlayerResult.DRAW);
    }
}
