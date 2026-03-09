package domain.config;

import domain.card.CardGenerator;
import domain.card.GameCardGenerator;
import domain.view.ApplicationView;
import domain.view.InputReader;
import domain.view.OutputWriter;

public record BlackjackGameConfiguration(ApplicationView view, CardGenerator gameCardGenerator) {

    public BlackjackGameConfiguration() {
        this(ApplicationView.of(new InputReader(), new OutputWriter()), new GameCardGenerator());
    }
}
