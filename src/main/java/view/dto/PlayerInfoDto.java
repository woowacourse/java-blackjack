package view.dto;

import domain.card.Card;
import domain.user.Player;
import view.mapper.CardNumberMapper;
import view.mapper.CardTypeMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerInfoDto {

    private final String playerName;
    private final List<String> cards;
    private final int result;

    private PlayerInfoDto(String playerName, List<String> cards, int result) {
        this.playerName = playerName;
        this.cards = cards;
        this.result = result;
    }

    private PlayerInfoDto(String playerName, List<String> cards) {
        this.playerName = playerName;
        this.cards = List.copyOf(cards);
        result = 0;
    }

    public static PlayerInfoDto from(Player player) {
        return new PlayerInfoDto(player.getPlayerName().getValue(), mapToCards(player.getHand().getCards()));
    }

    public static PlayerInfoDto of(Player player, int result) {
        return new PlayerInfoDto(player.getPlayerName().getValue(), mapToCards(player.getHand().getCards()), result);
    }

    private static List<String> mapToCards(List<Card> cards) {
        return cards.stream()
                .map(it -> CardNumberMapper.getCardNumber(it.getDenomination()) + CardTypeMapper.getCardName(it.getSuit()))
                .collect(Collectors.toList());
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getCards() {
        return List.copyOf(cards);
    }

    public int getResult() {
        return result;
    }
}
