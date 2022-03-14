package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Gamer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamerCardsResultDto {
    private final GamerCardsDto gamerCardsDto;
    private final int sum;

    public GamerCardsResultDto(GamerCardsDto gamerCardsDto, int sum) {
        this.gamerCardsDto = gamerCardsDto;
        this.sum = sum;
    }

    public static GamerCardsResultDto of(String name, List<Card> cards, int sum) {
        return new GamerCardsResultDto(GamerCardsDto.of(name, cards), sum);
    }

    public static List<GamerCardsResultDto> of(List<Gamer> gamersCards) {
        List<GamerCardsResultDto> gamersCardsResultDto = new ArrayList<>();
        for (Gamer gamer : gamersCards) {
            gamersCardsResultDto.add(of(gamer.getName(), gamer.getCards(), gamer.getCardGroupScore()));
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
