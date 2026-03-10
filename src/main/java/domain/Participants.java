package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vo.GameResult;

public class Participants {
    private static final Integer MAX_PLAYER_COUNT = 16;

    private final List<User> participants;
    private final Dealer dealer;

    public Participants(List<String> parsedParticipantsName) {
        this.participants = new ArrayList<>();
        this.dealer = new Dealer();
        validateParticipantsNumbers(parsedParticipantsName);
        saveUsers(parsedParticipantsName);
    }

    static void validateParticipantsNumbers(List<String> parsedParticipantsName) {
        if (parsedParticipantsName.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 최대 참가 인원은 16명 이하여야 합니다.");
        }
    }

    private void saveUsers(List<String> parsedParticipantsName) {
        parsedParticipantsName.forEach(name -> participants.add(new User(name)));
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

    public Map<String, GameResult> calculateUserResults() {
        Map<String, GameResult> userResults = new HashMap<>();
        for (User user : participants) {
            userResults.put(user.getName(), dealer.judgeUserWin(user.getScore()));
        }
        return userResults;
    }

    public EnumMap<GameResult, Integer> calculateDealerResults() {
        EnumMap<GameResult, Integer> dealerResults = initEnumMap();
        for (User user : participants) {
            GameResult dealerResult = dealer.judgeDealerResult(user.getScore());
            dealerResults.replace(dealerResult, dealerResults.get(dealerResult) + 1);
        }
        return dealerResults;
    }

    private EnumMap<GameResult, Integer> initEnumMap() {
        EnumMap<GameResult, Integer> dealerScore = new EnumMap<>(GameResult.class);
        for (GameResult result : GameResult.values()) {
            dealerScore.put(result, 0);
        }
        return dealerScore;
    }
}