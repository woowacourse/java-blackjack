package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.ShuffleOrderStrategy;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayerAnswer;

public class GameController {

    private final Deck deck;
    private final Players players;
    private final Dealer dealer;

    public GameController() {
        this.deck = Deck.from(new ShuffleOrderStrategy());
        this.players = initPlayers();
        this.dealer = new Dealer();
    }

    private Players initPlayers() {
        try {
            return new Players(InputView.getPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return initPlayers();
        }
    }

    public void betMoney() {
        players.getValue().forEach(this::initMoney);
    }

    private void initMoney(final Player player) {
        try {
            player.initMoney(InputView.getBettingAmount(player.getName()));
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            initMoney(player);
        }
    }

    public void initParticipants() {
        dealer.initCards(deck);
        players.initCards(deck);

        OutputView.printInitResult(players.getNames());
        OutputView.printDealerFirstCard(dealer.openFirstCard());

        OutputView.printCards(players);
    }

    public void progressPlayerTurns() {
        while (players.findDrawablePlayer().isPresent()) {
            final Player drawablePlayer = players.findDrawablePlayer().get();
            final PlayerAnswer playerAnswer = InputView.getHitOrStay(drawablePlayer.getName());

            final Player player = progressPlayerTurn(drawablePlayer, playerAnswer);
            OutputView.printCards(player);
        }
    }

    private Player progressPlayerTurn(Player player, final PlayerAnswer playerAnswer) {
        if (playerAnswer.isHit()) {
            player.hit(deck);
        }
        if (playerAnswer.isStay()) {
            player.stay();
        }

        return player;
    }

    public void progressDealerTurn() {
        int count = 0;
        while (dealer.isDrawable()) {
            dealer.hit(deck);
            count++;
        }

        dealer.updateStatus();
        OutputView.printDealerTurnResult(count);
    }

    public void endGame() {
        printAllCards();
        printDealerProfit();
        printPlayersPrize();
    }

    private void printAllCards() {
        OutputView.breakLine();

        OutputView.printCardsAndScore(dealer);
        OutputView.printCardsAndScore(players);
    }

    private void printDealerProfit() {
        double dealerProfit = players.getValue().stream()
                .mapToDouble(player -> player.calculateProfit(dealer.getScore(), dealer.isBlackjack()))
                .sum();

        OutputView.printDealerProfit(dealerProfit * -1);
    }

    private void printPlayersPrize() {
        for (Player player : players.getValue()) {
            final double prize = player.calculateProfit(dealer.getScore(), dealer.isBlackjack());
            OutputView.printPrize(player.getName(), prize);
        }
    }
}