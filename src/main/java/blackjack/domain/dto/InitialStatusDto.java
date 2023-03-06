package blackjack.domain.dto;

import blackjack.domain.Users;
import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitialStatusDto {
    private final CardDto dealerCard;
    private final Map<String, List<CardDto>> usersData;

    public InitialStatusDto(Dealer dealer, Users users) {
        this.dealerCard = new CardDto(dealer.getFirstCard());
        this.usersData = makeUsersCardsStatus(users);
    }

    private Map<String, List<CardDto>> makeUsersCardsStatus(final Users users) {
        final HashMap<String, List<CardDto>> resultMap = new HashMap<>();
        for (User user : users.getUsers()) {
            final Name userName = user.getName();
            resultMap.put(userName.getValue(), DtoUtils.makeCardToDto(users.getCardsOf(userName)));
        }
        return resultMap;
    }

    public CardDto getDealerCard() {
        return dealerCard;
    }

    public Map<String, List<CardDto>> getUsersData() {
        return usersData;
    }
}
