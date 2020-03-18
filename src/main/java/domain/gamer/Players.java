package domain.gamer;

import domain.card.Deck;
import domain.gamer.dto.GamerWithMoneyDto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players valueOf(Deck deck, List<GamerWithMoneyDto> gamerWithMoneyDtos) {
        validateDuplicateName(gamerWithMoneyDtos);
        return gamerWithMoneyDtos.stream()
                .map(gamerDto -> new Player(deck.dealInitCards(), gamerDto.getName(), gamerDto.getMoney()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }

    private static void validateDuplicateName(List<GamerWithMoneyDto> gamerWithMoneyDtos) {
        List<String> playerNames = gamerWithMoneyDtos.stream()
                .map(GamerWithMoneyDto::getName)
                .collect(Collectors.toList());
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new NameDuplicationException("중복되는 이름이 존재합니다.");
        }
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
