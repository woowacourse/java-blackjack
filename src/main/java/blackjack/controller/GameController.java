package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.ShuffleOrderStrategy;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayerAnswer;
import java.util.List;

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

        final List<Player> allPlayers = players.getValue();
        allPlayers.forEach(OutputView::printCards);
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
        if (playerAnswer.isDraw()) {
            player.hit(deck);
        }
        if (!playerAnswer.isDraw()) {
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
        printDealerPrize();
        printPlayersPrize();
    }

    private void printAllCards() {
        OutputView.breakLine();

        OutputView.printCardsAndScore(dealer);
        players.getValue().forEach(OutputView::printCardsAndScore);
    }

    private void printDealerPrize() {
        double dealerPrize = players.getValue().stream()
                .mapToDouble(player -> player.findCalculator()
                        .calculate(player.getScore(), dealer.getScore(), dealer.isBlackjack(),
                                player.getBettingAmount()))
                .sum();
        OutputView.printDealerPrize(dealerPrize * -1);
    }

    private void printPlayersPrize() {
        for (Player player : players.getValue()) {
            final double prize = player.findCalculator()
                    .calculate(player.getScore(), dealer.getScore(), dealer.isBlackjack(),
                            player.getBettingAmount());
            OutputView.printPrize(player.getName(), prize);
        }
    }
}