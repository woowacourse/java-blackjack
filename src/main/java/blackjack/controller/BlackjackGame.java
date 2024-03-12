package blackjack.controller;

import blackjack.domain.DrawDecision;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.MessageResolver;
import blackjack.view.OutputView;

public class BlackjackGame {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView(new MessageResolver());

    public BlackjackGame() {
    }

    public void play() {
        Participants participants = Participants.create(inputView.readNames());
        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();

        dealToAll(participants, cardDeck);
        outputView.printDealToAll(participants);

        drawToPlayers(participants.getPlayers(), cardDeck);
        drawToDealer(participants.getDealer(), cardDeck);
        outputView.printParticipantsHandScore(participants);
        outputView.printParticipantsResult(participants.getDealer(), participants.getPlayers());
    }

    private void dealToAll(Participants participants, CardDeck cardDeck) {
        for (Participant participant : participants.getParticipants()) {
            participant.hit(cardDeck.popCard());
            participant.hit(cardDeck.popCard());
        }
    }

    private void drawToPlayers(Players players, CardDeck cardDeck) {
        players.getPlayers().forEach(player -> drawToPlayer(player, cardDeck));
    }

    private void drawToPlayer(Player player, CardDeck cardDeck) {
        String playerName = player.getPlayerName();

        DrawDecision drawDecision = DrawDecision.HIT;
        while (player.isNotBust() && drawDecision == DrawDecision.HIT) {
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
