package blackjackgame.dto;

import java.util.List;

public class GamerDTO {
    private final String name;
    private final List<CardDTO> holdingCardsDto;
    private final int summationCardPoint;

    public GamerDTO(String name, List<CardDTO> holdingCardsDto, int summationCardPoint) {
        this.name = name;
        this.holdingCardsDto = holdingCardsDto;
        this.summationCardPoint = summationCardPoint;
    }

    public String getName() {
        return name;
    }

    public List<CardDTO> getHoldingCardsDto() {
        return holdingCardsDto;
    }

    public int getSummationCardPoint() {
        return summationCardPoint;
    }
}
