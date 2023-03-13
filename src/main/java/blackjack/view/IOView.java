package blackjack.view;

import blackjack.domain.card.dto.CardResponse;
import java.util.List;
import java.util.Map;

public class IOView {

    private final InputView inputView;
    private final OutputView outputView;

    public IOView(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public List<String> inputPlayerNames() {
        return inputView.inputPlayerNames();
    }

    public DrawCommand inputCommand(final String playerName) {
        return inputView.inputCommand(playerName);
    }

    public int inputPlayerMoney(final String playerName) {
        return inputView.inputPlayerMoney(playerName);
    }

    public void printInitialCards(
            final CardResponse dealerCard,
            final Map<String, List<CardResponse>> playerNameToCards) {
        outputView.printInitialCards(dealerCard, playerNameToCards);
    }

    public void printCardStatusOfPlayer(final String planerName, final List<CardResponse> playerCardsResponse) {
        outputView.printCardStatusOfPlayer(planerName, playerCardsResponse);
    }

    public void printDealerCardDrawMessage() {
        outputView.printDealerCardDrawMessage();
    }

    public void printFinalStatusOfDealer(final int score, final List<CardResponse> dealerCards) {
        outputView.printFinalStatusOfDealer(score, dealerCards);
    }

    public void printFinalStatusOfPlayers(
            final Map<String, List<CardResponse>> playersCardsResponse,
            final Map<String, Integer> playersScore) {
        outputView.printFinalStatusOfPlayers(playersCardsResponse, playersScore);
    }

    public void printFinalMoney(final Map<String, Integer> calculatePlayersMoney) {
        outputView.printFinalMoney(calculatePlayersMoney);
    }

    public void printError(final Exception e) {
        System.err.println(e.getMessage());
    }
}
