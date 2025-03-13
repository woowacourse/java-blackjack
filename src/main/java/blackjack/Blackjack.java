package blackjack;

import blackjack.card.Card;
import blackjack.gamer.Dealer;
import blackjack.gamer.Player;
import blackjack.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;

public class Blackjack {

    private static final int BLACKJACK_SCORE = 21;

    private final Dealer dealer;
    private final Players players;

    public Blackjack(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initDealer() {
        dealer.initCardMachine();
    }

    public void makePlayers(final InputView inputView) {
        final String names = inputView.readNames();
        try {
            players.addPlayersFrom(names);
        } catch (IllegalArgumentException e) {
            inputView.printErrorMessage(e);
            makePlayers(inputView);
        }
    }

    public void betMoney(final InputView inputView) {
        for (Player player : players.getPlayers()) {
            receiveBettingMoney(inputView, player);
        }
    }

    public void deal() {
        final List<Card> dealerCards = dealer.spreadTwoCards();
        dealer.receiveCards(dealerCards);
        for (Player player : players.getPlayers()) {
            final List<Card> playerCards = dealer.spreadTwoCards();
            player.receiveCards(playerCards);
        }
    }

    public void showInitialCards(final ResultView resultView) {
        resultView.printEmptyLine();
        resultView.printCards(dealer, dealer.showInitialCards());
        for (Player player : players.getPlayers()) {
            resultView.printCards(player, player.showInitialCards());
        }
    }

    public boolean isPush() {
        final int dealerSum = dealer.sumCards();
        if (dealerSum != BLACKJACK_SCORE) {
            return false;
        }
        return players.getPlayers().stream()
                .anyMatch(player -> player.sumCards() == BLACKJACK_SCORE);
    }

    public void hitOrStand(final InputView inputView, final ResultView resultView) {
        for (Player player : players.getPlayers()) {
            while (!player.isBust(BLACKJACK_SCORE)) {
                final boolean isHit = readHitOrStand(inputView, player);
                if (!isHit) {
                    break;
                }
                final Card card = dealer.spreadOneCard();
                player.receiveCard(card);
                resultView.printCards(player, player.showAllCards());
            }
        }
        while (dealer.isHit()) {
            final Card card = dealer.spreadOneCard();
            dealer.receiveCard(card);
            resultView.printDealerHit();
        }
    }

    public void showSum(final ResultView resultView) {
        resultView.printEmptyLine();
        resultView.printCardsSum(dealer, dealer.showAllCards(), dealer.sumCards());
        for (Player player : players.getPlayers()) {
            resultView.printCardsSum(player, player.showAllCards(), player.sumCards());
        }
    }

    private void receiveBettingMoney(final InputView inputView, final Player player) {
        try {
            player.bet(inputView.readBettingMoney(player));
        } catch (IllegalArgumentException e) {
            inputView.printErrorMessage(e);
            receiveBettingMoney(inputView, player);
        }
    }

    private boolean readHitOrStand(final InputView inputView, final Player player) {
        try {
            final String answer = inputView.readHitOrStand(player);
            final UserAnswer userAnswer = UserAnswer.of(answer);
            return userAnswer.isYes();
        } catch (IllegalArgumentException e) {
            inputView.printErrorMessage(e);
        }
        return readHitOrStand(inputView, player);
    }
}
