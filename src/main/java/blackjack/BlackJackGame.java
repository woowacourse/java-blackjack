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

    private final Deck deck;

    public BlackJackGame(Deck deck) {
        this.deck = deck;
    }

    public void run() {
        Dealer dealer = new Dealer();
        Gamers gamers = setGamers();

        startAllPlayer(dealer, gamers.getGamers());
        OutputView.printOpenCards(gamers.getGamers(), dealer);
        drawAdditionalCards(dealer, gamers);

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

    private void startAllPlayer(final Player dealer, final List<Gamer> gamers) {
        setInitialCards(dealer);
        for (Gamer gamer : gamers) {
            setInitialCards(gamer);
        }
    }

    private void setInitialCards(final Player player) {
        for (int i = 0; i < PLAYER_SETTING_CARD_SIZE; i++) {
            player.receiveCard(deck.draw());
        }
    }

    private void drawAdditionalCards(final Player dealer, final Gamers gamers) {
        for (Gamer gamer : gamers.getGamers()) {
            progressGamerAdditionalCard(gamer, deck);
        }
        progressDealerAdditionalCard(dealer, deck);
    }

    private void progressGamerAdditionalCard(final Gamer gamer, final Deck deck) {
        while (isReceivableCard(gamer)) {
            gamer.receiveCard(deck.draw());
            OutputView.printGamerCards(gamer);
        }
    }

    private boolean isReceivableCard(final Gamer gamer) {
        return gamer.isReceivable() && isAnswerYes(gamer.getName());
    }

    private boolean isAnswerYes(final String name) {
        try {
            return Answer.isYes(InputView.requestAnswer(name));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return isAnswerYes(name);
        }
    }

    private void progressDealerAdditionalCard(final Player dealer, final Deck deck) {
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
