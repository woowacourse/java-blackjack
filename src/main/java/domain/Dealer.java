package domain;

import constant.GameConstant;
import domain.dto.PlayerCardDto;

import java.util.List;

public class Dealer extends Player {

    public Dealer(String name) {
        super(name);
    }

    public boolean needAdditionalCard() {
        return calculateScore() <= GameConstant.DEALER_HIT_THRESHOLD;
    }

    public PlayerCardDto toOpeningCardDto() {
        return new PlayerCardDto(this.getName(), List.of(hand.getFirst().toCardDto()));
    }
}
