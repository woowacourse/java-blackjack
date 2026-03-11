package controller;

import domain.card.CardDeck;
import domain.participant.*;
import service.BlackJackService;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {
    private final BlackJackService blackJackService;

    public BlackJackController(BlackJackService blackJackService) {
        this.blackJackService = blackJackService;
    }

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Players players = new Players(getPlayer());
        Dealer dealer = new Dealer();

        initGame(cardDeck, dealer, players);

        for (Player player : players.getPlayers()) {
            progressGame(cardDeck, player);
        }

        dealerHitOrStand(dealer, cardDeck);
        OutputView.scoreStatisticsMessage(dealer, players);
        OutputView.gameResultMessage(blackJackService.calculateResult(dealer, players));
    }

    private List<Player> getPlayer() {
        List<Player> players = new ArrayList<>();
        OutputView.inputPlayerMessage();
        List<String> names = new ArrayList<>(InputView.inputName());
        for (String name : names) {
            OutputView.askBettingMoneyMessage(name);
            Money bettingMoney=new Money(InputView.inputBettingMoney());
            players.add(new Player(new ParticipantInfo(name, new Hand()), bettingMoney));
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
        return InputView.inputHitOrStand();
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
        while (dealer.canHit()) {
            OutputView.dealerHitMessage();
            dealer.keepCard(cardDeck.drawCard());
        }
    }
}