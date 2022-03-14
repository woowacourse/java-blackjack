package blackjack.dto;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;

public class PlayerDto {
    private final String name;
    private final List<PlayingCard> playingCards;
    private final int score;

    private PlayerDto(String name, List<PlayingCard> playingCards, final int score) {
        this.name = name;
        this.playingCards = playingCards;
        this.score = score;
    }

    public static PlayerDto from(Player player) {
        return new PlayerDto(player.getName(), player.getCards(), player.getSumOfCards());
    }

    public static PlayerDto getDealerFrom(Players players) {
        final Player dealer = players.getDealer();
        return new PlayerDto(dealer.getName(), dealer.getCards(), dealer.getSumOfCards());
    }

    public String getName() {
        return name;
    }

    public List<PlayingCard> getPlayingCards() {
        return playingCards;
    }

    public int getScore() {
        return score;
    }
}
