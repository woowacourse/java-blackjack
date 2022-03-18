package blackjack.view.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersDto {

    private final List<Player> players;

    public PlayersDto(List<Player> players) {
        this.players = players;
    }

    public static PlayersDto of(List<Player> players) {
        return new PlayersDto(players);
    }

    public String showNames() {
        return players.stream()
                .map(player -> player.getName().getValue())
                .collect(Collectors.joining(", "));
    }

    public String showEachCards() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : players) {
            stringBuilder.append(showCardHandStatus(player));
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }

    public String showCardHandStatus(Player player) {
        return String.format("%s 카드: %s", player.getName().getValue(), player.getCards().getCardHand().stream()
                .map(Card::toString)
                .collect(Collectors.joining(", ")));
    }

    public String showEachResult() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : players) {
            stringBuilder.append(showCardHandStatus(player) + " 결과 - " + player.calculateBestScore());
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }
}
