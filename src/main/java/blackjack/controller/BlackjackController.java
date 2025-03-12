package blackjack.controller;

import blackjack.domain.Answer;
import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardManager;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.random.CardRandomGenerator;
import blackjack.util.StringParser;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        final CardManager cardManager = new CardManager(new CardRandomGenerator());
        final Participants participants = makeParticipants();
        final BlackjackGame blackjackGame = new BlackjackGame(cardManager, participants);

        betPlayers(participants, blackjackGame);

        showInitialCards(blackjackGame, participants);

        boolean isPush = blackjackGame.checkBlackjack();
        if (!isPush) {
            spreadPlayersExtraCards(blackjackGame);
            spreadDealerExtraCards(blackjackGame);
        }

        showAllCards(participants);

        showBetResult(blackjackGame, isPush, participants);
    }

    private void betPlayers(final Participants participants, final BlackjackGame blackjackGame) {
        for (Player player : participants.getPlayers()) {
            readBetAmount(blackjackGame, player);
        }
    }

    private void readBetAmount(final BlackjackGame blackjackGame, final Player player) {
        try {
            final long betAmount = getBetAmount(player);
            blackjackGame.bet(player, betAmount);
        } catch (IllegalArgumentException e) {
            resultView.showException(e);
            readBetAmount(blackjackGame, player);
        }
    }

    private int getBetAmount(final Player player) {
        final String betAmount = inputView.readBetAmount(player);
        if (!isNumeric(betAmount)) {
            throw new IllegalArgumentException("베팅 금액이 잘못됐습니다.");
        }
        return Integer.parseInt(betAmount);
    }

    private boolean isNumeric(final String betAmount) {
        try {
            Integer.parseInt(betAmount);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showInitialCards(final BlackjackGame blackjackGame, final Participants participants) {
        blackjackGame.spreadInitialCards();
        resultView.printNames(participants);

        final Dealer dealer = participants.getDealer();
        final List<Card> dealerCard = dealer.showOneCard();
        resultView.printCards(dealer, dealerCard);

        final List<Player> players = participants.getPlayers();
        for (Player player : players) {
            final List<Card> playerCards = player.showAllCard();
            resultView.printCards(player, playerCards);
        }
    }

    private void spreadPlayersExtraCards(final BlackjackGame blackjackGame) {
        for (int index = 0; index < blackjackGame.getPlayerSize(); index++) {
            spreadExtraCards(blackjackGame, index);
        }
    }

    private void spreadDealerExtraCards(final BlackjackGame blackjackGame) {
        while (blackjackGame.canDealerMoreCard()) {
            blackjackGame.spreadOneCardToDealer();
            resultView.printDealerExtraCard();
        }
    }

    private void showAllCards(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final List<Card> dealerCard = dealer.showAllCard();
        resultView.printCardsAndSum(dealer, dealerCard);

        final List<Player> players = participants.getPlayers();
        for (Player player : players) {
            final List<Card> playerCards = player.showAllCard();
            resultView.printCardsAndSum(player, playerCards);
        }
    }

    private void spreadExtraCards(final BlackjackGame blackjackGame, final int index) {
        final Player player = blackjackGame.getPlayer(index);
        while (blackjackGame.canPlayerMoreCard(player) && isMoreCard(player)) {
            blackjackGame.spreadOneCardToPlayer(player);
            resultView.printParticipantTotalCards(player);
        }
    }

    private Participants makeParticipants() {
        final Dealer dealer = new Dealer(new Cards(new ArrayList<>()));
        final Players players = makePlayers();
        return new Participants(dealer, players);
    }

    private boolean isMoreCard(final Player player) {
        try {
            final Answer answer = Answer.from(inputView.askMoreCard(player));
            return answer.isYes();
        } catch (IllegalArgumentException exception) {
            resultView.showException(exception);
            return isMoreCard(player);
        }
    }

    private Players makePlayers() {
        String names = inputView.readNames();
        List<String> parsedNames = StringParser.parseComma(names);
        return Players.from(parsedNames);
    }

    private void showBetResult(final BlackjackGame blackjackGame, final boolean isPush,
                               final Participants participants) {
        blackjackGame.calculateWinningResult(isPush);
        resultView.printEarningTitle();
        resultView.printEarning(participants.getDealer());
        for (Player player : participants.getPlayers()) {
            resultView.printEarning(player);
        }
    }
}
