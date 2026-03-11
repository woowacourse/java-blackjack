package domain;

import dto.ParticipantsScoreDTO;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vo.GameResult;
import vo.Money;

public class Participants {
    private static final Integer MAXIMUM_NUMBER_OF_PARTICIPANTS = 16;

    private final List<User> participants;
    private final Dealer dealer;

    public Participants(List<String> parsedParticipantsName, List<Money> parsedBetAmounts) {
        this.participants = new ArrayList<>();
        this.dealer = new Dealer();
        validateParticipantsNumbers(parsedParticipantsName);
        saveUsers(parsedParticipantsName, parsedBetAmounts);
    }

    private void saveUsers(List<String> parsedParticipantsName, List<Money> parsedBetAmounts) {
        for (int i = 0; i < parsedParticipantsName.size(); i++) {
            String userName = parsedParticipantsName.get(i);
            Money betAmount = parsedBetAmounts.get(i);
            participants.add(new User(userName, betAmount));
        }
    }

    static void validateParticipantsNumbers(List<String> parsedParticipantsName) {
        if (parsedParticipantsName.size() > MAXIMUM_NUMBER_OF_PARTICIPANTS) {
            throw new IllegalArgumentException("[ERROR] 최대 참가 인원은 16명 이하여야 합니다.");
        }
    }

    public void dealOneCardToAll(Deck deck) {
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
                .map(this::makeOneUserCardDisplay)
                .collect(Collectors.toList());
    }

    public String getPlayerCardStatus(int userIndex) {
        return makeOneUserCardDisplay(participants.get(userIndex));
    }

    private String makeOneUserCardDisplay(User user) {
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

    public void calculateUserScore(int index) {
        participants.get(index).calculateScore();
    }

    public void calculateDealerScore() {
        dealer.calculateScore();
    }

    public Boolean shouldDealerDraw() {
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
            String userFinalDisplay = makeOneUserCardDisplay(user) + user.getUserFinalDisplay();
            userDisplays.add(userFinalDisplay);
        }
        return userDisplays;
    }

    public List<String> makeFinalWinnerDisplays() {
        ParticipantsScoreDTO participantsScoreDTO = judgeWinner();
        return formatFinalDisplays(participantsScoreDTO);
    }

    private ParticipantsScoreDTO judgeWinner() {
        EnumMap<GameResult, Integer> dealerScore = evaluateDealerScore();
        Map<String, GameResult> userScore = evaluateUserScore(dealerScore);
        return new ParticipantsScoreDTO(dealerScore, userScore);
    }

    private EnumMap<GameResult, Integer> evaluateDealerScore() {
        EnumMap<GameResult, Integer> dealerScore = new EnumMap<>(GameResult.class);

        for (GameResult result : GameResult.values()) {
            dealerScore.put(result, 0);
        }

        return dealerScore;
    }

    private Map<String, GameResult> evaluateUserScore(EnumMap<GameResult, Integer> dealerScore) {
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


