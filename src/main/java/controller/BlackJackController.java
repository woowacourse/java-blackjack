package controller;

import domain.BlackJack;
import domain.Deck;
import template.Repeater;
import domain.user.Name;
import view.InputView;
import view.OutputView;

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
        final List<Name> namesByView = Repeater.repeat(this::getNamesByView);
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
        boolean wantCard = Repeater.repeat(() -> getCardWantFromConsole(name));
        while (wantCard) {
            blackJack.drawCard(name);
            outputView.printCardsOf(name, blackJack.getUserCard(name));
            if (blackJack.isBusted(name)) {
                break;
            }
            wantCard = Repeater.repeat(() -> getCardWantFromConsole(name));
        }
    }

    private boolean getCardWantFromConsole(Name name) {
        return inputView.cardRequest(name.getValue());
    }

    private List<Name> getNamesByView() {
        return inputView.userNameRequest()
                .stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }
}
