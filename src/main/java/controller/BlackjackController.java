package controller;

import domain.BlackjackGame;
import domain.batting.Bet;
import domain.batting.BettingPool;
import domain.card.CardDeck;
import domain.card.CardDeckGenerator;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void gameStart() {
        BettingPool bettingPool = BettingPool.of();
        Dealer dealer = Dealer.of();

        List<Player> rawPlayers = readPlayers();
        readPlayersBet(rawPlayers, bettingPool);

        Players players = Players.of(rawPlayers);
        BlackjackGame blackjackGame = BlackjackGame.of(
                CardDeck.of(CardDeckGenerator.generateCardDeck()), dealer, players
        );

        blackjackGame.distributeCards();
        outputView.printInitCards(dealer, players);

        drawToPlayers(blackjackGame);
        drawToDealer(blackjackGame);

        Map<Player, Integer> profits = blackjackGame.calculatePlayerProfit(bettingPool);
        outputView.printFinalCardsContent(dealer, players);
        outputView.printProfits(profits);
    }

    private List<Player> readPlayers() {
        String rawNames = inputView.getPlayerNames();
        return Arrays.stream(rawNames.split(","))
                .map(String::trim)
                .map(Player::of)
                .toList();
    }

    private void readPlayersBet(List<Player> ps, BettingPool bettingPool) {
        for (Player p : ps) {
            String rawBet = inputView.getPlayerBetAmount(p);
            int betMoney = Integer.parseInt(rawBet);
            bettingPool.wager(p, Bet.of(betMoney));
        }
    }

    private void drawToPlayers(BlackjackGame blackjackGame) {
        List<String> playersNames = blackjackGame.getPlayersName();
        for (String name : playersNames) {
            processPlayerDecision(name, blackjackGame);
        }
    }

    private void drawToDealer(BlackjackGame blackjackGame) {
        boolean received = blackjackGame.passCardToDealer();
        while (received) {
            outputView.printDealerReceived();
            received = blackjackGame.passCardToDealer();
        }
    }

    private void processPlayerDecision(String name, BlackjackGame blackjackGame) {
        while (blackjackGame.checkPlayerCanReceive(name)) {
            String answer = inputView.askReceive(name);
            validateBinaryQuestion(answer);
            if (answer.equals("n")) {
                outputView.printCardsByName(blackjackGame.getPlayerByName(name));
                break;
            }
            blackjackGame.passCardToPlayer(name);
            outputView.printCardsByName(blackjackGame.getPlayerByName(name));
        }
    }

    private void validateBinaryQuestion(String question) {
        if (question.equals("y") || question.equals("n")) {
            return;
        }
        throw new IllegalArgumentException("유효하지 않은 입력입니다.");
    }
}
