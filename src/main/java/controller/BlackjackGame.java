package controller;

import domain.*;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;

import domain.strategy.ShuffleStrategy;
import dto.DealerResultInfo;
import dto.PlayerResultInfo;
import util.InputParser;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.SequencedCollection;

public class BlackjackGame {
    public static final String NO = "n";
    public static final String WIN = "승";
    public static final String TIE = "무";
    public static final String LOSE = "패";
    public static final int INITIAL_CARDS_COUNT = 2;
    private final InputView inputView;
    private final InputParser inputParser;
    private final OutputView outputView;
    private final ShuffleStrategy shuffleStrategy;

    public BlackjackGame(InputView inputView, InputParser inputParser, OutputView outputView, ShuffleStrategy shuffleStrategy) {
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
        participants.forEach(participant ->
                participant.receiveInitialCards(deck.drawInitialCards(INITIAL_CARDS_COUNT), INITIAL_CARDS_COUNT)
        );

        outputView.printInitialDistribution(players, dealer);
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.canReceive()) {
            dealer.receive(deck.draw());
            outputView.printDealerReceiveMessage();
        }
    }

    private void playerTurn(Player player, Deck deck) {
        while (player.canReceive()) {
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

        DealerResultInfo dealerResultInfo = new DealerResultInfo(
                gameResult.dealerWinCount(),
                gameResult.dealerTieCount(),
                gameResult.dealerLoseCount()
        );

        List<PlayerResultInfo> playerResultInfos = createPlayerResultInfos(gameResult);
        outputView.printGameResult(dealerResultInfo, playerResultInfos);
    }

    private List<PlayerResultInfo> createPlayerResultInfos(GameResult gameResult) {
        List<PlayerResultInfo> resultInfos = new ArrayList<>();

        for (Map.Entry<String, WinningStatus> entry : gameResult.getPlayerWinningStatus().entrySet()) {
            String name = entry.getKey();
            WinningStatus status = entry.getValue();

            resultInfos.add(new PlayerResultInfo(name, toKorean(status)));
        }

        return resultInfos;
    }

    private String toKorean(WinningStatus status) {
        return switch (status) {
            case WinningStatus.WIN -> WIN;
            case WinningStatus.TIE -> TIE;
            case WinningStatus.LOSE -> LOSE;
        };
    }
}
