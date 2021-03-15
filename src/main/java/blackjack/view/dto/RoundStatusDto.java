package blackjack.view.dto;

import blackjack.domain.Round;
import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RoundStatusDto {
    private final String dealerName;
    private final List<Card> dealerCards;
    private final List<PlayerStatusDto> playerStatusDto;
    private final int dealerScore;

    private RoundStatusDto(final String dealerName, final List<Card> dealerCards, final List<PlayerStatusDto> playerStatusDto, final int dealerScore) {
        this.dealerName = dealerName;
        this.dealerCards = dealerCards;
        this.playerStatusDto = playerStatusDto;
        this.dealerScore = dealerScore;
    }

    public static RoundStatusDto toDto(Round round) {
        return new RoundStatusDto(round.getDealerName(),
                round.getDealer().getState().cards().getCards(),
                round.getPlayers().stream()
                        .map(player -> new PlayerStatusDto(player.getName(), player.getState().cards().getCards(), player.getState().calculateScore()))
                        .collect(Collectors.toList()),
                round.getDealer().getState().calculateScore());
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public String getDealerName() {
        return dealerName;
    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public List<PlayerStatusDto> getPlayerStatusDto() {
        return playerStatusDto;
    }

    public List<Card> getSingleDealerCards() {
        return Collections.singletonList(dealerCards.get(0));
    }
}
