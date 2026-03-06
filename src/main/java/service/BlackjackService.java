package service;

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
        return blackjackConverter.convertPlayersDto(players, new Dealer("딜러"));
    }

    private void validatePlayerNames(List<String> names) {
        validatePlayerCount(names);
        validatePlayerCount(names.size());
    }

    private void validatePlayerCount(List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("[ERROR] 게임 참가자의 이름은 중복 되어선 안됩니다.");
        }
    }

    private void validatePlayerCount(int playerCount) {
        if (!(2 <= playerCount && playerCount <= 8)) {
            throw new IllegalArgumentException("[ERROR] 게임 참가자의 수는 2~8명 사이여야 합니다.");
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
        if (dealer.calculateScore() <= 16) {
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
        int win = 0;
        int draw = 0;
        int lose = 0;

        for (PlayerResultDto playerResultDto : playerResultDtoList) {
            switch (playerResultDto.result()) {
                case LOSE -> win++;
                case DRAW -> draw++;
                case WIN -> lose++;
            }
        }
        return new DealerResultDto(win, draw, lose);
    }

    private Result calculatePlayerResult(Dealer dealer, Player player) {
        if (dealer.isBust()) {
            if (player.isBust()) {
                return Result.LOSE;
            }
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

        Dealer dealer = playersDto.dealer();
        HandDto dealerHandDto = blackjackConverter.convertHandDto(dealer);
        int dealerScore = calculateScore(dealer);

        blackjackResultDtoList.add(
            blackjackConverter.convertBlackjackResultDto(dealerHandDto, dealerScore));
        for (Player player : playersDto.players()) {
            HandDto playerHandDto = blackjackConverter.convertHandDto(player);
            int playerScore = calculateScore(player);
            blackjackResultDtoList.add(
                blackjackConverter.convertBlackjackResultDto(playerHandDto, playerScore));
        }
        return Collections.unmodifiableList(blackjackResultDtoList);
    }

    public void validateHitOrStand(String hitOrStand) {
        if (!hitOrStand.strip().equals("y") && !hitOrStand.strip().equals("n")) {
            throw new IllegalArgumentException("[ERROR] \"y\" 또는 \"n\"을 입력해야 합니다.");
        }
    }

    public boolean shouldRepeat(Player player, String hitOrStand) {
        return hitOrStand.strip().equals("y") && !player.isBust();
    }

    public void updatePlayer(Player player) {
        player.addCard(drawCard());
    }

    public boolean isNo(String hitOrStand) {
        return hitOrStand.equals("n");
    }

    public List<HandDto> generaterHandDtoList(PlayersDto playersDto) {
        return blackjackConverter.convertHandDtoList(playersDto);
    }
}
