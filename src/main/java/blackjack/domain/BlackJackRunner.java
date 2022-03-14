package blackjack.domain;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Bet;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackRunner {

    private static final Deck deck;
    private static final BlackJackGame blackJackGame;

    static {
        deck = Deck.init();
        blackJackGame = startGame();
    }

    public static void run() {
        dealsCard();
        playHand();
        payOuts();
    }

    private static BlackJackGame startGame() {
        try {
            return initBlackJackGame();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return startGame();
        }
    }

    private static BlackJackGame initBlackJackGame() {
        BlackJackGame blackJackGame = new BlackJackGame(new Dealer(), toGamers());
        blackJackGame.handOutStartingCards(deck);
        return blackJackGame;
    }

    private static Gamers toGamers() {
        List<String> names = InputView.requestPlayerName();
        return new Gamers(names.stream()
                .map(name -> new Gamer(name, toBet(name)))
                .collect(toList()));
    }

    private static Bet toBet(final String name) {
        try {
            return new Bet(InputView.requestBettingMoney(name));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return toBet(name);
        }
    }

    private static void dealsCard() {
        OutputView.printOpenCards(blackJackGame.getDealer(), blackJackGame.getGamers());
    }

    private static void playHand() {
        for (Gamer gamer : blackJackGame.getGamers()) {
            progressGamerAdditionalCard(gamer);
        }
        progressDealerAdditionalCard(blackJackGame.getDealer());
    }

    private static void progressGamerAdditionalCard(final Gamer gamer) {
        while (gamer.isSatisfyReceiveCondition() && gamer.isHit(toAnswer(gamer.getName()))) {
            gamer.receiveCard(deck.draw());
            OutputView.printGamerCards(gamer);
        }
    }

    private static Answer toAnswer(final String name) {
        try {
            return Answer.of(InputView.requestAnswer(name));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return toAnswer(name);
        }
    }

    private static void progressDealerAdditionalCard(final Player dealer) {
        boolean receivable = dealer.isSatisfyReceiveCondition();
        OutputView.printDealerReceive(receivable);
        if (receivable) {
            dealer.receiveCard(deck.draw());
        }
    }

    private static void payOuts() {
        OutputView.printFinalResult(blackJackGame.getDealer(), blackJackGame.getGamers());
        OutputView.printFinalResultBoard(blackJackGame.calculateDealerResultBoard(),
                blackJackGame.calculateResultBoard());
    }

}
