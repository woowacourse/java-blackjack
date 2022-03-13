package blackjack.dto;

import static java.util.stream.Collectors.toList;

import blackjack.domain.user.Users;
import java.util.List;
import java.util.stream.Stream;

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

    public List<String> getPlayerNames() {
        return playersDto.stream()
                .map(UserDto::getUserName)
                .collect(toList());
    }

    public List<UserDto> getAllUserDto() {
        return Stream.concat(Stream.of(dealerDto), playersDto.stream())
                .collect(toList());
    }
}
