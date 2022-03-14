package blackjack.model.player;

import blackjack.model.MatchResult;
import blackjack.model.card.CardDeck;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Gamers {
    private static final int START_CARD_COUNT = 2;
    private static final String HIT_SIGN = "y";

    private final List<Player> values;

    public Gamers(List<String> names) {
        this.values = names.stream()
                .map(Gamer::new)
                .collect(Collectors.toList());
    }

    public void giveCardsToGamer() {
        for (Player gamer : values) {
            giveCardsTo(gamer);
        }
    }

    private void giveCardsTo(Player gamer) {
        CardDeck deck = CardDeck.getInstance();
        for (int i = 0; i < START_CARD_COUNT; i++) {
            gamer.receive(deck.draw());
        }
    }

    public void hitOrStayToGamer(Predicate<String> predicate, Consumer<Player> consumer) {
        for (Player gamer : values) {
            hitOrStayTo(gamer, predicate, consumer);
        }
    }

    private void hitOrStayTo(Player gamer, Predicate<String> predicate, Consumer<Player> consumer) {
        CardDeck deck = CardDeck.getInstance();
        while (canHit(gamer) && isHitSign(gamer, predicate)) {
            gamer.receive(deck.draw());
            consumer.accept(gamer);
        }
    }

    private boolean canHit(Player gamer) {
        return !gamer.isBlackJack() && !gamer.isImpossibleHit();
    }

    private boolean isHitSign(Player gamer, Predicate<String> predicate) {
        return predicate.test(gamer.getName());
    }

    /*
    public Map<String, MatchResult> getMatchResult(Player dealer) {
        Map<String, MatchResult> gamerResults = new LinkedHashMap<>();
        for (Player gamer : values) {
            MatchResult result = match(dealer, gamer);
            gamerResults.put(gamer.getName(), result);
        }
        return gamerResults;
    }

    public Map<MatchResult, Integer> getDealerMatchResult(Player dealer) {
        Map<MatchResult, Integer> dealerMatchResults = new LinkedHashMap<>();
        initDealerMatchResults(dealerMatchResults);
        for (Player gamer : values) {
            MatchResult result = match(dealer, gamer);
            dealerMatchResults.merge(result, 1, Integer::sum);
        }
        return dealerMatchResults;
    }

    private void initDealerMatchResults(Map<MatchResult, Integer> dealerResults) {
        for (MatchResult matchResult : MatchResult.values()) {
            dealerResults.put(matchResult, 0);
        }
    }

    private MatchResult match(Player dealer, Player gamer) {
        return MatchResult.find(dealer, gamer);
    }
*/

    public List<Player> getValues() {
        return values;
    }
}

