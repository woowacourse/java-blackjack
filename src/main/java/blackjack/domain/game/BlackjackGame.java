package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;

public class BlackjackGame {
    private final Deck deck;
    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(Deck deck, Players players, Dealer dealer) {
        this.deck = deck;
        this.players = players;
        this.dealer = dealer;
    }

    public void giveStartingCards() {
        players.getPlayers().forEach(this::giveStartingCards);
        giveStartingCards(dealer);
    }

    private void giveStartingCards(Participant participant) {
        List<Card> cards = deck.takeStartingCards();
        cards.forEach(participant::takeCard);
    }

    public void giveMoreCard(Participant participant) {
        Card card = deck.takeSingleCard();
        participant.takeCard(card);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
