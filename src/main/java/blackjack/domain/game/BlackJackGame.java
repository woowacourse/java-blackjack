package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Participants participants;
    private final Deck deck;

    private BlackJackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackJackGame create(Players players) {
        Participants participants = Participants.create(players);
        Deck deck = Deck.create(DeckFactory.createShuffledCard());

        return new BlackJackGame(participants, deck);
    }

    public void setUp() {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        for (Player player : players) {
            passCardTo(player);
            passCardTo(player);
        }
        passCardTo(dealer);
        passCardTo(dealer);
    }

    public Map<Player, Result> calculateResult() {
        Map<Player, Result> result = new HashMap<>();
        Dealer dealer = participants.getDealer();

        for (Player player : participants.getPlayers()) {
            result.put(player, Result.calculate(player, dealer));
        }
        return result;
    }

    public void passCardTo(Participant participant) {
        if (participant.canReceive()) {
            Card card = deck.draw();
            participant.addCard(card);
        }
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }
}
