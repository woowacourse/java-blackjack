package domain;

import dto.ProfitResultDTO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vo.GameResult;
import vo.Money;

public class Participants {
    private static final Integer MAXIMUM_NUMBER_OF_PARTICIPANTS = 16;

    private final List<User> players;
    private final Dealer dealer;

    public Participants(List<String> parsedParticipantsName, List<Money> parsedBetAmounts) {
        this.players = new ArrayList<>();
        this.dealer = new Dealer();
        validateParticipantsNumbers(parsedParticipantsName);
        saveUsers(parsedParticipantsName, parsedBetAmounts);
    }

    private void saveUsers(List<String> parsedParticipantsName, List<Money> parsedBetAmounts) {
        for (int i = 0; i < parsedParticipantsName.size(); i++) {
            String userName = parsedParticipantsName.get(i);
            Money betAmount = parsedBetAmounts.get(i);
            players.add(new User(userName, betAmount));
        }
    }

    static void validateParticipantsNumbers(List<String> parsedParticipantsName) {
        if (parsedParticipantsName.size() > MAXIMUM_NUMBER_OF_PARTICIPANTS) {
            throw new IllegalArgumentException("[ERROR] 최대 참가 인원은 16명 이하여야 합니다.");
        }
    }

    public void dealOneCardToAll(Deck deck) {
        deck.shuffleCards();
        for (User user : players) {
            user.receiveCard(deck.drawCard());
        }
        dealer.receiveCard(deck.drawCard());
    }

    public String getUserNames() {
        return players.stream().map(User::getName).collect(Collectors.joining(", "));
    }

    public String getDealerCardsDisplay() {
        return dealer.getCardsDisplay();
    }

    public List<String> getUserCardsDisplays() {
        return players.stream()
                .map(this::makeOneUserCardDisplay)
                .collect(Collectors.toList());
    }

    public String getPlayerCardStatus(int userIndex) {
        return makeOneUserCardDisplay(players.get(userIndex));
    }

    private String makeOneUserCardDisplay(User user) {
        return user.getName() + "카드: " + user.getCardsDisplay();
    }

    public List<String> askGetExtraCard() {
        return players.stream()
                .map(User::formatAskGetExtraCard)
                .collect(Collectors.toList());
    }

    public void dealCard(Deck deck, int index) {
        players.get(index).receiveCard(deck.drawCard());
    }

    public void calculateUserScore(int index) {
        players.get(index).calculateScore();
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
        for (User user : players) {
            String userFinalDisplay = makeOneUserCardDisplay(user) + user.getUserFinalDisplay();
            userDisplays.add(userFinalDisplay);
        }
        return userDisplays;
    }

    public List<String> makeProfitResultDisplays() {
        ProfitResultDTO profitResultDTO = calculateProfit();
        return formatProfitDisplays(profitResultDTO);
    }

    private List<String> formatProfitDisplays(ProfitResultDTO profitResultDTO) {
        List<String> profitDisplays = new ArrayList<>();
        Money dealerProfit = profitResultDTO.getDealerProfit();
        Map<String, Money> participantsProfit = profitResultDTO.getParticipantsProfit();

        profitDisplays.add(formatDealerProfitDisplay(dealerProfit));

        for (Map.Entry<String, Money> entry : participantsProfit.entrySet()) {
            String userName = entry.getKey();
            Money userProfit = entry.getValue();
            profitDisplays.add(formatUserProfitDisplay(userName, userProfit));
        }

        return profitDisplays;
    }

    private ProfitResultDTO calculateProfit() {
        Map<String, Money> participantsProfit = new LinkedHashMap<>();
        Money dealerProfit = new Money(0);

        for (User user : players) {
            GameResult isUserWin = dealer.judgeResultForUser(user);
            Money earnedMoney = user.updateProfitBy(isUserWin);
            dealerProfit = dealerProfit.subtract(earnedMoney);
            participantsProfit.put(user.getName(), earnedMoney);
        }

        return new ProfitResultDTO(dealerProfit, participantsProfit);
    }

    private String formatDealerProfitDisplay(Money dealerProfit) {
        return "딜러: " + dealerProfit.getValue();
    }

    private String formatUserProfitDisplay(String userName, Money userProfit) {
        return userName + ": " + userProfit.getValue();
    }
}


