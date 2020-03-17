package domain.player;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersTest {
    private User user1;
    private User user2;
    private List<User> userList;

    @BeforeEach
    private void setup() {
        Card card1 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card card2 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card card3 = Card.of(CardNumber.KING, CardSuitSymbol.CLUB);
        Card card4 = Card.of(CardNumber.KING, CardSuitSymbol.HEART);
        Card card5 = Card.of(CardNumber.TWO, CardSuitSymbol.CLUB);
        Card card6 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        User dealer = new Dealer(new ArrayList<>(Arrays.asList(card1, card2)));
        user1 = new Player("lavine", new ArrayList<>(Arrays.asList(card3, card4)));
        user2 = new Player("Subway", new ArrayList<>(Arrays.asList(card5, card6)));
        userList = new ArrayList(Arrays.asList(dealer, user1, user2));
    }

    @DisplayName("Users 생성자 테스트")
    @Test
    void playersConstructorTest() {
        Users users = new Users(userList);

        Assertions.assertThat(users).isInstanceOf(Users.class);
    }

    @DisplayName("딜러를 반환하는 메서드 테스트")
    @Test
    void getDealerTest() {
        Users users = new Users(userList);
        Dealer targetDealer = users.getDealer();

        Assertions.assertThat(targetDealer).isInstanceOf(Dealer.class);
    }

    @DisplayName("Users 에서 유저만 추출하는 메서드 테스트")
    @Test
    void getUsersTest() {
        Users users = new Users(userList);
        List<Player> players = users.getPlayer();

        for (Player player : players) {
            Assertions.assertThat(player).isInstanceOf(Player.class);
        }
        Assertions.assertThat(players).containsExactly((Player) user1, (Player) user2);
    }
}
