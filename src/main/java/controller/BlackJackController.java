package controller;

import domain.card.CardDeck;
import domain.result.Result;
import domain.participants.Dealer;
import domain.participants.Hand;
import domain.participants.Player;
import domain.participants.Players;
import service.BlackJackService;
import util.Parser;
import util.Validator;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {
    private static final String HIT_COMMAND = "y";
    private final BlackJackService blackJackService;

    public BlackJackController(BlackJackService blackJackService) {
        this.blackJackService = blackJackService;
    }

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Players players = new Players(getPlayer());
        Dealer dealer = new Dealer(new Hand());

        initGame(cardDeck, dealer, players);

        for (Player player : players.getPlayers()) {
            progressGame(cardDeck, player);
        }

        dealerHitOrStand(dealer, cardDeck);
        OutputView.scoreStatisticsMessage(dealer, players);
        Result result = blackJackService.calculateResult(dealer, players);
        OutputView.gameResultMessage(result);
    }

    private List<Player> getPlayer() {
        List<Player> players = new ArrayList<>();

        OutputView.inputPlayerMessage();
        String input = InputView.input();
        Validator.validateInput(input);
        List<String> names = Parser.separateBySeparator(input);

        for (String name : names) {
            players.add(new Player(name, new Hand()));
        }
        return players;
    }

    private void initGame(CardDeck cardDeck, Dealer dealer, Players players) {
        dealer.keepCard(cardDeck.drawCard());
        dealer.keepCard(cardDeck.drawCard());

        for (Player player : players.getPlayers()) {
            player.keepCard(cardDeck.drawCard());
            player.keepCard(cardDeck.drawCard());
        }

        OutputView.gameStartMessage(dealer, players);
    }

    private boolean isHitRequested(Player player) {
        OutputView.hitOrStandMessage(player);
        String input = InputView.input();
        Validator.validateChoiceInput(input);
        return HIT_COMMAND.equals(input);
    }

    private boolean canPlayerDraw(Player player) {
        if (player.getHand().isBust()) {
            player.getTotalCardScore();
            return false;
        }
        return isHitRequested(player);
    }

    private void drawAndShowCard(CardDeck cardDeck, Player player) {
        player.keepCard(cardDeck.drawCard());
        OutputView.holdingCardMessage(player);
    }

    private void finalizePlayerTurn(Player player) {
        OutputView.holdingCardMessage(player);
        player.getTotalCardScore();
    }

    private void progressGame(CardDeck cardDeck, Player player) {
        while (canPlayerDraw(player)) {
            drawAndShowCard(cardDeck, player);
        }
        if (!player.getHand().isBust()) {
            finalizePlayerTurn(player);
        }
    }

    private void dealerHitOrStand(Dealer dealer, CardDeck cardDeck) {
        while (dealer.dealerRule()) {
            OutputView.dealerHitMessage();
            dealer.keepCard(cardDeck.drawCard());
        }
    }
}
