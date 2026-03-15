package domain;

import dto.DealerResultDTO;
import dto.ParticipantsInitDTO;
import dto.ProfitResultDTO;
import dto.UserCardsDTO;
import dto.UserResultDTO;
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

    public Participants(List<ParticipantsInitDTO> participantsInitDTOS) {
        validateParticipantsNumbers(participantsInitDTOS);
        this.players = participantsInitDTOS.stream()
                .map(dto -> new User(dto.getUserName(), dto.getBettingMoney()))
                .collect(Collectors.toList());
        this.dealer = new Dealer();
    }

    private void validateParticipantsNumbers(List<ParticipantsInitDTO> participantsInitDTOS) {
        if (participantsInitDTOS.size() > MAXIMUM_NUMBER_OF_PARTICIPANTS) {
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

    public String getDealerOneCardDisplay() {
        return dealer.getOneCardDisplay();
    }

    public List<UserCardsDTO> getUserCards() {
        return players.stream()
                .map(UserCardsDTO::fromUser)
                .collect(Collectors.toList());
    }

    public UserCardsDTO getPlayerCards(int userIndex) {
        return UserCardsDTO.fromUser(players.get(userIndex));
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

    public DealerResultDTO getDealerResult() {
        return DealerResultDTO.fromDealer(dealer);
    }

    public List<UserResultDTO> getUserResults() {
        return players.stream()
                .map(UserResultDTO::fromUser)
                .collect(Collectors.toList());
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
            Money earnedMoney = isUserWin.calculateProfit(user.getBettingMoney());
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

    public boolean isBust(int index) {
        return players.get(index).isBust();
    }
}


