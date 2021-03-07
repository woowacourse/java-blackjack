package blackjack.dto;

import blackjack.domain.user.User;
import blackjack.domain.user.Users;

import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.*;

public class DtoMapper {
    public static UserCardsDto createUsersCardDto(Users users) {
        return users.stream()
                .collect(
                        collectingAndThen(
                                toMap(
                                        user -> user.getName().toString()
                                        , DtoMapper::createCardDtos
                                ), UserCardsDto::new));
    }

    public static UserCardsDto createUserCardsDto(User user){
        HashMap<String, List<CardDto>> userNameAndCards = new HashMap<>();
        userNameAndCards.put(user.getName().toString(), createCardDtos(user));
        return new UserCardsDto(userNameAndCards);
    }

    private static List<CardDto> createCardDtos(User user) {
        return user.getCards().stream()
                .map(card -> new CardDto(card.getSuitLetter(), card.getSymbolLetter()))
                .collect(toList());
    }
}
