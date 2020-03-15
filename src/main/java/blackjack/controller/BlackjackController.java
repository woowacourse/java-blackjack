package blackjack.controller;

import blackjack.controller.dto.request.NamesRequestDto;
import blackjack.controller.dto.response.GamersResultResponse;
import blackjack.controller.dto.response.HandResponseDto;
import blackjack.controller.dto.response.HandResponseDtos;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.BlackJackResult;
import blackjack.domain.rule.HandInitializer;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
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

        HandResponseDtos handResponseDtos = initializeHand(dealer, players, deck);
        outputView.printInitialHand(handResponseDtos);

        runPlayersHitOrStay(deck, players);
        runDealerDrawMoreCard(dealer, deck);

        HandResponseDtos result = getFinalHand(dealer, players);
        outputView.printHandWithScore(result);

        GamersResultResponse gamersResultResponse = getResultResponse(dealer, players);
        outputView.printResult(gamersResultResponse);
    }

    private Players createPlayers(NamesRequestDto namesRequestDto) {
        return Players.from(namesRequestDto.getNames());
    }

    private HandResponseDtos initializeHand(Dealer dealer, Players players, Deck deck) {
        HandInitializer.initialize(dealer, players, deck);
        List<HandResponseDto> handResponseDtoList = new ArrayList<>();
        handResponseDtoList.add(HandResponseDto.ofInitialDealer(dealer));
        for (Player player : players) {
            handResponseDtoList.add(HandResponseDto.of(player));
        }
        return new HandResponseDtos(handResponseDtoList);
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

    private HandResponseDtos getFinalHand(Dealer dealer, Players players) {
        List<HandResponseDto> handResponseDtos = new ArrayList<>();
        handResponseDtos.add(HandResponseDto.of(dealer));
        for (Player player : players) {
            handResponseDtos.add(HandResponseDto.of(player));
        }
        return new HandResponseDtos(handResponseDtos);
    }

    private GamersResultResponse getResultResponse(Dealer dealer, Players players) {
        Map<Player, BlackJackResult> playersResult = new HashMap<>();
        EnumMap<BlackJackResult, Integer> dealerResult = new EnumMap<>(BlackJackResult.class);
        for (Player player : players) {
            BlackJackResult result = player.match(dealer);
            playersResult.put(player, result);
            dealerResult.computeIfPresent(result.reverse(), (key, value) -> ++value);
            dealerResult.putIfAbsent(result.reverse(), 1);
        }

        return new GamersResultResponse(dealerResult, playersResult);
    }
}
