package blackjack.controller;

import blackjack.controller.dto.GamersResultResponse;
import blackjack.controller.dto.HandResponseDto;
import blackjack.controller.dto.NamesRequestDto;
import blackjack.domain.deck.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.BlackJackResult;
import blackjack.domain.result.PlayerResultMatcher;
import blackjack.domain.rule.HandInitializer;
import blackjack.domain.rule.PlayerAnswer;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    public Players createPlayers(NamesRequestDto namesRequestDto) {
        return Players.from(namesRequestDto.getNames());
    }

    public List<HandResponseDto> initializeHand(Dealer dealer, Players players, Deck deck) {
        HandInitializer.initialize(dealer, players, deck);
        List<HandResponseDto> handResponseDtos = new ArrayList<>();
        handResponseDtos.add(HandResponseDto.ofDealer(dealer));
        for (Player player : players) {
            handResponseDtos.add(HandResponseDto.of(player));
        }
        return handResponseDtos;
    }

    public HandResponseDto drawMoreCard(Player player, Deck deck, PlayerAnswer playerAnswer) {
        if (playerAnswer.isYes()) {
            player.draw(deck.pick());
        }
        return HandResponseDto.of(player);
    }

    public String drawMoreCard(Dealer dealer, Deck deck) {
        dealer.draw(deck.pick());
        return "딜러는 16이하라 한 장의 카드를 더 뽑았습니다.";
    }

    public List<HandResponseDto> getFinalHand(Dealer dealer, Players players) {
        List<HandResponseDto> handResponseDtos = new ArrayList<>();
        handResponseDtos.add(HandResponseDto.of(dealer));
        for (Player player : players) {
            handResponseDtos.add(HandResponseDto.of(player));
        }
        return handResponseDtos;
    }

    public GamersResultResponse getResult(Dealer dealer, Players players) {
        EnumMap<BlackJackResult, Integer> dealerResult = new EnumMap<>(BlackJackResult.class);
        Map<Player, BlackJackResult> playersResult = new HashMap<>();

        for (Player player : players) {
            BlackJackResult result = PlayerResultMatcher.match(dealer, player);
            playersResult.put(player, result);
            dealerResult.computeIfPresent(result.reversed(), (key, value) -> ++value);
            dealerResult.putIfAbsent(result.reversed(), 1);
        }

        return new GamersResultResponse(dealerResult, playersResult);
    }
}
