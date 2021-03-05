package blackjack;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardsGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Application {
    private static final String AGREE = "y";

    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck(CardsGenerator.generateCards());
        Dealer dealer = new Dealer();
        Players players = generatePlayers();
        List<Player> playersGroup = players.toList();

        distributeDefaultCards(dealer, players, cardDeck);
        playersGroup.forEach(player -> drawMoreCardForPlayer(player, cardDeck));
        drawMoreCardForDealer(dealer, cardDeck);

        OutputView.printFinalCardsAndScore(dealer, playersGroup);
        OutputView.printFinalResult(dealer, playersGroup);
    }

    private static Players generatePlayers() {
        List<String> playerNames = InputView.inputPlayerNames();
        List<Integer> bettingMoneys = InputView.inputAllPlayersBettingMoney(playerNames);
        return Players.of(playerNames, bettingMoneys);
    }

    private static void distributeDefaultCards(Dealer dealer, Players players, CardDeck cardDeck) {
        dealer.receiveCards(cardDeck.drawDefaultCards());
        players.receiveDefaultCards(cardDeck);
        OutputView.printCardDistributionMessage(dealer, players.toList());
    }

    private static void drawMoreCardForPlayer(Player player, CardDeck cardDeck) {
        String answer = AGREE;
        while (player.isAbleToReceiveCard() && AGREE.equals(answer)) {
            answer = InputView.inputAnswerForAdditionalCardDraw(player);
            drawOneCard(answer, player, cardDeck);
        }
    }

    private static void drawOneCard(String answer, Player player, CardDeck cardDeck) {
        if (AGREE.equals(answer)) {
            player.receiveCard(cardDeck.draw());
            OutputView.printEachPlayerCards(player);
        }
    }

    private static void drawMoreCardForDealer(Dealer dealer, CardDeck cardDeck) {
        while (dealer.isAbleToReceiveCard()) {
            dealer.receiveCard(cardDeck.draw());
        }
        OutputView.printDealerDrawingMessage(dealer);
    }
}
