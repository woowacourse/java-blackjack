package game;

import card.CardDeck;
import participant.Dealer;
import participant.Player;
import participant.Players;
import score.MatchResultType;
import score.MatchResultCaseType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJack {

    private static final int INITIAL_DEAL_COUNT = 2;
    private static final int ADDITIONAL_CARD_DEFAULT_COUNT = 1;

    private final Players players;
    private final Dealer dealer;
    private final CardDeck deck;

    public BlackJack(Players players, Dealer dealer, CardDeck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public void dealInitialCards() {
        dealCard(dealer, INITIAL_DEAL_COUNT);
        for (Player player : players.getPlayers()) {
            dealCard(player, INITIAL_DEAL_COUNT);
        }
    }

    public void dealCard(Player player, int amount) {
        for (int i = 0; i < amount; i++) {
            player.addCard(deck.pickCard());
            player.applyAceRule();
        }
    }


    public void dealCard(Dealer dealer, int amount) {
        for (int i = 0; i < amount; i++) {
            dealer.addCard(deck.pickCard());
            dealer.applyAceRule();
        }
    }

    public void receiveAdditionalCard(Player player) {
        dealCard(player, ADDITIONAL_CARD_DEFAULT_COUNT);
        player.applyAceRule();
    }

    public void receiveAdditionalCard(Dealer dealer) {
        dealCard(dealer, ADDITIONAL_CARD_DEFAULT_COUNT);
        dealer.applyAceRule();
    }

    public Map<MatchResultType, Integer> calculateMatchResult() {
        Map<MatchResultType, Integer> matchResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            MatchResultCaseType matchResultCaseType = createGameResult(player);
            List<MatchResultType> matches = matchResultCaseType.getMatches();
            MatchResultType dealerMatchResultType = matches.getFirst();
            MatchResultType playerMatchResultType = matches.getLast();
            updateDealerMatchResult(matchResult, dealerMatchResultType);
            player.updateResult(playerMatchResultType);
        }
        return matchResult;
    }

    private void updateDealerMatchResult(Map<MatchResultType, Integer> matchResult, MatchResultType dealerMatchResultType) {
        matchResult.computeIfAbsent(dealerMatchResultType, k -> 0);
        matchResult.put(dealerMatchResultType, matchResult.get(dealerMatchResultType) + 1);
    }

    private MatchResultCaseType createGameResult(Player player) {
        if (player.isBust()) {
            return MatchResultCaseType.WIN_LOSE;
        }
        if (dealer.isBust()) {
            return MatchResultCaseType.LOSE_WIN;
        }
        return MatchResultCaseType.of(dealer.getScore().compareTo(player.getScore()));

    }
}
