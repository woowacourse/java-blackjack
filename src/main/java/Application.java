import domain.GameResult;
import domain.Judge;
import domain.card.CardDeck;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import view.DealerResult;
import view.InputView;
import view.OutputView;
import view.PlayerResult;
import view.PlayerResults;

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
        Dealer dealer = new Dealer(getInitCards(cardDeck));
        Players players = initPlayers(cardDeck);
        play(cardDeck, dealer, players);
        end(dealer, players);
    }

    private CardDeck initDeck() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.shuffle();
        return cardDeck;
    }

    private Players initPlayers(CardDeck cardDeck) {
        List<String> names = InputView.readNames();
        Players players = Players.of(names);
        players.forEach(player -> player.initCards(getInitCards(cardDeck)));
        return players;
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

    private void end(Dealer dealer, Players players) {
        OutputView.printResults(dealer, players);
        PlayerResults playerResults = judgePlayerScores(dealer, players);
        DealerResult dealerResult = playerResults.getDealerResult();
        OutputView.printWinOrLose(dealerResult, playerResults);
    }

    private PlayerResults judgePlayerScores(Dealer dealer, Players players) {
        List<PlayerResult> playerResults = new ArrayList<>();

        for (Player player : players) {
            GameResult gameResult = Judge.of(dealer, player);
            playerResults.add(new PlayerResult(player.getName(), gameResult));
        }
        return new PlayerResults(playerResults);
    }
}
