package blackjack.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.CardGroup;
import blackjack.domain.gamer.Gamer;

public class GamerCardsResultDto {
    private final GamerCardsDto gamerCardsDto;
    private final int sum;

    public GamerCardsResultDto(GamerCardsDto gamerCardsDto, int sum) {
        this.gamerCardsDto = gamerCardsDto;
        this.sum = sum;
    }

    public static GamerCardsResultDto of(String name, CardGroup cards, int sum) {
        return new GamerCardsResultDto(GamerCardsDto.of(name, cards), sum);
    }
    public static List<GamerCardsResultDto> of(List<Gamer> gamersCards) {
        List<GamerCardsResultDto> gamersCardsResultDto = new ArrayList<>();
        for (Gamer gamer : gamersCards) {
            gamersCardsResultDto.add(of(gamer.getName(), gamer.getCardGroup(), gamer.getPlayerSum()));
        }
        return Collections.unmodifiableList(gamersCardsResultDto);
    }

    public int getSum() {
        return sum;
    }

    public GamerCardsDto getGamerCardsDto() {
        return gamerCardsDto;
    }
}
