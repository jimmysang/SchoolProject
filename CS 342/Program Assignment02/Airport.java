package datastructure.queue;

import java.util.Random;

public class Airport {
    private Runway runway;

    private int landTime;

    private int takeOffTime;

    private int avgLandArrivalTime;

    private int avgTakeOffArrivalTime;

    private int nextTakeOffArrivalTime;

    private int nextLandArrivalTime;

    private int totalTakeOffWaitTime;

    private int totalTakeOff;

    private int totalLandWaitTime;

    private int totalLand;

    private int maxWaitingTime;

    private int crashed;

    private int clock;

    Random random = new Random();

    LLQueue<Airplane> takeOffLine;

    LLQueue<Airplane> landLine;

    public Airport(int landTime, int takeOffTime, int maxWaitingTime, int avgLandArrivalTime, int avgTakeOffArrivalTime){
        this.landTime = landTime;
        this.takeOffTime = takeOffTime;
        this.maxWaitingTime = maxWaitingTime;
        this.avgTakeOffArrivalTime = avgTakeOffArrivalTime;
        this.avgLandArrivalTime = avgLandArrivalTime;
        runway = new Runway();
        takeOffLine = new LLQueue<>();
        landLine = new LLQueue<>();
    }

    //this method check whether there's a new plane join the waiting line, if yes, scheduel another one
    public void scheduleNext(){
        // every minute we check if there is new taking off plane or landing plane
        if(nextLandArrivalTime == clock){
            landLine.insert(new Airplane(clock));
            //generate a random number whose average value is avgLandArrivalTime and added to next arrival time
            nextLandArrivalTime += random.nextInt(avgLandArrivalTime * 2 - 1) + 1;
        }
        if(nextTakeOffArrivalTime == clock){
            takeOffLine.insert(new Airplane(clock));
            nextTakeOffArrivalTime += random.nextInt(avgTakeOffArrivalTime * 2 - 1) + 1;
        }

    }

    //this method check whether runway is available, if yes, arrange a plane to land or take off
    public void checkRunway() {
        if (!runway.isService()) {
            // check if there are planes waiting to land, unless there aren't we can arrange plane to take off
            if(!landLine.isEmpty()){
                //arrange a plane to land
                Airplane airplane = landLine.remove();
                // if this plane has crashed, we need to find another not crashed plane
                while(airplane != null && airplane.isCrashed(clock, maxWaitingTime)){
                    crashed++;
                    totalLandWaitTime += clock - airplane.getArriveTime();
                    airplane = landLine.remove();
                }
                if(airplane != null){
                    totalLand++;
                    totalLandWaitTime += clock - airplane.getArriveTime();
                    runway.accept(landTime);
                    System.out.println(totalLand + " Land accept" + clock);
                    return;
                }else{
                    // if all landing plane are crashed, we need to arrange a taking off plane
                    checkRunway();
                }
            }
            // if there is no planes waiting landing, we arrange planes to take off
            else if(!takeOffLine.isEmpty()){
                Airplane airplane = takeOffLine.remove();
                totalTakeOff++;
                totalTakeOffWaitTime += clock - airplane.getArriveTime();
                runway.accept(takeOffTime);
                System.out.println(totalTakeOff + " Take off accept" + clock);
            }
        }
    }


    public void run(int duration){
        //generate first two airplanes arrival time
        nextLandArrivalTime = random.nextInt(avgLandArrivalTime * 2 - 1) + 1;
        nextTakeOffArrivalTime = random.nextInt(avgTakeOffArrivalTime * 2 - 1) + 1;
        //simulate starts
        for(clock = 0; clock < duration; clock++){
            scheduleNext();
            checkRunway();
            runway.timeDecrement();
        }
        printRecord();
    }

    //print the result of simulation
    public void printRecord(){
        System.out.printf("There are %s planes has taken off today, their average waiting time is %s minutes.\n",
                totalTakeOff, totalTakeOff == 0 ? 0 : totalTakeOffWaitTime/totalTakeOff);
        System.out.printf("There are %s planes has landed today, their average waiting time is %s minutes.\n",
                totalLand, totalLand == 0 ? 0 : totalLandWaitTime/totalLand);
        System.out.printf("There are %s planes crashed today.", crashed);
    }

}
