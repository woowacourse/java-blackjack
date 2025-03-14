package controller;

import static domain.BlackjackGame.BLACKJACK_SCORE;

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

        outputView.printFinalCardsContent(dealer, players);
        outputView.printResult(dealer, players);
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
        while (blackjackGame.getScoreOf(name) < BLACKJACK_SCORE) { // TODO: 플레이어 모델이 더 받을 수 있는지 판단하도록 함
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
