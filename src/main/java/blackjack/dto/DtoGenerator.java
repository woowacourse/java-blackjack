package blackjack.dto;

import blackjack.model.card.Card;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Gamer;
import blackjack.model.gamer.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DtoGenerator {

    public static List<PlayerDto> createPlayerDtos(List<Player> players) {
        return players.stream()
                .map(DtoGenerator::createPlayerDto)
                .toList();
    }

    public static PlayerDto createPlayerDto(Player player) {
        return new PlayerDto(player.name(), createHandDeckDto(player));
    }

    public static DealerDto createInitialDealerDto(Card card) {
        return new DealerDto(createHandDeckDtoByCard(card));
    }

    public static DealerDto createDealerDto(Dealer dealer) {
        return new DealerDto(createHandDeckDto(dealer));
    }

    public static PlayersNetProfitDto createPlayerNetProfitDto(List<Player> players) {
        Map<String, Integer> playersNetProfit = new LinkedHashMap<>();
        for (Player player : players) {
            String playerName = player.name();
            int netProfit = player.netProfit();
            playersNetProfit.put(playerName, netProfit);
        }

        return new PlayersNetProfitDto(playersNetProfit);
    }

    public static DealerNetProfitDto createDealerNetProfitDto(Dealer dealer) {
        return new DealerNetProfitDto(dealer.netProfit());
    }

    private static HandDeckDto createHandDeckDto(Gamer gamer) {
        List<CardDto> cardDtos = gamer.allCards().stream()
                .map(DtoGenerator::createCardDto)
                .toList();

        int score = gamer.totalScore();

        return new HandDeckDto(cardDtos, score);
    }

    private static HandDeckDto createHandDeckDtoByCard(Card card) {
        List<CardDto> cardDto = List.of(createCardDto(card));
        int score = card.getScore();

        return new HandDeckDto(cardDto, score);
    }

    private static CardDto createCardDto(Card card) {
        String cardNumber = card.number().getName();
        String cardPattern = card.pattern().getName();
        return new CardDto(cardNumber, cardPattern);
    }
}
