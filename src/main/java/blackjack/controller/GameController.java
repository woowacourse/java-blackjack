package blackjack.controller;

import blackjack.domain.BlackjackRepository;
import blackjack.domain.card.group.CardDeck;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.group.Players;
import blackjack.domain.result.ResultStatistic;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public final class GameController {
    public void run() {
        BlackjackRepository blackjackRepository = BlackjackRepository.from(getPlayers());

        initGame(blackjackRepository);
        startGame(blackjackRepository);
        endGame(blackjackRepository);
    }

    private Players getPlayers() {
        return Players.fromText(InputView.inputPlayerNames());
    }

    private void initGame(final BlackjackRepository blackjackRepository) {
        blackjackRepository.initCard();
        OutputView.printInitCards(blackjackRepository);
    }

    private void startGame(final BlackjackRepository blackjackRepository) {
        for (Player player : blackjackRepository.getPlayers().get()) {
            hitOrStayPlayer(player, blackjackRepository.getCardDeck());
        }
        hitOrStayDealer(blackjackRepository);
    }

    private void hitOrStayPlayer(final Player player, final CardDeck cardDeck) {
        while (!player.isBust() && InputView.inputOneMoreCard(player.getName())) {
            player.addCard(cardDeck.pop());
            OutputView.printHumanHand(player);
        }
        if (player.isTwoCard()) {
            OutputView.printHumanHand(player);
        }
    }

    private void hitOrStayDealer(final BlackjackRepository blackjackRepository) {
        Dealer dealer = blackjackRepository.getDealer();
        if (dealer.isAbleToHit()) {
            dealer.addCard(blackjackRepository.getCardDeck().pop());
            OutputView.printDealerHit();
        }
    }

    private void endGame(final BlackjackRepository blackjackRepository) {
        OutputView.printHandAndPoint(blackjackRepository);

        ResultStatistic resultStatistic = ResultStatistic.from(blackjackRepository);

        OutputView.printDealerResult(resultStatistic.getDealerResults());
        OutputView.printPlayerResult(resultStatistic.getPlayersResult());
    }
}
