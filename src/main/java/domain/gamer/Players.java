package domain.gamer;

import domain.card.Deck;
import domain.gamer.dto.GamerMoneyDto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players valueOf(Deck deck, List<GamerMoneyDto> gamerMoneyDtos) {
        validateDuplicateName(gamerMoneyDtos);
        return gamerMoneyDtos.stream()
                .map(gamerDto -> new Player(deck.dealInitCards(), gamerDto.getName(), gamerDto.getMoney()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }

    private static void validateDuplicateName(List<GamerMoneyDto> gamerMoneyDtos) {
        List<String> playerNames = gamerMoneyDtos.stream()
                .map(GamerMoneyDto::getName)
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
