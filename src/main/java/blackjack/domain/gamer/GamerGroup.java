package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPack;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.Match;
import blackjack.domain.result.PlayerResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class GamerGroup {
    private static final int NUMBER_OF_INITIAL_CARDS = 2;

    private final Dealer dealer;
    private final PlayerGroup playerGroup;

    public GamerGroup(Dealer dealer, PlayerGroup playerGroup) {
        this.dealer = dealer;
        this.playerGroup = playerGroup;
    }

    public void addInitialCards(CardPack cardPack) {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARDS; i++) {
            playerGroup.addCardToAllPlayers(cardPack);
        }
        addInitialDealerCards(cardPack);
    }

    private void addInitialDealerCards(CardPack cardPack) {
        Card card = cardPack.pickOne();
        card.close();
        dealer.addCard(card);
        dealer.addCard(cardPack.pickOne());
    }

    public int addCardToDealer(CardPack cardPack) {
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
        return Collections.unmodifiableMap(matchResults);
    }

    private int countMatch(Collection<Match> matches, Match type) {
        return (int) matches.stream().filter(value -> value == type.getOpposite()).count();
    }

    public List<Gamer> getGamers() {
        List<Gamer> gamers = new ArrayList<>();
        gamers.add(dealer);
        gamers.addAll(playerGroup.getPlayers());
        return Collections.unmodifiableList(gamers);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
