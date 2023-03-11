package controller;

import static java.util.stream.Collectors.toList;

import domain.Account;
import domain.CardDeck;
import domain.Dealer;
import domain.DrawCommand;
import domain.Name;
import domain.Player;
import domain.Players;
import dto.BlackJackResult;
import dto.DrawnCardsInfo;
import dto.ParticipantResult;
import generator.CardDeckGenerator;
import generator.ParticipantGenerator;
import java.util.List;
import service.BlackjackCalculatorService;
import service.BlackjackResultService;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackResultService blackJackResultService;
    private final BlackjackCalculatorService blackjackCalculatorService;

    public BlackJackController(final InputView inputView,
                               final OutputView outputView,
                               final BlackjackResultService blackJackResultService,
                               final BlackjackCalculatorService blackjackCalculatorService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJackResultService = blackJackResultService;
        this.blackjackCalculatorService = blackjackCalculatorService;
    }

    public void run() {
        CardDeck cardDeck = CardDeckGenerator.create();
        Dealer dealer = ParticipantGenerator.createDealer();
        Players players = createPlayers();

        splitCards(players, dealer, cardDeck);
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

    private void splitCards(final Players players, final Dealer dealer, final CardDeck cardDeck) {
        blackjackCalculatorService.splitCards(players, dealer, cardDeck);
        List<DrawnCardsInfo> drawnCardsInfos = blackJackResultService.makeAfterSplitsInfos(players, dealer);
        outputView.printCardSplitMessage(drawnCardsInfos);
    }

    private void drawCards(final Players players, final Dealer dealer, final CardDeck cardDeck) {
        players.stream()
                .forEach(player -> drawPlayerCards(player, cardDeck));
        drawDealerCards(cardDeck, dealer);
    }

    private void drawPlayerCards(final Player player, final CardDeck cardDeck) {
        DrawCommand drawCommand;
        do {
            drawCommand = getRawCommand(player.getName());
            blackjackCalculatorService.drawCards(player, cardDeck, drawCommand);
            DrawnCardsInfo drawnCardsInfo = blackJackResultService.drawCards(player);
            outputView.printPlayerCardInfo(drawnCardsInfo);
        } while (blackjackCalculatorService.canDrawMore(player, drawCommand));
    }

    private DrawCommand getRawCommand(final String name) {
        try {
            String rawCommand = inputView.readChoiceOfDrawCard(name);
            DrawCommand drawCommand = new DrawCommand(rawCommand);
            return drawCommand;
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return getRawCommand(name);
        }
    }

    private void drawDealerCards(final CardDeck cardDeck, final Dealer dealer) {
        do {
            blackjackCalculatorService.pickDealerCard(cardDeck, dealer);
            outputView.printDealerCardPickMessage();
        } while (blackjackCalculatorService.canDealerDrawMore(dealer));
    }

    private void printParticipantsCardsResults(final Players players, final Dealer dealer) {
        List<ParticipantResult> participantsCardsResults = blackJackResultService.getParticipantsCardsResults(players,
                dealer);
        outputView.printParticipantsCardsResults(participantsCardsResults);
    }

    private void printParticipantAccountResults(final Players players, final Dealer dealer) {
        blackjackCalculatorService.calculateGameResults(players, dealer);
        List<BlackJackResult> gameResults = blackJackResultService.getParticipantAccountResults(players, dealer);
        outputView.printParticipantAccountResults(gameResults);
    }
}
