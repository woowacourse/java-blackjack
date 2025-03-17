package controller;

import static view.InputView.getContinueOrNot;
import static view.InputView.getPlayerNamesInput;
import static view.OutputView.printBust;
import static view.OutputView.printDealerExtraCardsCount;
import static view.OutputView.printDistributeResult;
import static view.OutputView.printEveryOneCardsNamesWithTotal;
import static view.OutputView.printHandCardsNames;
import static view.OutputView.printProfitPerParticipant;

import java.util.Map;
import model.Accountant;
import model.Dealer;
import model.Deck;
import model.HitOrHoldPolicy;
import model.Participants;
import model.Player;
import model.Players;
import view.InputView;

public class BlackJack {

    private static final String NO = "n";

    public void play() {
        Participants participants = new Participants(getPlayerNamesInput());
        Deck deck = new Deck();
        Accountant accountant = new Accountant();

        preset(participants, accountant, deck);
        playersTurn(participants.getPlayers(), deck);
        dealersTurn(participants.getDealer(), deck);
        printResult(participants, accountant);
    }

    private void preset(Participants participants, Accountant accountant, Deck deck) {
        accountBettingPrice(participants, accountant);
        deck.distributeCards(participants);
        printDistributeResult(participants);
    }

    private void accountBettingPrice(Participants participants, Accountant accountant) {
        Map<Player, Integer> bettingPriceForPlayer = InputView.inputBettingPrice(participants.getPlayers());
        for (Map.Entry<Player, Integer> entry : bettingPriceForPlayer.entrySet()) {
            accountant.accountBettingPrice(entry.getKey(), entry.getValue());
        }
    }

    private void playersTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            bustCheckOfHitOrHold(player, deck, new HitOrHoldPolicy() {
                @Override
                public boolean hold() {
                    return getContinueOrNot(player).equalsIgnoreCase(NO);
                }
            });
        }
    }

    private void bustCheckOfHitOrHold(Player player, Deck deck, HitOrHoldPolicy hitOrHoldPolicy) {
        boolean isAlive = player.resolveBust();
        while (isAlive) {
            if (hitOrHoldPolicy.hold()) {
                return;
            }
            isAlive = bustWithDrawOneMoreCard(player, deck);
        }
        player.setHandTotalToZero();
        printBust();
    }

    private boolean bustWithDrawOneMoreCard(Player player, Deck deck) {
        player.addCard(deck.draw());
        if (player.isNotDealer()) {
            printHandCardsNames(player);
        }
        return player.resolveBust();
    }

    public void dealersTurn(Dealer dealer, Deck deck) {
        bustCheckOfHitOrHold(dealer, deck, new HitOrHoldPolicy() {
            @Override
            public boolean hold() {
                return dealer.isOverThreshold();
            }
        });
    }

    private void printResult(Participants participants, Accountant accountant) {
        Players players = participants.getPlayers();
        Dealer dealer = participants.getDealer();

        printDealerExtraCardsCount(dealer);
        printEveryOneCardsNamesWithTotal(players, dealer);
        Map<Player, Integer> profitPerParticipant = accountant.calculateProfit(players, dealer);
        printProfitPerParticipant(profitPerParticipant, dealer);
    }

}
