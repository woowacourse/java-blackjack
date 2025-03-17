package controller;

import static view.InputView.getPlayerNamesInput;
import static view.OutputView.printDealerExtraCardsCount;
import static view.OutputView.printDistributeResult;
import static view.OutputView.printEveryOneCardsNamesWithTotal;
import static view.OutputView.printProfitPerParticipant;

import java.util.Map;
import model.casino.Accountant;
import model.casino.Deck;
import model.participants.Dealer;
import model.participants.Participants;
import model.participants.Player;
import model.participants.PlayerGroup;
import view.InputView;

public class BlackJack {

    public void play() {
        Participants participants = new Participants(getPlayerNamesInput());
        Deck deck = new Deck();
        Accountant accountant = new Accountant();

        preset(participants, accountant, deck);
        participants.progressGame(deck);
        printResult(participants, accountant);
    }

    private void preset(Participants participants, Accountant accountant, Deck deck) {
        accountBettingPrice(participants, accountant);
        deck.distributeCards(participants);
        printDistributeResult(participants);
    }

    private void accountBettingPrice(Participants participants, Accountant accountant) {
        Map<Player, Integer> bettingPriceForEachPlayer = InputView.inputBettingPrice(participants.getPlayerGroup());
        for (Map.Entry<Player, Integer> entry : bettingPriceForEachPlayer.entrySet()) {
            accountant.accountBettingPrice(entry.getKey(), entry.getValue());
        }
    }

    private void printResult(Participants participants, Accountant accountant) {
        PlayerGroup playerGroup = participants.getPlayerGroup();
        Dealer dealer = participants.getDealer();

        printDealerExtraCardsCount(dealer);
        printEveryOneCardsNamesWithTotal(playerGroup, dealer);
        Map<Player, Integer> profitPerParticipant = accountant.calculateProfit(playerGroup, dealer);
        printProfitPerParticipant(profitPerParticipant, dealer);
    }

}
