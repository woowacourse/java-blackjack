package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public final class Participants {

    private final List<Participant> participants;

    private Participants(final List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(final List<String> playerNames) {
        validateDuplicate(playerNames);
        List<Participant> participants = playerNames.stream()
                .map(Participant::from)
                .collect(Collectors.toList());

        return new Participants(participants);
    }

    private static void validateDuplicate(final List<String> playerNames) {
        if (isDuplicate(playerNames)) {
            throw new IllegalArgumentException("중복되지 않은 이름만 입력해주세요");
        }
    }

    public void drawCard(final Deck deck) {
        participants.forEach(participant -> {
            participant.takeCard(deck.dealCard());
            participant.takeCard(deck.dealCard());
        });
    }

    private static boolean isDuplicate(final List<String> playerNames) {
        final int uniqueNameCount = new HashSet<>(playerNames).size();
        return uniqueNameCount < playerNames.size();
    }

    public List<Participant> getParticipants() {
        return List.copyOf(participants);
    }

    public List<String> getNames() {
        return participants.stream()
                .map(Participant::getName)
                .collect(toList());
    }

    public Result getResult(final Dealer dealer) {
        Map<GameResult, List<Participant>> result = participants.stream()
                .collect(groupingBy(participant -> isWinner(dealer, participant)));

        List<String> winners = classifyParticipants(result.get(GameResult.VICTORY));
        List<String> losers = classifyParticipants(result.get(GameResult.DEFEAT));

        return new Result(winners, losers);
    }

    private GameResult isWinner(final Dealer dealer, final Participant participant) {
        if (participant.isBust() || isParticipantDefeat(dealer, participant)) {
            return GameResult.DEFEAT;
        }
        return GameResult.VICTORY;
    }

    private boolean isParticipantDefeat(final Dealer dealer, final Participant participant) {
        return !dealer.isBust() && dealer.getScore() > participant.getScore();
    }

    private List<String> classifyParticipants(final List<Participant> result) {
        if (result == null) {
            return new ArrayList<>();
        }
        return result.stream()
                .map(Player::getName)
                .collect(toList());
    }
}
