package blackjack.controller;

import blackjack.domain.BlackJack;
import blackjack.domain.RandomDeck;
import blackjack.domain.user.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private RandomDeck randomDeck;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        randomDeck = new RandomDeck();
    }

    public void run() {
        final List<Name> namesByView = getNamesByView();
        final BlackJack blackJack = makeBlackJackBy(namesByView);
        divideCardTo(blackJack, namesByView);
        finalizeDealerCardStatus(blackJack);
        printCompletedGame(blackJack);
    }

    private BlackJack makeBlackJackBy(final List<Name> namesByView) {
        final BlackJack blackJack = new BlackJack(namesByView, randomDeck);
        outputView.printInitialStatus(blackJack.getDealerFirstCard(), blackJack.getUsers());
        return blackJack;
    }

    private void printCompletedGame(final BlackJack blackJack) {
        outputView.printTotalPlayersStatus(blackJack.getDealer(), blackJack.getUsersStatus());
        outputView.printResult(blackJack.getGameResult());
    }

    private void finalizeDealerCardStatus(final BlackJack blackJack) {
        final int cardCount = blackJack.finalizeDealer(randomDeck);
        outputView.printAdditionalCardCountOfDealer(cardCount);
    }

    private void divideCardTo(final BlackJack blackJack, final List<Name> namesByView) {
        for (Name name : namesByView) {
            checkCardWanted(blackJack, name);
        }
    }

    private void checkCardWanted(BlackJack blackJack, final Name name) {
        boolean wantCard = getCardWantFromConsole(name);
        while (wantCard) {
            blackJack.giveCard(name, randomDeck);
            outputView.printCardsOf(name, blackJack.getUserCard(name));
            if (blackJack.checkBustBy(name)) {
                break;
            }
            wantCard = getCardWantFromConsole(name);
        }
    }

    private boolean getCardWantFromConsole(Name name) {
        try {
            return inputView.cardRequest(name.getValue());
        } catch (IllegalStateException e) {
            outputView.printException(e);
            return getCardWantFromConsole(name);
        }
    }

    private List<Name> getNamesByView() {
        try {
            return inputView.userNameRequest()
                    .stream()
                    .map(Name::new)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            outputView.printException(exception);
            return getNamesByView();
        }
    }
}
