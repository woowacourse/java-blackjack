package blackjack;

import static java.util.stream.Collectors.*;

import blackjack.domain.Answer;
import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackApplication {

    public static void main(String[] args) {
        Deck deck = Deck.init();
        BlackJackGame blackJackGame = startGame(deck);
        OutputView.printOpenCards(blackJackGame.getDealer(), blackJackGame.getGamers());
        for (Gamer gamer : blackJackGame.getGamers()) {
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
        BlackJackGame blackJackGame = new BlackJackGame(new Dealer(), toGamers());
        blackJackGame.handOutStartingCards(deck);
        return blackJackGame;
    }

    private static Gamers toGamers() {
        List<String> names = InputView.requestPlayerName();
        return new Gamers(names.stream()
                .map(Gamer::new)
                .collect(toList()));
    }

    private static void progressGamerAdditionalCard(final Deck deck, final Gamer gamer) {
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

    private static void progressDealerAdditionalCard(final Deck deck, final Player dealer) {
        boolean receivable = dealer.isSatisfyReceiveCondition();
        OutputView.printDealerReceive(receivable);
        if (receivable) {
            dealer.receiveCard(deck.draw());
        }
    }
}
