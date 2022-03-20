package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.dto.DistributeResult;
import blackjack.domain.dto.ProfitResult;
import blackjack.domain.dto.UserGameResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {

    public static final String DEALER_NAME = "딜러";
    private final List<Participant> participants;

    public Participants() {
        this.participants = new ArrayList<>();
    }

    public void addDealer() {
        this.participants.add(new Dealer());
    }

    public void addUsers(String[] userName) {
        List<User> users = Arrays.stream(userName)
                .map(User::new)
                .collect(Collectors.toList());
        this.participants.addAll(users);
    }

    public void addUsers(Map<String, Integer> priceByName) {
        for (Map.Entry<String, Integer> entries : priceByName.entrySet()) {
            participants.add(new User(entries.getKey(), entries.getValue()));
        }
    }

    public List<User> getUsers() {
        return participants.stream()
                .filter(Participant::isUser)
                .map(User.class::cast)
                .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return (Dealer) participants.stream()
                .filter(Participant::isDealer)
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    public void distributeCard(Deck deck) {
        for (Participant participant : participants) {
            participant.receiveCard(deck.drawCard());
        }
    }

    public List<DistributeResult> getDistributeResult() {
        return participants.stream()
                .map(DistributeResult::new)
                .collect(Collectors.toList());
    }

    public List<ProfitResult> calculateProfitResult() {
        List<ProfitResult> profitResults = new ArrayList<>();
        Dealer dealer = getDealer();
        profitResults.add(new ProfitResult(DEALER_NAME, calculateDealerProfit()));
        for (User user : getUsers()) {
            profitResults.add(new ProfitResult(user.getName(), user.calculateProfit(dealer)));
        }
        return profitResults;
    }

    public Participant getUserByName(String name) {
        return participants.stream()
                .filter(participant -> participant.checkName(name))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    public List<UserGameResult> getUserResults() {
        Participant dealer = getDealer();
        return participants.stream()
                .filter(Participant::isUser)
                .map(user -> new UserGameResult(user, dealer))
                .collect(Collectors.toList());
    }

    public int calculateDealerProfit() {
        int dealerProfit = 0;
        for (User user : getUsers()) {
            dealerProfit -= (user.calculateProfit(getDealer()));
        }

        return dealerProfit;
    }
}
