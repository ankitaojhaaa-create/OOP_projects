import java.util.Scanner;


interface Displayable {
    void display();
}
class InvalidPositionException extends Exception {

    InvalidPositionException(String msg) {
        super(msg);
 }
}
public class F1Manager {
class Driver implements Displayable {
        String name;
        String country;
        int age;
        int points;
        int wins;
        int podiums;

        Driver(String name, String country, int age, int points, int wins, int podiums) {
            this.name = name;
            this.country = country;
            this.age = age;
            this.points=points;
            this.wins=wins;
            this.podiums=podiums;
        }
     @Override
     public void display() {
            System.out.println("\nDriver: " + name);
            System.out.println("Country: " + country);
            System.out.println("Age: " + age);
            System.out.println("Points: " + points);
            System.out.println("Wins: " + wins);
            System.out.println("Podiums: " + podiums);
    }
    }
   class Team implements Displayable {

        String teamName;
        Driver[] drivers = new Driver[2];
        int count = 0;

        Team(String teamName) {
            this.teamName = teamName;
        }

        void addDriver(Driver d) {

            if (count < drivers.length) {
                drivers[count] = d;
                count++;
            } else {
                System.out.println("Team already has 2 drivers!");
        }
        }
        int calculateTeamPoints() {

            int total = 0;
            for (int i = 0; i < count; i++) {
                total += drivers[i].points;
            }
            return total;
        }

        @Override
        public void display() {

            System.out.println("\n=== " + teamName + " ===");
            for (int i = 0; i < count; i++) {
                drivers[i].display();
            }
            System.out.println("Team Points: " + calculateTeamPoints());
    }
    }
    
    class Race {

        String raceName;

        Race(String raceName) {
            this.raceName = raceName;
        }
        int getPoints(int position) {

            switch (position) {

                case 1: return 25;
                case 2: return 18;
                case 3: return 15;
                case 4: return 12;
                case 5: return 10;
                case 6: return 8;
                case 7: return 6;
                case 8: return 4;
                case 9: return 2;
                case 10: return 1;
                default: return 0;
        }
        }

        void recordResult(Driver d, int position)
                throws InvalidPositionException {

            if (position < 1 || position > 20) {

                throw new InvalidPositionException(
                        "Position must be between 1 and 20");
            }

            int pts = getPoints(position);

            d.points += pts;

            if (position == 1)
                d.wins++;

            if (position <= 3)
                d.podiums++;

            System.out.println(
                    d.name + " finished P" +
                    position + " and earned " +
                    pts + " points.");
    }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        F1Manager manager = new F1Manager();
        Driver[] drivers = new Driver[2];

        for (int i = 0; i < drivers.length; i++) {

            System.out.println("\nEnter Driver " + (i + 1));

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Country: ");
            String country = sc.nextLine();

            System.out.print("Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            drivers[i] = manager.new Driver(
                    name,
                    country,
                    age);
        }

        System.out.print("\nEnter Team Name: ");
        String teamName = sc.nextLine();

        Team team = manager.new Team(teamName);

        for (int i = 0; i < drivers.length; i++) {
            team.addDriver(drivers[i]);
        }

        System.out.print("\nEnter Race Name: ");
        String raceName = sc.nextLine();

        Race race = manager.new Race(raceName);
        for (int i = 0; i < drivers.length; i++) {

            boolean valid = false;

            while (!valid) {

                try {

                    System.out.print(
                            "Enter finishing position for "
                            + drivers[i].name + ": ");

                    int pos = sc.nextInt();

                    race.recordResult(
                            drivers[i],
                            pos);

                    valid = true;

                }
                catch (InvalidPositionException e) {

                    System.out.println(
                            "Error: "
                            + e.getMessage());

                    System.out.println(
                            "Try Again!");

          }
        }
        }
        System.out.println("\nFINAL STANDINGS");
        team.display();
        sc.close();
  }
}
