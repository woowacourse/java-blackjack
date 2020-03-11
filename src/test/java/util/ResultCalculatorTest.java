package util;

import domain.*;
import factory.CardFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ResultCalculatorTest {
    @Test
    void getResults() {
        Player player = new Player("플레이어");
        Player player2 = new Player("플레이어2");
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck(CardFactory.create());
        cardDeck.shuffle();
        dealer.giveOneCard(cardDeck, player);
        dealer.giveOneCard(cardDeck, player);
        dealer.giveOneCard(cardDeck, player2);
        dealer.giveOneCard(cardDeck, player2);
        dealer.addCard(cardDeck.drawOne());
        dealer.addCard(cardDeck.drawOne());
        Results results = ResultCalculator.getResults(dealer, new Players(Arrays.asList(player, player2)));

        System.out.println(dealer.getScore());
        System.out.println(player.getScore());
        System.out.println(player2.getScore());

        results.printAll();
    }
}
