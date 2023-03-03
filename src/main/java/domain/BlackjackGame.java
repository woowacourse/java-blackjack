package domain;

import java.util.*;

public class BlackjackGame {
    public final static int BLACK_JACK = 21;
    private final static int INITIAL_CARD_SET = 2;
    private final Dealer dealer;
    private final Players players;
    private final CardDeck cardDeck;

    public BlackjackGame(Dealer dealer, Players players, CardDeck cardDeck) {
        this.dealer = dealer;
        this.players = players;
        this.cardDeck = cardDeck;
    }

    public void shuffleCardDeck() {
        cardDeck.shuffle();
    }

    public void distributeInitialCard() {
        for (int i = 0; i < INITIAL_CARD_SET; i++) {
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
    }

    public Map<String, List<Result>> getDealerResult() {
        Map<String, Result> playerResult = getPlayersResult();
        Map<String, List<Result>> dealerResult = new LinkedHashMap<>();
        List<Result> dealerResults = new ArrayList<>();
        for (String name : playerResult.keySet()) {
            Result result = playerResult.get(name);
            dealerResults.add(result);
        }
        dealerResult.put(dealer.getName().getName(), dealerResults);
        return dealerResult;
    }

    public Map<String, Result> getPlayersResult() {
        Map<String, Result> result = new LinkedHashMap<>();
        int dealer = this.dealer.getCardsSum();
        for (Player player : players.getPlayers()) {
            result.put(player.getName().getName(), isPlayerWin(dealer, player.getCardsSum()));
        }
        return result;
    }

    public Result isPlayerWin(int dealerSum, int playerSum) {
        if (playerSum > BLACK_JACK) {
            return Result.LOSE;
        }
        if (dealerSum > BLACK_JACK) {
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

}
