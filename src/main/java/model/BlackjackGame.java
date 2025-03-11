package model;

import model.card.Deck;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
    private final static Deck deck = Deck.of();

    public void dealInitially(Players players, Dealer dealer) {
        players.dealInitialCards(deck);
        dealer.dealInitialCards(deck);
    }

    public void runPlayerTurn(Players players) {
        for (Player player : players.getPlayers()) {
            chooseAtOnePlayerTurn(player);
        }
    }

    public void runDealerTurn (Dealer dealer) {
        while (dealer.checkScoreUnderSixteen()) {
            OutputView.printDealerDealResult();
            dealer.receiveCard(deck.pick());
        }
    }

    private void chooseAtOnePlayerTurn(Player player) {
        boolean flag = true;
        while (flag == InputView.readHit(player)) {
            player.receiveCard(deck.pick());
            OutputView.printHitResult(player);
            if (player.isBust()) {
                break;
            }
        }
    }
}
