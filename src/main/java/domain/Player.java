package domain;

public final class Player extends Participant {
    private final Name name;
    private DrawState drawState;

    public Player(Name name) {
        super();
        this.name = name;
        this.drawState = DrawState.HITTING;
    }

    public Player(String name) {
        this(new Name(name));
    }

    public void hit(final Cards cards){
        drawCard(cards);
        final HandState handState = getHandState();
        this.drawState = drawState.hit(handState);
    }

    public void stand(){
        this.drawState = drawState.stand();
    }

    public boolean isDrawFinished(){
        return drawState.isFinished();
    }

    public String getName() {
        return name.getName();
    }
}
