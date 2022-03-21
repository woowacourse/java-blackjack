package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
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

    public static List<GamerCardsResultDto> of(List<Player> players) {
        List<GamerCardsResultDto> gamersCardsResultDto = new ArrayList<>();
        for (Player player : players) {
            gamersCardsResultDto.add(of(player.getName(), player.getCards(), player.getScore()));
        }
        return Collections.unmodifiableList(gamersCardsResultDto);
    }

    public static GamerCardsResultDto of(Dealer dealer) {
        return of(dealer.getName(), dealer.getCards(), dealer.getScore());
    }

    public int getSum() {
        return sum;
    }

    public GamerCardsDto getGamerCardsDto() {
        return gamerCardsDto;
    }
}
