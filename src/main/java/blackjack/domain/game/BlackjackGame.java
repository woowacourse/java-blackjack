package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;

public class BlackjackGame {
    private final Deck deck;
    private final Players players;

    public BlackjackGame(Deck deck, Players players) {
        this.deck = deck;
        this.players = players;
    }

    public void giveStartingCards() {
        players.getPlayers().forEach(this::giveStartingCards);
    }

    private void giveStartingCards(Participant participant) {
        List<Card> cards = deck.takeStartingCards();
        cards.forEach(participant::takeCard);
    }

    public void giveMoreCard(Participant participant) {
        Card card = deck.takeSingleCard();
        participant.takeCard(card);
    }

    public Players getParticipants() {
        return players;
    }
}
