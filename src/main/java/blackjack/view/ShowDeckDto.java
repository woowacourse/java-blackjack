package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Player;
import java.util.List;
import java.util.stream.Collectors;

public class ShowDeckDto {

    private String name;
    private List<String> cards;
    private int score;

    public ShowDeckDto(Player player) {
        this.name = player.name();
        this.cards = player.cards()
            .stream()
            .map(Card::name)
            .collect(Collectors.toList());
        this.score = player.score().get();
    }

    public String name() {
        return name;
    }

    public List<String> cards() {
        return cards;
    }

    public int score() {
        return score;
    }
}
