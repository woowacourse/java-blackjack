package blackjack;

import blackjack.domain.gamers.Name;
import blackjack.domain.result.Bettings;
import blackjack.domain.gamers.Dealer;
import blackjack.domain.gamers.Gamers;
import blackjack.domain.result.Judge;
import blackjack.domain.result.Money;
import blackjack.domain.gamers.Player;
import blackjack.domain.gamers.Players;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.card.ShuffledDeckFactory;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        final Gamers gamers = createGamers();
        final Bettings bettings = createBettings(gamers);
        final Judge judge = createJudge(gamers);

        drawInitialHands(gamers);
        hitGamers(gamers);

        OutputView.printFinalState(
                createDealerDto(gamers.getDealer().getHand()), createPlayerDtos(gamers.getPlayers()));
        OutputView.printFinalProfits(bettings.calculateDealerProfit(judge), bettings.calculatePlayerProfits(judge));
    }

    private static Gamers createGamers() {
        final Deck deck = new ShuffledDeckFactory().create();
        final Dealer dealer = Dealer.from(deck);
        final Players players = Players.from(InputView.readPlayerNames());
        return new Gamers(dealer, players);
    }

    private static Bettings createBettings(final Gamers gamers) {
        final Map<Player, Money> bettings = new LinkedHashMap<>();
        for (final Name playerName : gamers.playerNames()) {
            final Money betting = new Money(InputView.readBettingMoney(playerName));
            bettings.put(gamers.findPlayerBy(playerName), betting);
        }
        return new Bettings(bettings);
    }

    private static Judge createJudge(final Gamers gamers) {
        return new Judge(gamers.getDealer());
    }

    private static void drawInitialHands(final Gamers gamers) {
        gamers.drawInitialGamersHand();

        final DealerDto dealerDto = createDealerDto(gamers.openDealerFirstCard());
        final List<PlayerDto> playerDtos = createPlayerDtos(gamers.getPlayers());
        OutputView.printInitialState(dealerDto, playerDtos);
    }

    private static DealerDto createDealerDto(final Card card) {
        return createDealerDto(new Hand(List.of(card)));
    }

    private static DealerDto createDealerDto(final Hand hand) {
        return new DealerDto(hand.getCards(), hand.calculateOptimalSum());
    }

    private static List<PlayerDto> createPlayerDtos(final Players players) {
        return players.getPlayers().stream()
                .map(Application::createPlayerDto)
                .toList();
    }

    private static PlayerDto createPlayerDto(final Player player) {
        return new PlayerDto(player.getName(), player.getHand().getCards(), player.calculateScore());
    }

    private static void hitGamers(final Gamers gamers) {
        hitPlayers(gamers);
        OutputView.printLineSeparator();
        hitDealer(gamers);
        OutputView.printLineSeparator();
    }

    private static void hitPlayers(final Gamers gamers) {
        for (final Name playerName : gamers.playerNames()) {
            hitPlayer(gamers, playerName);
        }
    }

    private static void hitPlayer(final Gamers gamers, final Name playerName) {
        while (gamers.canPlayerHit(playerName) && InputView.readDoesWantHit(playerName)) {
            gamers.hitPlayer(playerName);
            OutputView.printCurrentState(createPlayerDto(gamers.findPlayerBy(playerName)));
        }
    }

    private static void hitDealer(final Gamers gamers) {
        while (gamers.canDealerHit()) {
            gamers.hitDealer();
            OutputView.printDealerDrawMessage();
        }
    }
}
