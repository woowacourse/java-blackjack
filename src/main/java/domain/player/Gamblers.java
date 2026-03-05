package domain.player;

import java.util.List;

public class Gamblers {
    private final List<Gambler> gamblers;
    public Gamblers(List<String> names){
        validate(names);
        init(names);
    }

    private void validate(List<String> names){

    }
}
