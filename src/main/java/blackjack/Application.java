package blackjack;

import static java.util.stream.Collectors.*;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Gamer;
import blackjack.domain.Player;
import blackjack.domain.Players;
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
        Dealer dealer = new Dealer(getInitCards(deck));
        Players players = createPlayers(deck);
        OutputView.printStartInfo(toGamerDto(dealer), toPlayersDto(players));

        List<GamerDto> playerDtos = playPlayers(deck, players);
        GamerDto dealerDto = playDealer(deck, dealer);

        OutputView.printResultInfo(dealerDto, playerDtos);
        printResult(dealer, players);
    }

    private static Players createPlayers(Deck deck) {
        List<Player> players = InputView.getNames().stream()
                .map(name -> new Player(name, getInitCards(deck)))
                .collect(toList());
        return new Players(players);
    }

    private static List<Card> getInitCards(Deck deck) {
        return List.of(deck.draw(),deck.draw());
    }

    public static List<GamerDto> toPlayersDto(Players players) {
        return players.getValue().stream()
                .map(GamerDto::from)
                .collect(Collectors.toList());
    }

    public static GamerDto toGamerDto(Gamer gamer) {
        return GamerDto.from(gamer);
    }

    public static List<GamerDto> playPlayers(Deck deck, Players players) {
        players.getValue().forEach(player -> playing(deck, player));
        return toPlayersDto(players);
    }

    private static void playing(Deck deck, Player player) {
        PlayCommand playCommand = PlayCommand.YES;
        while (player.canHit() && playCommand == PlayCommand.YES) {
            playCommand = InputView.getPlayCommand(player);
            drawCard(deck, player, playCommand);
        }
    }

    private static void drawCard(Deck deck, Player player, PlayCommand playCommand) {
        if (playCommand == PlayCommand.YES) {
            player.add(deck.draw());
            OutputView.printPlayerCardInfo(toGamerDto(player));
        }
    }

    public static GamerDto playDealer(Deck deck, Dealer dealer) {
        drawDealer(deck, dealer);
        return toGamerDto(dealer);
    }

    private static void drawDealer(Deck deck, Dealer dealer) {
        while (dealer.canHit()) {
            OutputView.printDealerDrawableInfo();
            dealer.add(deck.draw());
        }
    }

    private static Map<String, GameResultDto> createPlayerResult(Players players, Dealer dealer) {
        return players.getValue().stream()
                .collect(toMap(player -> player.getName(),
                        player -> GameResultDto.from(player.createResult(dealer))));
    }

    private static Map<GameResultDto, Long> createDealerResult(Players players, Dealer dealer) {
        return players.getValue().stream()
                .collect(groupingBy(player -> GameResultDto.from(dealer.createResult(player)),
                        counting()));
    }

    private static void printResult(Dealer dealer, Players players) {
        OutputView.printDealerGameResult(createDealerResult(players, dealer));
        OutputView.printPlayerGameResult(createPlayerResult(players, dealer));
    }
}
