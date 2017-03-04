package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LocationDAO {

    private static final String INSERT_LOCATION = "INSERT INTO location VALUES(loc_sn_seq.NEXTVAL,?,?)";
    private static final String SELECT_COUNT_DEVICE = "SELECT COUNT(DEVICE.SN),ADDRESS,KEEPER FROM DEVICE,LOCATION WHERE LOCATION.SN=? ";
    private static final String SELECT_COUNT_LOCTION = "SELECT COUNT(SN) FROM LOCATION";
    private static final String SELECT_LOCATION = "SELECT * FROM LOCATION";
    private static final String SELECT_LOCATION_BY_KEEPER = "SELECT * FROM location WHERE keeper_sn = ?";
    private static final String SELECT_LOCATION_BY_DEP = "SELECT location.* FROM keeper,location WHERE keeper_sn = keeper.sn AND keeper.department_sn = ?";
    private static final String UPDATE_LOCATION = "UPDATE location SET address = ? WHERE sn = ?";
    private static final String DELETE_LOCATION = "DELETE FROM location WHERE sn = ?";

    private Connection connection = null;

    private PreparedStatement pstmt = null;

    private ResultSet rs = null;

    private Statement stmt = null;

    public LocationDAO() {
        connection = DBConnection.getDBConnection();
    }
    
    
    public int addLocation(Location location) {
        int k = 0;
        try {
            pstmt = connection.prepareStatement(INSERT_LOCATION);
            pstmt.setString(1, location.getKeeper_sn());
            pstmt.setString(2, location.getAddress());
            k = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, pstmt, stmt, connection);
        }
        return k;
    }
    
    public ArrayList<Location> getLocation(String keeper) {
        // TODO
        ArrayList<Location> loc_list = new ArrayList<Location>();
        try {
            pstmt = connection.prepareStatement(SELECT_LOCATION_BY_KEEPER);
            pstmt.setString(1, keeper);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                loc_list.add(new Location(rs.getInt("sn"), rs.getString("keeper_sn"), rs.getString("address")));
            }
        } catch (SQLException e) {
            // TODO 
            e.printStackTrace();
        }finally {
            DBConnection.close(rs, pstmt, stmt, connection);
        }

        return loc_list;
    }
    
    public ArrayList<Location> getLocation(int dep_sn) {
        // TODO
        ArrayList<Location> loc_list = new ArrayList<Location>();
        try {
            pstmt = connection.prepareStatement(SELECT_LOCATION_BY_DEP);
            pstmt.setInt(1, dep_sn);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                loc_list.add(new Location(rs.getInt("sn"), rs.getString("keeper_sn"), rs.getString("address")));
            }
        } catch (SQLException e) {
            // TODO 
            e.printStackTrace();
        }finally {
            DBConnection.close(rs, pstmt, stmt, connection);
        }

        return loc_list;
    }

    
    public int updateLocation(int sn, String address) {
        // TODO 
        int k = 0;
        try {
            if (!address.equals("") && sn != 0) {
                pstmt = connection.prepareStatement(UPDATE_LOCATION);
                pstmt.setString(1, address);
                pstmt.setInt(2, sn);

                k = pstmt.executeUpdate();
            } else
                return k;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {

            DBConnection.close(rs, pstmt, stmt, connection);
        }
        return k;
    }
    
    
    public int deleteLocation(int sn) {
        // TODO 
        int k = 0;
        try {
            
            pstmt = connection.prepareStatement(DELETE_LOCATION);
            pstmt.setInt(1, sn);
            k = pstmt.executeUpdate();
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {

            DBConnection.close(rs, pstmt, stmt, connection);
        }
        return k;
    }

    public ArrayList<Location> getAllLocation() {
        ArrayList<Location> list = new ArrayList<Location>();
        try {
            pstmt = connection.prepareStatement(SELECT_LOCATION);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Location(rs.getInt("sn"), rs.getString("keeper_sn"), rs.getString("address")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, pstmt, stmt, connection);
        }
        return list;
    }

    
    
    
    
    
    
    
    
    
    
    

    // ÊàñÂæó‰∏?∏™Âú∞ÊñπÊâ?îæËÆæÂ§áÁöÑÊ?Êï?
    public int getAllDeviceCount(String sn) {
        int k = 0;
        try {
            pstmt = connection.prepareStatement(SELECT_COUNT_DEVICE);
            pstmt.setString(1, sn);
            k = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, pstmt, stmt, connection);
        }
        return k;
    }

   
    public int getLoctionCount() {// ËøîÂõûÊâ?úâÁöÑÂú∞Âù?
        int k = 0;
        try {
            pstmt = connection.prepareStatement(SELECT_COUNT_LOCTION);
            k = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, pstmt, stmt, connection);
        }
        return k;
    }

    

    
}
