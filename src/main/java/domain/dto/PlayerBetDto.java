package domain.dto;

import exception.BlackJackException;
import java.util.ArrayList;
import java.util.List;

public record PlayerBetDto(String name, int bet) {

    private static final String INVALID_PLAYER_BET = "베팅과 이름이 유효하지 않습니다.";

    public static List<PlayerBetDto> playerBetDtos(List<String> names, List<Integer> bets) {
        List<PlayerBetDto> playerBetDtos = new ArrayList<>();
        if (names.size() != bets.size()) {
            throw new BlackJackException(INVALID_PLAYER_BET);
        }
        for (int idx = 0; idx < names.size(); idx++) {
            playerBetDtos.add(new PlayerBetDto(names.get(idx), bets.get(idx)));
        }
        return playerBetDtos;
    }
}

