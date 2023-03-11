package controller;

import static java.util.stream.Collectors.toList;

import domain.card.CardDeck;
import domain.command.Command;
import domain.participants.Account;
import domain.participants.Dealer;
import domain.participants.Name;
import domain.participants.Player;
import domain.participants.Players;
import generator.CardDeckGenerator;
import generator.ParticipantGenerator;
import java.util.List;
import service.CalculatorService;
import service.ResultService;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ResultService blackJackResultService;
    private final CalculatorService calculatorService;

    public BlackJackController(final InputView inputView,
                               final OutputView outputView,
                               final ResultService resultService,
                               final CalculatorService calculatorService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJackResultService = resultService;
        this.calculatorService = calculatorService;
    }

    public void run() {
        CardDeck cardDeck = CardDeckGenerator.create();
        Dealer dealer = ParticipantGenerator.createDealer();
        Players players = createPlayers();

        splitInitCardsToParticipants(players, dealer, cardDeck);
        drawCards(players, dealer, cardDeck);
        printParticipantsCardsResults(players, dealer);
        printParticipantAccountResults(players, dealer);
    }

    private Players createPlayers() {
        List<Name> playerNames = createPlayerNames();
        List<Account> playerAccounts = createPlayerAccounts(playerNames);

        return ParticipantGenerator.createPlayers(playerNames, playerAccounts);
    }

    private List<Name> createPlayerNames() {
        try {
            List<String> rawNames = inputView.readPlayerNames();
            return rawNames.stream()
                    .map(Name::new)
                    .collect(toList());
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return createPlayerNames();
        }
    }

    private List<Account> createPlayerAccounts(final List<Name> playerNames) {
        return playerNames.stream()
                .map(this::createAccount)
                .collect(toList());
    }

    private Account createAccount(final Name playerName) {
        try {
            int account = inputView.readAccount(playerName.name());
            return new Account(account);
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return createAccount(playerName);
        }
    }

    private void splitInitCardsToParticipants(final Players players, final Dealer dealer, final CardDeck cardDeck) {
        calculatorService.splitCards(players, dealer, cardDeck);
        outputView.printSplitCardsMessage(blackJackResultService.makeAfterSplitsInfos(players, dealer));
    }

    private void drawCards(final Players players, final Dealer dealer, final CardDeck cardDeck) {
        players.stream()
                .forEach(player -> drawPlayerCards(player, cardDeck));

        drawDealerCards(cardDeck, dealer);
    }

    private void drawPlayerCards(final Player player, final CardDeck cardDeck) {
        Command command;
        do {
            command = getMoreDrawCardCommand(player.getName());
            calculatorService.drawCard(player, cardDeck, command);
            outputView.printPlayerCardInfo(blackJackResultService.drawCards(player));
        } while (calculatorService.canDrawMore(player, command));
    }

    private Command getMoreDrawCardCommand(final String name) {
        try {
            String rawCommand = inputView.readChoiceOfDrawCard(name);
            Command command = Command.fromCommand(rawCommand);
            return command;
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return getMoreDrawCardCommand(name);
        }
    }

    private void drawDealerCards(final CardDeck cardDeck, final Dealer dealer) {
        do {
            calculatorService.pickDealerCard(cardDeck, dealer);
            outputView.printDealerCardPickMessage();
        } while (calculatorService.canDealerDrawMore(dealer));
    }

    private void printParticipantsCardsResults(final Players players, final Dealer dealer) {
        outputView.printCardsResults(blackJackResultService.getParticipantsCardsResults(players, dealer));
    }

    private void printParticipantAccountResults(final Players players, final Dealer dealer) {
        calculatorService.calculateGameResults(players, dealer);
        outputView.printAccountResults(blackJackResultService.getParticipantAccountResults(players, dealer));
    }
}
