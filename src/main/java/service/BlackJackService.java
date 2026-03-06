package service;

import domain.*;

import java.util.HashMap;
import java.util.Map;

public class BlackJackService {

    private final static int INIT_HAND_VALUE = 2;

    private Deck deck;
    private Dealer dealer;
    private Players players;

    public BlackJackService(Deck deck, Dealer dealer, Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public void initHand() {
        for (int i = 0; i < INIT_HAND_VALUE; i++) {
            dealer.hit(deck.drawCard());
        }

        for (Player player : players.getPlayers()) {
            for (int i = 0; i < INIT_HAND_VALUE; i++) {
                player.hit(deck.drawCard());
            }
        }
    }

    public Map<String, MatchResult> calculateResults() {
        Map<String, MatchResult> playerResults = new HashMap<>();

        for (Player player : players.getPlayers()) {
            if (player.getHand().isBust()) {
                playerResults.put(player.getName(), MatchResult.LOSE);
                continue;
            }

            if (dealer.getHand().isBust()) {
                playerResults.put(player.getName(), MatchResult.WIN);
                continue;
            }

            if (player.getHand().calculateSum() > dealer.getHand().calculateSum()) {
                playerResults.put(player.getName(), MatchResult.WIN);
                continue;
            }

            if (player.getHand().calculateSum() == dealer.getHand().calculateSum()) {
                if (player.getHand().isBlackJack() && !dealer.getHand().isBlackJack()) {
                    playerResults.put(player.getName(), MatchResult.WIN);
                    continue;
                }
                if (!player.getHand().isBlackJack() && dealer.getHand().isBlackJack()) {
                    playerResults.put(player.getName(), MatchResult.LOSE);
                    continue;
                }
                if (!player.getHand().isBlackJack() && !dealer.getHand().isBlackJack()) {
                    playerResults.put(player.getName(), MatchResult.DRAW);
                    continue;
                }
            }

            if (player.getHand().calculateSum() < dealer.getHand().calculateSum()) playerResults.put(player.getName(), MatchResult.LOSE);
        }

        return playerResults;
    }
}
