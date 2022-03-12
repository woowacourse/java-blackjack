package dto;

import domain.card.PlayingCard;
import domain.player.Player;
import domain.util.ScoreUtil;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerDto {
    private static final int FIRST_CARD_INDEX = 0;
    private static final String CARD_NAME_JOIN_CHARACTER = ", ";

    private final String name;
    private final List<PlayingCard> playingCards;

    private PlayerDto(String name, List<PlayingCard> playingCards) {
        this.name = name;
        this.playingCards = List.copyOf(playingCards);
    }

    public static PlayerDto from(Player player) {
        return new PlayerDto(player.getName(), player.getPlayingCards());
    }

    public String getName() {
        return name;
    }

    public String getFirstCardName() {
        return playingCards.get(FIRST_CARD_INDEX)
                .getCardName();
    }

    public String getJoinedCardNames() {
        return playingCards.stream()
                .map(PlayingCard::getCardName)
                .collect(Collectors.joining(CARD_NAME_JOIN_CHARACTER));
    }

    public int getScore() {
        return ScoreUtil.getScore(playingCards);
    }
}
