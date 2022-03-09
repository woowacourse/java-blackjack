package blackjack.domain;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

public class BlackJack {
    private final Dealer dealer;
    private final PlayerGroup playerGroup;
    private final CardPack cardPack;

    public BlackJack(PlayerGroup playerGroup) {
        this.playerGroup = playerGroup;
        this.dealer = new Dealer();
        this.cardPack = new CardPack();
    }

    public void divideCards() {
        playerGroup.addTwoCards(cardPack.pickOne(), cardPack.pickOne());
        dealer.addTwoCards(cardPack.pickOne(), cardPack.pickOne());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void addCard(Player player) {
        player.addCard(cardPack.pickOne());
    }

    public GameResult getGameResult() {
        Map<String, Match> playerResults = playerGroup.getPlayerResult(dealer.getCardGroupSum());
        Collection<Match> playerMatches = playerResults.values();
        Map<Match, Integer> dealerMatches = initializeMatchResults(playerMatches);
        DealerResult dealerResult = new DealerResult(dealerMatches);
        return new GameResult(dealerResult);
    }

    private Map<Match, Integer> initializeMatchResults(Collection<Match> matches) {
        Map<Match, Integer> matchResults = new EnumMap<>(Match.class);
        for (Match match : Match.values()) {
            matchResults.put(match, countMatch(matches, match));
        }
        return matchResults;
    }

    private int countMatch(Collection<Match> matches, Match type) {
        return (int) matches.stream().filter(value -> value == type).count();
    }
}
