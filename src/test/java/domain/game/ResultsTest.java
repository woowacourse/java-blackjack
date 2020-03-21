package domain.game;

import domain.player.Dealer;
import domain.player.Users;
import factory.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.ResultGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultsTest {
    @Test
    @DisplayName("Results 생성")
    void create() {
        Dealer dealer = new Dealer();
        Users users = new Users(UserFactory.create("userA,userB"));
        List<BettingLog> bettingLogsList = new ArrayList<>();

        bettingLogsList.add(new BettingLog("userA", new Money("1000")));
        bettingLogsList.add(new BettingLog("userB", new Money("2000")));

        BettingLogs bettingLogs = new BettingLogs(bettingLogsList);

        assertThat(ResultGenerator.create(dealer, users, bettingLogs)).isInstanceOf(Results.class);
    }
}
