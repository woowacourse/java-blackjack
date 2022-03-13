package dto;

import domain.card.Card;
import domain.player.Player;
import domain.util.ScoreUtil;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerDto {
    private static final int FIRST_CARD_INDEX = 0;
    private static final String CARD_NAME_JOIN_CHARACTER = ", ";

    private final String name;
    private final List<Card> cards;

    private PlayerDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = List.copyOf(cards);
    }

    public static PlayerDto from(Player player) {
        return new PlayerDto(player.getName(), player.getCards());
    }

    public String getName() {
        return name;
    }

    public String getFirstCardName() {
        return cards.get(FIRST_CARD_INDEX)
                .getCardName();
    }

    public String getJoinedCardNames() {
        return cards.stream()
                .map(Card::getCardName)
                .collect(Collectors.joining(CARD_NAME_JOIN_CHARACTER));
    }

    public int getScore() {
        return ScoreUtil.getScore(cards);
    }
}
