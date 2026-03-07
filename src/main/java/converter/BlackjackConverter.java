package converter;

import constant.Result;
import domain.Dealer;
import domain.Hand;
import domain.Player;
import dto.BlackjackResultDto;
import dto.HandDto;
import dto.PlayerResultDto;
import dto.PlayersDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackConverter {

    public PlayersDto convertPlayersDto(List<Player> players, Dealer dealer) {
        return new PlayersDto(players, dealer);
    }

    public HandDto convertHandDtoForDealer(Dealer dealer) {
        Hand hand = dealer.getOnlyFirstHand();
        return new HandDto(dealer.getName(), hand.getCardNames());
    }

    public PlayerResultDto convertPlayerResultDto(String name, Result result) {
        return new PlayerResultDto(name, result);
    }

    public HandDto convertHandDto(Player player) {
        Hand hand = player.getHand();
        return new HandDto(player.getName(), hand.getCardNames());
    }

    public BlackjackResultDto convertBlackjackResultDto(HandDto handDto, int score) {
        return new BlackjackResultDto(handDto, score);
    }

    public List<HandDto> convertHandDtoList(PlayersDto playersDto) {
        Dealer dealer = playersDto.dealer();
        List<Player> players = playersDto.players();
        List<HandDto> handDtoList = new ArrayList<>();
        handDtoList.add(convertHandDtoForDealer(dealer));
        for (Player player : players) {
            handDtoList.add(convertHandDto(player));
        }
        return Collections.unmodifiableList(handDtoList);
    }
}
