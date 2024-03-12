package blackjack.controller;

import blackjack.domain.DrawDecision;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Hand;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = createParticipants();
        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();

        dealToAll(participants, cardDeck);
        outputView.printDealToAll(participants);

        drawToPlayers(participants.getPlayers(), cardDeck);
        drawToDealer(participants.getDealer(), cardDeck);
        outputView.printParticipantsHandScore(participants);
        outputView.printParticipantsResult(participants.getDealer(), participants.getPlayers());
    }

    private Participants createParticipants() {
        Players players = Players.from(inputView.readNames());
        Dealer dealer = new Dealer(new Hand());
        return new Participants(players, dealer);
    }

    private void dealToAll(Participants participants, CardDeck cardDeck) {
        for (Participant participant : participants.getParticipants()) {
            participant.hit(cardDeck.popCard());
            participant.hit(cardDeck.popCard());
        }
    }

    private void drawToPlayers(Players players, CardDeck cardDeck) {
        players.getPlayers().forEach(player -> dealToPlayer(player, cardDeck));
    }

    private void dealToPlayer(Player player, CardDeck cardDeck) {
        DrawDecision drawDecision = DrawDecision.HIT;
        while (player.isNotBust() && drawDecision == DrawDecision.HIT) {
            String playerName = player.getPlayerName();
            drawDecision = inputView.readDrawDecision(playerName);
            drawIfDrawDecisionIsHit(player, cardDeck, drawDecision);
        }
    }

    private void drawIfDrawDecisionIsHit(Player player, CardDeck cardDeck, DrawDecision drawDecision) {
        if (drawDecision == DrawDecision.HIT) {
            player.hit(cardDeck.popCard());
            outputView.printDrawToPlayer(player);
        }
    }

    private void drawToDealer(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canHit()) {
            dealer.hit(cardDeck.popCard());
            outputView.printDrawToDealer();
        }
    }
}
