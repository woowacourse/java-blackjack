package blackjack;

import static java.util.stream.Collectors.*;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.dto.DealerDto;
import blackjack.dto.DealerResultsDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayerResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayCommand;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Deck deck = new Deck(Card.VALUES);
        Dealer dealer = new Dealer(deck.getInitCards());

        List<String> names = InputView.getNames();
        List<Player> players = names.stream()
                .map(name -> new Player(name, deck.getInitCards()))
                .collect(toList());

        List<PlayerDto> playersDto = toPlayersDto(players);

        OutputView.printStartInfo(DealerDto.from(dealer), playersDto);

        players.forEach(player -> playing(deck, player));
        drawDealer(deck, dealer);

        OutputView.printResultInfo(DealerDto.from(dealer), toPlayersDto(players));

        OutputView.printDealerGameResult(DealerResultsDto.of(players, dealer));
        OutputView.printPlayerGameResult(createPlayerResult(players, dealer));
    }

    private static List<PlayerDto> toPlayersDto(List<Player> players) {
        return players.stream()
                .map(PlayerDto::from)
                .collect(toList());
    }

    public static void playing(Deck deck, Player player) {
        PlayCommand playCommand = PlayCommand.YES;
        while (player.isPlaying() && playCommand == PlayCommand.YES) {
            playCommand = InputView.getPlayCommand(player);
            drawCard(deck, player, playCommand);
        }
    }

    private static void drawCard(Deck deck, Player player, PlayCommand playCommand) {
        if (playCommand == PlayCommand.YES) {
            player.combine(deck.draw());
            OutputView.printPlayerCardInfo(PlayerDto.from(player));
        }
    }

    public static void drawDealer(Deck deck, Dealer dealer) {
        while (dealer.isDrawable()) {
            OutputView.printDealerDrawableInfo();
            dealer.combine(deck.draw());
        }
    }

    public static List<PlayerResultDto> createPlayerResult(List<Player> players, Dealer dealer) {
        return players.stream()
                .map(player -> PlayerResultDto.of(player, dealer))
                .collect(toList());
    }
}
