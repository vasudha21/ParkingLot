//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}

//
/*
large ticket price for heavy vehicle -> payment made based on diff hour,
    amnt difer based on vehicle type
    with diff payment mode
diff floors with diff entry and exit point
in exit -> show ticket and point will get deducted
strategy to assign parking -> nearest free spot to entering gate

apis:
    findParkingSpotAPI ->
        i/p: entry point, vehicleType, spotSelection strategy
        o/p parkingSpot
    GetTicketAPI ->
        i/p vehicleType, parkingSpot
        o/p -> ticket

    PayParkingFeesAPI
        i/p ticketID, paymentDetails (cash/card)
        o/p: success/fail

    VacateParkingSpotAPI
        i/p parkingSpot
 */