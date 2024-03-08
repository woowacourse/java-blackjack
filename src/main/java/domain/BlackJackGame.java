package domain;

import domain.dto.GameStatusDto;
import domain.dto.GamerDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {
    private final List<Gamer> players;
    private final Gamer dealer;
    private final Deck deck;

    public BlackJackGame(List<Gamer> players) {
        this.players = players;
        this.dealer = new Gamer(new Name("딜러"));
        this.deck = new Deck();
    }

    public void initialDealing() {
        deck.shuffle();
        takeTwoCards(dealer);
        players.forEach(this::takeTwoCards);
    }

    private void takeTwoCards(Gamer gamer) {
        gamer.takeCard(deck.draw());
        gamer.takeCard(deck.draw());
    }

    public GameStatusDto getGameStatusDto() {
        return GameStatusDto.of(GamerDto.from(dealer), toGamerDto());
    }

    public List<GamerDto> toGamerDto() {
        List<GamerDto> gamerDtos = new ArrayList<>();
        players.stream()
                .map(GamerDto::from)
                .forEach(gamerDtos::add);
        return Collections.unmodifiableList(gamerDtos);
    }

    public void drawCardFromName(String name) {
        players.stream().filter(player -> player.isName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 참여자 입니다."))
                .takeCard(deck.draw());
    }

    public boolean isBustFromName(String name) {
        return players.stream().filter(player -> player.isName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 참여자 입니다."))
                .isBust();
    }

    public int drawDealerCard() {
        int dealerDrawCount = 0;
        while (dealer.getTotalScore() <= 16) {
            dealer.takeCard(deck.draw());
            dealerDrawCount++;
        }
        return dealerDrawCount;
    }

    public BlackJackResult getGameResult() {
        return new BlackJackResult(
                dealer.getTotalScore(),
                players.stream()
                        .collect(Collectors
                                .toMap(Gamer::getName, Gamer::getTotalScore)));
    }
}
