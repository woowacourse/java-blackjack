package controller;

import domain.Deck;
import domain.GameResult;
import domain.WinningStatus;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import domain.strategy.ShuffleStrategy;
import dto.DealerResultInfo;
import dto.PlayerResultInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
    public static final int INITIAL_CARDS_COUNT = 2;
    private final InputView inputView;
    private final OutputView outputView;
    private final ShuffleStrategy shuffleStrategy;

    public BlackjackGame(InputView inputView, OutputView outputView,
                         ShuffleStrategy shuffleStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.shuffleStrategy = shuffleStrategy;
    }

    public void run() {
        List<String> parsedNames = inputView.getParsedNames();
        Players players = new Players(parsedNames);
        Dealer dealer = new Dealer();

        Deck deck = Deck.createDeck(shuffleStrategy);

        playGame(players, dealer, deck);
    }

    private void playGame(Players players, Dealer dealer, Deck deck) {
        initParticipantsHand(players, dealer, deck);
        outputView.printBlankLine();

        for (Player player : players.getPlayers()) {
            playerTurn(player, deck);
        }

        outputView.printBlankLine();
        dealerTurn(dealer, deck);
        outputView.printBlankLine();
        showGameResult(players, dealer);
    }

    private void initParticipantsHand(Players players, Dealer dealer, Deck deck) {
        List<Participant> participants = new ArrayList<>(players.getPlayers());
        participants.add(dealer);
        participants.forEach(
                participant -> participant.receiveInitialCards(deck.drawInitialCards(INITIAL_CARDS_COUNT)));

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
        DealerResultInfo dealerResultInfo = new DealerResultInfo(gameResult);

        List<PlayerResultInfo> playerResultInfos = createPlayerResultInfos(gameResult);
        outputView.printGameResult(dealerResultInfo, playerResultInfos);
    }

    private List<PlayerResultInfo> createPlayerResultInfos(GameResult gameResult) {
        List<PlayerResultInfo> resultInfos = new ArrayList<>();

        for (Map.Entry<String, WinningStatus> entry : gameResult.getPlayerWinningStatus().entrySet()) {
            String name = entry.getKey();
            WinningStatus status = entry.getValue();

            resultInfos.add(new PlayerResultInfo(name, status));
        }

        return resultInfos;
    }
}
