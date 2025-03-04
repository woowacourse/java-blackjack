package controller;

import domain.CardDeck;
import domain.Dealer;
import domain.Player;
import domain.Players;
import java.util.Arrays;
import java.util.List;
import view.InputView;

public class BlackjackController {

    private final InputView inputView;

    public BlackjackController(InputView inputView) {
        this.inputView = inputView;
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
    }
}
