package datastructure.queue;

public class Airplane {

    private int arriveTime;

    public Airplane(int arriveTime){
        this.arriveTime = arriveTime;
    }

    public boolean isCrashed(int now, int maxWaitingTime){
        return now - arriveTime > maxWaitingTime;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }


}
