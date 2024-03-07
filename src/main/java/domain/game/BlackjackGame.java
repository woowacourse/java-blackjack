package domain.game;

import domain.card.Card;
import domain.card.DeckCards;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;
import strategy.CardGenerator;

public class BlackjackGame {

    private final List<Player> players;
    private final Dealer dealer;
    private final DeckCards deckCards;

    public BlackjackGame(List<Player> players, Dealer dealer, CardGenerator cardGenerator) {
        this.players = players;
        this.dealer = dealer;
        this.deckCards = DeckCards.from(cardGenerator);
    }

    public void distributeStartingCards() {
        for (Player player : players) {
            List<Card> cards = List.of(deckCards.draw(),
                deckCards.draw()); // TODO: draw 2개 가능하게 리팩토링
            player.receive(cards);
        }

        List<Card> cards = List.of(deckCards.draw(), deckCards.draw());
        dealer.receive(cards);
    }

    public void giveOneCard(Participant participant) {
        if (participant.isReceivable()) {
            participant.receive(deckCards.draw());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
