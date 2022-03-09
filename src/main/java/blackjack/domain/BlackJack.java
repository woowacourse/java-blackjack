package blackjack.domain;

import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.*;
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

        OutputView.printTotalScore(generateAllResultDTO(players, dealer));

        List<PlayerResultDTO> playersResult = new ArrayList<>();
        for (Player player : players) {
            playersResult.add(new PlayerResultDTO(player.getName(), player.isWin(dealer.getScore())));
        }

        int loseCount = (int) playersResult.stream().filter(PlayerResultDTO::isWin).count();
        int winCount = playersResult.size() - loseCount;

        DealerResultDTO dealerResult = new DealerResultDTO(dealer.getName(), winCount, loseCount);

        OutputView.printResult(dealerResult, playersResult);

    }

    private List<CurrentCardsDTO> generateAllCurrentCardsDTO(List<Player> players, Dealer dealer) {
        List<CurrentCardsDTO> dtos = new ArrayList<>();
        dtos.add(new CurrentCardsDTO(dealer));
        dtos.addAll(players.stream()
                .map(CurrentCardsDTO::new)
                .collect(Collectors.toList()));
        return dtos;
    }

    private List<TotalScoreDTO> generateAllResultDTO(List<Player> players, Dealer dealer) {
        List<TotalScoreDTO> dtos = new ArrayList<>();
        dtos.add(new TotalScoreDTO(dealer));
        dtos.addAll(players.stream()
                .map(TotalScoreDTO::new)
                .collect(Collectors.toList()));
        return dtos;
    }

}
