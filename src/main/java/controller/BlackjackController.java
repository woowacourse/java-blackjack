package controller;

import static domain.GameManager.BLACKJACK_SCORE;

import domain.GameManager;
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
        Dealer dealer = Dealer.of();
        Players players = initParticipants();
        GameManager gameManager = GameManager.of(
                CardDeck.of(CardDeckGenerator.generateCardDeck()), dealer, players
        );

        gameManager.distributeCards();
        outputView.printInitCards(dealer, players);

        drawToPlayers(gameManager);
        drawToDealer(gameManager);

        outputView.printFinalCardsContent(dealer, players);
        outputView.printResult(dealer, players);
    }

    private Players initParticipants() {
        String rawNames = inputView.getPlayerNames();
        List<Player> players = Arrays.stream(rawNames.split(","))
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

    private void drawToDealer(GameManager gameManager) {
        boolean received = gameManager.passCardToDealer();
        while (received) {
            outputView.printDealerReceived();
            received = gameManager.passCardToDealer();
        }
    }

    private void processPlayerDecision(String name, GameManager gameManager) {
        while (gameManager.getScoreOf(name) < BLACKJACK_SCORE) {
            String answer = inputView.askReceive(name);
            validateBinaryQuestion(answer);
            if (answer.equals("n")) {
                outputView.printCardsByName(gameManager.getPlayerByName(name));
                break;
            }
            gameManager.passCardToPlayer(name);
            outputView.printCardsByName(gameManager.getPlayerByName(name));
        }
    }

    private void validateBinaryQuestion(String question) {
        if (question.equals("y") || question.equals("n")) {
            return ;
        }
        throw new IllegalArgumentException("유효하지 않은 입력입니다.");
    }
}
