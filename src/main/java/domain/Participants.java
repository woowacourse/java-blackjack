package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vo.Bet;
import vo.Name;

public class Participants {
    private final List<User> participants;

    public Participants(Map<Name, Bet> bets) {
        this.participants = new ArrayList<>();
        saveUsers(bets);
    }

    private void saveUsers(Map<Name, Bet> bets) {
        bets.forEach((name, bet) -> {
            participants.add(new User(name, bet));
        });
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(participants);
    }

    public List<String> getParticipantNames() {
        return participants.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public void dealCards(Deck deck) {
        for (User user : participants) {
            user.receiveCard(deck.dealCard());
        }
    }

    public void dealCard(Deck deck, User user) {
        user.receiveCard(deck.dealCard());
    }
}
