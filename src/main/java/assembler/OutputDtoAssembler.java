package assembler;

import domain.*;
import domain.card.Card;
import dto.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OutputDtoAssembler {

    public static HandDto toPlayerHandDto(Player player) {
        return new HandDto(
                player.getName(),
                player.getCards().stream()
                        .map(Card::getCardName)
                        .toList()
        );
    }

    public static HandDto toDealerHandDto(Dealer dealer) {
        return new HandDto(
                dealer.getName(),
                dealer.getCards().stream()
                        .map(Card::getCardName)
                        .toList()
        );
    }

    public static BlackJackInitStatusDto toBlackJackInitStatusDto(Dealer dealer,
                                                                  List<Player> players) {
        return new BlackJackInitStatusDto(toDealerInitHandDto(dealer),
                players.stream()
                        .map(OutputDtoAssembler::toPlayerHandDto)
                        .toList());
    }

    public static FinalResultDto toFinalResultDto(Dealer dealer,
                                                  List<Player> players,
                                                  BettingTable bettingTable) {
        List<ScoreResultDto> scoreResultDtos = createScoreResultDtos(dealer, players);
        Map<String, Long> playerMoneyMap = convertPlayerMoneyMap(bettingTable);
        return new FinalResultDto(scoreResultDtos, bettingTable.calculateDealerProfit(), playerMoneyMap);
    }

    private static Map<String, Long> convertPlayerMoneyMap(BettingTable bettingTable) {
        Map<Player, Earning> moneyTable = bettingTable.getMoneyTable();
        Map<String, Long> playerMoneyMap = new LinkedHashMap<>();
        for (Map.Entry<Player, Earning> entry : moneyTable.entrySet()) {
            Player Key = entry.getKey();
            Earning value = entry.getValue();

            String name = Key.getName();
            Long money = value.amount();
            playerMoneyMap.put(name, money);
        }
        return playerMoneyMap;
    }

    private static HandDto toDealerInitHandDto(Dealer dealer) {
        return new HandDto(
                dealer.getName(),
                List.of(dealer.getFirstCardName())
        );
    }

    private static ScoreResultDto toPlayerScoreResultDto(Player player) {
        return new ScoreResultDto(
                toPlayerHandDto(player),
                player.getScore()
        );
    }

    private static ScoreResultDto toDealerScoreResultDto(Dealer dealer) {
        return new ScoreResultDto(
                toDealerHandDto(dealer),
                dealer.getScore()
        );
    }

    private static List<ScoreResultDto> createScoreResultDtos(Dealer dealer,
                                                              List<Player> players) {
        List<ScoreResultDto> scoreResultDtos = new ArrayList<>();
        scoreResultDtos.add(toDealerScoreResultDto(dealer));
        scoreResultDtos.addAll(
                players.stream()
                        .map(OutputDtoAssembler::toPlayerScoreResultDto)
                        .toList()
        );
        return scoreResultDtos;
    }
}
