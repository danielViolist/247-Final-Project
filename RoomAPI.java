import java.util.ArrayList;
import java.util.Random;

public class RoomAPI {
	
	private ArrayList<Room> rooms;
	public static RoomAPI roomapi;
	
	public RoomAPI() {
		rooms = DataReader.loadRooms();
	}
	
	public static RoomAPI getInstance(){
		if (roomapi == null)
			return new RoomAPI();
		return roomapi;
	}
	
	public int getNewRoomID() {
		Random r = new Random();
		int rand = r.nextInt();
		while (DataReader.roomExists(rand)) 
			rand = r.nextInt();
		return rand;
	}
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public void createRoom(Room room) {
		DataWriter.writeRoom(room);
		rooms.add(room);
	}
	
	public void removeRoom(int id) {
		for(int i = 0; i < rooms.size(); i++) {
			if(rooms.get(i).getRoomID() == id) {
				DataWriter.removeRoom(id);
				rooms.remove(i);
			}
		}
	}
	
}