package BlackJack.dto;

import BlackJack.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    private String name;
    private List<CardDto> cards;

    public UserDto(String name,List<CardDto> cards) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards.stream()
                .map((card) -> card.getCardInfo())
                .collect(Collectors.toUnmodifiableList());
    }

    public static UserDto from(User user){
        List<CardDto> collect = user.getCards().stream()
                .map(CardDto::from)
                .collect(Collectors.toList());
        return new UserDto(user.getName(),collect);
    }

}
