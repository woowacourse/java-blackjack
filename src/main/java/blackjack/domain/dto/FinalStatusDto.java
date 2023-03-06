package blackjack.domain.dto;

import blackjack.domain.Users;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;

import java.util.HashMap;
import java.util.Map;

public class FinalStatusDto {
    private final UserDto dealer;
    private final Integer dealerScore;
    private final Map<UserDto, Integer> userScores;

    public FinalStatusDto(final Dealer dealer, final Users users) {
        this.dealer = new UserDto(dealer);
        this.dealerScore = dealer.getGamePoint().optimizeValue();
        this.userScores = makeUsersStatus(users);
    }

    private Map<UserDto, Integer> makeUsersStatus(final Users users) {
        final HashMap<UserDto, Integer> usersInfo = new HashMap<>();
        for (User user : users.getUsers()) {
            usersInfo.put(new UserDto(user), user.getGamePoint().optimizeValue());
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
