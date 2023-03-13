package blackjack.controller;

import blackjack.domain.BettingMoney;
import blackjack.domain.PrizeCalculator;
import blackjack.domain.RandomDeck;
import blackjack.domain.dto.DtoParser;
import blackjack.domain.game.BlackJack;
import blackjack.domain.user.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        final PrizeCalculator prizeCalculator = enrollStake(namesByView);
        final BlackJack blackJack = makeBlackJackBy(namesByView);
        drawCardBy(blackJack, namesByView);
        finalizeDealerCardStatus(blackJack, prizeCalculator);
        printCompletedGame(blackJack, prizeCalculator);
    }

    private PrizeCalculator enrollStake(final List<Name> names) {
        final Map<Name, BettingMoney> stakeData = new LinkedHashMap<>();
        for (Name name : names) {
            stakeData.put(name, getStakeOf(name));
        }
        return new PrizeCalculator(stakeData);
    }

    private BettingMoney getStakeOf(final Name name) {
        try {
            final int stake = inputView.getStakeOf(name.getValue());
            return new BettingMoney(stake);
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return getStakeOf(name);
        }
    }

    private BlackJack makeBlackJackBy(final List<Name> namesByView) {
        final BlackJack blackJack = new BlackJack(namesByView, randomDeck);
        outputView.printInitialStatus(DtoParser.getInitializeDto(blackJack));
        return blackJack;
    }

    private void printCompletedGame(final BlackJack blackJack, final PrizeCalculator prizeCalculator) {
        outputView.printFinalPlayersStatus(DtoParser.getFinalStatusDto(blackJack));
        outputView.printResult(DtoParser.getGameResultDto(prizeCalculator));
    }

    private void finalizeDealerCardStatus(final BlackJack blackJack, final PrizeCalculator prizeCalculator) {
        final int cardCount = blackJack.giveCardToDealerUntilDontNeed(randomDeck);
        prizeCalculator.enrollResult(blackJack.getAllUsersResult());
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
