import domain.card.Deck;
import domain.dto.PlayerResult;
import domain.dto.Profit;
import domain.participant.BetMoney;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.ResultView;

public class BlackjackController {
    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        Deck deck = Deck.createWithAllCards();
        Players players = readPlayerInfo(deck);
        Dealer dealer = Dealer.createReady(deck.drawInitialCards());

        resultView.printParticipantsCards(players.getPlayers(), dealer);

        hitStandPlayers(players, deck);
        hitStandDealer(dealer, deck);

        List<PlayerResult> playerResults = collectPlayerResults(players, dealer);

        List<Profit> profits = calculatePlayerProfits(playerResults);

        BetMoney dealerProfit = calculateDealerResult(profits);

        resultView.printCardsWithResult(players.getPlayers(), dealer);
        resultView.printProfits(profits, dealerProfit);
    }

    private Players readPlayerInfo(Deck deck) {
        List<String> names = inputView.readPlayerNames();
        List<String> betMoneys = inputView.readBetMoney(names);

        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            playerList.add(Player.of(deck.drawInitialCards(), names.get(i), betMoneys.get(i)));
        }

        return Players.from(playerList);
    }

    private void hitStandPlayers(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            hitStandPlayer(deck, player);
        }
    }

    private void hitStandPlayer(Deck deck, Player player) {
        while (!player.isFinished()) {
            hitByDecision(deck, player);
            resultView.printCards(player);
        }
    }

    private void hitByDecision(Deck deck, Player player) {
        if (inputView.readHitStand(player)) {
            player.draw(deck.draw());
            return;
        }
        player.stay();
    }

    private void hitStandDealer(Dealer dealer, Deck deck) {
        while (!dealer.isFinished()) {
            hitDealerByCondition(deck, dealer);
        }
        resultView.printDealerHitStand(false);
    }

    private void hitDealerByCondition(Deck deck, Dealer dealer) {
        if (dealer.isHittable()) {
            dealer.draw(deck.draw());
            resultView.printDealerHitStand(true);
            return;
        }
        dealer.stay();
    }

    private List<PlayerResult> collectPlayerResults(Players players, Dealer dealer) {
        return players.getPlayers().stream()
                .map(player -> new PlayerResult(player, player.judge(dealer)))
                .toList();
    }

    private List<Profit> calculatePlayerProfits(List<PlayerResult> playerResults) {
        return playerResults.stream()
                .map(result -> new Profit(result.player(), result.player().getProfit(result.result())))
                .toList();
    }

    private BetMoney calculateDealerResult(List<Profit> profits) {
        return profits.stream()
                .map(Profit::betMoney)
                .reduce(BetMoney.ZERO, BetMoney::sub);
    }
}
