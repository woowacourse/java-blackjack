package blackjack.domain.dto;

import java.util.List;

public class InitialStatusDto {
    private final CardDto dealerCard;
    private final List<UserDto> usersData;

    public InitialStatusDto(CardDto cardDto, List<UserDto> userDtos) {
        this.dealerCard = cardDto;
        this.usersData = userDtos;
    }

    public CardDto getDealerCard() {
        return dealerCard;
    }

    public List<UserDto> getUsersData() {
        return usersData;
    }
}
