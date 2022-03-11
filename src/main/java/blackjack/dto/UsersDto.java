package blackjack.dto;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import blackjack.domain.user.User;
import blackjack.domain.user.Users;

public class UsersDto {

    private final List<UserDto> playersDto;
    private final UserDto dealerDto;

    public UsersDto(List<UserDto> playersDto, UserDto dealerDto) {
        this.playersDto = playersDto;
        this.dealerDto = dealerDto;
    }

    public static UsersDto fromInit(Users users) {
        return toDto(users, UserDto::fromInit);
    }

    public static UsersDto fromEvery(Users users) {
        return toDto(users, UserDto::fromEvery);
    }

    private static UsersDto toDto(Users users, Function<User, UserDto> mapper) {
        List<UserDto> playersDto = users.getPlayers()
                .stream()
                .map(mapper)
                .collect(toList());
        UserDto dealerDto = mapper.apply(users.getDealer());
        return new UsersDto(playersDto, dealerDto);
    }

    public String getDealerName() {
        return dealerDto.getUserName();
    }

    public String getPlayerNames() {
        return playersDto.stream()
                .map(UserDto::getUserName)
                .collect(Collectors.joining(", "));
    }

    public List<UserDto> getAllUserDto() {
        return Stream.concat(
                Stream.of(dealerDto),
                        playersDto.stream()
                )
                .collect(toList());
    }
}
