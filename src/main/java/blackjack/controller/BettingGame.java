package blackjack.controller;

import blackjack.domain.participant.*;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.ResponseDTO.ProfitDTO;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BettingGame extends BlackJackController {

    @Override
    public void run() {
        List<Name> names = createNames();
        List<Money> bettingMoneys = createMoneys(names);

        Players players = new Players(names, bettingMoneys);
        Dealer dealer = new Dealer();

        dealFirstCards(dealer, players);

        dealAdditionalCards(players, dealer);

        showProfit(players, dealer);
    }

    private List<Name> createNames() {
        return InputView.enterNames().stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private List<Money> createMoneys(List<Name> names) {
        return InputView.enterBettingMoney(names).stream()
                .map(Money::new)
                .collect(Collectors.toList());
    }


    private void dealFirstCards(Dealer dealer, Players players) {
        deck.dealFirstCards(dealer);

        for (Player player : players.getPlayers()) {
            deck.dealFirstCards(player);
        }

        OutputView.printInitialStatus(players, dealer);
    }

    private void dealAdditionalCards(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            dealAdditionalCards(player);
        }

        dealAdditionalCards(dealer);

        OutputView.printFinalStatus(players, dealer);
    }

    private void dealAdditionalCards(Player player) {
        while (player.canGetMoreCard() && player.wantMoreCard(readYesOrNo(player))) {
            deck.dealAdditionalCard(player);
            OutputView.printCardsStatus(player.name(), player.showCards());
        }
    }

    private void dealAdditionalCards(Dealer dealer) {
        while (dealer.canGetMoreCard()) {
            deck.dealAdditionalCard(dealer);
            OutputView.printDealerGetMoreCard();
        }
    }

    private String readYesOrNo(Player player) {
        return InputView.readYesOrNo(player.name());
    }

    private void showProfit(Players players, Dealer dealer) {
        List<PlayerResult> playerResults = players.createPlayerResults(dealer);

        List<ProfitDTO> playerDTOS = playerResults.stream()
                .map(result -> new ProfitDTO(result.name(), result.computeProfit()))
                .collect(Collectors.toList());

        ProfitDTO dealerDTO = new ProfitDTO(dealer.name(), dealer.computeDealerProfit(playerResults));

        List<ProfitDTO> profitDTOS = new ArrayList<>();
        profitDTOS.add(dealerDTO);
        profitDTOS.addAll(playerDTOS);

        OutputView.printFinalProfit(profitDTOS);
    }
}
