package blackjack.controller;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Hand;
import blackjack.domain.game.PlayersResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Dealer2;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Player2;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.Players2;
import blackjack.view.InputView;
import blackjack.view.MessageResolver;
import blackjack.view.OutputView;
import java.util.List;

public class GameManager {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView(new MessageResolver());

    public GameManager() {
    }

    public void start() {
        List<String> strings = inputView.readNames();
        List<Player2> players = strings.stream()
                .map(Name::new)
                .map(name -> new Player2(name, new Hand()))
                .toList();

//        Participants participants = Participants.create(inputView.readNames());
        CardDeck cardDeck = CardDeck.createShuffledFullCardDeck();

        start2(cardDeck, new Dealer2(new Hand()), new Players2(players));

//        participants.deal(cardDeck);
//        outputView.printDealToAll(participants);
//
//        drawToPlayers(participants.getPlayers(), cardDeck);
//        drawToDealer(participants.getDealer(), cardDeck);
//        outputView.printParticipantsHandScore(participants);
//        outputView.printParticipantsResult(new Judge(participants));
    }

    public void start2(CardDeck cardDeck, Dealer2 dealer, Players2 players) {
        dealer.deal(cardDeck);
        players.deal(cardDeck);
        outputView.printDealToAll(dealer, players);

        drawToPlayers(players, cardDeck);
        drawToDealer(dealer, cardDeck);
        outputView.printParticipantsHandScore(dealer, players);

        PlayersResult playersResult = players.judge(dealer);
        outputView.printResult(playersResult);
    }

    private void drawToPlayers(Players2 players, CardDeck cardDeck) {
        players.draw(player -> drawToPlayer(player, cardDeck));
        outputView.printLineSeparator();
    }

    private void drawToPlayer(Player2 player, CardDeck cardDeck) {
        while (canPlayerHit(player)) {
            player.draw(cardDeck);
            outputView.printDrawToPlayer(player);
        }
    }

    private boolean canPlayerHit(Player2 player) {
        return player.canHit() && inputView.readDrawDecision(player.getName()).isHit();
    }

    private void drawToDealer(Dealer2 dealer, CardDeck cardDeck) {
        while (dealer.canHit()) {
            dealer.draw(cardDeck);
            outputView.printDrawToDealer();
        }
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
