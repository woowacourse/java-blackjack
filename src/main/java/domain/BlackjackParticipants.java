package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackParticipants {

    private final String PLAYER_NOT_EXIST = "존재하지 않는 플레이어입니다.";
    private final String DEALER_NOT_EXIST = "딜러가 존재하지 않습니다.";

    private final List<BlackjackParticipant> participants;

    public BlackjackParticipants(List<String> names, Dealer dealer) {
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();

        participants = new ArrayList<>();
        participants.addAll(players);
        participants.add(dealer);
    }

    private BlackjackParticipant findParticipant(String name) {
        return participants.stream()
                .filter(player -> player.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_EXIST));
    }

    public List<BlackjackResult> calculatePlayerResults() {
        List<BlackjackResult> blackjackResults = new ArrayList<>();
        for (String name : getPlayerNames()) {
            List<TrumpCard> trumpCards = participantCards(name);
            Score sum = calculateCardSum(name);
            blackjackResults.add(new BlackjackResult(name, trumpCards, sum));
        }
        return Collections.unmodifiableList(blackjackResults);
    }

    public List<String> getPlayerNames() {
        return participants.stream()
                .filter(participants -> !participants.isDealer())
                .map(BlackjackParticipant::name)
                .toList();
    }

    public BlackjackResult calculateResult(String name) {
        BlackjackParticipant participant = findParticipant(name);
        ParticipantHand hand = participant.hand;
        Score sum = hand.calculateCardSum();
        List<TrumpCard> trumpCards = hand.getCards();
        return new BlackjackResult(name, trumpCards, sum);
    }

    public List<TrumpCard> participantCards(String name) {
        BlackjackParticipant participant = findParticipant(name);
        return participant.trumpCards();
    }

    public Score calculateCardSum(String name) {
        BlackjackParticipant participant = findParticipant(name);
        return participant.calculateCardSum();
    }

    public String dealerName() {
        return participants.stream()
                .filter(BlackjackParticipant::isDealer)
                .map(BlackjackParticipant::name)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(DEALER_NOT_EXIST));
    }

    public boolean isBust(String name) {
        BlackjackParticipant participant = findParticipant(name);
        return participant.isBust();
    }

    public boolean isDrawable(String name) {
        BlackjackParticipant participant = findParticipant(name);
        return participant.isDrawable();
    }

    public void addCard(String name, TrumpCard trumpCard) {
        BlackjackParticipant participant = findParticipant(name);
        participant.addDraw(trumpCard);
    }
}
