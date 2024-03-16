package blackjack.domain.participant;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.result.ParticipantScoreStatus;
import blackjack.domain.result.Score;
import blackjack.domain.result.WinStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.dto.ParticipantCardsDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Players {
    private final List<Player> players;

    private Players(final List<Player> players) {
        validateDuplicate(players);
        this.players = players;
    }

    public static Players from(final List<String> playerNames) {
        final List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();

        return new Players(players);
    }

    private void validateDuplicate(final List<Player> players) {
        if (Set.copyOf(players).size() != players.size()) {
            throw new IllegalArgumentException("중복된 이름의 참여자는 참여할 수 없습니다.");
        }
    }

    public void divideCard(final List<Card> cards) {
        for (int index = 0; index < cards.size(); index++) {
            Player player = players.get(index / 2);
            player.addCard(cards.get(index));
        }
    }

    public void addCardTo(final Player player, final Card card) {
        player.addCard(card);
    }

    public Map<ParticipantName, WinStatus> determineWinStatus(final ParticipantScoreStatus dealerScoreStatus) {
        final Map<ParticipantName, WinStatus> playersWinStatus = new LinkedHashMap<>();

        for (Player player : players) {
            ParticipantScoreStatus playerScoreStatus = new ParticipantScoreStatus(player.isBlackjack(),
                    player.calculate());
            playersWinStatus.put(player.getName(), WinStatus.of(dealerScoreStatus, playerScoreStatus));
        }

        return playersWinStatus;
    }

    public int count() {
        return players.size();
    }

    public Player findPlayer(final Player inputPlayer) {
        return players.stream()
                .filter(player -> player.equals(inputPlayer))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 참가자 입니다."));
    }

    public Hands getCardsOf(final String name) {
        final Player findedPlayer = findPlayer(new Player(name));
        return findedPlayer.getHands();
    }

    public List<ParticipantCardsDto> getStartCards() {
        return players.stream()
                .map(ParticipantCardsDto::from)
                .toList();
    }

    public Map<ParticipantName, Hands> getPlayerHands() {
        return players.stream()
                .collect(toMap(Player::getName,
                        Player::getHands,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public Map<ParticipantName, Score> getPlayerScores() {
        return players.stream()
                .collect(toMap(Player::getName,
                        Player::calculate,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public List<ParticipantName> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
