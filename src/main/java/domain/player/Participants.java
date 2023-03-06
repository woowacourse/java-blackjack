package domain.player;

import domain.card.Deck;
import domain.result.GameResult;
import domain.result.Result;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
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
                .collect(toList());

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
        Map<GameResult, List<String>> result = participants.stream()
                .collect(groupingBy(participant -> isWinner(dealer, participant)
                        , mapping(Participant::getName, toList())));

        List<String> winners = result.getOrDefault(GameResult.VICTORY, new ArrayList<>());
        List<String> losers = result.getOrDefault(GameResult.DEFEAT, new ArrayList<>());

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
}
