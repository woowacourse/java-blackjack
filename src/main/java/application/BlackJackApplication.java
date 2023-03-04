package application;

import static java.util.stream.Collectors.toList;

import domain.card.CardDeck;
import domain.card.CardDeckGenerator;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.ParticipantGenerator;
import domain.participant.Player;
import domain.participant.Players;
import dto.request.DrawCommand;
import dto.response.DrawnCardsInfo;
import dto.response.ParticipantResult;
import dto.response.WinLoseResult;
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
        while (getDrawCommand(player).isDraw()) {
            DrawnCardsInfo drawnCardsInfo = blackJackService.drawPlayerCard(cardDeck, player);
            outputView.printPlayerCardInfo(drawnCardsInfo);

            if (!blackJackService.canPlayerDrawMore(player)) break;
        }
    }

    private DrawCommand getDrawCommand(final Player player) {
        try {
            String rawCommand = inputView.readChoiceOfDrawCard(player.getName());
            DrawCommand drawCommand = new DrawCommand(rawCommand);
            return drawCommand;
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return getDrawCommand(player);
        }
    }

    private void drawDealerCards(final CardDeck cardDeck, final Dealer dealer) {
        while (blackJackService.canDealerDrawMore(dealer)) {
            blackJackService.drawDealerCard(cardDeck, dealer);
            outputView.printDealerCardPickMessage();
        }
    }

    private void printParticipantResults(final Dealer dealer, final Players players) {
        List<ParticipantResult> participantResults = blackJackService.getParticipantResults(dealer, players);
        outputView.printParticipantResults(participantResults);
    }

    private void printWinLoseResult(final Dealer dealer, final Players players) {
        List<WinLoseResult> winLoseResults = blackJackService.getWinLoseResults(dealer, players);
        outputView.printWinLoseResult(dealer.getName(),winLoseResults);
    }
}
