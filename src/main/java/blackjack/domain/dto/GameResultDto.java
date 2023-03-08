package blackjack.domain.dto;

import blackjack.domain.BlackJack;
import blackjack.domain.Result;
import blackjack.domain.Users;
import blackjack.domain.card.GamePoint;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;

import java.util.*;

public class GameResultDto {
    private final Map<String, ResultDto> userResult;
    private final Map<ResultDto, Integer> dealerResult;

    public GameResultDto(Map<String, ResultDto> userResult, Map<ResultDto, Integer> dealerResult) {
        this.userResult = userResult;
        this.dealerResult = dealerResult;
    }
    public Map<String, ResultDto> getUserResult() {
        return userResult;
    }

    public Map<ResultDto, Integer> getDealerResult() {
        return dealerResult;
    }
}
