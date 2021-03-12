package blackjack.domain;

import java.util.*;

public class Participants {

    private final List<Participant> participants;
    private final Dealer dealer;
    private final List<Player> players;

    private Participants(Dealer dealer, List<Player> players) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        validateOverlappedNames(participants);
        this.dealer = dealer;
        this.players = players;
        this.participants = participants;
    }

    public static Participants of(Dealer dealer, List<Player> players) {
        return new Participants(dealer, players);
    }

    private void validateOverlappedNames(List<Participant> participants) {
        long participantsCount = participants.size();
        long distinctParticipantsCount = participants.stream()
                                                     .map(Participant::getName)
                                                     .distinct()
                                                     .count();
        if (participantsCount != distinctParticipantsCount) {
            throw new IllegalArgumentException("참가자들의 이름은 중복이 없어야 합니다.");
        }
    }

    public void receiveDefaultCards(CardDeck cardDeck) {
        for (Participant participant : participants) {
            participant.receiveCards(cardDeck.drawDefaultCards());
        }
    }

    public List<Participant> toList() {
        return Collections.unmodifiableList(participants);
    }

    List<Participant> getParticipants() {
        return participants;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Map<String, BetAmount> calculateFinalBetProfits() {
        Map<String, BetAmount> finalBetProfits = new LinkedHashMap<>();
        finalBetProfits.put(dealer.getName(), dealer.calculateFinalBetProfit(players));
        for (Player player : players) {
            finalBetProfits.put(player.getName(), player.calculateFinalBetProfit(dealer));
        }
        return Collections.unmodifiableMap(finalBetProfits);
    }
}
