package domain;

import static domain.constant.GameRule.INIT_CARD_COUNT;

import domain.card.Card;
import domain.card.Deck;
import domain.enums.Result;
import domain.participant.BetAmounts;
import domain.participant.Participants;
import domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private final Participants participants;
    private final BetAmounts betAmounts;

    public Game(Players players, BetAmounts betAmounts, Deck deck) {
        this.participants = new Participants(players);
        this.betAmounts = betAmounts;
        initializeGame(deck);
    }

    private void initializeGame(Deck deck) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            participants.distributeCardForAllParticipants(deck);
        }
    }

    public boolean isPlayerEnd(String name, boolean wantHit) {
        return participants.isPlayerEnd(name, wantHit);
    }

    public boolean isDealerEnd() {
        return participants.isDealerEnd();
    }

    public void playerHit(String name, Deck deck, boolean isPlayerEnd) {
        participants.playerHit(name, deck, isPlayerEnd);
    }

    public void dealerHit(Deck deck) {
        participants.dealerHit(deck);
    }

    public int calculatePlayerScore(String name) {
        return participants.calculatePlayerScore(name);
    }

    public int calculateDealerScore() {
        return participants.calculateDealerScore();
    }

    public Map<String, Integer> calculatePlayerProfits() {
        Map<String, Integer> profits = new LinkedHashMap<>();
        for (String name : participants.getAllPlayerNames()) {
            Result result = participants.calculatePlayerResult(name);
            int profit = betAmounts.calculatePlayerProfit(name, result);
            profits.put(name, profit);
        }
        return profits;
    }

    public int calculateDealerProfit() {
        int dealerProfit = 0;
        for (int profit : calculatePlayerProfits().values()) {
            dealerProfit += -profit;
        }
        return dealerProfit;
    }

    public List<Card> getPlayerCards(String name) {
        return participants.getPlayerCards(name);
    }

    public List<Card> getDealerCards() {
        return participants.getDealerCards();
    }

    public List<String> getAllPlayerNames() {
        return participants.getAllPlayerNames();
    }
}
