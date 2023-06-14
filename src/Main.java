// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.LinkedList;
import java.util.Scanner;

record Place(String name, double populationInMillion){
    @Override
    public String toString() {
        return String.format("[Place-> %s , Population-> %.2f Million]", name,populationInMillion);
    }
}
public class Main {
    public static void main(String[] args) {

        LinkedList<Place> placesToVisit = new LinkedList<>();

        Place France = new Place("France", 67.75);
        addPlace(placesToVisit,France);
        addPlace(placesToVisit, new Place("Turkey",84.78));
        addPlace(placesToVisit, new Place("Germany",83.2));
        addPlace(placesToVisit, new Place("Uganda",45.85));


        var iterator = placesToVisit.listIterator();
        Scanner scanner = new Scanner(System.in);
        boolean quitLoop = false;
        boolean forward = true;
        printMenu();
        while(!quitLoop){
            if(!iterator.hasPrevious()){
                System.out.println("Originating : "+ iterator.next());
                forward = true;
            }
            if(!iterator.hasNext()){
                System.out.println("Final : "+ iterator.previous());
                forward = false;
            }
            System.out.println("Enter Value: ");
            String menuItem = scanner.nextLine().toUpperCase().substring(0,1);

            switch (menuItem){
                case "F":
                    System.out.println("User Wants to Go Forward");
                    if(!forward){           //reversing direction
                        forward = true;
                        if(iterator.hasNext()){
                            iterator.next();  // Adjust position forward
                        }
                    }
                    if(iterator.hasNext()){
                        System.out.println(iterator.next());
                    }
                    break;
                case "B":
                    System.out.println("User Wants to Go Backwards");
                    if(forward){           //reversing direction
                        forward = false;
                        if(iterator.hasPrevious()){
                            iterator.previous();  // Adjust position backwards
                        }
                    }
                    if(iterator.hasPrevious()){
                        System.out.println(iterator.previous());
                    }
                    break;
                case "M":
                    printMenu();
                    break;
                case "L":
                    System.out.println(placesToVisit);
                    break;
                default:
                    quitLoop = true;
                    break;
            }
        }
    }

    private static void addPlace(LinkedList<Place> list, Place place){

    for(Place p : list){
        if(p.name().equalsIgnoreCase(place.name())){
            System.out.println("Found dublicate: " + place);
            return;
        }
    }
    int matchedIndex=0;
    for(var listPlace : list){
        if(place.populationInMillion()< listPlace.populationInMillion()){
            list.add(matchedIndex,place);
            return;
        }
        matchedIndex++;
    }
        list.add(place);
    }

    private static void printMenu(){
        System.out.println("""
                Available Actions (Select Word or Letter):
                (F)orward
                (B)ackwards
                (L)ist Places
                (M)enu
                (Q)uit""");
    }
}