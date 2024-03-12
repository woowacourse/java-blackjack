package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.rule.Judge;
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
