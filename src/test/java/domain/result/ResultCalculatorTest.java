package domain.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultCalculatorTest {

    @Test
    @DisplayName("플레이어와 딜러의 승부 결과를 계산이 올바르게 반환된다.")
    void calculate() {
        Card card1 = new Card(Suit.CLOVER, Denomination.SIX);
        Card card2 = new Card(Suit.CLOVER, Denomination.NINE);
        Card card3 = new Card(Suit.HEART, Denomination.TEN);
        List<Card> playerCards = new ArrayList<>();
        List<Card> dealerCards = new ArrayList<>();

        playerCards.add(card1);
        playerCards.add(card2);
        dealerCards.add(card1);
        dealerCards.add(card3);


        List<Player> players = new ArrayList<>();

        Player player = new Player(new Name("seongha"), new HandCards(playerCards));
        Dealer dealer = new Dealer(new HandCards(dealerCards));
        players.add(player);

        ResultCalculator resultCalculator = new ResultCalculator(dealer, new Players(players));
        resultCalculator.calculate(player, dealer);

        Map<String, String> finalFightResults = resultCalculator.getFinalFightResults();

        Assertions.assertThat(finalFightResults.get("seongha")).isEqualTo("패");
        Assertions.assertThat(finalFightResults.get("딜러")).isEqualTo("1승 ");
    }
}
