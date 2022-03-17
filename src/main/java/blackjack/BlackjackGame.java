package blackjack;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.result.MatchResult;

public class BlackjackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackjackGame(final DeckGenerator deckGenerator) {
        this.deck = Deck.generate(deckGenerator);
        this.participants = new Participants(deck);
    }

    public void registerPlayers(final List<String> playerNames) {
        participants.registerPlayers(playerNames, deck);
    }

    public void playerBet(final String playerName, final int amount) {
        participants.playerBet(playerName, amount);
    }

    public void playerDrawCard(final String playerName) {
        participants.playerDrawCard(playerName, deck);
    }

    public boolean isPlayerPossibleToDrawCard(final String playerName) {
        return participants.isPlayerPossibleToDrawCard(playerName);
    }

    public boolean isDealerPossibleToDrawCard() {
        return participants.isDealerPossibleToDrawCard();
    }

    public void dealerDrawCard() {
        participants.dealerDrawCard(deck);
    }

    public MatchResult calculateMatchResult() {
        return participants.calculateMatchResult();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public String getDealerName() {
        return getDealer().getName();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public List<String> getPlayerNames() {
        return getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Card> getPlayerCards(final String playerName) {
        return participants.getPlayerCards(playerName);
    }

}
