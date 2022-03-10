package blackjack;

import static blackjack.view.OutputView.*;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Name;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;

public class BlackjackApplication {

    public static void main(String[] args) {
        Players players = requestPlayers();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        players.drawAll(deck);
        players.drawAll(deck);
        dealer.drawCard(deck);
        dealer.drawCard(deck);
        List<PlayerDto> playerDtos = toDto(players);
        PlayerDto dealerDto = toDto(dealer);

        printInitGameMessage(playerDtos, dealerDto);
        printOpenCard(playerDtos, dealerDto);
    }

    private static PlayerDto toDto(Dealer dealer) {
        return PlayerDto.from(dealer);
    }

    private static List<PlayerDto> toDto(Players players) {
        return players.getValue()
            .stream()
            .map(PlayerDto::from)
            .collect(Collectors.toList());
    }

    private static Players requestPlayers() {
        List<String> inputNames = InputView.requestNames();

        try {
            List<Player> players = inputNames.stream()
                .map(String::trim)
                .map(Name::new)
                .map(Player::new)
                .collect(Collectors.toList());
            return new Players(players);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestPlayers();
        }
    }
}
