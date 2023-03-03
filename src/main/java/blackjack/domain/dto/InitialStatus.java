package blackjack.domain.dto;

import blackjack.domain.Users;
import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitialStatus {
    private final Card dealerCard;
    private final Map<String, List<Card>> usersData;

    public InitialStatus(Dealer dealer, Users users) {
        this.dealerCard = dealer.getFirstCard();
        this.usersData = makeUsersCardsStatus(users);
    }

    private Map<String, List<Card>> makeUsersCardsStatus(final Users users) {
        final HashMap<String, List<Card>> resultMap = new HashMap<>();
        for (User user : users.getUsers()) {
            final Name userName = user.getName();
            resultMap.put(userName.getValue(), users.getCardsOf(userName));
        }
        return resultMap;
    }

    public Card getDealerCard() {
        return dealerCard;
    }

    public Map<String, List<Card>> getUsersData() {
        return usersData;
    }
}
