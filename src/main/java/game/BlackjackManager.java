package game;

import deck.Deck;
import java.util.Map;
import participant.Dealer;
import participant.Player;
import participant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackManager {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackGame blackjackGame;

    public BlackjackManager(InputView inputView, OutputView outputView, BlackjackGame blackjackGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackGame = blackjackGame;
    }

    public void start(Dealer dealer, Players players, Deck deck) {
        processDistributionTwoCardsToParticipants(dealer, players, deck);
        processPlayersTurn(players, deck);
        processDealerTurn(dealer, deck);
        processParticipantResults(dealer, players);
    }

    private void processDistributionTwoCardsToParticipants(Dealer dealer, Players players, Deck deck) {
        blackjackGame.distributeTwoCardsToParticipants(dealer, players, deck);
        outputView.printDistributionCards(dealer, players);
    }

    private void processPlayersTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            while (player.canReceiveCard()) {
                boolean drawOneMore = inputView.readDrawOneMore(player.getNickname());
                if (!drawOneMore) {
                    break;
                }
                blackjackGame.runPlayerTurn(player, deck);
                outputView.printPlayerCards(player);
            }
        }
    }

    private void processDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.canReceiveCard()) {
            blackjackGame.runDealerTurn(dealer, deck);
            outputView.printDealerReceivedCard();
        }
    }

    private void processParticipantResults(Dealer dealer, Players players) {
        outputView.printAllCardAndScore(players, dealer);
        Map<Player, GameResult> playersGameResults = blackjackGame.calculatePlayersGameResults(dealer, players);
        Map<GameResult, Integer> dealerGameResults = blackjackGame.calculateDealerGameResults(playersGameResults);
        outputView.printResult(dealerGameResults, playersGameResults);
    }
}
