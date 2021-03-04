package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
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
    Card jack = new Card(Suit.CLUB, CardNumber.JACK);
    Card ace = new Card(Suit.CLUB, CardNumber.ACE);
    Card seven = new Card(Suit.CLUB, CardNumber.SEVEN);

    @BeforeEach
    void setUp() {
        users = new Users(new Dealer(), Arrays.asList("youngE", "kimkim"));
        player = users.getPlayers().get(0);
        player2 = users.getPlayers().get(1);
        player.hit(ace);
        player.hit(jack);
        player2.hit(ace);
        player2.hit(seven);
    }

    @DisplayName("딜러와 각 플레이어 간의 승패를 가린다. - 딜러가 블랙잭일 때")
    @Test
    void checkWinOrLoseWhenDealerHasBlackJackTest() {
        Map<User, ResultType> resultMap = users.checkWinOrLose(Card.BLACKJACK_SCORE);
        assertThat(resultMap).isEqualTo(new HashMap<User, ResultType>() {
            {
                put(player, ResultType.DRAW);
                put(player2, ResultType.LOSE);
            }
        });
    }

    @DisplayName("딜러와 각 플레이어 간의 승패를 가린다. - 딜러가 블랙잭이 아니었을 때")
    @Test
    void checkWinOrLoseTest() {
        Map<User, ResultType> resultMap = users.checkWinOrLose(21);
        assertThat(resultMap).isEqualTo(new HashMap<User, ResultType>() {
            {
                put(player, ResultType.WIN);
                put(player2, ResultType.LOSE);
            }
        });
    }
}