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
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        CardDeck cardDeck = createCardDeck();
        List<Name> playerNames = getPlayerNames();
        Dealer dealer = new Dealer();
        Round round = new Round(cardDeck, playerNames, dealer);
        round.distributeInitialCards();
        OutputView.printInitialDistributionPrompt(playerNames);
        OutputView.printGamblerCards(new Name("딜러"), dealer.getCards());
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
        List<Card> cards = new ArrayList<>();
        for (CardShape shape : CardShape.values()) {
            for (CardType type : CardType.values()) {
                Card card = new Card(shape, type);
                cards.add(card);
            }
        }
        return new CardDeck(cards);
    }
}
