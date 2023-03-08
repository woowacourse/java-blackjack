package blackjack.controller;

import blackjack.domain.BlackJack;
import blackjack.domain.RandomDeck;
import blackjack.domain.dto.*;
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
        drawCardBy(blackJack, namesByView);
        finalizeDealerCardStatus(blackJack);
        printCompletedGame(blackJack);
    }

    private BlackJack makeBlackJackBy(final List<Name> namesByView) {
        final BlackJack blackJack = new BlackJack(namesByView, randomDeck);
        outputView.printInitialStatus(DtoParser.getInitializeDto(blackJack));
        return blackJack;
    }

    private void printCompletedGame(final BlackJack blackJack) {
        outputView.printFinalPlayersStatus(DtoParser.getFinalStatusDto(blackJack));
        outputView.printResult(DtoParser.getGameResultDto(blackJack));
    }

    private void finalizeDealerCardStatus(final BlackJack blackJack) {
        final int cardCount = blackJack.giveCardToDealerUntilDontNeed(randomDeck);
        outputView.printAdditionalCardCountOfDealer(cardCount);
    }

    private void drawCardBy(final BlackJack blackJack, final List<Name> names) {
        for (Name name : names) {
            drawCardUntilWanted(blackJack, name);
        }
    }

    private void drawCardUntilWanted(BlackJack blackJack, final Name name) {
        while (getCardWantFromConsole(name)) {
            blackJack.giveCard(name, randomDeck);
            outputView.printCardsOf(DtoParser.getUserDto(blackJack.getUser(name)));
            if (blackJack.isBust(name)) {
                break;
            }
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
