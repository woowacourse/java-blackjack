package domain.user;

import java.util.ArrayList;
import java.util.List;

public class Names {
    private final List<Name> names = new ArrayList<>();

    public Names(List<String> inputNames) {
        inputNames.forEach(name -> names.add(new Name(name)));
    }

    public List<Name> getNames() {
        return new ArrayList<>(names);
    }
}
