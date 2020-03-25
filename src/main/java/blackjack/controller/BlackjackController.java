package blackjack.controller;

import blackjack.controller.dto.request.BettingDto;
import blackjack.controller.dto.request.NamesRequestDto;
import blackjack.controller.dto.response.GamersResultResponseDto;
import blackjack.controller.dto.response.HandResponseDto;
import blackjack.controller.dto.response.HandResponseDtos;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.GamerProfitTable;
import blackjack.domain.rule.BettingTable;
import blackjack.domain.rule.HandInitializer;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private InputView inputView;
    private OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(Dealer dealer, Deck deck) {
        Players players = createPlayers(inputView.askPlayerNames());

        BettingDto bettingDto = inputView.askBettingMoney(players.getNames());
        BettingTable bettingTable = createBettingTable(players, bettingDto);

        initializeHand(dealer, players, deck);
        HandResponseDtos handResponseDtos = getInitialHand(dealer, players);
        outputView.printInitialHand(handResponseDtos);

        runPlayersHitOrStay(deck, players);
        runDealerDrawMoreCard(dealer, deck);

        HandResponseDtos result = getFinalHand(dealer, players);
        outputView.printHandWithScore(result);

        GamersResultResponseDto gamersResultResponseDto = getGamersResultResponse(dealer, players, bettingTable);
        outputView.printResult(gamersResultResponseDto);

    }

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
        GamerProfitTable gamerProfitTable = bettingTable.calculateProfitResult(players, dealer);
        return GamersResultResponseDto.from(gamerProfitTable);
    }

    private void runPlayersHitOrStay(Deck deck, Players players) {
        for (Player player : players) {
            runOnePlayerHitOrStay(deck, player);
        }
    }

    private void runOnePlayerHitOrStay(Deck deck, Player player) {
        String name = player.getName();
        while (!player.isBusted() && inputView.askPlayerAnswer(name).isYes()) {
            player.draw(deck.pick());
            HandResponseDto handResponseDto = HandResponseDto.of(player);
            outputView.printHand(handResponseDto);
        }
    }

    private void runDealerDrawMoreCard(Dealer dealer, Deck deck) {
        while (dealer.shouldDrawCard()) {
            dealer.draw(deck.pick());
            outputView.printDealerDrawCard();
        }
    }

}
