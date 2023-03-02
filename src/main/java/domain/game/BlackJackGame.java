package domain.game;

import domain.area.CardArea;
import domain.deck.CardDeck;
import domain.player.Dealer;
import domain.player.DealerCompeteResult;
import domain.player.Name;
import domain.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class BlackJackGame {

    private final List<Player> players;
    private final Dealer dealer;
    private final CardDeck cardDeck;

    BlackJackGame(final List<Player> players, final Dealer dealer, final CardDeck cardDeck) {
        this.players = players;
        this.dealer = dealer;
        this.cardDeck = cardDeck;
    }

    public static BlackJackGame defaultSetting(final CardDeck cardDeck, final List<Name> participantNames) {
        final List<Player> players = participantNames.stream()
                .map(it -> new Player(it, new CardArea(cardDeck.draw(), cardDeck.draw())))
                .collect(toList());
        final Dealer dealer = new Dealer(new CardArea(cardDeck.draw(), cardDeck.draw()));
        return new BlackJackGame(players, dealer, cardDeck);
    }

    public boolean existCanHitParticipant() {
        return players.stream().anyMatch(Player::canHit);
    }

    public Player findCanHitParticipant() {
        return players.stream()
                .filter(Player::canHit)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Hit 가능한 참여자가 없습니다."));
    }

    public void hitOrStayForParticipant(final Player player) {
        players.stream()
                .filter(player::equals)
                .filter(Player::wantHit)
                .findAny()
                .ifPresent(it -> it.hit(cardDeck.draw()));
    }

    public boolean isDealerShouldMoreHit() {
        return dealer.canHit();
    }

    public void hitForDealer() {
        dealer.hit(cardDeck.draw());
    }

    public GameStatistic statistic() {
        final Map<Player, DealerCompeteResult> resultPerParticipant = players.stream()
                .collect(toMap(
                        identity(),
                        dealer::compete
                ));

        return new GameStatistic(dealer, players, resultPerParticipant);
    }

    public List<Player> participants() {
        return new ArrayList<>(players);
    }

    public Dealer dealer() {
        return dealer;
    }
}