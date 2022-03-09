package BlackJack.controller;

import BlackJack.domain.Dealer;
import BlackJack.domain.Player;
import BlackJack.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    public List<UserDto> joinGame(List<String> inputPlayerNames) {
        Dealer dealer = new Dealer("딜러");
        List<Player> players = new ArrayList<>();
        for (String name : inputPlayerNames) {
            players.add(new Player(name));
        }
        return convertToDto(dealer, players);
    }

    private List<UserDto> convertToDto(Dealer dealer, List<Player> players) {
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(UserDto.from(dealer));
        for (Player player : players) {
            userDtos.add(UserDto.from(player));
        }
        return userDtos;
    }
}
