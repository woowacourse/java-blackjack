package blackjack.domain.participant;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.ParticipantScoreStatus;
import blackjack.domain.result.Score;
import blackjack.domain.result.WinStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
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
        for (int i = 0; i < cards.size(); i++) {
            Player player = players.get(i / 2);
            player.addCard(cards.get(i));
        }
    }

    public void addCardTo(final String name, final Card card) {
        final Player findedPlayer = findParticipant(name);
        findedPlayer.addCard(card);
    }

//    public Map<ParticipantName, WinStatus> determineWinStatus(final Score dealerScore) {
//        final Map<ParticipantName, WinStatus> playersWinStatus = new LinkedHashMap<>();
//
//        for (Player player : players) {
//            playersWinStatus.put(player.getName(), WinStatus.of(dealerScore, player.calculate(), player.getStatus()));
//        }
//
//        return playersWinStatus;
//    }
    public Map<ParticipantName, WinStatus> determineWinStatus(final ParticipantScoreStatus dealerScoreStatus) {
        final Map<ParticipantName, WinStatus> playersWinStatus = new LinkedHashMap<>();

        for (Player player : players) {
            playersWinStatus.put(player.getName(), WinStatus.of(dealerScoreStatus, new ParticipantScoreStatus( player.isBlackjack(), player.calculate())));
        }

        return playersWinStatus;
    }

    public int count() {
        return players.size();
    }

    public boolean isNotDead(final String name) {
        final Player player = findParticipant(name);
        return player.isNotDead();
    }

    private Player findParticipant(final String name) {
        return players.stream()
                .filter(participant -> participant.isName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 참가자 입니다."));
    }

    public Hands getCardsOf(final String name) {
        final Player findedPlayer = findParticipant(name);
        return findedPlayer.getHands();
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
}
