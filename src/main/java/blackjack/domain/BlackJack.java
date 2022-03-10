package blackjack.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
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
        playerGroup.addTwoCards(cardPack);
        dealer.addTwoCards(cardPack.pickOne(), cardPack.pickOne());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void addCardTo(Player player) {
        player.addCard(cardPack.pickOne());
    }

    public GameResult getGameResult() {
        Map<String, Match> playerResults = playerGroup.getPlayerResult(dealer.getMaxCardGroupSum());
        PlayerResult playerResult = new PlayerResult(playerResults);

        Collection<Match> playerMatches = playerResults.values();
        Map<Match, Integer> dealerMatches = initializeDealerResults(playerMatches);
        DealerResult dealerResult = new DealerResult(dealer.getName(), dealerMatches);

        return new GameResult(dealerResult, playerResult);
    }

    private Map<Match, Integer> initializeDealerResults(Collection<Match> matches) {
        Map<Match, Integer> matchResults = new EnumMap<>(Match.class);
        for (Match match : Match.values()) {
            matchResults.put(match, countMatch(matches, match));
        }
        return matchResults;
    }

    private int countMatch(Collection<Match> matches, Match type) {
        return (int) matches.stream().filter(value -> value == type.getOpposite()).count();
    }

    public List<Gamer> getGamersCards() {
        List<Gamer> gamers = new ArrayList<>();
        gamers.add(dealer);
        playerGroup.addAllTo(gamers);
        return gamers;
    }

    public int addCardToDealer() {
        int addedCardsCount = 0;
        while (dealer.isAddable()) {
            dealer.addCard(cardPack.pickOne());
            addedCardsCount++;
        }

        return addedCardsCount;
    }

    public void openDealerCards() {
        dealer.openAllCards();
    }
}
