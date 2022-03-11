package blackjack;

import blackjack.domain.WinDrawLose;
import blackjack.domain.card.Deck;
import blackjack.domain.dto.CardDto;
import blackjack.domain.dto.HitResultDto;
import blackjack.domain.dto.WinDrawLoseDto;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackRound {
    private final Deck deck;
    private final Gamer dealer;
    private final Players players;

    public BlackjackRound(Players players) {
        this.dealer = new Dealer();
        this.players = players;
        this.deck = new Deck();
        handOutInitCards();
    }

    public void handOutInitCards() {
        dealer.hit(deck.draw());
        dealer.hit(deck.draw());
        for (Player player : players.getPlayers()) {
            player.hit(deck.draw());
            player.hit(deck.draw());
        }
    }

    public Map<String, List<CardDto>> getCardStatus() {
        Map<String, List<CardDto>> cardStatus = new LinkedHashMap<>();
        addCardDto(cardStatus, dealer);
        List<Player> playerList = players.getPlayers();
        for (Player player : playerList) {
            addCardDto(cardStatus, player);
        }

        return cardStatus;
    }

    private void addCardDto(Map<String, List<CardDto>> cardStatus, Gamer gamer) {
        cardStatus.put(gamer.getName(), toListCardDto(gamer));
    }

    public List<CardDto> toListCardDto(Gamer gamer) {
        return gamer.getViewCard().stream()
                .map(card -> new CardDto(card.getDenomination().getName(), card.getSuit().getName()))
                .collect(Collectors.toList());
    }

    public void playerHit() {
        Player nowTurnPlayer = players.getNowPlayer();
        nowTurnPlayer.hit(deck.draw());
    }

    public void dealerHit() {
        if (!(dealer instanceof Dealer)) {
            throw new RuntimeException("딜러의 구현체가 Dealer가 맞는지 확인.");
        }
        while (((Dealer) dealer).checkHitFlag()) {
            dealer.hit(deck.draw());
        }
    }

    public List<HitResultDto> getHitResult() {
        List<HitResultDto> hitResultDtos = new ArrayList<>();
        hitResultDtos.add(
                new HitResultDto(dealer.getName(), toListCardDto(dealer), dealer.getCards().calculateScore()));
        for (Player player : players.getPlayers()) {
            hitResultDtos.add(
                    new HitResultDto(player.getName(), toListCardDto(player), player.getCards().calculateScore()));
        }
        return hitResultDtos;
    }

    public List<WinDrawLoseDto> judgeWinDrawLose() {
        WinDrawLose.judge(dealer, players);
        return makeWinDrawLoseDto();
    }

    private List<WinDrawLoseDto> makeWinDrawLoseDto() {
        List<WinDrawLoseDto> winDrawLoseDtos = new ArrayList<>();
        winDrawLoseDtos.add(new WinDrawLoseDto(dealer.getName(), dealer.getWinDrawLoseString()));
        for (Player player : players.getPlayers()) {
            winDrawLoseDtos.add(new WinDrawLoseDto(player.getName(), player.getWinDrawLoseString()));
        }
        return winDrawLoseDtos;
    }

    public Players getPlayers() {
        return players;
    }
}
