package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.player.User;
import domain.stats.MatchResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class BlackjackManager {
    private final Players players;
    private final Deck deck;

    public BlackjackManager(Players players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public void distributeInitialCards() {
        players.distributeInitialCards(deck);
    }

    public void openInitialCards() {
        players.openInitialCards();
    }

    public void addMoreCardsToUsers(Function<String, Boolean> wantMoreCard,
                                    BiConsumer<String, List<Card>> callback) {
        for (Player user : getUsers()) {
            addMorCardsToUser(user, wantMoreCard, callback);
        }
    }

    private void addMorCardsToUser(Player user,
                                   Function<String, Boolean> wantMoreCard,
                                   BiConsumer<String, List<Card>> callback) {
        while (!user.isBust() && wantMoreCard.apply(user.getName())) {
            user.drawOneCard(deck);
            callback.accept(user.getName(), user.getCards());
        }
    }

    public boolean addCardToDealerIfLowSum() {
        return getDealer().drawOneCardIfLowScore(deck);
    }

    public Map<Player, Integer> computePlayerSum() {
        Map<Player, Integer> results = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            results.put(player, player.computeOptimalSum());
        }
        return results;
    }

    public Map<MatchResult, Integer> computeDealerMatchResultCount() {
        Map<Player, MatchResult> userNameAndMatchResult = computeUsersMatchResult();
        Map<MatchResult, Integer> matchResultCount = new LinkedHashMap<>();
        MatchResult.sortedValues().forEach(matchResult -> matchResultCount.put(matchResult, 0));

        userNameAndMatchResult.forEach((key, value) -> matchResultCount.put(MatchResult.inverse(value),
                matchResultCount.getOrDefault(MatchResult.inverse(value), 0) + 1));
        return matchResultCount;
    }

    public Map<Player, MatchResult> computeUsersMatchResult() {
        Dealer dealer = getDealer();
        List<User> users = getUsers();

        Map<Player, MatchResult> results = new LinkedHashMap<>();
        for (Player user : users) {
            MatchResult matchResult = computeUserMatchResult(dealer, user);
            results.put(user, matchResult);
        }
        return results;
    }

    private MatchResult computeUserMatchResult(Dealer dealer, Player user) {
        if (user.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust()) {
            return MatchResult.WIN;
        }
        return MatchResult.compareBySum(user.computeOptimalSum(),
                dealer.computeOptimalSum());
    }

    public String getDealerName() {
        return getDealer().getName();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }

    public List<User> getUsers() {
        return players.getUsers();
    }
}
