package controller;

import domain.BlackJack;
import domain.Deck;
import domain.user.User;
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

    public void process() {
        final List<Name> userNames = userNameRequest();
        this.blackJack = BlackJack.getInstance(userNames, new Deck());

        outputView.printInitialStatus(blackJack.getDealer(), blackJack.getUsers());

        for (User user : blackJack.getUserList()) {
            boolean wantCard = Repeater.repeat(() -> cardRequest(user.getName()));
            while (wantCard) {
                blackJack.drawCard(user);
                outputView.printCardsOf(user, blackJack.getCardsFrom(user));
                if (blackJack.isBusted(user)) {
                    break;
                }
                wantCard = Repeater.repeat(() -> cardRequest(user.getName()));
            }
        }
        finalizeDealerCardStatus();
        outputView.printTotalPlayersStatus(blackJack.getDealerStatus(), blackJack.getUsersStatus());
        outputView.printResult(blackJack.getGameResult());
    }

    private List<Name> userNameRequest() {
        final List<String> strings = Repeater.repeat(inputView::userNameRequest);
        return strings
                .stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private void finalizeDealerCardStatus() {
        final int cardCount = blackJack.finalizeDealer();
        outputView.printAdditionalCardCountOfDealer(cardCount);
    }

    private boolean cardRequest(Name name) {
        return inputView.cardRequest(name);
    }
}
