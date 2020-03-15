package domain.game;

import domain.user.Dealer;
import domain.user.Users;
import factory.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.ResultGenerator;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultsTest {
    @Test
    @DisplayName("Results 생성")
    void create() {
        Dealer dealer = new Dealer();
        Users users = new Users(UserFactory.create("userA,userB"));

        assertThat(ResultGenerator.create(dealer, users)).isInstanceOf(Results.class);
    }
}
