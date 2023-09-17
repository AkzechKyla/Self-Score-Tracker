import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreTracker {
	
	private static void load_actions(Map<String, Integer> actions) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("actions.txt"));
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" = ");
				
				String action = parts[0];
				int points = Integer.parseInt(parts[1]);
				actions.put(action, points);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Creating a new actions.txt file...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void save_actions(Map<String, Integer> actions) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("actions.txt"));
			for (Map.Entry<String, Integer> entry : actions.entrySet()) {
				String action = entry.getKey();
				Integer points = entry.getValue();
				writer.write(action + " = " + points + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void add_action(Map<String, Integer> actions, Scanner scanner) {
		System.out.println("\nMENU: Add an action");
		System.out.println("\nEnter an action you want to add on your list of actions:");
		String action = scanner.nextLine();
		System.out.println("\nEnter corresponding points for that action, e.g., +2 or -1:");
		int points = scanner.nextInt();
		scanner.nextLine();
		actions.put(action, points);
		System.out.println("Action added successfully!");
	}
	
	private static void read_actions(Map<String, Integer> actions) {	
		for (Map.Entry<String, Integer> entry : actions.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
	
	private static void delete_actions(Map<String, Integer> actions, Scanner scanner) {
		if (actions.isEmpty()) {
            System.out.println("\nNo actions to delete.");
            return;
        }
		
		System.out.println("\nMENU: Delete an action: ");
		System.out.println("\nWhich of the following actions do you want to remove?");
		
		read_actions(actions);
		
		System.out.println("\nEnter the name of the action");
		System.out.print(">>> ");
		
		String action_to_delete = scanner.nextLine();
		
		if (actions.containsKey(action_to_delete)) {
            actions.remove(action_to_delete);
            System.out.println("\nAction deleted successfully!");
        } else {
            System.out.println("\nAction not found.");
        }
	}

	public static void main(String[] args) throws IOException {
		int total_score = 100;
		Map<String, Integer> actions = new HashMap<>();
		String action;
		int points;
		
		load_actions(actions);
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.println("\nOVERALL SCORE FOR TODAY: " + total_score);
			System.out.println("\n\tMAIN MENU");
			System.out.println("\n1. Add an action");
			System.out.println("2. Read all actions");
			System.out.println("3. Delete an action");
			System.out.println("4. Exit\n");
			System.out.print(">> ");
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
				case 1:
					add_action(actions, scanner);
					break;
				case 2:
					System.out.println("\nLIST OF ACTIONS: ");
					read_actions(actions);
					break;
				case 3:
					delete_actions(actions, scanner);
					break;
				case 4:
					System.out.println("Goodbye!");
	                scanner.close();
	                save_actions(actions);
	                System.exit(0);
	            default:
	            	System.out.println("Invalid choice. Please select a valid option.");
	            	
			}
		}

	}

}
