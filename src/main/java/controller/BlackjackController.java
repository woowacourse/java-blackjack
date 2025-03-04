package controller;

import domain.CardDeck;
import domain.Dealer;
import domain.Player;
import domain.Players;
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
        Dealer dealer = Dealer.of(CardDeck.of());

        String rawNames = inputView.getPlayerNames();
        List<Player> participants = Arrays.stream(rawNames.split(","))
                .map(String::trim)
                .map(Player::of)
                .toList();
        Players players = Players.of(participants);

        players.receiveCardFrom(dealer);
        players.receiveCardFrom(dealer);

        dealer.receive(dealer.passCard());
        dealer.receive(dealer.passCard());
        outputView.printInitCards(dealer, players);
    }
}
