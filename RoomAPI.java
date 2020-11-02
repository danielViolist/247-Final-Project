import java.util.ArrayList;
import java.util.Random;

public class RoomAPI {
	
	private static ArrayList<Room> rooms;
	
	public RoomAPI() {
		rooms = DataReader.loadRooms();
	}
	
	private static void check() {
		if (rooms == null) 
			rooms = DataReader.loadRooms();
	}
	
	public static int getNewRoomID() {
		check();
		Random r = new Random();
		int rand = r.nextInt();
		while (DataReader.roomExists(rand)) 
			rand = r.nextInt();
		return rand;
	}
	
	public static ArrayList<Room> getRooms() {
		check();
		return rooms;
	}
	
	public static void createRoom(Room room) {
		check();
		DataWriter.writeRoom(room);
		rooms.add(room);
	}
	
	public static void removeRoom(Room room) {
		check();
		DataWriter.removeRoom(room.getRoomID());
		rooms.remove(room);
	}
	
}
