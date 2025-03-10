package controller;

import constant.Answer;
import domain.GameManager;
import domain.card.Card;
import domain.card.CardDeck;
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

    public void run() {
        try {
            gameStart();
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printError(e.getMessage());
        }
    }

    private void gameStart() {
        Dealer dealer = Dealer.of(CardDeck.of());
        Players players = initParticipants();
        GameManager gameManager = GameManager.of(dealer, players);

        gameManager.distributeCards();
        outputView.printInitCards(dealer, players);

        drawToPlayers(gameManager);
        drawToDealer(dealer);

        outputView.printFinalCardsContent(dealer, players);
        outputView.printResult(dealer, players);
    }

    private Players initParticipants() {
        List<Player> players = Arrays.stream(inputView.getPlayerNames())
                .map(String::trim)
                .map(Player::of)
                .toList();
        return Players.of(players);
    }

    private void drawToPlayers(GameManager gameManager) {
        List<String> playersNames = gameManager.getPlayersName();
        for (String name : playersNames) {
            processPlayerDecision(name, gameManager);
        }
    }

    private void drawToDealer(Dealer dealer) {
        while (true) {
            boolean received = dealer.passCardToDealer();
            if (!received) {
                break;
            }
            outputView.printDealerReceived();
        }
    }

    private void processPlayerDecision(String name, GameManager gameManager) {
        while (gameManager.getScoreOf(name) < Card.BLACKJACK_SCORE) {
            String answer = inputView.askReceive(name);
            if (Answer.getAnswer(answer) == Answer.NO) {
                outputView.printCardsByName(gameManager.getPlayerByName(name));
                break;
            }
            gameManager.passCardToPlayer(name);
            outputView.printCardsByName(gameManager.getPlayerByName(name));
        }
    }
}
