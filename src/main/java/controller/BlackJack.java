package controller;

import static view.InputView.accountBettingPrice;
import static view.InputView.getContinueOrNot;
import static view.InputView.getPlayerNamesInput;
import static view.OutputView.printBust;
import static view.OutputView.printDealerExtraCardsCount;
import static view.OutputView.printDistributeResult;
import static view.OutputView.printEveryOneCardsNamesWithTotal;
import static view.OutputView.printHandCardsNames;
import static view.OutputView.printProfitPerParticipant;

import domain.Accountant;
import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.Players;
import domain.WinLossResult;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJack {

    private static final String NO = "n";

    public void play() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        Accountant accountant = new Accountant();
        Players players = preset(dealer, deck, accountant);
        playersTurn(players, deck);
        dealersTurn(dealer, deck);
        printResult(players, dealer, accountant);
    }

    private Players preset(Dealer dealer, Deck deck, Accountant accountant) {
        Players players = new Players(getPlayerNamesInput());
        accountBettingPrice(players, accountant);
        deck.distributeCards(dealer, players);
        printDistributeResult(players, dealer);
        return players;
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

    private void printResult(Players players, Dealer dealer, Accountant accountant) {
        printDealerExtraCardsCount(dealer);
        printEveryOneCardsNamesWithTotal(players, dealer);
        Map<Player, Integer> profitPerParticipant = accountant.calculateProfit(players, dealer);
        printProfitPerParticipant(profitPerParticipant, dealer);
    }

}
