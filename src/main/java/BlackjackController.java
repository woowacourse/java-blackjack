import domain.BetMoney;
import domain.Result;
import domain.Score;
import domain.card.Deck;
import domain.dto.PlayerResult;
import domain.dto.Profit;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.state.State;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.ResultView;

public class BlackjackController {
    public static final int INITIAL_CARD_COUNT = 2;
    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        Players players = readPlayerInfos();
        Dealer dealer = Dealer.createReady();
        Deck deck = Deck.createWithAllCards();

        giveHand(players, dealer, deck);

        resultView.printParticipantsCards(players.getPlayers(), dealer);

        hitStandPlayers(players, deck);
        hitStandDealer(dealer, deck);

        List<PlayerResult> playerResults = new ArrayList<>();

//        for (Player player : players.getPlayers()) {
//            playerResults.add(new PlayerResult(player, GameResult.judgeResult(player, dealer)));
//        }

        State state = dealer.getState();
        for (Player player : players.getPlayers()) {
            Result judge = player.getState().judge(state);
            playerResults.add(new PlayerResult(player, judge));
        }

        List<Profit> profits = new ArrayList<>();
        for (PlayerResult playerResult : playerResults) {
            profits.add(new Profit(playerResult.player(), playerResult.player().getProfit(playerResult.result())));
        }

        BetMoney dealerResult = calculateDealerResult(profits);

        resultView.printCardsWithResult(players.getPlayers(), dealer);

        resultView.printProfits(profits, dealerResult);

    }

    private Players readPlayerInfos() {
        List<String> names = inputView.readPlayerNames();
        List<String> betMoneys = inputView.readBetMoney(names);

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            players.add(Player.of(names.get(i), betMoneys.get(i)));
        }
        return Players.from(players);
    }

    private void hitStandPlayers(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            while (!player.isBust() && inputView.readHitStand(player)) {
                player.addCard(deck.draw());
                resultView.printCards(player);
            }
        }
    }

    private static void giveHand(Players players, Dealer dealer, Deck deck) {
        players.addCardsToEachPlayers(deck, INITIAL_CARD_COUNT);
        dealer.addCards(deck.drawWithAmount(INITIAL_CARD_COUNT));
    }

    private void hitStandDealer(Dealer dealer, Deck deck) {
        while (dealer.isHittable(Score.DEALER_HIT_STAND_BOUNDARY)) {
            dealer.addCard(deck.draw());
            resultView.printDealerHitStand(true);
        }
        resultView.printDealerHitStand(false);
    }

    private BetMoney calculateDealerResult(List<Profit> profits) {
        return profits.stream()
                .map(Profit::betMoney)
                .reduce(BetMoney.ZERO, BetMoney::sub);
    }
}
