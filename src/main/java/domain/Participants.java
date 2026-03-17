package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import vo.Bet;
import vo.GameResult;
import vo.Name;

public class Participants {
    private static final int MAX_PLAYER_COUNT = 16;

    private final List<User> participants;

    public Participants(List<Entry<Name, Bet>> bets) {
        validate(bets);
        this.participants = new ArrayList<>();
        saveUsers(bets);
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

    public GameSummary judgeAll(Dealer dealer) {
        Map<User, GameResult> userResults = new LinkedHashMap<>();
        for (User user : participants) {
            userResults.put(user,
                    GameJudge.judge(user, dealer));
        }
        return new GameSummary(userResults);
    }

    private void validate(List<Entry<Name, Bet>> bets) {
        if (bets == null || bets.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 참가자는 최소 1명이어야 합니다.");
        }
        if (bets.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 최대 참가 인원은 16명 이하여야 합니다.");
        }
    }

    private void saveUsers(List<Entry<Name, Bet>> bets) {
        bets.forEach((entry) -> {
            participants.add(new User(entry.getKey(), entry.getValue()));
        });
    }
}
