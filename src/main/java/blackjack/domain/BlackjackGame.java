package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.card.HoldCards.INIT_CARD_SIZE;

public class BlackjackGame {
    public static final int BLACKJACK_NUMBER = 21;

    private final Deck deck;
    private final GameParticipants gameParticipants;

    public BlackjackGame(Map<PlayerName, Integer> names) {
        this.deck = Deck.of(Card.createDeck());
        this.gameParticipants = new GameParticipants(createDealer(), toPlayers(names));
    }

    public void hit(PlayerName name) {
        Participant participant = find(name);
        participant.putCard(deck.draw());
    }

    public Participant find(PlayerName playerName) {
        return gameParticipants.find(playerName.getValue());
    }

    public boolean canDealerHit() {
        return gameParticipants.getDealer().canHit();
    }

    public boolean canHit(Command command, PlayerName playerName) {
        return command.isHit() && find(playerName).canHit();
    }

    public Map<Participant, Integer> getCardResult() {
        return gameParticipants.getAllParticipants().stream()
                .collect(Collectors.toMap(participant -> participant, Participant::calculateCardsSum));
    }

    public Map<Participant, Integer> getBettingResult() {
        return gameParticipants.calculateBettingResult();
    }

    public List<Participant> getParticipant() {
        return gameParticipants.getAllParticipants();
    }

    private Dealer createDealer() {
        return new Dealer(HoldCards.init(deck.drawCards(INIT_CARD_SIZE)));
    }

    private Players toPlayers(Map<PlayerName, Integer> names) {
        return new Players(names.entrySet().stream()
                .map(entry -> new Player(entry.getKey(), entry.getValue(), HoldCards.init(deck.drawCards(INIT_CARD_SIZE))))
                .collect(Collectors.toList()));
    }
}
