package blackjack.service;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.gamer.Player;
import blackjack.dto.NamesRequestDto;

public class BlackjackService {

    private final List<Player> players = new ArrayList<>();

    public void setPlayer(NamesRequestDto requestDto) {
        players.addAll(
            requestDto.names().stream()
                .map(Player::new)
                .toList());
    }
}
