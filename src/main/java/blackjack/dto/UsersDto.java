package blackjack.dto;

import static java.util.stream.Collectors.toList;

import blackjack.domain.user.Users;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsersDto {

    private final List<UserDto> playersDto;
    private final UserDto dealerDto;

    public UsersDto(Users users) {
        this.playersDto = users.getPlayers()
                .stream()
                .map(UserDto::from)
                .collect(toList());
        this.dealerDto = UserDto.from(users.getDealer());
    }

    public String getDealerName() {
        return dealerDto.getUserName();
    }

    public String getPlayerNames() {
        return playersDto.stream()
                .map(UserDto::getUserName)
                .collect(Collectors.joining(", "));
    }

    public Map<String, String> getUserAndCards() {
        Map<String, String> userCards = new LinkedHashMap<>(
                Map.of(dealerDto.getUserName(), dealerDto.getCardNames())
        );

        for (UserDto dto : playersDto) {
            userCards.put(dto.getUserName(), dto.getCardNames());
        }

        return userCards;
    }
}
