package blackjack.object;

import static java.util.stream.Collectors.toMap;

import blackjack.object.card.Card;
import blackjack.object.card.CardDeck;
import blackjack.object.gambler.Dealer;
import blackjack.object.gambler.Gambler;
import blackjack.object.gambler.Name;
import blackjack.object.gambler.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Round {
    public static final int DEALER_RECEIVE_CRITERIA = 16;
    private final CardDeck cardDeck;
    private final List<Gambler> gamblers;

    public Round(final CardDeck cardDeck, final List<Name> playerNames) {
        this.cardDeck = cardDeck;
        this.gamblers = registerGamblers(playerNames);
    }

    private List<Gambler> registerGamblers(final List<Name> playerNames) {
        List<Gambler> gamblers = new ArrayList<>();
        gamblers.add(new Dealer());
        for (final Name playerName : playerNames) {
            Player player = new Player(playerName);
            gamblers.add(player);
        }
        return gamblers;
    }

    public void distributeCards(final Name playerName, final int cardCount) {
        Gambler foundGambler = findGambler(playerName);
        for (int i = 0; i < cardCount; i++) {
            Card card = cardDeck.getCard();
            foundGambler.addCard(card);
        }
    }

    public int getScore(final Name name) {
        return findGambler(name).calculateScore();
    }

    public void distributeInitialCards() {
        for (final Gambler gambler : gamblers) {
            gambler.addCard(cardDeck.getCard());
            gambler.addCard(cardDeck.getCard());
        }
    }

    public List<Card> getCards(final Name name) {
        return findGambler(name).getCards();
    }

    public List<Card> getInitialCards(final Name name) {
        Gambler gambler = findGambler(name);
        return gambler.getInitialCards();
    }

    public boolean isGamblerCanReceiveCard(final Name name, final int score) {
        return findGambler(name).isScoreBelow(score);
    }

    public ProfitCalculator getProfitCalculator(Map<Name, Integer> bettingRecords) {
        Map<Name, Integer> gamblerScores = createGamblerScores();
        return new ProfitCalculator(gamblerScores, bettingRecords);
    }

    private Gambler findGambler(final Name name) {
        return gamblers.stream()
                .filter(gambler -> gambler.isNameEquals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 겜블러 입니다:" + name));
    }

    private Map<Name, Integer> createGamblerScores() {
        return gamblers.stream()
                .collect(toMap(Gambler::getName, Gambler::calculateScore));
    }

    public boolean isPlayerOwnsCardExceptInitialCards(final Name name) {
        return findGambler(name).getCards().size() > 2;
    }
}
