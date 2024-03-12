package blackjack.model.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.results.DealerResult;
import blackjack.model.results.PlayerResult;
import blackjack.model.results.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DealerTest {

    @DisplayName("카드를 추가발급받는다")
    @Test
    void addCardLessThan() {
        List<Card> given = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Dealer dealer = new Dealer();
        dealer.addCards(given);

        Card card = new Card(CardNumber.ACE, CardShape.DIAMOND);
        dealer.addCard(card);

        assertThat(dealer.getCards().getCards()).hasSize(3);
    }

    @DisplayName("플레이어 게임 결과로 딜러 결과를 결정한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4", "2,3,4,5", "10,1,9,2"})
    void getDealerResult(int winByBlackJack, int winCount, int loseCount, int pushCount) {
        PlayerResult playerResult = new PlayerResult(createResults(winByBlackJack, winCount, loseCount, pushCount));
        Dealer dealer = new Dealer();
        DealerResult dealerResult = dealer.getResult(playerResult);

        assertAll(
                () -> assertThat(dealerResult.getDealerResult().get(Result.WIN)).isEqualTo(loseCount),
                () -> assertThat(dealerResult.getDealerResult().get(Result.LOSE)).isEqualTo(winCount),
                () -> assertThat(dealerResult.getDealerResult().get(Result.PUSH)).isEqualTo(pushCount),
                () -> assertThat(dealerResult.getDealerResult().get(Result.LOSE_BY_BLACKJACK)).isEqualTo(winByBlackJack)
        );
    }

    private Map<Player, Result> createResults(int winByBlackJack, int win, int lose, int push) {
        Map<Player, Result> map = new HashMap<>();
        for (int i = 0; i < winByBlackJack; i++) {
            map.put(new Player("ella" + i), Result.WIN_BY_BLACKJACK);
        }
        for (int i = 0; i < win; i++) {
            map.put(new Player("daon" + i), Result.WIN);
        }
        for (int i = 0; i < lose; i++) {
            map.put(new Player("pobi" + i), Result.LOSE);
        }
        for (int i = 0; i < push; i++) {
            map.put(new Player("woni" + i), Result.PUSH);
        }
        return map;
    }

    @DisplayName("플레이어 결과와 베팅 금액으로 딜러의 수익을 계산한다")
    @ParameterizedTest
    @CsvSource(value = {"1,1,1,1,-3500", "1,0,0,0,-4500", "0,1,0,0,-4000", "0,0,1,0,5000", "0,0,0,1,0"})
    void calculateFinalBetAmount(int winByBlackJack, int winCount, int loseCount, int pushCount, int result) {
        PlayerResult playerResult = new PlayerResult(
                createResultsForBet(winByBlackJack, winCount, loseCount, pushCount));
        Dealer dealer = new Dealer();

        int dealerProfit = dealer.calculateFinalBetAmount(playerResult);

        assertThat(dealerProfit).isEqualTo(result);
    }

    private Map<Player, Result> createResultsForBet(int winByBlackJack, int win, int lose, int push) {
        Map<Player, Result> map = new HashMap<>();
        for (int i = 0; i < winByBlackJack; i++) {
            map.put(getPlayer("ella", 3000), Result.WIN_BY_BLACKJACK);
        }
        for (int i = 0; i < win; i++) {
            map.put(getPlayer("daon", 4000), Result.WIN);
        }
        for (int i = 0; i < lose; i++) {
            map.put(getPlayer("lily", 5000), Result.LOSE);
        }
        for (int i = 0; i < push; i++) {
            map.put(getPlayer("pobi", 6000), Result.PUSH);
        }
        return map;
    }

    private Player getPlayer(String name, int betAmount) {
        Player player = new Player(name);
        player.betMoney(betAmount);
        return player;
    }
}
