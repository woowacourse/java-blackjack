package domain.state;

public enum DrawState {
    HITTING{
        @Override
        public DrawState hit(final HandState handState){
            if(handState.isNormal()){
                return HITTING;
            }

            return FINISHED;
        }

        @Override
        public DrawState stand(){
            return FINISHED;
        }
    },
    FINISHED{
        @Override
        public DrawState hit(final HandState handState){
            return FINISHED;
        }

        @Override
        public DrawState stand(){
            return FINISHED;
        }
    };

    public boolean isFinished(){
        return this == FINISHED;
    }

    public abstract DrawState hit(HandState handState);
    public abstract DrawState stand();
}
