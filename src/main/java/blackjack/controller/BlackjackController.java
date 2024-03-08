package blackjack.controller;

import blackjack.model.deck.DeckGenerator;
import blackjack.model.GameRule;
import blackjack.model.deck.PlayingDeck;
import blackjack.model.card.Card;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Player;
import blackjack.model.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck());

    public void run() {
        List<Player> players = registerPlayer();
        playBlackJack(new Dealer(), players);
    }

    private List<Player> registerPlayer() {
        List<String> playersName = InputView.readPlayersName();
        return playersName.stream()
                .map(Player::new)
                .toList();
    }

    private void playBlackJack(Dealer dealer, List<Player> players) {
        initialDraw(dealer, players);
        runPlayerTurn(players);
        runDealerTurn(dealer);
        calculateResult(dealer, players);
    }

    private void initialDraw(Dealer dealer, List<Player> players) {
        drawCardToDealer(dealer);
        drawCardToPlayer(players);
        OutputView.printInitialDrawResult(dealer, players);
    }

    private void drawCardToDealer(Dealer dealer) {
        Card firstDealerCard = playingDeck.drawCard();
        Card secondDealerCard = playingDeck.drawCard();

        dealer.receiveCard(firstDealerCard);
        dealer.receiveCard(secondDealerCard);
    }

    private void drawCardToPlayer(List<Player> players) {
        for (Player player : players) {
            Card firstPlayerCard = playingDeck.drawCard();
            Card secondPlayerCard = playingDeck.drawCard();

            player.receiveCard(firstPlayerCard);
            player.receiveCard(secondPlayerCard);
        }
    }

    private void runPlayerTurn(List<Player> players) {
        for (Player player : players) {
            hitOrStand(player);
        }
    }

    private void hitOrStand(Player player) {
        while (player.canHit() && InputView.askPlayerForCard(player)) {
            executeHit(player);
        }
        if (player.canHit()) {
            OutputView.printPlayerCard(player);
        }
    }

    private void executeHit(Player player) {
        Card card = playingDeck.drawCard();
        player.receiveCard(card);
        OutputView.printPlayerCard(player);
    }

    private void runDealerTurn(Dealer dealer) {
        if (dealer.canHit()) {
            Card card = playingDeck.drawCard();
            dealer.receiveCard(card);
            OutputView.printDealerHit();
        }
    }

    private void calculateResult(Dealer dealer, List<Player> players) {
        GameResult gameResult = new GameResult();

        for (Player player : players) {
            GameRule.decideWinner(dealer, player, gameResult);
        }

        OutputView.printCardScore(dealer, players);
        OutputView.printResult(gameResult);
    }
}
