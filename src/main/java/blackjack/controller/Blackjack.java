package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckCardGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.Judge;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class Blackjack {

    public void play() {
        final Deck deck = new Deck(new DeckCardGenerator());
        final List<Player> participants = createParticipants(InputView.responseNames(), deck);
        final Player dealer = createDealer(deck);
        final Players players = new Players(participants, dealer);

        OutputView.printPlayersInitCardInfo(players);
        decideGetMoreCard(players, deck);
        announcePlayersFinishInfo(players);
        OutputView.printGameResult(Judge.calculateGameResult(players));
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
        decideDealerMoreCard(players.getDealer(), deck);
    }

    private void decideParticipantOneMoreCard(final Player participant, final Deck deck) {
        while (isNotOverMaxScore(participant) && InputView.oneMoreCard(participant)) {
            participant.addCard(deck.draw());
            OutputView.printPlayerCardInfo(participant);
        }
    }

    private boolean isNotOverMaxScore(final Player participant) {
        if (!participant.acceptableCard()) {
            OutputView.printParticipantOverMaxScore(participant.getName());
        }
        return participant.acceptableCard();
    }

    private void decideDealerMoreCard(final Player dealer, final Deck deck) {
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

}
