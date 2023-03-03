package blackjack.controller;

import blackjack.domain.BlackJack;
import blackjack.domain.Deck;
import blackjack.domain.user.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private BlackJack blackJack;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final List<Name> namesByView = getNamesByView();
        this.blackJack = new BlackJack(namesByView, new Deck());
        outputView.printInitialStatus(blackJack.getDealer(), blackJack.getUsers());
        divideCardTo(namesByView);
        finalizeDealerCardStatus();
        outputView.printTotalPlayersStatus(blackJack.getDealerStatus(), blackJack.getUsersStatus());
        outputView.printResult(blackJack.getGameResult());
    }

    private void finalizeDealerCardStatus() {
        final int cardCount = blackJack.finalizeDealer();
        outputView.printAdditionalCardCountOfDealer(cardCount);
    }

    private void divideCardTo(final List<Name> namesByView) {
        for (Name name : namesByView) {
            checkCardWanted(name);
        }
    }

    private void checkCardWanted(final Name name) {
        boolean wantCard = getCardWantFromConsole(name);
        while (wantCard) {
            blackJack.drawCard(name);
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
