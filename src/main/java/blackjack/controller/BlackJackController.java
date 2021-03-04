package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.rule.BlackJackScoreRule;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    public void play() {
        Deck deck = Deck.generate();
        Participants participants = getParticipants(InputView.inputNames());
        participants.dealCardsAllParticipants(deck);

        OutputView.printInitialCardStatus(participants);

        List<Player> players = startPlayerTurns(deck, participants);
        Dealer dealer = startDealerTurn(deck, participants);

        OutputView.printAllParticipantsCards(participants);
        OutputView.printScoreResults(dealer.getDealerResult(players), dealer.decideWinOrLoseResults(players));
    }

    private List<Player> startPlayerTurns(Deck deck, Participants participants) {
        List<Player> players = participants.extractPlayers();
        for (Player player : players) {
            startPlayerTurn(deck, player);
        }
        return players;
    }

    private Dealer startDealerTurn(Deck deck, Participants participants) {
        Dealer dealer = participants.extractDealer();
        if (dealer.isReceiveCard()) {
            dealer.receiveCard(deck.draw());
        }
        return dealer;
    }

    private void startPlayerTurn(Deck deck, Player player) {
        while (player.isReceiveCard() && InputView.inputAskMoreCard(player)) {
            player.receiveCard(deck.draw());
            OutputView.printParticipantCards(player);
        }
    }

    private Participants getParticipants(List<String> names) {
        List<Participant> participants = names.stream()
                .map(name -> new Player(name, new BlackJackScoreRule()))
                .collect(Collectors.toList());
        participants.add(0, new Dealer(new BlackJackScoreRule()));
        return new Participants(participants);
    }
}
