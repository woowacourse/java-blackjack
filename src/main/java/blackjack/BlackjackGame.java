package blackjack;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.MatchResult;

public class BlackjackGame {

    private final Deck deck;
    private final Dealer dealer;
    private Players players;

    public BlackjackGame(final DeckGenerator deckGenerator) {
        this.deck = Deck.generate(deckGenerator);
        this.dealer = Dealer.readyToPlay(deck);
    }

    public void registerPlayers(final List<String> playerNames) {
        this.players = Players.readyToPlay(playerNames, deck);
    }

    public void playerBet(final String playerName, final int amount) {
        players.betAmount(playerName, amount);
    }

    public void playerDrawCard(final String playerName) {
        players.drawCard(playerName, deck);
    }

    public boolean isPlayerPossibleToDrawCard(final String playerName) {
        return players.isPlayerPossibleToDrawCard(playerName);
    }

    public boolean isDealerPossibleToDrawCard() {
        return dealer.isPossibleToDrawCard();
    }

    public void dealerDrawCard() {
        dealer.drawCard(deck);
    }

    public MatchResult calculateMatchResult() {
        return players.judgeWinners(dealer);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<Card> getPlayerCards(final String playerName) {
        return players.getPlayerCards(playerName);
    }

}
