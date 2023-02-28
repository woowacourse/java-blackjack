package blackjack.domain;

public class BlackJackGame {

    private final CardsGenerator cardsGenerator;
    private final Deck deck;
    
    public BlackJackGame(CardsGenerator cardsGenerator) {
        this.cardsGenerator = cardsGenerator;
        this.deck = new Deck(cardsGenerator.generate());
    }

}
