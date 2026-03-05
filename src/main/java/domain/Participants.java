package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {
    private static final Integer MAXIMUM_NUMBER_OF_PARTICIPANTS = 16;

    private List<User> participants;
    private Dealer dealer;

    public Participants(List<String> parsedParticipantsName) {
        this.participants = new ArrayList<>();
        this.dealer = new Dealer();
        validateParticipantsNumbers(parsedParticipantsName);
        saveUsers(parsedParticipantsName);
    }

    private void saveUsers(List<String> parsedParticipantsName) {
        parsedParticipantsName.forEach(name -> {
            participants.add(new User(name));
        });
    }

    static void validateParticipantsNumbers(List<String> parsedParticipantsName) {
        if (parsedParticipantsName.size() > MAXIMUM_NUMBER_OF_PARTICIPANTS) {
            throw new IllegalArgumentException("[ERROR] 최대 참가 인원은 16명 이하여야 합니다.");
        }
    }

    public void dealCards(Deck deck) {
        for (User user : participants) {
            user.receiveCard(deck.dealCard());
        }
        dealer.receiveCard(deck.dealCard());
    }

    public String getUserNames() {
        return participants.stream().map(User::getName).collect(Collectors.joining(", "));
    }

    public String getDealerCardsDisplay() {
        return dealer.getCardsDisplay();
    }

    public List<String> getUserCardsDisplays() {
        return participants.stream()
                .map(user -> user.getName() + "카드: " + user.getCardsDisplay())
                .collect(Collectors.toList());
    }
}


