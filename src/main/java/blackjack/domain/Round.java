package blackjack.domain;

import static blackjack.domain.Rule.BLACK_JACK;
import static blackjack.domain.Rule.DEALER_DRAW_THRESHOLD;
import static blackjack.domain.Rule.DEALER_NAME;
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
    private final CardDeck cardDeck;
    private final List<Gambler> gamblers;

    public Round(final CardDeck cardDeck, final List<Name> playerNames) {
        this.cardDeck = cardDeck;
        this.gamblers = registerGamblers(playerNames);
    }

    private List<Gambler> registerGamblers(final List<Name> playerNames) {
        List<Gambler> gamblers = new ArrayList<>();
        gamblers.add(new Dealer(DEALER_NAME));
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

    public boolean dealerMustDraw() {
        Gambler dealer = findGambler(DEALER_NAME);
        return dealer.isScoreBelow(DEALER_DRAW_THRESHOLD);
    }

    public boolean isPlayerBusted(Name name) {
        return !findGambler(name).isScoreBelow(BLACK_JACK);
    }

    public WinningDiscriminator getWinningDiscriminator() {
        Gambler dealer = findGambler(DEALER_NAME);
        int dealerScore = dealer.calculateScore();
        Map<Name, Integer> playerScores = gamblers.stream()
                .filter(gambler -> gambler instanceof Player)
                .collect(toMap(Gambler::getName, Gambler::calculateScore));
        return new WinningDiscriminator(dealerScore, playerScores);
    }

    private Gambler findGambler(final Name name) {
        return gamblers.stream()
                .filter(gambler -> gambler.isNameEquals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어입니다:" + name));
    }
}
