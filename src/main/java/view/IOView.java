package view;

import domain.model.Dealer;
import domain.model.Player;
import domain.vo.Profit;
import java.util.List;

public class IOView {

    private final InputView inputView;
    private final OutputView outputView;

    public IOView(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public List<String> inputNames() {
        return inputView.inputNames();
    }


    public boolean inputCardIntent(final String name) {
        return inputView.inputCardIntent(name);
    }

    public List<Double> inputBattings(final List<String> names) {
        return inputView.inputBattings(names);
    }

    public void printInitialCards(final Dealer dealer, final List<Player> players) {
        outputView.printInitialCards(dealer, players);
    }

    public void printCard(final Player player) {
        outputView.printCard(player);
    }

    public void printDealerReceiveNotice() {
        outputView.printDealerReceiveNotice();
    }

    public void printTotalCardState(final Dealer dealer, final List<Player> players) {
        outputView.printTotalCardState(dealer, players);
    }

    public void printProfits(final Profit dealerProfit, final List<Profit> profits, final List<String> names) {
        outputView.printProfits(dealerProfit, profits, names);
    }
}
