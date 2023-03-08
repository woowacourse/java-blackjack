package blackjack.domain.dto;

import blackjack.domain.BlackJack;
import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;

import java.util.*;

public class DtoParser {

    public static InitialStatusDto getInitializeDto(BlackJack blackJack) {
        return new InitialStatusDto(getCardDto(blackJack.getDealer().getFirstCard()), makeUsersDto(blackJack.getUsers()));
    }

    private static List<UserDto> makeUsersDto(final Users users) {
        final List<UserDto> userDtos = new ArrayList<>();
        for (User user : users.getUsers()) {
            userDtos.add(getUserDto(user));
        }
        return userDtos;
    }

    private static CardDto getCardDto(Card card) {
        return new CardDto(card.getShape().getName(), card.getCardNumber().getName());
    }

    public static UserDto getUserDto(User user) {
        return new UserDto(user.getName().getValue(), makeCardToDto(user.openCards()));
    }

    private static List<CardDto> makeCardToDto(List<Card> cards) {
        final List<CardDto> cardsDto = new ArrayList<>();
        for (Card card : cards) {
            cardsDto.add(getCardDto(card));
        }
        return cardsDto;
    }

    public static FinalStatusDto getFinalStatusDto(BlackJack blackJack) {
        return new FinalStatusDto(
                getDealerDto(blackJack.getDealer()),
                blackJack.getDealer().getGamePoint().getValue(),
                makeUserAndScore(blackJack.getUsers().getUsers())
        );
    }

    private static Map<UserDto, Integer> makeUserAndScore(final List<User> users) {
        final Map<UserDto, Integer> resultMap = new HashMap<>();
        for (User user : users) {
            resultMap.put(getUserDto(user), user.getGamePoint().getValue());
        }
        return resultMap;
    }

    private static UserDto getDealerDto(final Dealer dealer) {
        return new UserDto(dealer.getName().getValue(), makeCardToDto(dealer.openCards()));
    }

    public static GameResultDto getGameResultDto(final BlackJack blackJack) {
        return new GameResultDto(enrollUser(blackJack), getDealerResultDto(blackJack.getDealerResult()));
    }

    private static Map<ResultDto, Integer> getDealerResultDto(final Map<Result, Integer> dealerResult) {
        final Map<ResultDto, Integer> returnMap = new HashMap<>();
        for (Result result : dealerResult.keySet()) {
            if (dealerResult.get(result) != 0) {
                returnMap.put(getDtoOfResult(result), dealerResult.get(result));
            }
        }
        return returnMap;
    }

    private static Map<String, ResultDto> enrollUser(BlackJack blackJack) {
        final Map<String, ResultDto> result = new LinkedHashMap<>();
        enroll(result, blackJack.getUserOf(Result.WIN), Result.WIN);
        enroll(result, blackJack.getUserOf(Result.LOSE), Result.LOSE);
        enroll(result, blackJack.getUserOf(Result.DRAW), Result.DRAW);
        return result;
    }

    private static void enroll(final Map<String, ResultDto> board, final List<User> users, final Result result) {
        for (User user : users) {
            board.put(user.getName().getValue(), getDtoOfResult(result));
        }
    }

    private static ResultDto getDtoOfResult(final Result result) {
        if (result == Result.WIN) {
            return new ResultDto("승");
        }
        if (result == Result.DRAW) {
            return new ResultDto("무승부");
        }
        if (result == Result.LOSE) {
            return new ResultDto("패");
        }
        throw new AssertionError();
    }
}
