package service;

import java.util.List;

import domain.Deck;
import domain.Participants;
import domain.RandomShuffleStrategy;

public class SettingService {
    public Deck createDeck() {
        return Deck.from(new RandomShuffleStrategy());
    }

    public Participants createParticipants(List<String> names) {
        return Participants.from(names);
    }
}
