package blackjack;

import blackjack.domain.Answer;
import blackjack.domain.GameResult;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private static final int PLAYER_SETTING_CARD_SIZE = 2;

    public void run() {
        Dealer dealer = new Dealer();
        Gamers gamers = setGamers();
        Deck deck = Deck.initializeDeck();

        startAllPlayer(dealer, gamers.getGamers(), deck);
        OutputView.printOpenCards(gamers.getGamers(), dealer);
        drawAdditionalCards(dealer, gamers, deck);

        printFinalMessage(dealer, gamers);
    }

    private Gamers setGamers() {
        try {
            return Gamers.createGamers(InputView.requestPlayerName());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return setGamers();
        }
    }

    private void startAllPlayer(final Player dealer, final List<Gamer> gamers, final Deck deck) {
        setInitialCards(dealer, deck);
        for (Gamer gamer : gamers) {
            setInitialCards(gamer, deck);
        }
    }

    private void setInitialCards(final Player player, final Deck deck) {
        for (int i = 0; i < PLAYER_SETTING_CARD_SIZE; i++) {
            player.receiveCard(deck.draw());
        }
    }

    private void drawAdditionalCards(final Player dealer, final Gamers gamers, final Deck deck) {
        for (Gamer gamer : gamers.getGamers()) {
            progressGamerAdditionalCard(deck, gamer);
        }
        progressDealerAdditionalCard(deck, dealer);
    }

    private static void progressGamerAdditionalCard(final Deck deck, final Gamer gamer) {
        while (isReceivableCard(gamer)) {
            gamer.receiveCard(deck.draw());
            OutputView.printGamerCards(gamer);
        }
    }

    private static boolean isReceivableCard(final Gamer gamer) {
        return gamer.isReceivable() && isAnswerYes(gamer.getName());
    }

    private static boolean isAnswerYes(final String name) {
        try {
            return Answer.isYes(InputView.requestAnswer(name));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return isAnswerYes(name);
        }
    }

    private static void progressDealerAdditionalCard(final Deck deck, final Player dealer) {
        boolean receivable = dealer.isReceivable();
        OutputView.printDealerReceive(receivable);
        if (receivable) {
            dealer.receiveCard(deck.draw());
        }
    }

    private static void printFinalMessage(final Dealer dealer, final Gamers gamers) {
        OutputView.printFinalResult(dealer, gamers.getGamers());
        Map<Gamer, GameResult> gamerResultBoard =
            GameResult.calculateGamersFinalResultBoard(dealer, gamers.getGamers());
        OutputView.printFinalResultBoard(gamerResultBoard,
            GameResult.calculateDealerFinalResultBoard(gamerResultBoard));
    }

}
