package controller;

import domain.CardDeck;
import domain.Dealer;
import domain.GameManager;
import domain.Participant;
import domain.Participants;
import domain.Player;
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

    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        BlackjackController blackjackController = new BlackjackController(inputView, outputView);
        blackjackController.gameStart();
    }

    public void gameStart() {
        Participant dealer = Dealer.of();

        String rawNames = inputView.getPlayerNames();
        List<Participant> players = Arrays.stream(rawNames.split(","))
                .map(String::trim)
                .map(Player::of)
                .map(player -> (Participant) player)
                .toList();

        Participants participants = Participants.of(dealer, players);

        GameManager gameManager = GameManager.of(CardDeck.of(), participants);
        gameManager.distributeCards();
//        outputView.printInitCards(participants);
    }
}
