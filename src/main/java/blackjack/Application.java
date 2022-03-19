package blackjack;

import static java.util.stream.Collectors.*;

import blackjack.domain.BettingMoney;
import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.dto.GameResultDto;
import blackjack.dto.GameResultsDto;
import blackjack.dto.GamerDto;
import blackjack.dto.PlayersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayCommand;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Deck deck = new Deck(Card.VALUES);
        Dealer dealer = new Dealer(getInitCards(deck));
        Players players = createPlayers(deck);
        OutputView.printStartInfo(GamerDto.from(dealer), PlayersDto.from(players));

        players.playPlayers(deck);
        GamerDto dealerDto = playDealer(deck, dealer);

        OutputView.printResultInfo(dealerDto, PlayersDto.from(players));
        printResult(dealer, players);
    }

    private static List<Card> getInitCards(Deck deck) {
        return List.of(deck.draw(), deck.draw());
    }

    private static Players createPlayers(Deck deck) {
        try {
            List<Player> players = InputView.getNames().stream()
                    .map(name -> new Player(name, createBettingMoney(name), getInitCards(deck)))
                    .collect(toList());
            return new Players(players);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createPlayers(deck);
        }
    }

    private static BettingMoney createBettingMoney(String playerName) {
        try {
            return new BettingMoney(InputView.insertBettingMoney(playerName));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createBettingMoney(playerName);
        }
    }

    public static void playing(Deck deck, Player player) {
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
        players.createPlayerResult(dealer);
        players.createDealerResult(dealer);
        OutputView.printGameResult(GameResultsDto.from(players), GameResultDto.from(dealer));
    }
}
