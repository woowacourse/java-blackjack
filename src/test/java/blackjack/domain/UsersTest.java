package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {
    Users users;
    Player player;
    Player player2;
    Dealer dealer;
    Card jack = new Card(Suit.CLUB, CardNumber.JACK);
    Card ace = new Card(Suit.CLUB, CardNumber.ACE);
    Card seven = new Card(Suit.CLUB, CardNumber.SEVEN);
    Card six = new Card(Suit.CLUB, CardNumber.SIX);

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        users = new Users(dealer, Arrays.asList("youngE", "kimkim"));
        player = users.getPlayers().get(0);
        player2 = users.getPlayers().get(1);
        player.hit(ace);
        player.hit(jack);
        // 플레이어 youngE에게 블랙잭을 준다.
        player2.hit(ace);
        player2.hit(six);
        // 플레이어 Kimkim에게 17을 준다.
    }

    @DisplayName("딜러와 각 플레이어 간의 승패를 가린다. - 딜러가 블랙잭일 때")
    @Test
    void checkWinOrLoseWhenDealerHasBlackJackTest() {
        dealer.hit(ace);
        dealer.hit(jack);
        Map<User, ResultType> resultMap = users.generateResultsMapAgainstDealer();
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
        dealer.hit(ace);
        dealer.hit(seven);
        Map<User, ResultType> resultMap = users.generateResultsMapAgainstDealer();
        assertThat(resultMap).isEqualTo(new HashMap<User, ResultType>() {
            {
                put(player, ResultType.WIN);
                put(player2, ResultType.LOSE);
            }
        });
    }
}