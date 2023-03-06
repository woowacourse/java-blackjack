package blackjack.domain.dto;

import blackjack.domain.BlackJack;
import blackjack.domain.Users;
import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitialStatusDto {
    private final CardDto dealerCard;
    private final List<UserDto> usersData;

    public InitialStatusDto(BlackJack blackJack) {
        this.dealerCard = new CardDto(blackJack.getDealer().getFirstCard());
        this.usersData = makeUsersDto(blackJack.getUsers());
    }

    private List<UserDto> makeUsersDto(final Users users) {
        final ArrayList<UserDto> userDtos = new ArrayList<>();
        for (User user : users.getUsers()) {
            userDtos.add(new UserDto(user));
        }
        return userDtos;
    }

    public CardDto getDealerCard() {
        return dealerCard;
    }

    public List<UserDto> getUsersData() {
        return usersData;
    }
}
