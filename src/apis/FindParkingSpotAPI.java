package apis;

import data.EntryPoint;
import data.SpotSelection;
import data.VehicleType;
import finder.ParkingSpotFinder;
import manager.VehicleParkingManager;
import manager.VehicleTypeManagerFactory;
import selector.ParkingSpotSelector;
import selector.SpotSelectorFactory;

public class FindParkingSpotAPI {

    public  ParkingSpot findParkingSpot(EntryPoint entryPoint, VehicleType vehicle,
                                        SpotSelection spotSelection){
        //there are 3 vehicle type
        /*
        trick to write super extensible classes
            code to abstractions rather than concretions and free yourself from nasty if else checks
            dependency on concrete classes
            enjoy better extensibility for high level classes
            eg: abstractType.do () -> if() concreteType.do(); else if() concreteType2.do() else concreteType3.do();

            how would you instantiate the class which implement vehiclemngr -> factory pattern
                factory pattern says -> all creational responsibilities shouls be delegated to seperate factory class.
                usage of new keyword in multiple classes can thus be prevented

            keeping factory stateless -> pvt constructor is prohibiting instantiation of object of this class. we need to keep all other methods static.
            thus user of this factory won't have to use new keyword to create its object and invoke methods
         */

        VehicleParkingManager vehicleParkingManager = VehicleTypeManagerFactory.getVehicleParkingManager(vehicle);
        ParkingSpotSelector parkingSpotSelector;
        if(spotSelection.equals(SpotSelection.RANDOM)) parkingSpotSelector = SpotSelectorFactory.getRandonSelector();
        else if (spotSelection.equals(SpotSelection.NEAREST)) parkingSpotSelector = SpotSelectorFactory.getNearestSelector(entryPoint);
        else throw new RuntimeException();

        /*
        strategy pattern -> to mng parking and get spot
         */
        return new ParkingSpotFinder(vehicleParkingManager, parkingSpotSelector).findParkingSpot();

        /*
        NOTE: you can reflec upon: how dependency version principle (SOLID principle)
        and strategy pattern (a design pattern) go hand in hand
         */
    }
}
