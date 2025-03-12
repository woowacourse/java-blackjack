package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participants {

    private final String PLAYER_NOT_EXIST = "존재하지 않는 플레이어입니다.";
    private final String DEALER_NOT_EXIST = "딜러가 존재하지 않습니다.";

    private final List<Participant> participants;

    public Participants(List<String> names, Dealer dealer) {
        List<Player> players = names.stream()
                .map(Player::new)
                .toList();

        participants = new ArrayList<>();
        participants.addAll(players);
        participants.add(dealer);
    }

    private Participant findParticipant(ParticipantName name) {
        return participants.stream()
                .filter(participant -> participant.isNameMatch(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_EXIST));
    }

    public List<Result> calculatePlayerResults() {
        List<Result> results = new ArrayList<>();
        for (ParticipantName name : getPlayerNames()) {
            List<TrumpCard> trumpCards = participantCards(name);
            Score sum = calculateCardSum(name);
            results.add(new Result(name, trumpCards, sum));
        }
        return Collections.unmodifiableList(results);
    }

    public List<ParticipantName> getPlayerNames() {
        return participants.stream()
                .filter(participant -> !participant.isDealer())
                .map(Participant::name)
                .toList();
    }

    public Result calculateResult(ParticipantName name) {
        Participant participant = findParticipant(name);
        ParticipantHand hand = participant.hand;
        Score sum = hand.calculateCardSum();
        List<TrumpCard> trumpCards = hand.getCards();
        return new Result(name, trumpCards, sum);
    }

    public List<TrumpCard> participantCards(ParticipantName name) {
        Participant participant = findParticipant(name);
        return participant.trumpCards();
    }

    public Score calculateCardSum(ParticipantName name) {
        Participant participant = findParticipant(name);
        return participant.calculateCardSum();
    }

    public ParticipantName dealerName() {
        return participants.stream()
                .filter(Participant::isDealer)
                .map(Participant::name)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(DEALER_NOT_EXIST));
    }

    public boolean isBust(ParticipantName name) {
        Participant participant = findParticipant(name);
        return participant.isBust();
    }

    public boolean isDrawable(ParticipantName name) {
        Participant participant = findParticipant(name);
        return participant.isDrawable();
    }

    public void addCard(ParticipantName name, TrumpCard trumpCard) {
        Participant participant = findParticipant(name);
        participant.addDraw(trumpCard);
    }
}
