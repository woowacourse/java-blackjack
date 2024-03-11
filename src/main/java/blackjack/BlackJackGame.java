package blackjack;

import static blackjack.domain.DrawDecision.YES;

import blackjack.domain.DrawDecision;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Hand;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import blackjack.domain.rule.Judge;
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
        Dealer dealer = new Dealer(Hand.createHandFrom(cardDeck));
        printPlayersInformation(players, dealer);

        completePlayersHand(players, cardDeck);
        completeDealerHand(dealer, cardDeck);
        outputView.printDealerPopCount(Dealer.HIT_THRESHOLD, dealer.countPop());

        printParticipantScore(dealer);
        printPlayersScore(players);
        printDealerGameResult(dealer, players);
        printPlayersGameResult(players, dealer);
    }

    private Players initPlayers(CardDeck cardDeck) {
        InputMapper inputMapper = new InputMapper();
        List<PlayerName> playerNames = inputMapper.mapToPlayers(inputView.readNames());

        return new Players(playerNames.stream()
                .map(playerName -> new Player(playerName, Hand.createHandFrom(cardDeck)))
                .toList());
    }

    private void printPlayersInformation(Players players, Dealer dealer) {
        outputView.printHandOutEvent(players, 2);
        outputView.printDealerInitialHand(dealer);
        players.getPlayers().forEach(outputView::printPlayerHand);
    }

    private void completePlayersHand(Players players, CardDeck cardDeck) {
        players.getPlayers().forEach(player -> completePlayerHand(player, cardDeck));
    }

    private void completePlayerHand(Player participant, CardDeck cardDeck) {
        while (participant.canHit() && readHitDecision(participant) == YES) {
            participant.appendCard(cardDeck.popCard());
            outputView.printPlayerHand(participant);
        }
    }

    private DrawDecision readHitDecision(Player participant) {
        InputMapper inputMapper = new InputMapper();
        return inputMapper.mapToDrawDecision(inputView.readDrawPlan(participant.getName()));
    }

    private void completeDealerHand(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canHit()) {
            Card card = cardDeck.popCard();
            dealer.appendCard(card);
        }
    }

    private void printPlayersScore(Players players) {
        players.getPlayers().forEach(this::printParticipantScore);
    }

    private void printParticipantScore(Participant participant) {
        outputView.printParticipantScore(participant, participant.calculateHandScore());
    }

    private void printDealerGameResult(Dealer dealer, Players players) {
        Judge judge = new Judge();
        outputView.printDealerGameResult(judge.calculateDealerGameResult(dealer, players));
    }

    private void printPlayersGameResult(Players players, Dealer dealer) {
        Judge judge = new Judge();
        players.getPlayers()
                .forEach(player -> outputView.printPlayerGameResult(player, judge.isPlayerWin(dealer, player)));
    }
}
