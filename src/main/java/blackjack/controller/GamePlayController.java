package blackjack.controller;

import blackjack.model.DeckGenerator;
import blackjack.model.GameRule;
import blackjack.model.PlayingDeck;
import blackjack.model.card.Card;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Player;
import blackjack.view.InputView;
import java.util.List;

public class GamePlayController {

    private final PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck());

    public void playBlackJack(Dealer dealer, List<Player> players) {
        initialDraw(dealer, players);
        runPlayerTurn(players);
        runDealerTurn(dealer);
        calculateResult(dealer, players);
    }

    private void initialDraw(Dealer dealer, List<Player> players) {
        drawCardToDealer(dealer);
        drawCardToPlayer(players);
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
            while (player.canHit()) {
                if (InputView.askPlayerForCard(player)) {
                    Card card = playingDeck.drawCard();
                    player.receiveCard(card);
                }
            }
        }
    }

    private void runDealerTurn(Dealer dealer) {
        if (dealer.canHit()) {
            Card card = playingDeck.drawCard();
            dealer.receiveCard(card);
        }
    }

    private void calculateResult(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            GameRule.selectWinner(dealer, player);
        }
    }
}
