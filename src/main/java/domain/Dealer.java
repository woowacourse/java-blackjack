package domain;

import domain.dto.PlayerCardDto;
import constant.GameConstant;

import java.util.List;

public class Dealer extends Player {
    public static final String DEALER_NAME = "딜러";

    public Dealer(String name) {
        super(name);
    }

    public boolean needAdditionalCard() {
        return hand.calculateScore() <= GameConstant.DEALER_HIT_THRESHOLD;
    }

    public PlayerCardDto toOpeningCardDto() {
        return new PlayerCardDto(this.getName(), List.of(hand.getFirst().toCardDto()));
    }
}
