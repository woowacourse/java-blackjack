package blackjack.domain;

import static blackjack.domain.Outcome.DRAW;
import static blackjack.domain.Outcome.LOSE;
import static blackjack.domain.Outcome.WIN;
import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Pattern.CLOVER;
import static blackjack.domain.card.Pattern.DIAMOND;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinResultTest {

    @Test
    @DisplayName("승패 결과를 Map에 저장한다")
    void saveIntoMap() {
        // given
        Dealer dealer = createDealer(TEN);

        Player winPlayer = createPlayer("win", ACE);
        Player drawPlayer = createPlayer("draw", TEN);
        Player losePlayer = createPlayer("lose", NINE);
        List<Player> players = List.of(winPlayer, drawPlayer, losePlayer);

        Map<Outcome, Integer> outcomeMap = createOutcomeMap(1, 1, 1);

        // when
        WinResult winResult = new WinResult(dealer, players);
        Map<Outcome, Integer> dealerResult = winResult.getDealerResult();
        Map<String, Outcome> playersResult = winResult.getPlayersResult();

        // then
        assertAll(
                () -> assertThat(dealerResult).isEqualTo(outcomeMap),
                () -> assertThat(playersResult.get(winPlayer.getName())).isEqualTo(WIN),
                () -> assertThat(playersResult.get(drawPlayer.getName())).isEqualTo(DRAW),
                () -> assertThat(playersResult.get(losePlayer.getName())).isEqualTo(LOSE)
        );
    }

    private static Dealer createDealer(Denomination denomination2) {
        Card card1 = new Card(DIAMOND, TEN);
        Card card2 = new Card(CLOVER, denomination2);
        List<Card> dealerCards = List.of(card1, card2);
        return new Dealer(dealerCards);
    }

    private static Player createPlayer(String name, Denomination denomination2) {
        Card card1 = new Card(DIAMOND, TEN);
        Card card2 = new Card(CLOVER, denomination2);
        List<Card> playerCards = List.of(card1, card2);
        return new Player(new Name(name), playerCards);
    }

    private static Map<Outcome, Integer> createOutcomeMap(int win, int draw, int lose) {
        Map<Outcome, Integer> judgementMap = new EnumMap<>(Outcome.class);
        judgementMap.put(WIN, win);
        judgementMap.put(DRAW, draw);
        judgementMap.put(LOSE, lose);

        return judgementMap;
    }
}
