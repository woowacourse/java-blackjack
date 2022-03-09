package blackjack.domain;

import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJack {

    public void run() {
        String[] names = InputView.inputName();
        Deck deck = new Deck();

        List<Player> players = Arrays.stream(names)
                .map(n -> new Player(n, deck.initialDraw()))
                .collect(Collectors.toList());
        Dealer dealer = new Dealer(deck.initialDraw());

        OutputView.printInitialCards(generateAllCurrentCardsDTO(players, dealer));

        for (Player player : players) {
            while (player.canHit() && InputView.requestHitOrNot(player.getName())) {
                player.addCard(deck.draw());
                OutputView.printCurrentStatus(new CurrentCardsDTO(player));
            }
            player.endTurn();
        }

        if (dealer.needMoreCard()) {
            dealer.addCard(deck.draw());
            dealer.endTurn();
            OutputView.printDealerAdded(dealer.getName());
        }

        OutputView.printResult(generateAllResultDTO(players, dealer));



    }

    private List<CurrentCardsDTO> generateAllCurrentCardsDTO(List<Player> players, Dealer dealer) {
        List<CurrentCardsDTO> dtos = new ArrayList<>();
        dtos.add(new CurrentCardsDTO(dealer));
        dtos.addAll(players.stream()
                .map(CurrentCardsDTO::new)
                .collect(Collectors.toList()));
        return dtos;
    }

    private List<ResultDTO> generateAllResultDTO(List<Player> players, Dealer dealer) {
        List<ResultDTO> dtos = new ArrayList<>();
        dtos.add(new ResultDTO(dealer));
        dtos.addAll(players.stream()
                .map(ResultDTO::new)
                .collect(Collectors.toList()));
        return dtos;
    }

}
