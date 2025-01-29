package selector;

import data.EntryPoint;

public class SpotSelectorFactory {

    private SpotSelectorFactory() {

    }

    public static ParkingSpotSelector getNearestSelector(EntryPoint entryPoint){
        return new NearestSelector(entryPoint);
    }

    public static ParkingSpotSelector getRandonSelector(EntryPoint entryPoint){
        return new RandomSpotSelector();
    }
}
