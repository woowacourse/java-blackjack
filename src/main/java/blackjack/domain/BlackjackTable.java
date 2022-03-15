package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.domain.entry.Participants;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackTable {
    private final Deck deck;
    private final Participants participants;

    public BlackjackTable(List<String> names) {
        this.deck = Deck.of(Card.createDeck());
        this.participants = new Participants(createDealer(), toPlayers(names));
    }

    public void hit(Participant participant) {
        participant.addCard(deck.draw());
    }

    public boolean needMoreCardByDealer() {
        return participants.isHitDealer();
    }

    public void hitDealer() {
        participants.hitDealer(deck.draw());
    }

    public boolean canHit(Participant participant) {
        return participant.canHit();
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }

    public Map<PlayerOutcome, List<Player>> countGameResult() {
        return participants.getGameResults();
    }

    private Dealer createDealer() {
        return new Dealer(HoldCards.initTwoCards(deck.draw(), deck.draw()));
    }

    private List<Player> toPlayers(List<String> names) {
        return names.stream()
            .map(name -> new Player(name, HoldCards.initTwoCards(deck.draw(), deck.draw())))
            .collect(Collectors.toList());
    }
}
