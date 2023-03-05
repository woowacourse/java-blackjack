package service;

import java.util.List;

import domain.card.Deck;
import domain.people.Participants;
import domain.card.RandomShuffleStrategy;

public class SettingService {
    public Deck createDeck() {
        return Deck.from(new RandomShuffleStrategy());
    }

    public Participants createParticipants(List<String> names) {
        return Participants.from(names);
    }
}
