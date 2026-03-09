package domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vo.GameResult;

public class Participants {
    private static final Integer MAXIMUM_NUMBER_OF_PARTICIPANTS = 16;

    private final List<User> participants;
    private final Dealer dealer;

    public Participants(List<String> parsedParticipantsName) {
        this.participants = new ArrayList<>();
        this.dealer = new Dealer();
        validateParticipantsNumbers(parsedParticipantsName);
        saveUsers(parsedParticipantsName);
    }

    static void validateParticipantsNumbers(List<String> parsedParticipantsName) {
        if (parsedParticipantsName.size() > MAXIMUM_NUMBER_OF_PARTICIPANTS) {
            throw new IllegalArgumentException("[ERROR] 최대 참가 인원은 16명 이하여야 합니다.");
        }
    }

    private void saveUsers(List<String> parsedParticipantsName) {
        parsedParticipantsName.forEach(name -> {
            participants.add(new User(name));
        });
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
        return dealer.getOneCardDisplay();
    }

    public Object getAllDealerCardsDisplay() {
        return dealer.getCardsDisplay();
    }

    public List<String> getUserCardsDisplays() {
        return participants.stream()
                .map(this::makeOneUserCard)
                .collect(Collectors.toList());
    }

    public String makeOneUserCardDelegator(int userIndex) {
        return makeOneUserCard(participants.get(userIndex));
    }

    private String makeOneUserCard(User user) {
        return user.getName() + "카드: " + user.getCardsDisplay();
    }

    public List<String> askToGetExtraCard() {
        return participants.stream()
                .map(User::getFormatedAskGetExtraCard)
                .collect(Collectors.toList());
    }

    public void dealCard(Deck deck, int index) {
        participants.get(index).receiveCard(deck.dealCard());
    }

    public Boolean determineDealerDealMore() {
        return dealer.determineDealerDealMore();
    }

    public void dealCardToDealer(Card card) {
        dealer.receiveCard(card);
    }

    public String getDealerFinalDisplay() {
        return dealer.getFinalDisplay();
    }

    public List<String> addScoreToUserHand() {
        List<String> userDisplays = new ArrayList<>();
        for (User user : participants) {
            String userFinalDisplay = makeOneUserCard(user) + user.getFinalDisplay();
            userDisplays.add(userFinalDisplay);
        }
        return userDisplays;
    }

    public List<String> judgeWinner() {
        Map<String, GameResult> userResults = calculateUserResults();
        EnumMap<GameResult, Integer> dealerResults = calculateDealerResults();

        List<String> displays = new ArrayList<>();
        displays.add(formatDealerDisplay(dealerResults));
        displays.addAll(formatUserDisplays(userResults));
        return displays;
    }

    private Map<String, GameResult> calculateUserResults() {
        Map<String, GameResult> userResults = new HashMap<>();
        for (User user : participants) {
            userResults.put(user.getName(), dealer.judgeUserWin(user.getScore()));
        }
        return userResults;
    }

    private EnumMap<GameResult, Integer> calculateDealerResults() {
        EnumMap<GameResult, Integer> dealerResults = initEnumMap();
        for (User user : participants) {
            GameResult dealerResult = dealer.judgeDealerResult(user.getScore());
            dealerResults.replace(dealerResult, dealerResults.get(dealerResult) + 1);
        }
        return dealerResults;
    }

    private String formatDealerDisplay(EnumMap<GameResult, Integer> dealerResults) {
        return "딜러: " + dealerResults.get(GameResult.WIN) + "승 " + dealerResults.get(GameResult.LOSE) + "패";
    }

    private List<String> formatUserDisplays(Map<String, GameResult> userResults) {
        return participants.stream()
                .map(user -> user.getName() + ": " + userResults.get(user.getName()).getName())
                .collect(Collectors.toList());
    }

    private EnumMap<GameResult, Integer> initEnumMap() {
        EnumMap<GameResult, Integer> dealerScore = new EnumMap<>(GameResult.class);
        for (GameResult result : GameResult.values()) {
            dealerScore.put(result, 0);
        }
        return dealerScore;
    }

}


