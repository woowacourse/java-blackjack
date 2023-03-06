package blackjack.domain.dto;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;

import java.util.List;

public class UserDto {
    private final String name;
    private final List<CardDto> cards;

    public UserDto(User user) {
        this.name = user.getName().getValue();
        this.cards = DtoUtils.makeCardToDto(user.openCards());
    }

    public UserDto(Dealer dealer) {
        this.name = dealer.getName().getValue();
        this.cards = DtoUtils.makeCardToDto(dealer.openCards());
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCards() {
        return cards;
    }

}
