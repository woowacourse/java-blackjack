package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.User;
import domain.player.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WinningResultTest {
    @DisplayName("승패 결과에 올바른 결과가 담기는지 테스트")
    @Test
    void getterTest() {
        Card card1 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card card2 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card card3 = Card.of(CardNumber.KING, CardSuitSymbol.CLUB);
        Card card4 = Card.of(CardNumber.KING, CardSuitSymbol.HEART);
        Card card5 = Card.of(CardNumber.TWO, CardSuitSymbol.CLUB);
        Card card6 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);

        User dealer = new Dealer(new ArrayList<>(Arrays.asList(card1, card2)));
        User user1 = new Player("lavine", new ArrayList<>(Arrays.asList(card3, card4)));
        User user2 = new Player("Subway", new ArrayList<>(Arrays.asList(card5, card6)));
        List<User> userList = new ArrayList<>(Arrays.asList(dealer, user1, user2));
        Users users = new Users(userList);

        WinningResult winningResult = new WinningResult(users);
        Map<String, Boolean> winningPlayerResult = winningResult.getWinningPlayer();

        Assertions.assertThat(winningPlayerResult.keySet()).containsSequence("lavine", "Subway");
        Assertions.assertThat(winningPlayerResult.values()).containsSequence(true, false);
    }

    @DisplayName("올바른 수익 결과가 담기는지 테스트")
    @Test
    void getWinningResultTest() {
        Card card1 = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card card2 = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card card3 = Card.of(CardNumber.KING, CardSuitSymbol.CLUB);
        Card card4 = Card.of(CardNumber.KING, CardSuitSymbol.HEART);
        Card card5 = Card.of(CardNumber.TWO, CardSuitSymbol.HEART);
        Card card6 = Card.of(CardNumber.ACE, CardSuitSymbol.HEART);

        User dealer = new Dealer(new ArrayList<>(Arrays.asList(card1, card2)));
        User user1 = new Player("lavine", new ArrayList<>(Arrays.asList(card3, card4)),10_000);
        User user2 = new Player("Subway", new ArrayList<>(Arrays.asList(card5, card6)),10_000);
        List<User> userList = new ArrayList<>(Arrays.asList(dealer, user1, user2));
        Users users = new Users(userList);

        WinningResult winningResult = new WinningResult(users);
        Map<String, Double> winningPlayerResult = winningResult.getWinningResult();

        Assertions.assertThat(winningPlayerResult.keySet()).containsExactly("lavine", "Subway");
        Assertions.assertThat(winningPlayerResult.values()).containsExactly(10_000d, -10_000d);
    }
}
