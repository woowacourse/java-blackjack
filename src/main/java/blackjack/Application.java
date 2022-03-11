package blackjack;

import static java.util.stream.Collectors.*;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Gamer;
import blackjack.domain.Player;
import blackjack.dto.GameResultDto;
import blackjack.dto.GamerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayCommand;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Deck deck = new Deck(Card.VALUES);
        Dealer dealer = new Dealer(deck.getInitCards());
        List<Player> players = createPlayers(deck);
        OutputView.printStartInfo(toGamerDto(dealer), toPlayersDto(players));

        List<GamerDto> playerDtos = playPlayers(deck, players);
        GamerDto dealerDto = playDealer(deck, dealer);

        OutputView.printResultInfo(dealerDto, playerDtos);
        printResult(dealer, players);
    }

    private static List<Player> createPlayers(Deck deck) {
        List<Player> players = InputView.getNames().stream()
                .map(name -> new Player(name, deck.getInitCards()))
                .collect(toList());
        return players;
    }

    public static List<GamerDto> toPlayersDto(List<Player> players) {
        return players.stream()
                .map(GamerDto::from)
                .collect(Collectors.toList());
    }

    public static GamerDto toGamerDto(Gamer gamer) {
        return GamerDto.from(gamer);
    }

    public static List<GamerDto> playPlayers(Deck deck, List<Player> players) {
        players.forEach(player -> playing(deck, player));
        return toPlayersDto(players);
    }

    private static void playing(Deck deck, Player player) {
        PlayCommand playCommand = PlayCommand.YES;
        while (player.isPlaying() && playCommand == PlayCommand.YES) {
            playCommand = InputView.getPlayCommand(player);
            drawCard(deck, player, playCommand);
        }
    }

    private static void drawCard(Deck deck, Player player, PlayCommand playCommand) {
        if (playCommand == PlayCommand.YES) {
            player.combine(deck.draw());
            OutputView.printPlayerCardInfo(toGamerDto(player));
        }
    }

    public static GamerDto playDealer(Deck deck, Dealer dealer) {
        drawDealer(deck, dealer);
        return toGamerDto(dealer);
    }

    private static void drawDealer(Deck deck, Dealer dealer) {
        while (dealer.isDrawable()) {
            OutputView.printDealerDrawableInfo();
            dealer.combine(deck.draw());
        }
    }

    private static Map<String, GameResultDto> createPlayerResult(List<Player> players, Dealer dealer) {
        return players.stream()
                .collect(toMap(player -> player.getName(),
                        player -> GameResultDto.from(player.createResult(dealer.getTotalScore()))));
    }

    private static Map<GameResultDto, Long> createDealerResult(List<Player> players, Dealer dealer) {
        return players.stream()
                .collect(groupingBy(player -> GameResultDto.from(dealer.createResult(player.getTotalScore())),
                        counting()));
    }

    private static void printResult(Dealer dealer, List<Player> players) {
        OutputView.printDealerGameResult(createDealerResult(players, dealer));
        OutputView.printPlayerGameResult(createPlayerResult(players, dealer));
    }
}
