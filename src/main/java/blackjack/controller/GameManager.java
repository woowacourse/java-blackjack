package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.game.Judge;
import blackjack.view.InputView;
import blackjack.view.MessageResolver;
import blackjack.view.OutputView;

public class GameManager {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView(new MessageResolver());

    public GameManager() {
    }

    public void start() {
        Participants participants = Participants.create(inputView.readNames());
        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();

        participants.deal(cardDeck);
        outputView.printDealToAll(participants);

        drawToPlayers(participants.getPlayers(), cardDeck);
        drawToDealer(participants.getDealer(), cardDeck);
        outputView.printParticipantsHandScore(participants);
        outputView.printParticipantsResult(new Judge(participants));
    }

    private void drawToPlayers(Players players, CardDeck cardDeck) {
        players.getPlayers().forEach(player -> drawToPlayer(player, cardDeck));
        outputView.printLineSeparator();
    }

    private void drawToPlayer(Player player, CardDeck cardDeck) {
        String playerName = player.getPlayerName().getValue();
        while (canPlayerHit(player.canHit(), playerName)) {
            player.draw(cardDeck.popCard());
            outputView.printDrawToPlayer(player);
        }
    }

    private boolean canPlayerHit(boolean canHit, String playerName) {
        return canHit && inputView.readDrawDecision(playerName).isHit();
    }

    private void drawToDealer(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canHit()) {
            dealer.draw(cardDeck.popCard());
            outputView.printDrawToDealer();
        }
    }
}
