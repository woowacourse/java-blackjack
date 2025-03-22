package domain;

import domain.card.Card;
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

    public void initializeGame(final List<String> playerNames) {
        deck.shuffleCards();
        giveCardsToPlayers(playerNames);
        giveCardsToDealer();
    }

    private void giveCardsToDealer() {
        dealer.giveCard(deck.pollCard());
        dealer.giveCard(deck.pollCard());
    }

    public int promptDealerHit() {
        int count = 0;
        while (dealer.canReceiveCard()) {
            dealer.giveCard(deck.pollCard());
            count++;
        }
        return count;
    }

    public List<Card> getPlayerCardsByName(final String playerName) {
        return playerGroup.getCardsByName(playerName);
    }

    public boolean shouldContinuePlayerHit(final String playerName, final AnswerCommand answerCommand) {
        final boolean wantsToHit = answerCommand.isYes();
        if (wantsToHit) {
            playerGroup.giveCardByName(playerName, deck.pollCard());
        }
        return wantsToHit;
    }

    public boolean canPlayerReceiveCard(final String playerName) {
        return playerGroup.canPlayerReceiveCard(playerName);
    }

    public Map<String, Double> calculatePlayerBettingAmountOfReturn() {
        return playerGroup.calculatePlayersGameResult(dealer)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> playerGroup.calculateBettingOfReturn(entry, dealer),
                        (newValue, oldValue) -> newValue
                ));
    }
    public double calculateDealerBettingAmountOfReturn() {
        return dealer.calculateBettingAmountOfReturn(calculatePlayerBettingAmountOfReturn());
    }

    private void giveCardsToPlayers(final List<String> playerNames) {
        for (String playerName : playerNames) {
            playerGroup.giveCardByName(playerName, deck.pollCard());
            playerGroup.giveCardByName(playerName, deck.pollCard());
        }
    }
}
