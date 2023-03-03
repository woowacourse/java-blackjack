package application;

import static java.util.stream.Collectors.toList;

import domain.CardDeck;
import domain.CardDeckGenerator;
import domain.Dealer;
import domain.DrawCommand;
import domain.Name;
import domain.ParticipantGenerator;
import domain.Player;
import domain.Players;
import dto.DealerWinLoseResult;
import dto.DrawnCardsInfo;
import dto.ParticipantResult;
import dto.WinLoseResult;
import java.util.List;
import service.BlackJackService;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackJackService blackJackService;

    public BlackJackApplication(final InputView inputView,
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
        printParticipantResults(dealer, players);
        printWinLoseResult(dealer, players);
    }

    private Players createPlayers() {
        try {
            List<String> rawNames = inputView.readPlayerNames();

            List<Name> names = rawNames.stream()
                    .map(Name::new)
                    .collect(toList());

            return ParticipantGenerator.createPlayers(names);
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return createPlayers();
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
            DrawnCardsInfo drawnCardsInfo = blackJackService.drawCardByCommand(cardDeck, player, drawCommand);
            outputView.printPlayerCardInfo(drawnCardsInfo);
        } while (blackJackService.canPlayerDrawMore(player, drawCommand));
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
            blackJackService.drawDealerCard(cardDeck, dealer);
            outputView.printDealerCardPickMessage();
        } while (blackJackService.canDealerDrawMore(dealer));
    }

    private void printParticipantResults(final Dealer dealer, final Players players) {
        List<ParticipantResult> participantResults = blackJackService.getParticipantResults(dealer, players);
        outputView.printParticipantResults(participantResults);
    }

    private void printWinLoseResult(final Dealer dealer, final Players players) {
        List<WinLoseResult> winLoseResults = blackJackService.getWinLoseResults(dealer, players);
        DealerWinLoseResult dealerWinLoseResult = blackJackService.getDealerResult(winLoseResults, dealer);
        outputView.printWinLoseResult(winLoseResults, dealerWinLoseResult);
    }
}
