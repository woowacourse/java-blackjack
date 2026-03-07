package config;

import domain.BlackJack;
import domain.deck.CardDeck;
import domain.deck.Deck;

public class AppConfig {

    private final CardDeck cardDeck;
    private final BlackJack blackJack;

    public AppConfig() {
        this.cardDeck = new Deck();
        this.blackJack = new BlackJack(cardDeck);
    }

    public BlackJack blackJack() {
        return blackJack;
    }
}
