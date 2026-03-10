package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private static final Integer MAX_PLAYER_COUNT = 16;

    private final List<User> participants;
    private final Dealer dealer;

    public Participants(String participantsName) {
        this.participants = new ArrayList<>();
        this.dealer = new Dealer();
        saveUsers(participantsName);
    }

    private void saveUsers(String participantsName) {
        parseName(participantsName)
                .forEach(name -> participants.add(new User(name.trim())));
    }

    private List<String> parseName(String participantsName) {
        List<String> parsedName = List.of(participantsName.split(","));
        validateParticipantsNumbers(parsedName);

        return parsedName;
    }

    private void validateParticipantsNumbers(List<String> parsedName) {
        if (parsedName.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 최대 참가 인원은 16명 이하여야 합니다.");
        }
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(participants);
    }

    public Dealer getDealer() {
        return dealer;
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
        dealer.receiveCard(deck.dealCard());
    }

    public void dealCard(Deck deck, int index) {
        participants.get(index)
                .receiveCard(deck.dealCard());
    }

    public Boolean determineDealerDealMore() {
        return dealer.determineDealerDealMore();
    }

    public void dealCardToDealer(Card card) {
        dealer.receiveCard(card);
    }
}