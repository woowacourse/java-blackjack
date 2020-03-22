package util;

import domain.game.Results;
import domain.player.Dealer;
import domain.player.User;
import domain.player.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultGeneratorTest {
    @Test
    @DisplayName("결과 생성 확인")
    void create() {
        User userA = new User("userA");
        User userB = new User("userB");
        Dealer dealer = new Dealer();

        assertThat(ResultGenerator.create(dealer, new Users(Arrays.asList(userA, userB)))).isInstanceOf(Results.class);
    }
}
