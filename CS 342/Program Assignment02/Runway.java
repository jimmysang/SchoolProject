package datastructure.queue;

public class Runway {
    private int servicetime;

    public void accept(int servicetime){
        this.servicetime = servicetime;
    }

    public void timeDecrement(){
        servicetime--;
    }

    public boolean isService(){
        return servicetime > 0;
    }

    public int getServicetime() {
        return servicetime;
    }

    public void setServicetime(int servicetime) {
        this.servicetime = servicetime;
    }
}
