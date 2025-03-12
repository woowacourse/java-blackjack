package blackjack.controller;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Result;
import blackjack.factory.DeckGenerator;
import java.util.List;

public class BlackjackProcessManager {

    private static final int STARTING_CARD_SIZE = 2;
    private static final int ADDITIONAL_CARD_SIZE = 1;

    private final DeckGenerator deckGenerator;

    public BlackjackProcessManager(DeckGenerator deckGenerator) {
        this.deckGenerator = deckGenerator;
    }

    public Deck generateDeck() {
        return new Deck(deckGenerator.generate());
    }

    public Players generatePlayers(List<String> names) {
        List<Player> players = names.stream()
                .map(name -> new Player(name, new Hand()))
                .toList();

        return Players.from(players);
    }

    public Dealer generateDealer() {
        return new Dealer(new Hand());
    }

    public void giveStartingCardsFor(Deck deck, Participant participant) {
        List<Card> cards = deck.takeCards(STARTING_CARD_SIZE);

        cards.forEach(participant::takeCard);
    }

    public void giveCard(Deck deck, Participant participant) {
        List<Card> cards = deck.takeCards(ADDITIONAL_CARD_SIZE);

        cards.forEach(participant::takeCard);
    }

    public Result calculateCardResult(Players players, Dealer dealer) {
        return Result.of(players, dealer);
    }
}
