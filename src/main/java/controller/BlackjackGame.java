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
import util.InputParser;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
    public static final String NO = "n";
    public static final int INITIAL_CARDS_COUNT = 2;
    private final InputView inputView;
    private final InputParser inputParser;
    private final OutputView outputView;
    private final ShuffleStrategy shuffleStrategy;

    public BlackjackGame(InputView inputView, InputParser inputParser, OutputView outputView,
                         ShuffleStrategy shuffleStrategy) {
        this.inputView = inputView;
        this.inputParser = inputParser;
        this.outputView = outputView;
        this.shuffleStrategy = shuffleStrategy;
    }

    public void run() {
        String names = inputView.getNames();
        List<String> parsedName = inputParser.parseName(names);

        Players players = new Players(parsedName);
        Dealer dealer = new Dealer();

        Deck deck = Deck.createDeck(shuffleStrategy);

        playGame(players, dealer, deck);
    }

    private void playGame(Players players, Dealer dealer, Deck deck) {
        initParticipantsHand(players, dealer, deck);

        for (Player player : players.getPlayers()) {
            playerTurn(player, deck);
        }

        dealerTurn(dealer, deck);

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
            String choice = inputView.getUserChoice(player.name());
            String userChoice = inputParser.parseUserChoice(choice);

            if (userChoice.equals(NO)) {
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

        GameResult gameResult = new GameResult(players, dealer);

        DealerResultInfo dealerResultInfo = new DealerResultInfo(gameResult.dealerWinCount(),
                gameResult.dealerTieCount(), gameResult.dealerLoseCount());

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
