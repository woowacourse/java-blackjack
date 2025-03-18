package blackjack;

import card.Card;
import card.Deck;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import player.Dealer;
import player.Participant;
import player.Participants;

public class Blackjack {
    private final Participants participants;
    private final Dealer dealer;

    public Blackjack(Participants participants, Deck deck) {
        this.participants = participants;
        this.dealer = new Dealer(deck);
    }

    public Blackjack(List<String> names, Deck deck) {
        this.participants = new Participants(createParticipants(names));
        this.dealer = new Dealer(deck);
    }

    public void distributeInitialCards() {
        dealer.receiveInitialCards();
        participants.distributeInitialCards(getDeck());
    }

    public Map<String, List<Card>> openParticipantsInitialCards() {
        return participants.openInitialCards();
    }

    public List<Card> openDealerInitialCards() {
        return dealer.openInitialCards();
    }

    public void addCardToParticipant(Participant participant) {
        participant.drawOneCard(getDeck());
    }

    public boolean addCardToDealerIfLowScore() {
        return dealer.drawOneCardIfLowScore();
    }

    public Map<String, Integer> getNameAndSumOfAllPlayers() {
        return participants.mapToNameAndSum();
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }

    private List<Participant> createParticipants(List<String> names) {
        return names.stream()
                .map(Participant::new)
                .collect(Collectors.toList());
    }

    private Deck getDeck() {
        return dealer.getDeck();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
