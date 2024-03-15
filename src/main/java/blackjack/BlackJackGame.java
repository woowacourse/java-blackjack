package blackjack;

import blackjack.domain.bet.BettingBank;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Hand;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import blackjack.domain.result.PlayerProfitResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardDeck cardDeck = CardDeck.createShuffledDeck();
        Players players = initPlayers(cardDeck);
        BettingBank bettingBank = inputView.readBetInformation(players);
        Dealer dealer = new Dealer(Hand.createHandFrom(cardDeck));
        outputView.printParticipantInitialHand(dealer, players);

        completePlayersHand(players, cardDeck);
        dealer.completeHand(cardDeck);
        outputView.printCompletedHandsStatus(dealer, players);

        PlayerProfitResult playerProfitResult = bettingBank.calculateProfitResult(dealer);
        outputView.printProfitResults(playerProfitResult);
    }

    private Players initPlayers(CardDeck cardDeck) {
        List<PlayerName> playerNames = inputView.readNames();
        return new Players(playerNames.stream()
                .map(playerName -> new Player(playerName, Hand.createHandFrom(cardDeck)))
                .toList());
    }

    private void completePlayersHand(Players players, CardDeck cardDeck) {
        players.getPlayers().forEach(player -> completePlayerHand(player, cardDeck));
    }

    private void completePlayerHand(Player participant, CardDeck cardDeck) {
        while (participant.canHit() && inputView.readDrawDecision(participant.getName()).isYes()) {
            participant.appendCard(cardDeck.popCard());
            outputView.printPlayerHand(participant);
        }
    }
}
