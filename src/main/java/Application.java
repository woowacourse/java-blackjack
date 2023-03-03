import domain.CardDeck;
import domain.Cards;
import domain.Dealer;
import domain.GameResult;
import domain.Judge;
import domain.Player;
import java.util.ArrayList;
import java.util.List;
import view.DealerScore;
import view.InputView;
import view.OutputView;
import view.PlayerScore;

public class Application {

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    public void run() {
        CardDeck cardDeck = initDeck();
        Dealer dealer = new Dealer(getInitCards(cardDeck));
        List<Player> players = makePlayers(cardDeck);
        play(cardDeck, dealer, players);
        end(dealer, players);
    }

    private CardDeck initDeck() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.shuffle();
        return cardDeck;
    }

    private static Cards getInitCards(CardDeck cardDeck) {
        return new Cards(new ArrayList<>(List.of(cardDeck.pick(), cardDeck.pick())));
    }

    private List<Player> makePlayers(CardDeck cardDeck) {
        List<String> names = InputView.readNames();
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            Cards playerCards = getInitCards(cardDeck);
            players.add(new Player(name, playerCards));
        }

        return players;
    }

    private void play(CardDeck cardDeck, Dealer dealer, List<Player> players) {
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
        boolean isContinue = false;
        while (player.canAddCard() && (isContinue = InputView.readYesOrNo(player.getName()))) {
            player.hit(cardDeck.pick());
            OutputView.printCard(player);

        }
        if (isContinue) {
            return;
        }
        OutputView.printCard(player);
    }

    private void end(Dealer dealer, List<Player> players) {
        OutputView.printResults(dealer, players);
        List<PlayerScore> playerScores = judgePlayerScores(dealer, players);
        DealerScore dealerScore = new DealerScore(playerScores);
        OutputView.printWinOrLose(dealerScore, playerScores);
    }

    private List<PlayerScore> judgePlayerScores(Dealer dealer, List<Player> players) {
        List<PlayerScore> playerScores = new ArrayList<>();

        for (Player player : players) {
            GameResult gameResult = Judge.of(dealer, player);
            playerScores.add(new PlayerScore(player.getName(), gameResult));
        }
        return playerScores;
    }
}
