package domain.game;

import domain.user.Dealer;
import domain.user.Players;
import factory.PlayerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.ResultCalculator;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultsTest {
    @Test
    @DisplayName("Results 생성")
    void create() {
        Dealer dealer = new Dealer();
        Players players = new Players(PlayerFactory.create("playerA,PlayerB"));

        assertThat(ResultCalculator.getResults(dealer,players)).isInstanceOf(Results.class);
    }
}
