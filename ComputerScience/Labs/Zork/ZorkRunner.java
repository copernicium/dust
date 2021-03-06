package ComputerScience.Labs.Zork;

import Util.Util;
import java.util.Scanner;

/**
 * Runs the Zork game
 */
public class ZorkRunner {
	/**
	 * Represents any of the defined commands the player can run
	 */
	enum Command{
		HELP,QUIT,INVENTORY,MOVE,TAKE,ROOM_INFO,UNKNOWN;

		/**
		 * Reads a string and parses a command from it
		 * @param s the string to read
		 * @return the command it parsed
		 */
		public static Command toCommand(String s){
			s = Util.stringToLowerCase(s);
			switch(s){
				case "help":
					return HELP;
				case "quit":
					return QUIT;
				case "inventory":
					return INVENTORY;
				case "move":
					return MOVE;
				case "take":
					return TAKE;
				case "room info":
					return ROOM_INFO;
				default:
					return UNKNOWN;
			}
		}
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		Game game = new Game();

		System.out.println(
				"Welcome to the Wilsonville Robotics District Event!\n" +
				"Your mission, should you choose to accept it, is to\n" +
				"assemble a robot from parts and load software onto \n" +
				"it in order to compete and win! The game is afoot!\n"
		);
		while(true){
			if(game.getVictory()){
				System.out.println("Congratulations! YOU WIN THE COMPETITION! EXCLAMATION POINT! Redundant! Redundant!");
				return;
			}

			System.out.print("Enter a command (type \"help\" for help): ");
			Command command = Command.toCommand(in.nextLine().trim());

			switch(command){
				case ROOM_INFO:
					{
						String message = "";
						message += "You are currently standing in the " + game.getCurrentRoom().getName() + ".";
						message += "\n" + game.getCurrentRoom().getDesc();
						message += "\nThere are exits to " + game.getCurrentRoom().getExits() + ".";
						System.out.println(message);
						break;
					}
				case HELP:
					{
						String message = "Command help\n";
						message += "\t\"help\"         List all commands and their descriptions\n";
						message += "\t\"inventory\"    Display the player's inventory\n";
						message += "\t\"move\"         Prompt the player to enter a new room\n";
						message += "\t\"take\"         Remove the treasure from the current room and add it in the player's inventory\n";
						message += "\t\"quit\"         Exit the game\n";
						message += "\t\"room info\"    Display information about the room the player is in";

						System.out.println(message);
						break;
					}
				case INVENTORY:
					{
						System.out.println(game.printInventory());
					}
					break;
				case MOVE:
					{
						boolean done = false;
						while(!done) {
							final String STAY = "stay";

							System.out.print("The exits from the " + game.getCurrentRoom().getName() + " are to " + game.getCurrentRoom().getExits() + ". Which room do you want to enter? (Type \"" + STAY + "\" to stay where you are): ");
							String query = Util.stringToLowerCase(in.nextLine().trim());

							if(query.equals(STAY)) break;

							for (Room r : game.getCurrentRoom().showExits()) {
								if (query.equals(Util.stringToLowerCase(r.getName()))) {
									game.enterRoom(game.getRoom(game.getRoomIndex(r)));
									done = true;
									break;
								}
							}
							if(!done){
								System.out.println("\"" + query + "\"" + " is not a valid room.");
							}
						}
					}
					break;
				case QUIT:
					{
						System.out.println("Goodbye");
						return;
					}
				case TAKE:
					{
						game.pickUpTreasure(game.getCurrentRoom().getTreasure(), game.getCurrentRoom(),game.getInventory());
						break;
					}
				case UNKNOWN:
					{
						System.out.println("Command not found. Type \"help\" for help");
						break;
					}
				default:
					Util.nyi(Util.getFileName(), Util.getLineNumber());
			}
			System.out.print("\n");
		}
	}
}
