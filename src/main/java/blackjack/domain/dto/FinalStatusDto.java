package blackjack.domain.dto;

import blackjack.domain.BlackJack;
import blackjack.domain.Users;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;

import java.util.HashMap;
import java.util.Map;

public class FinalStatusDto {
    private final UserDto dealer;
    private final Integer dealerScore;
    private final Map<UserDto, Integer> userScores;

    public FinalStatusDto(BlackJack blackJack) {
        this.dealer = new UserDto(blackJack.getDealer());
        this.dealerScore = blackJack.getDealer().getGamePoint().getValue();
        this.userScores = makeUsersStatus(blackJack.getUsers());
    }

    private Map<UserDto, Integer> makeUsersStatus(final Users users) {
        final HashMap<UserDto, Integer> usersInfo = new HashMap<>();
        for (User user : users.getUsers()) {
            usersInfo.put(new UserDto(user), user.getGamePoint().getValue());
        }
        return usersInfo;
    }

    public Integer getDealerScore() {
        return dealerScore;
    }

    public UserDto getDealer() {
        return dealer;
    }

    public Map<UserDto, Integer> getUserScores() {
        return userScores;
    }
}
