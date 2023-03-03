package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    private final Dealer dealer;
    private final Players players;
    private final CardDeck cardDeck;

    public BlackjackGame(Dealer dealer, Players players, CardDeck cardDeck) {
        this.dealer = dealer;
        this.players = players;
        this.cardDeck = cardDeck;
    }

    public void distributeInitialCard() {
        for (int i = 0; i < 2; i++) {
            distributeDealer();
            distributePlayers();
        }

        List<CardNumber> list = new ArrayList<>();
        list.add(CardNumber.ACE);
    }

    public void distributeDealer() {
        dealer.addCard(cardDeck.poll());
    }

    public void distributePlayers() {
        for (Player player : players.getPlayers()) {
            distributePlayer(player);
        }
    }

    public void distributePlayer(Player player) {
        player.addCard(cardDeck.poll());

        Map<String, CardNumber> map = new HashMap<>();
        map.put("a", CardNumber.ACE);

    }


    public Map<String, Result> getPlayersResult() {
        Map<String,Result> result = new HashMap<>();
        int delaerSum = dealer.getCardsSum();
        for (Player player : players.getPlayers()) {
            result.put(player.getName().getName(),isPlayerWin(delaerSum,player.getCardsSum()));
        }
        return result;
    }

    public Result isPlayerWin(int dealerSum, int playerSum) {
        if (playerSum > 21) {
            return Result.LOSE;
        }
        if (dealerSum > 21) {
            return Result.WIN;
        }
        if (dealerSum > playerSum) {
            return Result.LOSE;
        }
        if (dealerSum < playerSum) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

//    public Map<String,List<Integer>> calculateWinOrLose() {
//        Map<String,List<Integer>> result = new HashMap<>();
//        int dealerSum = dealer.getCardsSum();
//        for(Player player: players.getPlayers()){
//            int playerSum = player.getCardsSum();
//            if(playerSum > 21){
//                result.put(dealer.getName().getName(),List.of(1,0,0));
//            }
//        }
//    }


}
