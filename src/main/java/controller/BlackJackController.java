package controller;

import domain.*;
import service.BlackJackService;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackJackController {
    private final BlackJackService blackJackService;
    private Result result;

    public BlackJackController(BlackJackService blackJackService, Result result) {
        this.blackJackService = blackJackService;
        this.result = result;
    }

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Players players = new Players(getPlayer());
        Dealer dealer = new Dealer(new Hand());

        dealer.keepCard(cardDeck.drawCard());
        dealer.keepCard(cardDeck.drawCard());

        for (Player player : players.getPlayers()) {
            player.keepCard(cardDeck.drawCard());
            player.keepCard(cardDeck.drawCard());
        }

        OutputView.gameStartMessage(dealer, players);

        for (Player player : players.getPlayers()) {
            while (true) {
                OutputView.hitOrStandMessage(player);
                String input = InputView.input();
                if (input.equals("n")) {
                    OutputView.holdingCardMessage(player);
                    player.getTotalCardScore();
                    break;
                }
                player.keepCard(cardDeck.drawCard());
                OutputView.holdingCardMessage(player);
                if (player.getHand().isBust()) {
                    player.getTotalCardScore();
                    break;
                }
            }
        }

        if (dealer.dealerRule()) {
            OutputView.dealerHitMessage();
            dealer.keepCard(cardDeck.drawCard());
        }

        OutputView.scoreStatisticsMessage(dealer, players);

        result = blackJackService.calculateResult(dealer, players);

        OutputView.gameResultMessage(result);

    }

    private List<Player> getPlayer() {
        List<Player> players = new ArrayList<>();

        OutputView.inputPlayerMessage();
        String input = InputView.input();
        List<String> names = Arrays.asList(input.split(","));

        for (String name : names) {
            players.add(new Player(name, new Hand()));
        }
        return players;
    }
}
