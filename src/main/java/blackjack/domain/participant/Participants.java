package blackjack.domain.participant;

import blackjack.domain.DistributeResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;

    public Participants() {
        this.participants = new ArrayList<>();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public void addDealer() {
        this.participants.add(new Dealer());
    }

    public void addUsers(String[] userName) {
        List<User> users = Arrays.stream(userName)
                .map(User :: new)
                .collect(Collectors.toList());
        this.participants.addAll(users);
    }

    public List<User> getUsers() {
        return participants.stream()
                .filter(Participant::isUser)
                .map(User.class::cast)
                .collect(Collectors.toList());
    }

    public List<String> getUserNames() { // user객체 안으로 이동
        return getUsers().stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return (Dealer) participants.stream()
                .filter(Participant::isDealer)
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    public void distributeCard(Deck deck) {
        for (Participant participant : participants) {
            participant.receiveCard(deck.drawCard());
        }
    }

    public List<DistributeResult> getDistributeResult() {
        return participants.stream()
                .map(DistributeResult::new)
                .collect(Collectors.toList());
    }
}
