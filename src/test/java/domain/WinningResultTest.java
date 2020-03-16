package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.player.User;
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

        Player dealer = new Dealer(new ArrayList<>(Arrays.asList(card1, card2)));
        Player player1 = new User("lavine", new ArrayList<>(Arrays.asList(card3, card4)));
        Player player2 = new User("Subway", new ArrayList<>(Arrays.asList(card5, card6)));
        List<Player> playerList = new ArrayList<>(Arrays.asList(dealer, player1, player2));
        Players players = new Players(playerList);

        WinningResult winningResult = new WinningResult(players);
        Map<String, Boolean> winningPlayerResult = winningResult.getWinningResult();

        Assertions.assertThat(winningPlayerResult.keySet()).containsSequence("lavine", "Subway");
        Assertions.assertThat(winningPlayerResult.values()).containsSequence(true, false);
    }
}
