package blackjack;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        final Dealer dealer = Dealer.getInstance();
        final List<Player> players = getPlayersByNamesAndBettingMoney(getPlayerNames());

        initializeParticipants(dealer, players);
        OutputView.printParticipantHands(dealer, players);

        progressPlayersTurn(dealer, players);
        progressDealerTurn(dealer);

        OutputView.printCardResult(dealer, players);
        OutputView.printGameResult(GameResultDto.calculate(dealer, players));
    }

    private static List<Name> getPlayerNames() {
        try {
            String playerNames = InputView.getPlayerName();
            return toNameList(playerNames);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return getPlayerNames();
        }
    }

    private static List<Player> getPlayersByNamesAndBettingMoney(List<Name> names) {
        return names.stream()
                .map(name -> Player.from(name, getBettingMoney(name)))
                .collect(Collectors.toList());
    }

    private static List<Name> toNameList(String playerNames) {
        return Arrays.stream(playerNames.split(","))
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private static Money getBettingMoney(Name name) {
        try {
            String bettingMoneyInput = InputView.getBettingMoney(name.getName());
            return Money.getBettingMoney(bettingMoneyInput);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return getBettingMoney(name);
        }

    }

    private static void initializeParticipants(Dealer dealer, List<Player> players) {
        dealer.drawBaseCard();
        dealer.drawBaseCardToPlayers(players);
    }

    private static void progressPlayersTurn(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            playerTurn(dealer, player);
        }
    }

    private static void playerTurn(Dealer dealer, Player player) {
        try {
            deal(dealer, player);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            playerTurn(dealer, player);
        }
    }

    private static void deal(Dealer dealer, Player player) {
        while (!player.isBust() && InputView.askDrawOrStay(player.getName())) {
            dealer.deal(player);
            OutputView.printHand(player);
        }
    }

    private static void progressDealerTurn(Dealer dealer) {
        while (dealer.shouldReceive()) {
            OutputView.printDealerDrawMessage();
            dealer.selfDraw();
        }
    }
}
