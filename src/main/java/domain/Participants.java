package domain;

import static domain.BlackjackRule.MAX_PARTICIPANTS_COUNT;

import dto.ParticipantsInitDTO;
import dto.ProfitResultDTO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vo.GameResult;
import vo.Money;

public class Participants {
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
        if (participantsInitDTOS.size() > MAX_PARTICIPANTS_COUNT) {
            String errorMessage = String.format("[ERROR] 최대 참가 인원은 %d명 이하여야 합니다.", MAX_PARTICIPANTS_COUNT);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public void dealOneCardToAll(Deck deck) {
        deck.shuffleCards();
        for (User user : players) {
            user.receiveCard(deck.drawCard());
        }
        dealer.receiveCard(deck.drawCard());
    }

    public Card getDealerFirstCard() {
        return dealer.getFirstCard();
    }

    public List<User> getPlayers() {
        return List.copyOf(players);
    }

    public User getPlayer(int index) {
        return players.get(index);
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(User::getUserName)
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

    public Dealer getDealer() {
        return dealer;
    }

    public ProfitResultDTO calculateProfit() {
        Map<String, Money> participantsProfit = new LinkedHashMap<>();
        Money dealerProfit = new Money(0);

        for (User user : players) {
            GameResult isUserWin = GameJudge.judgeResultForUser(user, dealer);
            Money earnedMoney = isUserWin.calculateProfit(user.getBettingMoney());
            dealerProfit = dealerProfit.subtract(earnedMoney);
            participantsProfit.put(user.getUserName(), earnedMoney);
        }

        return new ProfitResultDTO(dealerProfit, participantsProfit);
    }

    public boolean isBust(int index) {
        return players.get(index).isBust();
    }
}


