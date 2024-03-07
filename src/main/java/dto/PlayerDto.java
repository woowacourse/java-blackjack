package dto;

import domain.Dealer;
import domain.Participant;

import java.util.List;

public class PlayerDto {
    private final String name;
    private final List<String> cards;
    private final int totalSum;

    public PlayerDto(final String name, final List<String> cards, final int totalSum) {
        this.name = name;
        this.cards = cards;
        this.totalSum = totalSum;
    }

    public static PlayerDto from(final Participant participant) {
        return new PlayerDto(participant.getName(), participant.getCardNames(), participant.handsSum());
    }

    public static PlayerDto from(final Dealer dealer) {
        return new PlayerDto(dealer.getName(), dealer.getCardNames(), dealer.getTotalCardSum());
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }

    public int getTotalSum() {
        return totalSum;
    }
}
