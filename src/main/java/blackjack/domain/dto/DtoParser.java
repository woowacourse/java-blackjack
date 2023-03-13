package blackjack.domain.dto;

import blackjack.domain.PrizeCalculator;
import blackjack.domain.card.Card;
import blackjack.domain.game.BlackJack;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;

import java.util.*;

public class DtoParser {

    public static InitialStatusDto getInitializeDto(BlackJack blackJack) {
        final List<Card> dealerCards = blackJack.getDealer().getCards().getCards();
        final Card firstDelaerCard = dealerCards.get(0);
        return new InitialStatusDto(getCardDto(firstDelaerCard), makeUsersDto(blackJack.getUsers()));
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

    public static GameResultDto getGameResultDto(final PrizeCalculator prizeCalculator) {
        final LinkedHashMap<String, Integer> nameAndProfit = new LinkedHashMap<>();
        enrollDealerProfit(nameAndProfit, prizeCalculator);
        enrollUserProfit(nameAndProfit, prizeCalculator);
        return new GameResultDto(nameAndProfit);
    }

    private static void enrollDealerProfit(final LinkedHashMap<String, Integer> nameAndProfit, final PrizeCalculator prizeCalculator) {
        final int dealerProfit = prizeCalculator.getDealerProfit();
        nameAndProfit.put("딜러", dealerProfit);
    }

    private static void enrollUserProfit(final LinkedHashMap<String, Integer> nameAndProfit, final PrizeCalculator prizeCalculator) {
        final Map<Name, Integer> profit = prizeCalculator.getProfit();
        for (Name name : profit.keySet()) {
            nameAndProfit.put(name.getValue(), profit.get(name));
        }
    }


}
