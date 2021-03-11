package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {
    Users users;
    Player player;
    Player player2;


    @BeforeEach
    void setUp() {
        users = new Users(new Dealer(), Arrays.asList("youngE", "kimkim"));
        player = users.getPlayers().get(0);
        player2 = users.getPlayers().get(1);
        player.hit(Fixture.CLUBS_ACE, Fixture.CLUBS_KING);
        player2.hit(Fixture.CLUBS_ACE, Fixture.CLUBS_TWO);
    }

    @DisplayName("딜러와 각 플레이어 간의 승패를 가린다. - 딜러가 블랙잭일 때")
    @Test
    void checkWinOrLoseWhenDealerHasBlackJackTest() {
        Dealer dealer = new Dealer();

        dealer.hit(Fixture.CLUBS_KING, Fixture.CLUBS_ACE);

        Map<User, Result> resultMap = users.checkResult(dealer);
        assertThat(resultMap).isEqualTo(new HashMap<User, Result>() {
            {
                put(player, Result.DRAW);
                put(player2, Result.LOSE);
            }
        });
    }

    @DisplayName("딜러와 각 플레이어 간의 승패를 가린다. - 딜러가 블랙잭이 아니었을 때")
    @Test
    void checkWinOrLoseTest() {
        Dealer dealer = new Dealer();

        dealer.hit(Fixture.CLUBS_KING, Fixture.CLUBS_TEN);
        dealer.hit(Fixture.CLUBS_ACE);
        Map<User, Result> resultMap = users.checkResult(dealer);
        assertThat(resultMap).isEqualTo(new HashMap<User, Result>() {
            {
                put(player, Result.BLACKJACK);
                put(player2, Result.LOSE);
            }
        });
    }
}