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

        BlackjackGame blackjackGame = initBlackjackGame(bettingPool);

        distributeInitialCards(blackjackGame);

        playGame(blackjackGame);

        finishGame(blackjackGame, bettingPool);
    }

    private BlackjackGame initBlackjackGame(BettingPool bettingPool) {
        Dealer dealer = Dealer.of();

        List<Player> rawPlayers = readPlayers();
        readPlayersBet(rawPlayers, bettingPool);

        Players players = Players.of(rawPlayers);
        return BlackjackGame.of(
                CardDeck.of(CardDeckGenerator.generateCardDeck()), dealer, players
        );
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
            int betMoney = parseValidateInteger(rawBet);
            bettingPool.wager(p, Bet.of(betMoney));
        }
    }

    private int parseValidateInteger(String rawInt) {
        try {
            return Integer.parseInt(rawInt);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 숫자입니다.");
        }
    }

    private void distributeInitialCards(BlackjackGame game) {
        game.distributeCards();
        outputView.printInitCards(game.getDealer(), game.getPlayers());
    }

    private void playGame(BlackjackGame game) {
        playPlayerTurns(game);
        playDealerTurn(game);
    }

    private void playPlayerTurns(BlackjackGame game) {
        for (String playerName : game.getPlayersName()) {
            playPlayerTurn(game, playerName);
        }
    }

    private void playPlayerTurn(BlackjackGame game, String playerName) {
        while (game.checkPlayerCanReceive(playerName)) {
            String answer = inputView.askReceive(playerName);
            if (!isValidAnswer(answer)) {
                throw new IllegalArgumentException("유효하지 않은 입력입니다.");
            }

            if (answer.equals("n")) {
                break;
            }

            game.passCardToPlayer(playerName);
            outputView.printCardsByName(game.getPlayerByName(playerName));
        }
    }

    private boolean isValidAnswer(String answer) {
        return answer.equals("y") || answer.equals("n");
    }

    private void playDealerTurn(BlackjackGame game) {
        while (game.passCardToDealer()) {
            outputView.printDealerReceived();
        }
    }

    private void finishGame(BlackjackGame game, BettingPool bettingPool) {
        Map<Player, Integer> profits = game.calculatePlayerProfit(bettingPool);
        outputView.printFinalCardsContent(game.getDealer(), game.getPlayers());
        outputView.printProfits(profits);
    }
}
