package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackReferee;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import static blackjack.domain.gamer.Gamer.INIT_DISTRIBUTION_COUNT;

public class BlackJackApplication {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Gamers gamers = new Gamers(InputView.getNames(), deck);
        OutputView.printFirstCards(gamers.getDealer(), gamers.getPlayers());

        drawAdditionalCard(deck, gamers);

        printAdditionalDrawDealer(deck, gamers);
        OutputView.printFinalCards(gamers.getDealer(), gamers.getPlayers());
        OutputView.printFinalResult(new BlackJackReferee(gamers.getPlayers(), gamers.getDealer()));
    }

    private static void printAdditionalDrawDealer(Deck deck, Gamers gamers) {
        gamers.distributeAdditionalToDealer(deck);
        OutputView.printAdditionalDrawDealer(gamers.getDealerCardSize() - INIT_DISTRIBUTION_COUNT);
    }

    private static void drawAdditionalCard(Deck deck, Gamers gamers) {
        for (Player player : gamers.getPlayers()) {
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
