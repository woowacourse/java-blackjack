package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.money.Money;
import blackjack.domain.utils.ProfitCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProfitCalculatorTest {

    @DisplayName("승리자 판단 : 성공")
    @Test
    void decideWinner() {
        Dealer dealer = new Dealer();
        dealer.initState(createInitialCardOfDealer());

        Players players = Players.of(Arrays.asList("joanne"), Arrays.asList(new Money(10000)));
        for (Player player : players) {
            player.initState(createInitialCardOfPlayer());
        }

        Map<String, Double> answer = new LinkedHashMap<>();
        answer.put("딜러", 0.0);
        answer.put("joanne", 0.0);
        assertThat(ProfitCalculator.calculateProfitOf(dealer, players)).isEqualTo(answer);
    }

    private List<Card> createInitialCardOfPlayer() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(Suit.SPADE, Denomination.ACE));
        cards.add(Card.of(Suit.CLUB, Denomination.THREE));
        return cards;
    }

    private List<Card> createInitialCardOfDealer() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(Suit.HEART, Denomination.ACE));
        cards.add(Card.of(Suit.HEART, Denomination.THREE));
        return cards;
    }
}