package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class Blackjack {

    public void play() {
        final Deck deck = new Deck();
        final List<Player> players = createParticipants(InputView.responseNames(), deck);
        final Player dealer = createDealer(deck);
        final Players collectPlayers = new Players(players, dealer);

        OutputView.printPlayersInitCardInfo(collectPlayers);
        decideGetMoreCard(collectPlayers, deck);
        announcePlayersFinishInfo(collectPlayers);
        competeWithDealer(collectPlayers);
        OutputView.printResult(collectPlayers);
    }

    private List<Player> createParticipants(final List<String> names, final Deck deck) {
        return names.stream()
                .map(name -> new Participant(deck.makeDistributeCard(), name))
                .collect(Collectors.toList());
    }

    private Dealer createDealer(final Deck deck) {
        return new Dealer(deck.makeDistributeCard());
    }

    private void decideGetMoreCard(final Players players, final Deck deck) {
        for (Player participant : players.getParticipants()) {
            decideParticipantOneMoreCard(participant, deck);
        }
        decideDealerMoreCard(Dealer.changeToDealer(players.getDealer()), deck);
    }

    private void decideParticipantOneMoreCard(final Player player, final Deck deck) {
        while (isNotOverMaxScore(player) && InputView.oneMoreCard(player)) {
            player.addCard(deck.draw());
            OutputView.printPlayerCardInfo(player);
        }
    }

    private boolean isNotOverMaxScore(final Player player) {
        Participant participant = Participant.changeToParticipant(player);
        if (participant.isOverMaxScore()) {
            OutputView.printParticipantOverMaxScore(player.getName());
        }
        return !participant.isOverMaxScore();
    }

    private void decideDealerMoreCard(final Dealer dealer, final Deck deck) {
        if (dealer.acceptableCard()) {
            dealer.addCard(deck.draw());
            OutputView.printDealerAcceptCard();
            return;
        }
        OutputView.printDealerDenyCard();
    }

    private void announcePlayersFinishInfo(final Players players) {
        OutputView.printPlayerFinalInfo(players.getDealer());
        OutputView.printFinishParticipantInfo(players.getParticipants());
    }

    private void competeWithDealer(final Players players) {
        players.getParticipants()
                .forEach(player -> (Dealer.changeToDealer(players.getDealer()))
                        .compete((Participant) player));
    }
}
