package blackjack.domain;

import static blackjack.domain.gambler.Dealer.DEALER_NAME;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Gambler;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Names;
import blackjack.domain.gambler.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Round {
    public static final int MIN_PLAYER_COUNT = 1;
    public static final int MAX_PLAYER_COUNT = 6;
    public static final int BLACK_JACK = 21;
    public static final int DEALER_DRAW_THRESHOLD = 16;

    private final CardDeck cardDeck;
    private final List<Gambler> gamblers = new ArrayList<>();

    public Round(final CardDeck cardDeck, final Names playerNames) {
        this.cardDeck = cardDeck;
        registerGamblers(playerNames);
    }

    private void registerGamblers(final Names playerNames) {
        gamblers.add(new Dealer());
        List<Player> players = playerNames.getNames()
                .stream()
                .map(Player::new)
                .toList();
        gamblers.addAll(players);
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

    public boolean isPlayerBusted(final Name name) {
        return !findGambler(name).isScoreBelow(BLACK_JACK);
    }

    public WinningDiscriminator getWinningDiscriminator() {
        Gambler dealer = findGambler(DEALER_NAME);
        List<Gambler> players = gamblers.stream()
                .filter(Gambler::isPlayer)
                .toList();
        return new WinningDiscriminator(dealer, players);
    }

    private Gambler findGambler(final Name name) {
        return gamblers.stream()
                .filter(gambler -> gambler.isNameEquals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 플레이어입니다:" + name));
    }
}
