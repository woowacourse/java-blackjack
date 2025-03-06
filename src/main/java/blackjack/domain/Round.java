package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Card;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Gambler;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Round {
    private static final Name DEALER = new Name("딜러");

    private final CardDeck cardDeck;
    private final List<Gambler> gamblers;

    public Round(final CardDeck cardDeck, final List<Name> playerNames) {
        this.cardDeck = cardDeck;
        this.gamblers = registerGamblers(playerNames);
    }

    private List<Gambler> registerGamblers(final List<Name> playerNames) {
        List<Gambler> gamblers = new ArrayList<>();
        gamblers.add(new Dealer(DEALER));
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

    public List<Card> getInitialCardsByDealer() {
        Dealer dealer = findDealer();
        return dealer.getInitialCards();
    }

    public boolean dealerMustDraw() {
        Gambler dealer = findGambler(DEALER);
        return dealer.isScoreBelow(16);
    }

    public boolean isPlayerBusted(Name name) {
        return !findGambler(name).isScoreBelow(21);
    }

    public WinningDiscriminator getWinningDiscriminator() {
        Dealer dealer = findDealer();
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

    private Dealer findDealer() {
        Gambler gambler = findGambler(DEALER);
        if (gambler instanceof Dealer) {
            return (Dealer) gambler;
        }
        throw new IllegalArgumentException("딜러가 존재하지 않습니다.");
    }
}
