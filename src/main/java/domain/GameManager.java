package domain;

import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.PlayerGroup;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class GameManager {

    private final Dealer dealer;
    private final PlayerGroup playerGroup;
    private final Deck deck;

    public GameManager(final Dealer dealer, final PlayerGroup playerGroup, final Deck deck) {
        this.dealer = dealer;
        this.playerGroup = playerGroup;
        this.deck = deck;
    }

    public void shuffleCards() {
        deck.shuffleCards();
    }

    public void giveOneCardToPlayerByName(final String playerName) {
        playerGroup.giveCardByName(playerName, deck.pollCard());
    }

    public void giveCardsToPlayers(final List<String> playerNames) {
        for (String playerName : playerNames) {
            playerGroup.giveCardByName(playerName, deck.pollCard());
            playerGroup.giveCardByName(playerName, deck.pollCard());
        }
    }

    public int giveCardsToDealer() {
        int count = 0;
        while (dealer.isLessThen(16)) {
            dealer.giveCard(deck.pollCard());
            count++;
        }
        return count;
    }

    public Map<String, Integer> calculatePlayerBattingAmountOfReturn(final Map<String, GameResult> playersGameResult) {
    public Map<String, Double> calculatePlayerBattingAmountOfReturn() {
        return playerGroup.calculatePlayersGameResult(dealer)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> playerGroup.calculateBettingOfReturn(entry, dealer),
                        (newValue, oldValue) -> newValue
                ));
    }
}
