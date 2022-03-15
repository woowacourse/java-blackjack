package blackjack.view.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.stream.Collectors;

public class PlayersDto {

    private final Players players;

    public PlayersDto(Players players) {
        this.players = players;
    }

    public static PlayersDto of(Players players) {
        return new PlayersDto(players);
    }

    public String showNames() {
        return players.getPlayers().stream()
                .map(player -> player.getName().getValue())
                .collect(Collectors.joining(", "));
    }

    public String showEachCards() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : players.getPlayers()) {
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
        for (Player player : players.getPlayers()) {
            stringBuilder.append(showCardHandStatus(player) + " 결과 - " + player.calculateBestScore());
            stringBuilder.append(System.getProperty("line.separator"));
        }
        return stringBuilder.toString();
    }
}
