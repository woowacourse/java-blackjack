package blackjack.domain;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Pattern.CLOVER;
import static blackjack.domain.card.Pattern.DIAMOND;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    @Test
    @DisplayName("수익을 Map에 저장한다")
    void saveIntoMap() {
// given
        Dealer dealer = createDealer(NINE);
        Betting betting = new Betting(1000);

        Player blackJackPlayer = createPlayer("blackjack", ACE, betting);
        Player winPlayer = createPlayer("win", TEN, betting);
        Player drawPlayer = createPlayer("draw", NINE, betting);
        Player losePlayer = createPlayer("lose", EIGHT, betting);
        List<Player> players = List.of(blackJackPlayer, winPlayer, drawPlayer, losePlayer);

        // when
        GameResult gameResult = GameResult.of(dealer, players);
        Map<String, Integer> earnings = gameResult.getEarnings();

        // then
        assertAll(
                () -> assertThat(earnings.get("딜러")).isEqualTo(-1500),
                () -> assertThat(earnings.get(blackJackPlayer.getName())).isEqualTo(1500),
                () -> assertThat(earnings.get(winPlayer.getName())).isEqualTo(1000),
                () -> assertThat(earnings.get(drawPlayer.getName())).isEqualTo(0),
                () -> assertThat(earnings.get(losePlayer.getName())).isEqualTo(-1000)
        );
    }

    private static Dealer createDealer(Denomination denomination2) {
        Card card1 = new Card(DIAMOND, TEN);
        Card card2 = new Card(CLOVER, denomination2);
        List<Card> dealerCards = List.of(card1, card2);
        return new Dealer(dealerCards);
    }

    private static Player createPlayer(String name, Denomination denomination2, Betting betting) {
        Card card1 = new Card(DIAMOND, TEN);
        Card card2 = new Card(CLOVER, denomination2);
        List<Card> playerCards = List.of(card1, card2);
        return new Player(new Name(name), playerCards, betting);
    }
}
