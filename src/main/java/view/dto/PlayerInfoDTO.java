package view.dto;

import domain.card.Card;
import domain.user.Player;
import view.mapper.CardNumberMapper;
import view.mapper.CardTypeMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerInfoDTO {

    private final String playerName;
    private final List<String> cards;
    private final int result;

    private PlayerInfoDTO(String playerName, List<String> cards, int result) {
        this.playerName = playerName;
        this.cards = cards;
        this.result = result;
    }

    private PlayerInfoDTO(String playerName, List<String> cards) {
        this.playerName = playerName;
        this.cards = List.copyOf(cards);
        result = 0;
    }

    public static PlayerInfoDTO from(Player player) {
        return new PlayerInfoDTO(player.getPlayerName().getValue(), mapToCards(player.getHand().getCards()));
    }

    public static PlayerInfoDTO of(Player player, int result) {
        return new PlayerInfoDTO(player.getPlayerName().getValue(), mapToCards(player.getHand().getCards()), result);
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
