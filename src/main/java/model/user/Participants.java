package model.user;

import model.card.Deck;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

public class Participants {

    private final List<Player> players;
    private final Dealer dealer;

    private Participants(final List<Player> players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static Participants from(final List<Player> players) {
        return new Participants(players, new Dealer());
    }

    public List<Score> getPlayersFinalResult() {
        return createFinalResultWithoutDealer(dealer.getCardTotalValue());
    }

    private List<Score> createFinalResultWithoutDealer(final int dealerTotalValue) {
        return players.stream()
                .map(player -> player.judgeResult(dealerTotalValue))
                .collect(toUnmodifiableList());
    }

    public void receiveInitialCards(final Deck deck) {
        players.forEach(player -> player.receiveInitialCards(deck));
        dealer.receiveInitialCards(deck);
        ifPlayerIsBlackJackReceiveMoney();
    }

    public void ifPlayerIsBlackJackReceiveMoney() {
        boolean dealerIsBlackjack = dealer.isBlackJack();
        for (Player player : players) {
            if (canReceiveTwiceBattingMoney(dealerIsBlackjack, player)) {
                player.receiveMoney();
            }
        }
    }

    private static boolean canReceiveTwiceBattingMoney(boolean dealerIsBlackjack, Player player) {
        return player.isBlackJack() && !dealerIsBlackjack;
    }

    public int getDealerProfit() {
        List<Score> finalResult = getPlayersFinalResult();
        int dealerMoney = 0;
        for (int i = 0, size = finalResult.size(); i < size; i++) {
            Player player = players.get(i);
            Score score = finalResult.get(i);
            dealerMoney = calculateUserMoney(dealerMoney, player, score);
        }
        return dealerMoney;
    }

    private static int calculateUserMoney(int dealerMoney, Player player, Score score) {
        if (score == Score.WIN) {
            dealerMoney -= player.getMoney();
        }
        if (score == Score.LOSE){
            dealerMoney += player.getMoney();
            player.lose();
        }
        return dealerMoney;
    }


    public List<Player> getPlayers() {
        return this.players;
    }

    public Dealer getDealer() {
        return this.dealer;
    }
}
