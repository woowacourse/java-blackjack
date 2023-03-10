package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Dealer;
import domain.Player;
import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suit;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultsTest {

    private final Dealer dealer = getScore20Dealer();
    private final Player player1 = getScore19Player();
    private final Player player2 = getScore21Player();
    private final Results results = Results.of(dealer, List.of(player1, player2));

    @Test
    @DisplayName("1승 0무 1패의 결과가 포함된 DealerResult를 반환해야 한다.")
    void getDealerResultTest() {
        DealerResult dealerResult = results.getDealerResult();
        Map<ResultState, Integer> stateCount = dealerResult.getResult();

        assertThat(stateCount.get(ResultState.WIN)).isEqualTo(1);
        assertThat(stateCount.get(ResultState.DRAW)).isEqualTo(0);
        assertThat(stateCount.get(ResultState.LOSS)).isEqualTo(1);
    }
    @Test
    @DisplayName("승과 패를 각각 가지는 PlayerResult 들을 반환해야 한다.")
    void getPlayerResultsTest() {
        List<PlayerResult> playerResults = results.getPlayerResults();
        PlayerResult result1 = playerResults.get(0);
        PlayerResult result2 = playerResults.get(1);

        assertThat(result1.getName()).isEqualTo("playerScore19");
        assertThat(result1.getResultState()).isEqualTo(ResultState.LOSS);
        assertThat(result2.getName()).isEqualTo("playerScore21");
        assertThat(result2.getResultState()).isEqualTo(ResultState.WIN);
    }

    private static Player getScore19Player() {
        Cards player2Cards = Cards.of(
            new Card(Suit.DIAMOND, Denomination.NINE),
            new Card(Suit.HEART, Denomination.TEN));
        return new Player("playerScore19", player2Cards);
    }

    private static Player getScore21Player() {
        Cards player1Cards = Cards.of(
            new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.HEART, Denomination.TEN));
        return new Player("playerScore21", player1Cards);
    }

    private static Dealer getScore20Dealer() {
        Cards dealerCards = Cards.of(
            new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.HEART, Denomination.TEN));
        return new Dealer(dealerCards);
    }
}
