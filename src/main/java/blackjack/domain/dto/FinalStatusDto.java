package blackjack.domain.dto;

import blackjack.domain.Users;
import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FinalStatusDto {
    private final List<CardDto> dealerCards;
    private final int dealerPoint;
    private final List<String> usersNames;
    private final Map<String, List<CardDto>> userCardsData;
    private final Map<String, Integer> userScores;

    public FinalStatusDto(final Dealer dealer, final Users users) {
        this.dealerCards = DtoUtils.makeCardToDto(dealer.openCards());
        this.dealerPoint = dealer.getGamePoint().optimizeValue();
        this.usersNames = getListStringBy(users.getUsers());
        this.userCardsData = makeCardStatusOf(users);
        this.userScores = makeScoreOf(users);
    }


    private List<String> getListStringBy(final List<User> users) {
        return users.stream().map(user -> user.getName().getValue())
                .collect(Collectors.toList());
    }

    private Map<String, List<CardDto>> makeCardStatusOf(final Users users) {
        final HashMap<String, List<CardDto>> cardStatus = new HashMap<>();
        for (User user : users.getUsers()) {
            final Name name = user.getName();
            cardStatus.put(name.getValue(), DtoUtils.makeCardToDto(users.getCardsOf(name)));
        }
        return cardStatus;
    }

    private Map<String, Integer> makeScoreOf(final Users users) {
        final HashMap<String, Integer> scoreData = new HashMap<>();
        for (User user : users.getUsers()) {
            final Name name = user.getName();
            scoreData.put(name.getValue(), user.getGamePoint().optimizeValue());
        }
        return scoreData;
    }

    public List<CardDto> getDealerCards() {
        return dealerCards;
    }

    public int getDealerPoint() {
        return dealerPoint;
    }

    public List<String> getUsersNames() {
        return usersNames;
    }

    public Map<String, List<CardDto>> getUserCardsData() {
        return userCardsData;
    }

    public Map<String, Integer> getUserScores() {
        return userScores;
    }
}
