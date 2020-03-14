package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Dealer;
import domain.player.User;
import domain.player.Users;
import domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WinningResultTest {
    private Card card1;
    private Card card2;
    private Card card3;
    private Card card4;
    private Card card5;
    private Card card6;

    @BeforeEach
    private void setup() {
        card1 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        card2 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        card3 = Card.of(CardNumber.KING, CardSuitSymbol.CLUB);
        card4 = Card.of(CardNumber.KING, CardSuitSymbol.HEART);
        card5 = Card.of(CardNumber.TWO, CardSuitSymbol.CLUB);
        card6 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
    }

    @Test
    void getterTest() {
        User dealer = new Dealer(card1, card2);
        User user1 = new Player("Labin", card3, card4);
        User user2 = new Player("Subway", card5, card6);
        List<User> userList = new ArrayList<>(Arrays.asList(dealer, user1, user2));
        Users users = new Users(userList);

        WinningResult winningResult = new WinningResult(users);
        Map<String, Boolean> winningPlayerResult = winningResult.getWinningResult();

        Assertions.assertThat(winningPlayerResult.keySet()).containsSequence("Labin", "Subway");
        Assertions.assertThat(winningPlayerResult.values()).containsSequence(true, false);

    }
}
