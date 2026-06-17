import java.util.Scanner;

class InvalidFineException extends Exception {

InvalidFineException(String msg) {
    super(msg);
}

}

class Fine {

String reason;
double amount;
boolean paid;

Fine(String reason, double amount) {
    this.reason = reason;
    this.amount = amount;
    paid = false;
}

void markAsPaid() {
    paid = true;
}

void displayFine() {
    System.out.println("Reason: " + reason);
    System.out.println("Amount: Rs." + amount);
    System.out.println("Paid: " + paid);
}

}

class Student {

String name;
int roomNo;
Fine[] fines;
int count;

Student(String name, int roomNo) {

    this.name = name;
    this.roomNo = roomNo;
    fines = new Fine[20];
    count = 0;
}

void addFine(Fine fine) throws InvalidFineException {

    if (fine.amount <= 0) {
        throw new InvalidFineException(
                "Fine amount must be positive.");
    }

    fines[count++] = fine;
}

void viewPendingFines() {
    System.out.println("\nPending Fines:");

    for (int i = 0; i < count; i++) {

        if (!fines[i].paid) {

            System.out.println("Fine:" + i);

            fines[i].displayFine();
            System.out.println();
        }
    }
}

void viewPaidFines() {

    System.out.println("\nPaid Fines:");

    for (int i = 0; i < count; i++) {

        if (fines[i].paid) {

            System.out.println("Fine:" + i);

            fines[i].displayFine();
            System.out.println();
        }
    }
}

void payFine(int index) {

    if (index >= 0 && index < count) {

        fines[index].markAsPaid();

        System.out.println(
                "Fine paid successfully!");
    }
    else {

        System.out.println(
                "Invalid Fine Number.");
    }
}

}

class Warden {
String wardenName;

Warden(String wardenName) {
    this.wardenName = wardenName;
}

void issueFine(Student s,
               String reason,
               double amount)
        throws InvalidFineException {

    Fine fine =
            new Fine(reason, amount);

    s.addFine(fine);

    System.out.println(
            "Fine issued successfully.");
}

}

public class HostelSystem {
public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.print("Enter Student Name: ");
    String name = sc.nextLine();

    System.out.print("Enter Room Number: ");
    int room = sc.nextInt();
    sc.nextLine();

    Student student =
            new Student(name, room);

    Warden warden =
            new Warden("Mr. Sharma");

    try {

        warden.issueFine(
                student,
                "Late Entry",
                200);

        warden.issueFine(
                student,
                "Mess Damage",
                500);

    }
     catch (InvalidFineException e) {

        System.out.println(
                e.getMessage());
    }

    student.viewPendingFines();

    System.out.print(
            "\nEnter Fine Number To Pay: ");

    int choice = sc.nextInt();

    student.payFine(choice);

    student.viewPaidFines();

    sc.close();
}

}
