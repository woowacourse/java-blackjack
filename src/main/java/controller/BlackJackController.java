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
import service.BlackJackService;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackJackService blackJackService;

    public BlackJackController(final InputView inputView,
                               final OutputView outputView,
                               final BlackJackService blackJackService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJackService = blackJackService;
    }

    public void run() {
        CardDeck cardDeck = CardDeckGenerator.create();
        Dealer dealer = ParticipantGenerator.createDealer();
        Players players = createPlayers();

        splitCards(cardDeck, dealer, players);
        drawCards(cardDeck, dealer, players);
        printParticipantsCardsResults(dealer, players);
        printParticipantAccountResults(dealer, players);
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

    private void splitCards(final CardDeck cardDeck, final Dealer dealer, final Players players) {
        List<DrawnCardsInfo> drawnCardsInfos = blackJackService.splitCards(dealer, players, cardDeck);
        outputView.printCardSplitMessage(drawnCardsInfos);
    }

    private void drawCards(final CardDeck cardDeck, final Dealer dealer, final Players players) {
        players.stream()
                .forEach(player -> drawPlayerCards(cardDeck, player));
        drawDealerCards(cardDeck, dealer);
    }

    private void drawPlayerCards(final CardDeck cardDeck, final Player player) {
        DrawCommand drawCommand;
        do {
            drawCommand = getRawCommand(player.getName());
            DrawnCardsInfo drawnCardsInfo = blackJackService.drawCards(cardDeck, player, drawCommand);
            outputView.printPlayerCardInfo(drawnCardsInfo);
        } while (blackJackService.canDrawMore(player, drawCommand));
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
            blackJackService.pickDealerCard(cardDeck, dealer);
            outputView.printDealerCardPickMessage();
        } while (blackJackService.canDealerDrawMore(dealer));
    }

    private void printParticipantsCardsResults(final Dealer dealer, final Players players) {
        List<ParticipantResult> participantsCardsResults = blackJackService.getParticipantsCardsResults(dealer, players);
        outputView.printParticipantsCardsResults(participantsCardsResults);
    }

    private void printParticipantAccountResults(final Dealer dealer, final Players players) {
        blackJackService.calculateGameResults(dealer, players);
        List<BlackJackResult> gameResults = blackJackService.getParticipantAccountResults(dealer, players);
        outputView.printParticipantAccountResults(gameResults);
    }
}
