package blackjack;

import blackjack.domain.CardDeck;
import blackjack.domain.Round;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardType;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        CardDeck cardDeck = createCardDeck();
        List<Name> playerNames = getPlayerNames();
        Dealer dealer = new Dealer();
        Round round = new Round(cardDeck, playerNames, dealer);
        round.distributeInitialCards();
        OutputView.printInitialDistributionPrompt(playerNames);
        OutputView.printGamblerCards(new Name("딜러"), dealer.getInitialCards());
        for (Name playerName : playerNames) {
            OutputView.printGamblerCards(playerName, round.getCardsByPlayer(playerName));
        }
        for (Name playerName : playerNames) {
            while (isHit(playerName)) {
                round.distributeCards(playerName, 1);
                OutputView.printGamblerCards(playerName, round.getCardsByPlayer(playerName));
                boolean isBusted = round.isPlayerBusted(playerName);
                if (isBusted) {
                    OutputView.printBusted(playerName);
                    break;
                }
            }
        }
    }

    private static boolean isHit(Name playerName) {
        try {
            return InputView.inputPlayerHit(playerName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isHit(playerName);
        }
    }

    private static List<Name> getPlayerNames() {
        try {
            return InputView.inputPlayerName();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPlayerNames();
        }
    }

    private static CardDeck createCardDeck() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .flatMap(shape -> Arrays.stream(CardType.values())
                        .map(type -> new Card(shape, type)))
                .collect(Collectors.toList());
        return new CardDeck(cards);
    }
}
