package controller;

import domain.GameManager;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
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
        GameManager gameManager = GameManager.of(CardDeck.of(), dealer, players);

        gameManager.distributeCards();
        // TODO: 초기화 된 카드 결과 출력

        drawToPlayers(gameManager);
        drawToDealer(gameManager);
        // TODO: 승패 결과 출력
    }

    private Players initParticipants() {
        String rawNames = inputView.getPlayerNames();
        List<Participant> players = Arrays.stream(rawNames.split(","))
                .map(String::trim)
                .map(Player::of)
                .map(player -> (Participant) player)
                .toList();
        return Players.of(players);
    }

    private void drawToPlayers(GameManager gameManager) {
        List<String> playersNames = gameManager.getPlayersName();
        for (String name : playersNames) {
            processPlayerDecision(name, gameManager);
        }
    }

    private static void drawToDealer(GameManager gameManager) {
        while (true) {
            boolean received = gameManager.passCardToDealer();
            if (!received) {
                break;
            }
            // TODO: 딜러 카드 한장 더 받았다는 것을 알림
        }
    }

    private void processPlayerDecision(String name, GameManager gameManager) {
        while (true) {
            String answer = inputView.askReceive(name);
            if (answer.equals("n") || gameManager.getScoreOf(name) > 21) {
                break;
            }
            gameManager.passCardToPlayer(name);
        }
        // TODO: 총 카드 결과 출력
    }
}
