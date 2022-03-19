package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPack;
import blackjack.domain.gamer.role.Dealer;
import blackjack.domain.gamer.role.Player;
import blackjack.domain.gamer.role.Role;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.Match;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.ProfitResult;

public class Gamers {
    private final Dealer dealer;
    private final PlayerGroup playerGroup;

    public Gamers(Dealer dealer, PlayerGroup playerGroup) {
        this.dealer = dealer;
        this.playerGroup = playerGroup;
    }

    public void addInitialCards(CardPack cardPack) {
        playerGroup.addCard(cardPack);
        playerGroup.addCard(cardPack);
        addInitialDealerCards(cardPack);
    }

    private void addInitialDealerCards(CardPack cardPack) {
        Card card = cardPack.pickOne();
        card.close();
        dealer.addCard(card);
        dealer.addCard(cardPack.pickOne());
    }

    public int addCardsToDealer(CardPack cardPack) {
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
        Map<String, Match> playerResults = getPlayerResults();
        PlayerResult playerResult = new PlayerResult(playerResults);

        Collection<Match> playerMatches = playerResults.values();
        Map<Match, Integer> dealerMatches = initializeDealerResults(playerMatches);
        DealerResult dealerResult = new DealerResult(dealer.getName(), dealerMatches);

        return new GameResult(dealerResult, playerResult);
    }

    private Map<String, Match> getPlayerResults() {
        Map<Player, Match> playerResult = playerGroup.getPlayerResult(dealer.getScore());
        Map<String, Match> playerResults = new LinkedHashMap<>();
        for (Map.Entry<Player, Match> entry : playerResult.entrySet()) {
            playerResults.put(entry.getKey().getName(), entry.getValue());
        }
        return playerResults;
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

    public List<Role> getPlayers() {
        List<Role> roles = new ArrayList<>();
        roles.add(dealer);
        playerGroup.addAllTo(roles);
        return Collections.unmodifiableList(roles);
    }

    public Dealer getDealer() {
        return dealer;
    }

    private int money(Player player, Match match) {
        return player.getProfitMoney(match);
    }

    public ProfitResult getProfitResult() {
        int dealerMoney = 0;

        Map<String, Integer> profitResult = new LinkedHashMap<>();
        Map<Player, Match> playerResult = playerGroup.getPlayerResult(dealer.getScore());
        profitResult.put(dealer.getName(), dealerMoney);
        for (Map.Entry<Player, Match> entry : playerResult.entrySet()) {
            int money = money(entry.getKey(), entry.getValue());
            profitResult.put(entry.getKey().getName(), money);
            dealerMoney -= money;
        }
        profitResult.replace(dealer.getName(), dealerMoney);
        return new ProfitResult(profitResult);
    }
}
