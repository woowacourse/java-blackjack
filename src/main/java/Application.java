import domain.BetAmount;
import domain.BettingTable;
import domain.Judge;
import domain.card.CardDeck;
import domain.card.Cards;
import domain.gamestate.GameState;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import view.Gain;
import view.Gains;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        Application application = new Application();
        try {
            application.run();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
        }
    }

    private static Cards getInitCards(CardDeck cardDeck) {
        return new Cards(new ArrayList<>(List.of(cardDeck.pick(), cardDeck.pick())));
    }

    private void run() {
        CardDeck cardDeck = initDeck();
        Players players = initPlayers();
        BettingTable bettingTable = initBettingTable(players);
        initCards(players, cardDeck);
        Dealer dealer = new Dealer(getInitCards(cardDeck));

        play(cardDeck, dealer, players);
        end(dealer, players, bettingTable);
    }

    private CardDeck initDeck() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.shuffle();
        return cardDeck;
    }

    private Players initPlayers() {
        List<String> names = InputView.readNames();
        return Players.of(names);
    }

    private BettingTable initBettingTable(Players players) {
        Map<Player, BetAmount> result = new HashMap<>();
        for (Player player : players) {
            int betAmount = InputView.readBetAmount(player.getName());
            result.put(player, BetAmount.of(betAmount));
        }
        return new BettingTable(result);
    }

    private void initCards(Players players, CardDeck cardDeck) {
        players.forEach(player -> player.initCards(getInitCards(cardDeck)));
    }

    private void play(CardDeck cardDeck, Dealer dealer, Players players) {
        OutputView.printStart(dealer, players);
        for (Player player : players) {
            draw(cardDeck, player);
        }

        if (dealer.canAddCard()) {
            dealer.hit(cardDeck.pick());
            OutputView.printHit();
        }
    }

    private void draw(CardDeck cardDeck, Player player) {
        boolean canContinue = false;
        while (player.canAddCard() && (canContinue = InputView.readYesOrNo(player.getName()))) {
            player.hit(cardDeck.pick());
            OutputView.printCard(player);

        }
        if (canContinue) {
            return;
        }
        OutputView.printCard(player);
    }

    private void end(Dealer dealer, Players players, BettingTable bettingTable) {
        OutputView.printResults(dealer, players);
        Gains playerGaines = judgePlayerGaines(dealer, players, bettingTable);
        Gain dealerGain = playerGaines.getDealerGain();
        OutputView.printGains(dealerGain, playerGaines);
    }

    private Gains judgePlayerGaines(Dealer dealer, Players players, BettingTable bettingTable) {
        List<Gain> gains = new ArrayList<>();

        for (Player player : players) {
            GameState gameState = Judge.gameResult(dealer, player);
            gains.add(
                new Gain(player.getName(), gameState.calculate(bettingTable.getBetAmount(player))));
        }
        return new Gains(gains);
    }
}
