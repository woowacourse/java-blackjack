package blackjack;

import blackjack.domain.Answer;
import blackjack.domain.BlackJackGame;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Gamer;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackApplication {

    public static void main(String[] args) {
        Deck deck = Deck.init();
        BlackJackGame blackJackGame = startGame(deck);
        OutputView.printOpenCards(blackJackGame.getDealer(), blackJackGame.getGamers());
        for (Player gamer : blackJackGame.getGamers()) {
            progressGamerAdditionalCard(deck, gamer);
        }
        progressDealerAdditionalCard(deck, blackJackGame.getDealer());
        OutputView.printFinalResult(blackJackGame.getDealer(), blackJackGame.getGamers());
        OutputView.printFinalResultBoard(blackJackGame.calculateResultBoard());
    }

    private static BlackJackGame startGame(final Deck deck) {
        try {
            return initBlackJackGame(deck);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return startGame(deck);
        }
    }

    private static BlackJackGame initBlackJackGame(final Deck deck) {
        BlackJackGame blackJackGame = new BlackJackGame(new Dealer(), toGamerList());
        blackJackGame.giveFirstCards(deck);
        return blackJackGame;
    }

    private static List<Player> toGamerList() {
        List<String> names = InputView.requestPlayerName();
        return names.stream()
                .map(Gamer::new)
                .collect(Collectors.toList());
    }

    private static void progressGamerAdditionalCard(final Deck deck, final Player gamer) {
        while (isReceivable(gamer)) {
            gamer.receiveCard(deck.draw());
            OutputView.printGamerCards(gamer);
        }
    }

    private static boolean isReceivable(final Player gamer) {
        return gamer.isReceivable() && isAnswerYes(gamer.showName());
    }

    private static boolean isAnswerYes(final String name) {
        try {
            return Answer.YES == Answer.of(InputView.requestAnswer(name));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
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
}
