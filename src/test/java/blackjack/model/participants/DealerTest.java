package blackjack.model.participants;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.results.PlayerResult;
import blackjack.model.results.Result;
import blackjack.vo.Money;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.AssertionsForClassTypes;
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

    @DisplayName("카드를 추가로 받을 수 있는지 확인한다")
    @ParameterizedTest
    @CsvSource(value = {"SIX,true", "SEVEN,false"})
    void checkDrawCardStateOverThresholdScore(CardNumber cardNumber, boolean expected) {
        List<Card> given = List.of(
                new Card(cardNumber, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Dealer dealer = new Dealer();
        dealer.addCards(given);

        AssertionsForClassTypes.assertThat(dealer.canHit()).isEqualTo(expected);
    }

    @DisplayName("플레이어 결과와 베팅 금액으로 딜러의 수익을 계산한다")
    @ParameterizedTest
    @CsvSource(value = {"1,1,1,1,-3500", "1,0,0,0,-4500", "0,1,0,0,-4000", "0,0,1,0,5000", "0,0,0,1,0"})
    void calculateFinalBetAmount(int winByBlackJack, int winCount, int loseCount, int pushCount, int result) {
        PlayerResult playerResult = new PlayerResult(
                createResultsForBet(winByBlackJack, winCount, loseCount, pushCount));
        Dealer dealer = new Dealer();

        Money dealerProfit = dealer.calculateDealerProfit(playerResult);

        assertThat(dealerProfit).isEqualTo(new Money(result));
    }

    private Map<Player, Result> createResultsForBet(int winByBlackJack, int win, int lose, int push) {
        Map<Player, Result> map = new HashMap<>();
        for (int i = 0; i < winByBlackJack; i++) {
            map.put(getPlayer("ella", new Money(3000)), Result.WIN_BY_BLACKJACK);
        }
        for (int i = 0; i < win; i++) {
            map.put(getPlayer("daon", new Money(4000)), Result.WIN);
        }
        for (int i = 0; i < lose; i++) {
            map.put(getPlayer("lily", new Money(5000)), Result.LOSE);
        }
        for (int i = 0; i < push; i++) {
            map.put(getPlayer("pobi", new Money(6000)), Result.PUSH);
        }
        return map;
    }

    private Player getPlayer(String name, Money betAmount) {
        Player player = new Player(name);
        player.betMoney(betAmount);
        return player;
    }
}
