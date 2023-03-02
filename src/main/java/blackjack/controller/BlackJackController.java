package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {
    public static final int REPEAT_COUNT = 2;
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Trump trump = new Trump(new RandomNumberGenerator());
        Players players = generatePlayers(trump);
        Dealer dealer = new Dealer(getInitialCards(trump));
        playAllPlayer(players, trump);


    }

    private Players generatePlayers(final Trump trump) {
        List<String> playerNames = inputView.readPlayerNames();

        Validator validator = Validator.getInstance();
        validator.validatePlayerNames(playerNames);

        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName, getInitialCards(trump)));
        }
        return new Players(players);
    }

    private List<Card> getInitialCards(final Trump trump) {
        List<Card> initialCards = new ArrayList<>();
        for (int i = 0; i < REPEAT_COUNT; i++) {
            initialCards.add(trump.getCard());
        }
        return initialCards;
    }

    private void playAllPlayer(Players players, Trump trump) {
        for (Player player : players.getPlayers()) {
            playEachPlayer(player, trump);
        }
    }

    private void playEachPlayer(Player player, Trump trump) {
        String intention = getIntention();
        while (player.isAbleToReceive() && intention.equals("y")) {
            player.receiveCard(trump.getCard());
            intention = getIntention();
        }
    }

    private String getIntention() {
        String intention = inputView.readPlayerIntention();
        Validator.getInstance().validatePlayerIntention(intention);

        return intention;
    }

    private void playDealer(Dealer dealer, Trump trump) {
        while (dealer.isAbleToReceive()) {
            dealer.receiveCard(trump.getCard());
        }

    }
}
