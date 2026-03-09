package domain;

import dto.ParticipantsScoreDTO;
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
                .map(this::makeOneUserCard)
                .collect(Collectors.toList());
    }

    public String makeOneUserCardDelegator(int userIndex) {
        return makeOneUserCard(participants.get(userIndex));
    }

    private String makeOneUserCard(User user) {
        return user.getName() + "카드: " + user.getCardsDisplay();
    }

    public List<String> askGetExtraCard() {
        return participants.stream()
                .map(User::formatAskGetExtraCard)
                .collect(Collectors.toList());
    }

    public void dealCard(Deck deck, int index) {
        participants.get(index).receiveCard(deck.dealCard());
    }

    public void calculateScore(int index) {
        participants.get(index).calculateScore();
    }

    public void caculateDealerscore() {
        dealer.calculateScore();
    }

    public Boolean determineDealerDealMore() {
        return dealer.determineDealerDealMore();
    }

    public void dealCardToDealer(Card card) {
        dealer.receiveCard(card);
        dealer.calculateScore();
    }

    public String getDealerFinalDisplay() {
        return dealer.getDealerFinalDisplay();
    }

    public List<String> addScoreToUserHand() {
        List<String> userDisplays = new ArrayList<>();
        for (User user : participants) {
            String userFinalDisplay = makeOneUserCard(user) + user.getUserFinalDisplay();
            userDisplays.add(userFinalDisplay);
        }
        return userDisplays;
    }

    public List<String> makeFinalWinnerDisplays() {
        ParticipantsScoreDTO participantsScoreDTO = judgeWinner();
        return formatFinalDisplays(participantsScoreDTO);
    }

    private ParticipantsScoreDTO judgeWinner() {
        EnumMap<GameResult, Integer> dealerScore = calculateDealerScore();
        Map<String, GameResult> userScore = calculateUserScore(dealerScore);
        return new ParticipantsScoreDTO(dealerScore, userScore);
    }

    private EnumMap<GameResult, Integer> calculateDealerScore() {
        EnumMap<GameResult, Integer> dealerScore = new EnumMap<>(GameResult.class);

        for (GameResult result : GameResult.values()) {
            dealerScore.put(result, 0);
        }

        return dealerScore;
    }

    private Map<String, GameResult> calculateUserScore(EnumMap<GameResult, Integer> dealerScore) {
        Map<String, GameResult> userScore = new HashMap<>();

        for (User user : participants) {
            GameResult isDealerWin = dealer.judgeUserResult(user.getHand());
            dealerScore.replace(isDealerWin, dealerScore.get(isDealerWin) + 1);

            GameResult isUserWin = dealer.judgeUserWin(user.getHand());
            userScore.put(user.getName(), isUserWin);
        }

        return userScore;
    }

    private List<String> formatFinalDisplays(ParticipantsScoreDTO participantsScoreDTO) {
        EnumMap<GameResult, Integer> dealerScore = participantsScoreDTO.getDealerScore();
        Map<String, GameResult> userScore = participantsScoreDTO.getUserScore();

        List<String> finalTotalDisplays = new ArrayList<>();
        finalTotalDisplays.add(formatDealerFinalDisplay(dealerScore));

        for (User user : participants) {
            finalTotalDisplays.add(formatUserFinalDisplay(user, userScore));
        }

        return finalTotalDisplays;
    }

    private String formatDealerFinalDisplay(EnumMap<GameResult, Integer> dealerScore) {
        return "딜러: " + dealerScore.get(GameResult.WIN) + "승 " + dealerScore.get(GameResult.LOSE) + "패";
    }

    private String formatUserFinalDisplay(User user, Map<String, GameResult> userScore) {
        return user.getName() + ": " + userScore.get(user.getName()).getName();
    }
}


