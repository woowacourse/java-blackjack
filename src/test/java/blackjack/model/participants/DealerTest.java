package blackjack.model.participants;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.results.PlayerProfits;
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

    @DisplayName("플레이어 결과로 딜러의 수익을 계산한다")
    @ParameterizedTest
    @CsvSource(value = {"1000,2000,3000,-6000", "-1000,-2000,-3000,6000", "1000,1500,-2000,-500", "1000,-1000,0,0"})
    void calculateFinalBetAmount(int profit1, int profit2, int profit3, int expected) {
        Map<Player, Money> profits = new HashMap<>();
        profits.put(new Player("ella"), new Money(profit1));
        profits.put(new Player("daon"), new Money(profit2));
        profits.put(new Player("lily"), new Money(profit3));

        PlayerProfits playerProfits = new PlayerProfits(profits);
        Dealer dealer = new Dealer();

        Money dealerProfit = dealer.calculateDealerProfit(playerProfits);

        assertThat(dealerProfit).isEqualTo(new Money(expected));
    }
}
