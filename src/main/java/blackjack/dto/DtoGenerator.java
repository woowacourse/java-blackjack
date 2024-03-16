package blackjack.dto;

import blackjack.model.card.Card;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Gamers;
import blackjack.model.gamer.Player;
import blackjack.model.betting.BettingInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DtoGenerator {

    public static GamersDto createGamersDtoByInitialCard(Gamers gamers) {
        List<PlayerDto> playerDtos = createPlayerDtos(gamers.getPlayers());
        DealerDto dealerDto = createInitialDealerDto(gamers.getDealer());
        return new GamersDto(dealerDto, playerDtos);
    }

    public static GamersDto createGamersDto(Gamers gamers) {
        List<PlayerDto> playerDtos = createPlayerDtos(gamers.getPlayers());
        DealerDto dealerDto = createDealerDto(gamers.getDealer());
        return new GamersDto(dealerDto, playerDtos);
    }

    public static GamersNetProfitDto createGamersNetProfitDto(BettingInfo bettingInfo, List<Player> players) {
        int dealerNetProfit = bettingInfo.calculateDealerNetProfit();
        Map<String, Integer> playersNetProfit = bringPlayersNetProfit(bettingInfo, players);
        return new GamersNetProfitDto(dealerNetProfit, playersNetProfit);
    }

    private static List<PlayerDto> createPlayerDtos(List<Player> players) {
        return players.stream()
                .map(DtoGenerator::createPlayerDto)
                .toList();
    }

    public static PlayerDto createPlayerDto(Player player) {
        String playerName = player.getName();
        List<CardDto> cardDtos = createCardDtos(player.getCards());
        int score = player.calculateScore().getScore();
        return new PlayerDto(playerName, cardDtos, score);
    }

    private static DealerDto createInitialDealerDto(Dealer dealer) {
        CardDto cardDto = createCardDto(dealer.getFistCard());
        return new DealerDto(List.of(cardDto), 0);
    }

    private static DealerDto createDealerDto(Dealer dealer) {
        List<CardDto> cardDtos = createCardDtos(dealer.getCards());
        int score = dealer.calculateScore().getScore();
        return new DealerDto(cardDtos, score);
    }

    private static List<CardDto> createCardDtos(List<Card> cards) {
        return cards.stream()
                .map(DtoGenerator::createCardDto)
                .toList();
    }

    private static CardDto createCardDto(Card card) {
        String cardNumber = card.getNumber().getName();
        String cardPattern = card.getPattern().getName();
        return new CardDto(cardNumber, cardPattern);
    }

    private static Map<String, Integer> bringPlayersNetProfit(BettingInfo bettingInfo, List<Player> players) {
        Map<String, Integer> playersNetProfit = new HashMap<>();
        for (Player player : players) {
            String playerName = player.getName();
            int netProfit = bettingInfo.calculatePlayerNetProfit(player);
            playersNetProfit.put(playerName, netProfit);
        }

        return playersNetProfit;
    }
}
