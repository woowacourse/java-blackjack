package domain;

import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Players;
import domain.service.HitService;
import domain.service.InitService;

public class BlackjackGame {
    private final Players players;
    private final CardDeck cardDeck;

    private BlackjackGame(Players players, CardDeck cardDeck) {
        this.players = players;
        this.cardDeck = cardDeck;
    }

    public static BlackjackGame init(InitService initService) {
        return new BlackjackGame(initService.initPlayers(), initService.initCardDeck());
    }

    public Players spreadCards() {
        players.spreadCard(cardDeck);
        players.spreadCard(cardDeck);
        return players;
    }

    public Players play(HitService hitService) {
        playForGamblers(hitService);
        playForDealer(hitService);
        return players;
    }

    public BlackJackResult getResult() {
        return BlackJackResult.of(players.getDealer(), players.getGamblers());
    }

    private void playForGamblers(HitService hitService) {
        Gamblers gamblers = players.getGamblers();

        for (Gambler gambler : gamblers.getGamblers()) {
            playForSingleGambler(hitService, gambler);
        }
    }

    private void playForSingleGambler(HitService hitService, Gambler gambler) {
        boolean isHit = gambler.isHittable() && hitService.isHit(gambler.getName());
        if (!isHit) {
            hitService.showGamblerHitStatus(gambler);
            return;
        }

        do {
            gambler.addCard(cardDeck.drawCard());
            hitService.showGamblerHitStatus(gambler);
        } while (gambler.isHittable() && hitService.isHit(gambler.getName()));
    }

    private void playForDealer(HitService hitService) {
        Dealer dealer = players.getDealer();

        while (dealer.isHittable()) {
            dealer.addCard(cardDeck.drawCard());
            hitService.showDealerHitStatus(dealer);
        }
    }
}
