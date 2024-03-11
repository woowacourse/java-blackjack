package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPicker;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Referee;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayerCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public class BlackjackController {

    public void run() {
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()));
        Players players = requestUntilValid(() -> Players.from(InputView.readPlayersName()));
        CardPicker cardPicker = new CardPicker(new Deck(new ArrayList<>(Arrays.asList(Card.values()))));
        BlackjackGame blackjackGame = new BlackjackGame(cardPicker);

        // 딜 후 딜러, 플레이어 카드 출력
        blackjackGame.deal(dealer, players);
        OutputView.printDealAnnounce(dealer.getName(), players.getNames());
        OutputView.printDealCard(dealer.getName(), dealer.getOneDealCard());
        players.forEach(player ->
                OutputView.printDealCards(player.getName(), player.getCards()));
        OutputView.printNewLine();

        // 모든 사용자에게 y or n 요청
        for (Player player : players.getPlayers()) {
            // 블랙잭이면 안물어봄
            if (player.isBlackjack()) {
                continue;
            }
            // y or n 반복
            while (true) {
                PlayerCommand command = requestUntilValid(() -> PlayerCommand.from(InputView.readPlayerCommand(player.getName())));
                blackjackGame.hitOrStand(player, command);
                if (command == PlayerCommand.STAND) {
                    if (player.getCards().size() == 2) {
                        OutputView.printDealCards(player.getName(), player.getCards());
                    }
                    break;
                }
                OutputView.printDealCards(player.getName(), player.getCards());
                if (player.isBust()) {
                    break;
                }
            }
        }

        OutputView.printNewLine();

        // 딜러 16 넘을때까지 뽑
        while (dealer.isScoreUnderBound()) {
            blackjackGame.drawIfScoreUnderBound(dealer);
            OutputView.printDealerHitAnnounce();
        }

        OutputView.printNewLine();

        OutputView.printGamerCards(dealer.getName(), dealer.getCards(), dealer.getScore());
        players.forEach(player ->
                OutputView.printGamerCards(player.getName(), player.getCards(), player.getScore()));


        Referee referee = new Referee();
        referee.calculatePlayersResults(players, dealer);

        printAllGameResult(dealer, referee);
    }

    private void printAllGameResult(Dealer dealer, Referee referee) {
        OutputView.printWinAnnounce();
        OutputView.printDealerWinStatus(dealer.getName(), referee.getDealerResult());
        referee.getPlayersResults().forEach(OutputView::printPlayerWinStatus);
    }


    private <T> T requestUntilValid(Supplier<T> supplier) {
        Optional<T> result = Optional.empty();
        while (result.isEmpty()) {
            result = tryGet(supplier);
        }
        return result.get();
    }

    private <T> Optional<T> tryGet(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }
}
