package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.BlackJackReferee;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.gamer.Gamer.INIT_DISTRIBUTION_COUNT;

public class BlackJackApplication {
    public static void main(String[] args) {
        Deck deck = new Deck();
        List<String> names = InputView.getNames();
        Players players = new Players(names, getBettingMoney(names), deck);
        Dealer dealer = new Dealer(List.of(deck.draw(), deck.draw()));

        OutputView.printFirstCards(dealer, players.getPlayers());
        drawAdditionalCard(deck, players);

        printAdditionalDrawDealer(deck, dealer);
        OutputView.printFinalCards(dealer, players.getPlayers());
        OutputView.printFinalResult(new BlackJackReferee(players.getPlayers(), dealer));
    }

    private static List<Integer> getBettingMoney(List<String> names) {
        List<Integer> bettingMoneys = new ArrayList<>();
        for (String name : names) {
            bettingMoneys.add(InputView.getBettingMoney(name));
        }
        return bettingMoneys;
    }

    private static void printAdditionalDrawDealer(Deck deck, Dealer dealer) {
        dealer.distribute(deck);
        OutputView.printAdditionalDrawDealer(dealer.getCardsSize() - INIT_DISTRIBUTION_COUNT);
    }

    private static void drawAdditionalCard(Deck deck, Players players) {
        for (Player player : players.getPlayers()) {
            printPlayerDrawCard(deck, player);
        }
    }

    private static void printPlayerDrawCard(Deck deck, Player player) {
        while (player.isDrawPossible(InputView::getAnswerOfAdditionalDraw)) {
            player.addCard(deck.draw());
            OutputView.printPlayerCard(player);
        }
    }
}
