package service;

import constant.HitOrStand;
import constant.PolicyConstant;
import constant.Result;
import converter.BlackjackConverter;
import domain.Card;
import domain.CardMachine;
import domain.Dealer;
import domain.Player;
import dto.BlackjackResultDto;
import dto.DealerResultDto;
import dto.HandDto;
import dto.PlayerResultDto;
import dto.PlayersDto;
import exception.ErrorMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class BlackjackService {

    private final BlackjackConverter blackjackConverter = new BlackjackConverter();
    private final CardMachine cardMachine = new CardMachine();

    public PlayersDto createPlayers(List<String> names) {
        validatePlayerNames(names);
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(name));
        }
        return blackjackConverter.convertPlayersDto(players, new Dealer(PolicyConstant.DEALER_NAME));
    }

    private void validatePlayerNames(List<String> names) {
        validatePlayerCount(names);
        validatePlayerCount(names.size());
    }

    private void validatePlayerCount(List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_DUPLICATED.getMessage());
        }
    }

    private void validatePlayerCount(int playerCount) {
        if (!(PolicyConstant.PLAYER_MIN_COUNT <= playerCount
            && playerCount <= PolicyConstant.PLAYER_MAX_COUNT)) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE.getMessage());
        }
    }

    public Card drawCard() {
        return cardMachine.drawCard();
    }

    public PlayersDto dealInitialCards(PlayersDto playersDto) {
        List<Player> players = playersDto.players();
        for (Player player : players) {
            player.addCard(drawCard());
            player.addCard(drawCard());
        }
        Dealer dealer = playersDto.dealer();
        dealer.addCard(drawCard());
        dealer.addCard(drawCard());
        return blackjackConverter.convertPlayersDto(players, dealer);
    }

    public boolean drawDealerCard(Dealer dealer) {
        if (dealer.calculateScore() <= PolicyConstant.DEALER_HIT_MAX_SCORE) {
            dealer.addCard(drawCard());
            return true;
        }

        return false;
    }

    public List<PlayerResultDto> calculatePlayerResults(PlayersDto playersDto) {
        Dealer dealer = playersDto.dealer();
        List<PlayerResultDto> playerResultDtoList = new ArrayList<>();
        for (Player player : playersDto.players()) {
            Result result = calculatePlayerResult(dealer, player);
            playerResultDtoList.add(
                blackjackConverter.convertPlayerResultDto(player.getName(), result));
        }
        return playerResultDtoList;
    }

    public DealerResultDto calculateDealerResult(PlayersDto playersDto) {
        List<PlayerResultDto> playerResultDtoList = calculatePlayerResults(playersDto);
        int win = 0, draw = 0, lose = 0;
        for (PlayerResultDto playerResultDto : playerResultDtoList) {
            Result result = playerResultDto.result();
            win += judgeResult(result, Result.LOSE);
            draw += judgeResult(result, Result.DRAW);
            lose += judgeResult(result, Result.WIN);
        }
        return new DealerResultDto(win, draw, lose);
    }

    private int judgeResult(Result result, Result playerResult) {
        if (result.equals(playerResult)) {
            return 1;
        }
        return 0;
    }

    private Result calculatePlayerResult(Dealer dealer, Player player) {
        if (dealer.isBust() && player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust() && !player.isBust()) {
            return Result.WIN;
        }
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (player.calculateScore() > dealer.calculateScore()) {
            return Result.WIN;
        }
        if (player.calculateScore() == dealer.calculateScore()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    public HandDto generateHandDto(Player player) {
        return blackjackConverter.convertHandDto(player);
    }

    private int calculateScore(Player player) {
        return player.calculateScore();
    }

    public List<BlackjackResultDto> generateBlackjackResultDto(PlayersDto playersDto) {
        List<BlackjackResultDto> blackjackResultDtoList = new ArrayList<>();
        addResult(playersDto.dealer(), blackjackResultDtoList);
        for (Player player : playersDto.players()) {
            addResult(player, blackjackResultDtoList);
        }
        return Collections.unmodifiableList(blackjackResultDtoList);
    }

    private void addResult(Player player, List<BlackjackResultDto> blackjackResultDtoList) {
        HandDto handDto = blackjackConverter.convertHandDto(player);
        int score = calculateScore(player);
        blackjackResultDtoList.add(
            blackjackConverter.convertBlackjackResultDto(handDto, score));
    }

    public void validateHitOrStand(String hitOrStand) {
        if (!hitOrStand.strip().equals(HitOrStand.HIT.getHitOrStand()) && !hitOrStand.strip()
            .equals(HitOrStand.STAND.getHitOrStand())) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_YES_NO_INPUT.getMessage());
        }
    }

    public boolean shouldRepeat(Player player, String hitOrStand) {
        return hitOrStand.strip().equals(HitOrStand.HIT.getHitOrStand()) && !player.isBust();
    }

    public void updatePlayer(Player player) {
        player.addCard(drawCard());
    }

    public boolean isNo(String hitOrStand) {
        return hitOrStand.equals(HitOrStand.STAND.getHitOrStand());
    }

    public List<HandDto> generaterHandDtoList(PlayersDto playersDto) {
        return blackjackConverter.convertHandDtoList(playersDto);
    }
}
