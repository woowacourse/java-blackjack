package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Gambler;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Player;
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
        gamblers.add(new Dealer(Name.createDealer()));
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

    public WinningDiscriminator getWinningDiscriminator() {
        Gambler dealer = findGambler(Name.createDealer());
        int dealerScore = dealer.calculateScore();
        Map<Name, Integer> playerScores = createPlayerScores();
        return new WinningDiscriminator(dealerScore, playerScores); 
    }

    private Gambler findGambler(final Name name) {
        return gamblers.stream()
                .filter(gambler -> gambler.isNameEquals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어입니다:" + name));
    }

    private Map<Name, Integer> createPlayerScores() {
        return gamblers.stream()
                .filter(gambler -> gambler instanceof Player)
                .collect(toMap(Gambler::getName, Gambler::calculateScore));
    }

    public boolean isPlayerOwnsCardExceptInitialCards(final Name name) {
        return findGambler(name).getCards().size() > 2;
    }
}
