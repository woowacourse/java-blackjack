package service;

import domain.*;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static util.BlackJackConstant.INIT_HAND_SIZE;

public class BlackJackService {

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackJackService(Deck deck, Dealer dealer, Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public void initHand() {
        for (int i = 0; i < INIT_HAND_SIZE; i++) {
            dealer.hit(deck.drawCard());
        }

        for (Player player : players.getPlayers()) {
            for (int i = 0; i < INIT_HAND_SIZE; i++) {
                player.hit(deck.drawCard());
            }
        }
    }

    public Map<String, MatchResult> calculateResults() {
        Map<String, MatchResult> playerResults = new LinkedHashMap<>();

        for (Player player : players.getPlayers()) {
            if (handleBust(player, playerResults)) continue;
            compareScore(player, playerResults);
        }

        return playerResults;
    }

    private boolean handleBust(Player player, Map<String, MatchResult> playerResults) {
        if (player.getHand().isBust()) {
            playerResults.put(player.getName(), MatchResult.LOSE);
            return true;
        }

        if (dealer.getHand().isBust()) {
            playerResults.put(player.getName(), MatchResult.WIN);
            return true;
        }

        return false;
    }

    private void compareScore(Player player, Map<String, MatchResult> playerResults) {
        if (player.getHand().calculateSum() > dealer.getHand().calculateSum()) {
            playerResults.put(player.getName(), MatchResult.WIN);
            return;
        }

        if (handleDraw(player, playerResults)) return;

        if (player.getHand().calculateSum() < dealer.getHand().calculateSum()) {
            playerResults.put(player.getName(), MatchResult.LOSE);
        }
    }

    private boolean handleDraw(Player player, Map<String, MatchResult> playerResults) {
        if (player.getHand().calculateSum() == dealer.getHand().calculateSum()) {
            if (player.getHand().isBlackJack() && !dealer.getHand().isBlackJack()) {
                playerResults.put(player.getName(), MatchResult.WIN);
                return true;
            }
            if (!player.getHand().isBlackJack() && dealer.getHand().isBlackJack()) {
                playerResults.put(player.getName(), MatchResult.LOSE);
                return true;
            }
            if (!player.getHand().isBlackJack() && !dealer.getHand().isBlackJack()) {
                playerResults.put(player.getName(), MatchResult.DRAW);
                return true;
            }
        }

        return false;
    }

    public Map<MatchResult, Integer> calculateDealerResult(Map<String, MatchResult> playerResults) {
        Map<MatchResult, Integer> dealerResult = new EnumMap<>(MatchResult.class);

        for (MatchResult matchResult : playerResults.values()) {
            dealerResult.put(matchResult.exchange(), dealerResult.getOrDefault(matchResult.exchange(), 0) + 1);
        }

        return dealerResult;
    }
}
