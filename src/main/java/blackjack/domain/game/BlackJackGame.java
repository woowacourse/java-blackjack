package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerTurn;
import blackjack.domain.participant.Players;
import java.util.List;

public class BlackJackGame {

    private final Players players;
    private final Deck deck;

    public BlackJackGame(List<String> names) {
        this.players = Players.from(names);
        this.deck = Deck.createShuffledDeck();
    }

    public void drawStartCards() {
        players.drawStartCards(deck);
    }

    public void drawAdditionalCard(PlayerTurn playTurn) {
        players.play(playTurn, deck);
    }

    public boolean isDealerDrawable() {
        return players.isDealerDrawable();
    }

    public void drawDealerCard() {
        players.drawDealerCard(deck);
    }

    public List<String> getPlayerNames() {
        return players.getPlayers().stream()
                .map(Player::getName)
                .toList();
    }

    public List<PlayerResult> getAllGameResults() {
        return players.getAllResult();
    }

    public Players getPlayers() {
        return this.players;
    }

    public Dealer getDealer() {
        return players.getDealer();
    }
}
