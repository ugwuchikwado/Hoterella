
package com.bean;

/**
 *
 * @author AUSTINS
 */
public class RoomService {
     private String roomtype;
    private String roomposition;
    private String roomnumber;

    public RoomService(String roomposition, String roomnumber) {
        this.roomposition = roomposition;
        this.roomnumber = roomnumber;
    }

    public RoomService(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    
    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getRoomposition() {
        return roomposition;
    }

    public void setRoomposition(String roomposition) {
        this.roomposition = roomposition;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }
}
