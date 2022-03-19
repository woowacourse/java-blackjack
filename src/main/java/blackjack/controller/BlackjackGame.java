package blackjack.controller;

import blackjack.domain.cards.CardDeck;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Player;
import blackjack.domain.participant.human.name.Name;
import blackjack.domain.result.PrideCalculator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class BlackjackGame {
    private static final int INIT_CARD_NUMBER = 2;

    public void run() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.popCards(INIT_CARD_NUMBER));
        Players players = initPlayers(cardDeck);
        OutputView.printInitCards(players, dealer);

        startGame(players, dealer, cardDeck);
        printGameResult(players, dealer);
    }

    private Players initPlayers(final CardDeck cardDeck) {
        List<Name> names = InputView.inputPlayerNames();
        return new Players(names.stream()
                .map(Player::new)
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
        while (!player.isBust() && !player.isMaxPoint() && InputView.inputOneMoreCard(player.getName())) {
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

    private void printGameResult(final Players players, final Dealer dealer) {
        OutputView.printHandAndPoint(players, dealer);

        int dealerMoney = 0;
        Map<Player, Integer> prides = new HashMap<>();
        for (Player player : players.get()) {
            prides.put(player, PrideCalculator.compute(player, dealer));
            dealerMoney -= prides.get(player);
        }

        OutputView.printResult(dealer, dealerMoney);
        for (Player player : players.get()) {
            OutputView.printHumanResult(player, prides.get(player));
        }
    }
}
