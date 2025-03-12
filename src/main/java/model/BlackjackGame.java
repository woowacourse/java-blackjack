package model;

import model.card.Deck;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;
import model.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
    private static final int INITIAL_DEAL_CARD_COUNT = 2;
    private final static Deck deck = Deck.of();

    public void dealInitially(Players players, Dealer dealer) {
        dealInitialCardsToAllPlayers(players);
        dealInitialCardsToParticipant(dealer);
    }

    public void runPlayerTurn(Players players) {
        for (Player player : players.getPlayers()) {
            chooseAtOnePlayerTurn(player);
        }
    }

    public void runDealerTurn(Dealer dealer) {
        while (dealer.checkScoreUnderSixteen()) {
            OutputView.printDealerDealResult();
            dealer.receiveCard(deck.pick());
        }
    }

    private void dealInitialCardsToAllPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            dealInitialCardsToParticipant(player);
        }
    }

    private void dealInitialCardsToParticipant(Participant participant) {
        for (int i = 0; i < INITIAL_DEAL_CARD_COUNT; i++) {
            participant.receiveCard(deck.pick());
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
