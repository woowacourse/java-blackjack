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
    @DisplayName("올바른 수익 결과가 담기는지 테스트")
    @Test
    void getWinningResultTest() {
        Card aceClub = Card.of(CardNumber.ACE, CardSuitSymbol.CLUB);
        Card fiveClub = Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB);
        Card kingClub = Card.of(CardNumber.KING, CardSuitSymbol.CLUB);
        Card kingHeart = Card.of(CardNumber.KING, CardSuitSymbol.HEART);
        Card twoHeart = Card.of(CardNumber.TWO, CardSuitSymbol.HEART);
        Card aceHeart = Card.of(CardNumber.ACE, CardSuitSymbol.HEART);

        User dealer = new Dealer(new ArrayList<>(Arrays.asList(aceClub, fiveClub)));
        User user1 = new Player("lavine", new ArrayList<>(Arrays.asList(kingClub, kingHeart)),10_000);
        User user2 = new Player("Subway", new ArrayList<>(Arrays.asList(twoHeart, aceHeart)),10_000);
        List<User> userList = new ArrayList<>(Arrays.asList(dealer, user1, user2));
        Users users = new Users(userList);

        WinningResult winningResult = WinningResult.create(users);
        Map<String, Double> winningPlayerResult = winningResult.getWinningResult();

        Assertions.assertThat(winningPlayerResult.keySet()).containsExactly("lavine", "Subway");
        Assertions.assertThat(winningPlayerResult.values()).containsExactly(10_000d, -10_000d);
    }
}
