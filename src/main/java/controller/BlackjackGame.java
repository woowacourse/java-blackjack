package controller;

import domain.game.BlackjackRule;
import domain.card.Deck;
import domain.game.GameResult;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import domain.strategy.RandomShuffleStrategy;
import dto.DealerResultInfo;
import dto.PlayerResultInfo;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> parsedNames = inputView.getParsedNames();
        Players players = new Players(parsedNames);
        Dealer dealer = new Dealer();
        Deck deck = Deck.createDeck(new RandomShuffleStrategy());

        initializeBetting(players);
        initializeParticipantsHand(players, dealer, deck);
        proceedEachTurnWith(players, dealer, deck);
        showGameResult(players, dealer);
    }

    private void initializeBetting(Players players) {
        outputView.printBlankLine();

        for (Player player : players.getPlayers()) {
            int amount = inputView.askForBettingAmount(player.name());
            player.bet(amount);
            outputView.printBlankLine();
        }
    }

    private void proceedEachTurnWith(Players players, Dealer dealer, Deck deck) {
        for (Player player : players.getPlayers()) {
            playerTurn(player, deck);
        }
        outputView.printBlankLine();
        dealerTurn(dealer, deck);
        outputView.printBlankLine();
    }

    private void initializeParticipantsHand(Players players, Dealer dealer, Deck deck) {
        outputView.printBlankLine();
        List<Participant> participants = new ArrayList<>(players.getPlayers());
        participants.add(dealer);
        participants.forEach(
                participant -> participant.receiveInitialCards(
                        deck.drawInitialCards(BlackjackRule.INITIAL_CARD_COUNT)
                )
        );

        outputView.printInitialDistribution(players, dealer);
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.canDraw()) {
            dealer.receive(deck.draw());
            outputView.printDealerReceiveMessage();
        }
    }

    private void playerTurn(Player player, Deck deck) {
        while (player.canDraw()) {
            if (!inputView.askForOneMoreCard(player.name())) {
                break;
            }

            player.receive(deck.draw());
            outputView.printParticipantCards(player);
        }
    }

    private void showGameResult(Players players, Dealer dealer) {
        List<Participant> participants = new ArrayList<>(players.getPlayers());
        participants.add(dealer);
        participants.forEach(outputView::printFinalResult);
        outputView.printBlankLine();

        GameResult gameResult = new GameResult(players, dealer);
        DealerResultInfo dealerResultInfo = new DealerResultInfo(gameResult.dealerProfit());
        List<PlayerResultInfo> playerResultInfos = gameResult.playerResults().stream()
                .map(result -> new PlayerResultInfo(result.name(), result.profit()))
                .toList();

        outputView.printGameResult(dealerResultInfo, playerResultInfos);
    }
}
