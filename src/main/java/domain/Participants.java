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
    private final GameJudge gameJudge = new GameJudge();

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

    public Map<String, GameResult> calculateUserResults() {
        Map<String, GameResult> userResults = new HashMap<>();
        for (User user : participants) {
            userResults.put(user.getName(), gameJudge.judge(dealer.getScore(), user.getScore()));
        }
        return userResults;
    }

    public EnumMap<GameResult, Integer> calculateDealerResults() {
        EnumMap<GameResult, Integer> dealerResults = initEnumMap();
        for (User user : participants) {
            GameResult dealerResult = gameJudge.judge(dealer.getScore(), user.getScore()).opposite();
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