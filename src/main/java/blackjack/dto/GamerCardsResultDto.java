package blackjack.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.CardGroup;
import blackjack.domain.gamer.Player;

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
    public static List<GamerCardsResultDto> of(List<Player> playersCards) {
        List<GamerCardsResultDto> gamersCardsResultDto = new ArrayList<>();
        for (Player player : playersCards) {
            gamersCardsResultDto.add(of(player.getName(), player.getCardGroup(), player.getScore()));
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
