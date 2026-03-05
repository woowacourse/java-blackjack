package service;

import converter.BlackjackConverter;
import domain.Card;
import domain.CardMachine;
import domain.Player;
import dto.PlayersDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BlackjackService {

    private final BlackjackConverter blackjackConverter = new BlackjackConverter();
    private final CardMachine cardMachine = new CardMachine();

    public PlayersDto createPlayers(List<String> names) {
        validatePlayerNames(names);
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(name));
        }
        return blackjackConverter.convertPlayersDto(players);
    }

    private void validatePlayerNames(List<String> names) {
        validatePlayerCount(names);
        validatePlayerCount(names.size());
    }

    private void validatePlayerCount(List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("[ERROR] 게임 참가자의 이름은 중복 되어선 안됩니다.");
        }
    }

    private void validatePlayerCount(int playerCount) {
        if (!(2 <= playerCount && playerCount <= 8)) {
            throw new IllegalArgumentException("[ERROR] 게임 참가자의 수는 2~8명 사이여야 합니다.");
        }
    }

    public Card drawCard() {
        return cardMachine.drawCard();
    }
}
