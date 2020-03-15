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

public class PlayersTest {
    private Player player1;
    private Player player2;
    private List<Player> playerList;

    @BeforeEach
    private void setup() {
        Card card1 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card card2 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card card3 = Card.of(CardNumber.KING, CardSuitSymbol.CLUB);
        Card card4 = Card.of(CardNumber.KING, CardSuitSymbol.HEART);
        Card card5 = Card.of(CardNumber.TWO, CardSuitSymbol.CLUB);
        Card card6 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        Player dealer = new Dealer(card1, card2);
        player1 = new User("lavine", card3, card4);
        player2 = new User("Subway", card5, card6);
        playerList = new ArrayList(Arrays.asList(dealer, player1, player2));
    }

    @DisplayName("Players 생성자 테스트")
    @Test
    void playersConstructorTest() {
        Players players = new Players(playerList);

        Assertions.assertThat(players).isInstanceOf(Players.class);
    }

    @DisplayName("딜러를 반환하는 메서드 테스트")
    @Test
    void getDealerTest() {
        Players players = new Players(playerList);
        Dealer targetDealer = players.getDealer();

        Assertions.assertThat(targetDealer).isInstanceOf(Dealer.class);
    }

    @DisplayName("Players 에서 유저만 추출하는 메서드 테스트")
    @Test
    void getUsersTest() {
        Players players = new Players(playerList);
        List<User> users = players.getUsers();

        for (User user : users) {
            Assertions.assertThat(user).isInstanceOf(User.class);
        }
        Assertions.assertThat(users).containsSequence((User) player1, (User) player2);
    }
}
