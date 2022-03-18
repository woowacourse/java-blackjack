package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.MatchResult;

public class Participants {

    private final Dealer dealer;
    private Players players;

    public Participants(final Deck deck) {
        this.dealer = Dealer.readyToPlay(deck);
    }

    public void registerPlayers(final List<String> playerNames, final Deck deck) {
        this.players = Players.readyToPlay(playerNames, deck);
    }

    public void playerBet(final String playerName, final int amount) {
        players.betAmount(playerName, amount);
    }

    public void playerDrawCard(final String playerName, final Deck deck, final boolean needToDrawCard) {
        players.drawCard(playerName, deck, needToDrawCard);
    }

    public boolean isPlayerPossibleToDrawCard(final String playerName) {
        return players.isPlayerPossibleToDrawCard(playerName);
    }

    public void dealerDrawCard(final Deck deck) {
        dealer.drawCard(deck);
    }

    public boolean isDealerPossibleToDrawCard() {
        return dealer.isPossibleToDrawCard();
    }

    public MatchResult calculateMatchResult() {
        return players.judgeMatchStatusOfPlayers(dealer);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players.getPlayers());
    }

    public List<Card> getPlayerCards(final String playerName) {
        return players.getPlayerCards(playerName);
    }

}
