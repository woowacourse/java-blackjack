package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.dto.CurrentCardsDto;
import blackjack.dto.ProfitDTO;
import blackjack.dto.TotalScoreDto;

import java.util.*;
import java.util.stream.Collectors;

public class Players {

    private static final int MAX_NUMBER_OF_PLAYER = 7;
    private static final String TOO_MANY_PLAYERS = "최대 " + MAX_NUMBER_OF_PLAYER + "명까지 플레이 가능합니다.";
    private static final String NAME_DUPLICATED = "중복되는 이름은 허용되지 않습니다.";

    private final List<Player> players;

    public Players(String[] names) {
        validateNames(names);
        players = Arrays.stream(names)
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void addForAllPlayers(Deck deck) {
        players.forEach(player -> player.addCard(deck.draw()));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<CurrentCardsDto> generateCurrentCardsDTO() {
        return players.stream()
                .map(CurrentCardsDto::of)
                .collect(Collectors.toList());
    }

    public List<TotalScoreDto> computeTotalScore() {
        return players.stream()
                .map(Participant::computeTotalScore)
                .collect(Collectors.toList());
    }

    public List<ProfitDTO> computeTotalProfit(Dealer dealer) {
        return players.stream()
                .map(player -> new ProfitDTO(player, Profit.of(player, dealer)))
                .collect(Collectors.toList());
    }

    private void validateNames(String[] names) {
        validateLength(names);
        validateDuplication(names);
    }

    private void validateLength(String[] names) {
        if (names.length > MAX_NUMBER_OF_PLAYER) {
            throw new IllegalArgumentException(TOO_MANY_PLAYERS);
        }
    }

    private void validateDuplication(String[] names) {
        Set<String> set = new HashSet<>(Arrays.asList(names));
        if (set.size() != names.length) {
            throw new IllegalArgumentException(NAME_DUPLICATED);
        }
    }
}
