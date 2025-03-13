package domain;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackJack {
    private final Players players;
    private final Dealer dealer;

    public BlackJack(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void hitCardsToParticipant(Deck cardDeck) {
        players.hitCards(cardDeck);
        dealer.addCard(cardDeck.hitCard());
        dealer.addCard(cardDeck.hitCard());
    }

    public void drawPlayers(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck, Deck deck) {
        players.draw(answer, playerDeck, deck);
    }

    public void drawDealer(Deck cardDeck) {
        dealer.draw(cardDeck.hitCard());
    }

    public Map<Player, MatchResult> calculatePlayerResult() {
        return players.calculateWinner(dealer.calculateSum());
    }
}
