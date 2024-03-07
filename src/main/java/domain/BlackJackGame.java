package domain;

import domain.dto.GameStatusDto;
import domain.dto.GamerDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        return GameStatusDto.of(toGamerDto());
    }

    public List<GamerDto> toGamerDto() {
        // TODO: 네이밍 생각하기
        List<GamerDto> gamerDtos = new ArrayList<>();
        gamerDtos.add(GamerDto.from(dealer));
        players.stream()
                .map(GamerDto::from)
                .forEach(gamerDtos::add);
        return Collections.unmodifiableList(gamerDtos);
    }
}
