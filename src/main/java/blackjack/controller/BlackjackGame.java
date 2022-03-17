package blackjack.controller;

import blackjack.domain.human.humans.Participant;
import blackjack.domain.card.cards.CardDeck;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.humans.Players;
import blackjack.domain.result.ResultStatistic;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public final class BlackjackGame {

    public void run() {
        Participant participant = Participant.from(getPlayers());
        CardDeck cardDeck = CardDeck.newInstance();
        initGame(participant, cardDeck);
        startGame(participant, cardDeck);
        endGame(participant);
    }

    private Players getPlayers() {
        return Players.fromText(InputView.inputPlayerNames());
    }

    private void initGame(final Participant participant, final CardDeck cardDeck) {
        participant.initCard(cardDeck);
        OutputView.printInitCards(participant);
    }

    private void startGame(final Participant participant, final CardDeck cardDeck) {
        for (Player player : participant.getRawPlayers()) {
            hitOrStayPlayer(player, cardDeck);
        }
        hitOrStayDealer(participant.getDealer(), cardDeck);
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

    private void hitOrStayDealer(final Dealer dealer, final CardDeck cardDeck) {
        if (dealer.isAbleToHit()) {
            dealer.addCard(cardDeck.pop());
            OutputView.printDealerHit();
        }
    }

    private void endGame(final Participant participant) {
        OutputView.printHandAndPoint(participant);
        ResultStatistic resultStatistic = ResultStatistic.from(participant);
        OutputView.printDealerResult(resultStatistic.getDealerResults());
        OutputView.printPlayerResult(resultStatistic.getPlayersResult());
    }
}
