package blackjack.controller;

import blackjack.domain.cards.CardDeck;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Player;
import blackjack.domain.participant.human.name.Name;
import blackjack.domain.result.ResultStatistic;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public final class BlackjackGame {
    private static final int INIT_CARD_NUMBER = 2;

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.popCards(2));
        Players players = initPlayers(cardDeck);
        OutputView.printInitCards(players, dealer);

        startGame(players, dealer, cardDeck);
        endGame(players, dealer);
    }

    private Players initPlayers(final CardDeck cardDeck) {
        List<Name> names = InputView.inputPlayerNames();
        return Players.fromRawValue(names.stream()
                .map(Player::fromName)
                .map(this::requestBetting)
                .collect(Collectors.toList())
        ).initCard(cardDeck);
    }

    private Player requestBetting(Player player) {
        return player.initBetting(InputView.inputPlayerBetting(player.getName()));
    }

    private void startGame(final Players players, final Dealer dealer, final CardDeck cardDeck) {
        players.get()
                .forEach(player -> hitOrStayPlayer(player, cardDeck));
        hitOrStayDealer(dealer, cardDeck);
    }

    private void hitOrStayPlayer(final Player player, final CardDeck cardDeck) {
        while (!player.isBust()  && !player.isBlackjack() && InputView.inputOneMoreCard(player.getName())) {
            player.addCard(cardDeck.pop());
            OutputView.printHumanHand(player);
        }
        if (player.isCardsThatSize(INIT_CARD_NUMBER)) {
            OutputView.printHumanHand(player);
        }
    }

    private void hitOrStayDealer(final Dealer dealer, final CardDeck cardDeck) {
        if (dealer.isAbleToHit()) {
            dealer.addCard(cardDeck.pop());
            OutputView.printDealerHit();
        }
    }

    private void endGame(final Players players, final Dealer dealer) {
        OutputView.printHandAndPoint(players, dealer);
        ResultStatistic resultStatistic = new ResultStatistic(players, dealer);
        OutputView.printDealerResult(resultStatistic.getDealerResults());
        OutputView.printPlayerResult(resultStatistic.getPlayersResult());
    }
}
