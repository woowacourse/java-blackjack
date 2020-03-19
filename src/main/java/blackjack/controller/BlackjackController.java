package blackjack.controller;

import blackjack.controller.dto.request.BettingDto;
import blackjack.controller.dto.request.NamesRequestDto;
import blackjack.controller.dto.response.GamersResultResponseDto;
import blackjack.controller.dto.response.HandResponseDto;
import blackjack.controller.dto.response.HandResponseDtos;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.gamer.Profit;
import blackjack.domain.rule.BettingTable;
import blackjack.domain.rule.HandInitializer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public Players createPlayers(NamesRequestDto namesRequestDto) {
        return Players.from(namesRequestDto.getNames());
    }

    public void initializeHand(Dealer dealer, Players players, Deck deck) {
        HandInitializer.initialize(dealer, players, deck);
    }

    public HandResponseDtos getInitialHand(Dealer dealer, Players players) {
        List<HandResponseDto> handResponseDtoList = new ArrayList<>();
        handResponseDtoList.add(HandResponseDto.ofInitialDealer(dealer));
        for (Player player : players) {
            handResponseDtoList.add(HandResponseDto.of(player));
        }
        return new HandResponseDtos(handResponseDtoList);
    }

    public HandResponseDtos getFinalHand(Dealer dealer, Players players) {
        List<HandResponseDto> handResponseDtos = new ArrayList<>();
        handResponseDtos.add(HandResponseDto.of(dealer));
        for (Player player : players) {
            handResponseDtos.add(HandResponseDto.of(player));
        }
        return new HandResponseDtos(handResponseDtos);
    }

    public BettingTable createBettingTable(Players players, BettingDto bettingDto) {
        Map<Player, BettingMoney> playerMoneyMap = new LinkedHashMap<>();
        Map<String, String> bettingTableDto = bettingDto.getBettingTable();
        for (Map.Entry<String, String> nameMoneyEntry : bettingTableDto.entrySet()) {
            Player player = players.findPlayerBy(nameMoneyEntry.getKey());
            BettingMoney bettingMoney = BettingMoney.from(nameMoneyEntry.getValue());
            playerMoneyMap.put(player, bettingMoney);
        }

        return new BettingTable(playerMoneyMap);
    }

    public GamersResultResponseDto getGamersResultResponse(Dealer dealer, Players players, BettingTable bettingTable) {
        Map<Gamer, Profit> calculateBettingMoney = bettingTable.calculateBettingResult(players, dealer);
        Map<String, Integer> map = calculateBettingMoney.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        entry -> entry.getValue().getProfit()
                ));
        return new GamersResultResponseDto(map);
    }
}
