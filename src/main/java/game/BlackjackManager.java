package game;

import deck.Deck;
import java.util.Map;
import participant.Dealer;
import participant.Player;
import participant.Players;
import participant.Profit;
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
        processUpdateMoney(dealer, players);
        processParticipantResults(dealer, players);
    }

    private void processDistributionTwoCardsToParticipants(Dealer dealer, Players players, Deck deck) {
        blackjackGame.distributeTwoCardsToParticipants(dealer, players, deck);
        outputView.printDistributionCards(dealer, players);
    }

    private void processPlayersTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            while (player.canReceiveCard()) {
                if (!inputView.readDrawOneMore(player.getNickname())) {
                    break;
                }
                blackjackGame.runParticipantTurn(player, deck);
                outputView.printPlayerCards(player);
            }
        }
    }

    private void processDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.canReceiveCard()) {
            blackjackGame.runParticipantTurn(dealer, deck);
            outputView.printDealerReceivedCard();
        }
    }

    private void processUpdateMoney(Dealer dealer, Players players) {
        blackjackGame.updatePlayerMoney(dealer, players);
    }

    private void processParticipantResults(Dealer dealer, Players players) {
        outputView.printAllCardAndScore(players, dealer);
        Map<Playable, Profit> participantGameResults = blackjackGame.calculateParticipantGameResults(dealer, players);
        outputView.printResult(participantGameResults);
    }
}
