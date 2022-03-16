package blackjack;

import static java.util.stream.Collectors.*;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.dto.GameResultDto;
import blackjack.dto.GamerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayCommand;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static final int BATTING_MONEY_INIT_VALUE = 0;

    public static void main(String[] args) {
        Deck deck = new Deck(Card.VALUES);
        Dealer dealer = new Dealer(getInitCards(deck), BATTING_MONEY_INIT_VALUE);
        Players players = createPlayers(deck);
        OutputView.printStartInfo(GamerDto.from(dealer), toPlayersDto(players));

        List<GamerDto> playerDtos = playPlayers(deck, players);
        GamerDto dealerDto = playDealer(deck, dealer);

        OutputView.printResultInfo(dealerDto, playerDtos);
        printResult(dealer, players);
    }

    private static List<Card> getInitCards(Deck deck) {
        return List.of(deck.draw(), deck.draw());
    }

    private static Players createPlayers(Deck deck) {
        List<Player> players = InputView.getNames().stream()
                .map(name -> new Player(name, createBattingMoney(name), getInitCards(deck)))
                .collect(toList());
        return new Players(players);
    }

    private static int createBattingMoney(String playerName) {
        return InputView.insertBattingMoney(playerName);
    }

    private static List<GamerDto> toPlayersDto(Players players) {
        return players.getValue().stream()
                .map(GamerDto::from)
                .collect(Collectors.toList());
    }

    private static List<GamerDto> playPlayers(Deck deck, Players players) {
        players.getValue().forEach(player -> playing(deck, player));
        return toPlayersDto(players);
    }

    private static void playing(Deck deck, Player player) {
        PlayCommand playCommand = PlayCommand.YES;
        while (player.canHit() && playCommand.isContinue()) {
            playCommand = InputView.getPlayCommand(player);
            drawCard(deck, player, playCommand);
        }
    }

    private static void drawCard(Deck deck, Player player, PlayCommand playCommand) {
        if (playCommand.isContinue()) {
            player.addCard(deck.draw());
            OutputView.printPlayerCardInfo(GamerDto.from(player));
        }
    }

    private static GamerDto playDealer(Deck deck, Dealer dealer) {
        drawDealer(deck, dealer);
        return GamerDto.from(dealer);
    }

    private static void drawDealer(Deck deck, Dealer dealer) {
        while (dealer.canHit()) {
            OutputView.printDealerDrawableInfo();
            dealer.addCard(deck.draw());
        }
    }

    private static void printResult(Dealer dealer, Players players) {
        OutputView.printGameResult(createPlayerResult(players, dealer), createDealerResult(players, dealer));
    }

    private static List<GameResultDto> createPlayerResult(Players players, Dealer dealer) {
        return players.getValue().stream()
                .filter(player -> player.calculateBattingMoneyResult(dealer))
                .map(player -> GameResultDto.from(player))
                .collect(toList());
    }

    private static GameResultDto createDealerResult(Players players, Dealer dealer) {
        players.getValue().forEach(player -> dealer.calculateBattingMoneyResult(player));
        return GameResultDto.from(dealer);
    }
}
