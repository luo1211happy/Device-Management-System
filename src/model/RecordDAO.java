package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecordDAO {
	
	private static final String INSERT_RECORD = "INSERT INTO record(sn,device_sn,fault,fault_date) VALUES(rec_sn_seq.NEXTVAL,?,?,?)";
	private static final String SELECT_UNFINISHED_RECORD = "SELECT record.* FROM record, device WHERE device.keeper = ? AND device.sn = record.device_sn AND finish = 'N' ORDER BY record.sn";
	
	private static final String SELECT_FINISHED_RECORD1 = "SELECT record.* FROM record, device WHERE device.keeper = ? AND device.sn = record.device_sn AND finish = 'Y' AND fault_date >= ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORD2 = "SELECT record.* FROM record, device WHERE device.keeper = ? AND device.sn = record.device_sn AND finish = 'Y' AND fault_date <= ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORD3 = "SELECT record.* FROM record, device WHERE device.keeper = ? AND device.sn = record.device_sn AND finish = 'Y' AND device_sn = ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORD4 = "SELECT record.* FROM record, device WHERE device.keeper = ? AND device.sn = record.device_sn AND finish = 'Y' AND fault_date BETWEEN ? AND ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORD5 = "SELECT record.* FROM record, device WHERE device.keeper = ? AND device.sn = record.device_sn AND finish = 'Y' AND fault_date >= ? AND device_sn = ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORD6 = "SELECT record.* FROM record, device WHERE device.keeper = ? AND device.sn = record.device_sn AND finish = 'Y' AND fault_date <= ? AND device_sn = ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORD7 = "SELECT record.* FROM record, device WHERE device.keeper = ? AND device.sn = record.device_sn AND finish = 'Y' AND fault_date BETWEEN ? AND ? AND device_sn = ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORD8 = "SELECT record.* FROM record, device WHERE device.keeper = ? AND device.sn = record.device_sn AND finish = 'Y' ORDER BY record.fault_date DESC";
	
	private static final String SELECT_FINISHED_RECORDS1 = "SELECT record.* FROM record, device WHERE device.sn = record.device_sn AND finish = 'Y' AND fault_date >= ? ORDER BY device.keeper, record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDS2 = "SELECT record.* FROM record, device WHERE device.sn = record.device_sn AND finish = 'Y' AND fault_date <= ? ORDER BY device.keeper, record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDS3 = "SELECT record.* FROM record, device WHERE device.sn = record.device_sn AND finish = 'Y' AND device_sn = ? ORDER BY device.keeper, record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDS4 = "SELECT record.* FROM record, device WHERE device.sn = record.device_sn AND finish = 'Y' AND fault_date BETWEEN ? AND ? ORDER BY device.keeper, record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDS5 = "SELECT record.* FROM record, device WHERE device.sn = record.device_sn AND finish = 'Y' AND fault_date >= ? AND device_sn = ? ORDER BY device.keeper, record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDS6 = "SELECT record.* FROM record, device WHERE device.sn = record.device_sn AND finish = 'Y' AND fault_date <= ? AND device_sn = ? ORDER BY device.keeper, record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDS7 = "SELECT record.* FROM record, device WHERE device.sn = record.device_sn AND finish = 'Y' AND fault_date BETWEEN ? AND ? AND device_sn = ? ORDER BY device.keeper, record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDS8 = "SELECT record.* FROM record, device WHERE device.sn = record.device_sn AND finish = 'Y' ORDER BY device.keeper, record.fault_date DESC";
	
	private static final String SELECT_FINISHED_RECORDM1 = "SELECT record.* FROM record, device, keeper WHERE device.keeper = keeper.sn AND keeper.department_sn = ? AND device.sn = record.device_sn AND finish = 'Y' AND fault_date >= ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDM2 = "SELECT record.* FROM record, device, keeper WHERE device.keeper = keeper.sn AND keeper.department_sn = ? AND device.sn = record.device_sn AND finish = 'Y' AND fault_date <= ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDM3 = "SELECT record.* FROM record, device, keeper WHERE device.keeper = keeper.sn AND keeper.department_sn = ? AND device.sn = record.device_sn AND finish = 'Y' AND device_sn = ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDM4 = "SELECT record.* FROM record, device, keeper WHERE device.keeper = keeper.sn AND keeper.department_sn = ? AND device.sn = record.device_sn AND finish = 'Y' AND fault_date BETWEEN ? AND ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDM5 = "SELECT record.* FROM record, device, keeper WHERE device.keeper = keeper.sn AND keeper.department_sn = ? AND device.sn = record.device_sn AND finish = 'Y' AND fault_date >= ? AND device_sn = ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDM6 = "SELECT record.* FROM record, device, keeper WHERE device.keeper = keeper.sn AND keeper.department_sn = ? AND device.sn = record.device_sn AND finish = 'Y' AND fault_date <= ? AND device_sn = ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDM7 = "SELECT record.* FROM record, device, keeper WHERE device.keeper = keeper.sn AND keeper.department_sn = ? AND device.sn = record.device_sn AND finish = 'Y' AND fault_date BETWEEN ? AND ? AND device_sn = ? ORDER BY record.fault_date DESC";
	private static final String SELECT_FINISHED_RECORDM8 = "SELECT record.* FROM record, device, keeper WHERE device.keeper = keeper.sn AND keeper.department_sn = ? AND device.sn = record.device_sn AND finish = 'Y' ORDER BY record.fault_date DESC";
	
	private static final String SELECT_SINGLE_RECORD = "SELECT * FROM record WHERE sn=?";
	
	private static final String UPDATE_REPAIR = "UPDATE record SET repair = ?, repair_date = ? WHERE sn = ?";
	private static final String FINISH = "UPDATE record SET finish = 'Y' WHERE sn = ?";	
	
	private Connection connection = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public RecordDAO() {
        connection = DBConnection.getDBConnection();
    }
    
    public void addRecord(Record rec) {
        try {
            pstmt = connection.prepareStatement(INSERT_RECORD);
            pstmt.setString(1, rec.getDevice_sn());
            pstmt.setString(2, rec.getFault());
            pstmt.setDate(3, rec.getFault_date());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {

            DBConnection.close(rs, pstmt, null, connection);
        }

    }
    
    public void updateRepair(int sn, String repair, Date repair_date) {
        try {
            pstmt = connection.prepareStatement(UPDATE_REPAIR);
            pstmt.setString(1, repair);
            pstmt.setDate(2, repair_date);
            pstmt.setInt(3, sn);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {

            DBConnection.close(rs, pstmt, null, connection);
        }

    }
    
    public void finish(int sn) {
        try {
            pstmt = connection.prepareStatement(FINISH);
            pstmt.setInt(1, sn);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("A database error occured." + e.getMessage());
        } finally {

            DBConnection.close(rs, pstmt, null, connection);
        }

    }
    
    public ArrayList<Record> getUnfinishedRecords(String keeper) {

        ArrayList<Record> rec_list = new ArrayList<Record>();
        int sn;
        String device_sn;
        String fault;
        Date fault_date;
        String repair;
        Date repair_date;

        try {
            pstmt = connection.prepareStatement(SELECT_UNFINISHED_RECORD);
            pstmt.setString(1, keeper);
            rs = pstmt.executeQuery();

            while (rs.next()) {            	
                sn = rs.getInt("sn");
                device_sn = rs.getString("device_sn");
                fault = rs.getString("fault");
                repair = rs.getString("repair");
                fault_date = rs.getDate("fault_date");
                repair_date = rs.getDate("repair_date");
                
                rec_list.add(new Record(sn, device_sn, fault, repair, fault_date, repair_date, "N"));
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured." + se.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
        return rec_list;

    }
    
    public Record getSingleRecord(int sn) {

        String device_sn;
        String fault;
        Date fault_date;
        String repair;
        Date repair_date;
        String finish;
        Record record = null;

        try {
            pstmt = connection.prepareStatement(SELECT_SINGLE_RECORD);
            pstmt.setInt(1, sn);
            rs = pstmt.executeQuery();

            while (rs.next()) {            	
                sn = rs.getInt("sn");
                device_sn = rs.getString("device_sn");
                fault = rs.getString("fault");
                repair = rs.getString("repair");
                fault_date = rs.getDate("fault_date");
                repair_date = rs.getDate("repair_date");
                finish = rs.getString("finish");
                
                record = new Record(sn, device_sn, fault, repair, fault_date, repair_date, finish);
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured." + se.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
        return record;

    }
    
    public ArrayList<Record> getFinishedRecords(String keeper, String from_date, String to_date, String dev_sn) {

        ArrayList<Record> rec_list = new ArrayList<Record>();
        int sn;
        String device_sn;
        String fault;
        Date fault_date;
        String repair;
        Date repair_date;

        try {
        	if(!from_date.equals("")){
        		if(!to_date.equals("")){
        			if(!dev_sn.equals("")){
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORD7);
        				pstmt.setString(1, keeper);
        				pstmt.setDate(2, Date.valueOf(from_date));
        				pstmt.setDate(3, Date.valueOf(to_date));
        				pstmt.setString(4, dev_sn);
        			}else{
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORD4);       				
        				pstmt.setString(1, keeper);
        				pstmt.setDate(2, Date.valueOf(from_date));
        				pstmt.setDate(3, Date.valueOf(to_date));
        			}
        		}else{
                    if(!dev_sn.equals("")){
                    	pstmt = connection.prepareStatement(SELECT_FINISHED_RECORD5);
                    	pstmt.setString(1, keeper);
                    	pstmt.setDate(2, Date.valueOf(from_date));  				
        				pstmt.setString(3, dev_sn);
        			}else{
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORD1);
        				pstmt.setString(1, keeper);
        				pstmt.setDate(2, Date.valueOf(from_date));
        			}
        		}
        	}else{
        		if(!to_date.equals("")){
        			if(!dev_sn.equals("")){
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORD6);
        				pstmt.setString(1, keeper);       				
        				pstmt.setDate(2, Date.valueOf(to_date));
        				pstmt.setString(3, dev_sn);
        			}else{
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORD2);
        				pstmt.setString(1, keeper);       				        				
        				pstmt.setDate(2, Date.valueOf(to_date));        				
        			}
        		}else{
                    if(!dev_sn.equals("")){
                    	pstmt = connection.prepareStatement(SELECT_FINISHED_RECORD3);
                    	pstmt.setString(1, keeper);                   	
        				pstmt.setString(2, dev_sn);
        			}else{
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORD8);
        				pstmt.setString(1, keeper);
        			}
        		}
        	}

            rs = pstmt.executeQuery();

            while (rs.next()) {            	
                sn = rs.getInt("sn");
                device_sn = rs.getString("device_sn");
                fault = rs.getString("fault");
                repair = rs.getString("repair");
                fault_date = rs.getDate("fault_date");
                repair_date = rs.getDate("repair_date");
                
                rec_list.add(new Record(sn, device_sn, fault, repair, fault_date, repair_date, "Y"));
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured." + se.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
        return rec_list;

    }
    
    
    public ArrayList<Record> getFinishedRecords(int dep_sn, String from_date, String to_date, String dev_sn) {

        ArrayList<Record> rec_list = new ArrayList<Record>();
        int sn;
        String device_sn;
        String fault;
        Date fault_date;
        String repair;
        Date repair_date;

        try {
        	if(!from_date.equals("")){
        		if(!to_date.equals("")){
        			if(!dev_sn.equals("")){
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDM7);
        				pstmt.setInt(1, dep_sn);
        				pstmt.setDate(2, Date.valueOf(from_date));
        				pstmt.setDate(3, Date.valueOf(to_date));
        				pstmt.setString(4, dev_sn);
        			}else{
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDM4);       				
        				pstmt.setInt(1, dep_sn);
        				pstmt.setDate(2, Date.valueOf(from_date));
        				pstmt.setDate(3, Date.valueOf(to_date));
        			}
        		}else{
                    if(!dev_sn.equals("")){
                    	pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDM5);
                    	pstmt.setInt(1, dep_sn);
                    	pstmt.setDate(2, Date.valueOf(from_date));  				
        				pstmt.setString(3, dev_sn);
        			}else{
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDM1);
        				pstmt.setInt(1, dep_sn);
        				pstmt.setDate(2, Date.valueOf(from_date));
        			}
        		}
        	}else{
        		if(!to_date.equals("")){
        			if(!dev_sn.equals("")){
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDM6);
        				pstmt.setInt(1, dep_sn);       				
        				pstmt.setDate(2, Date.valueOf(to_date));
        				pstmt.setString(3, dev_sn);
        			}else{
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDM2);
        				pstmt.setInt(1, dep_sn);       				        				
        				pstmt.setDate(2, Date.valueOf(to_date));        				
        			}
        		}else{
                    if(!dev_sn.equals("")){
                    	pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDM3);
                    	pstmt.setInt(1, dep_sn);                   	
        				pstmt.setString(2, dev_sn);
        			}else{
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDM8);
        				pstmt.setInt(1, dep_sn);
        			}
        		}
        	}

            rs = pstmt.executeQuery();

            while (rs.next()) {            	
                sn = rs.getInt("sn");
                device_sn = rs.getString("device_sn");
                fault = rs.getString("fault");
                repair = rs.getString("repair");
                fault_date = rs.getDate("fault_date");
                repair_date = rs.getDate("repair_date");
                
                rec_list.add(new Record(sn, device_sn, fault, repair, fault_date, repair_date, "Y"));
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured." + se.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
        return rec_list;

    }
    
    
    
    public ArrayList<Record> getFinishedRecords(String from_date, String to_date, String dev_sn) {

        ArrayList<Record> rec_list = new ArrayList<Record>();
        int sn;
        String device_sn;
        String fault;
        Date fault_date;
        String repair;
        Date repair_date;

        try {
        	if(!from_date.equals("")){
        		if(!to_date.equals("")){
        			if(!dev_sn.equals("")){
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDS7);        				
        				pstmt.setDate(1, Date.valueOf(from_date));
        				pstmt.setDate(2, Date.valueOf(to_date));
        				pstmt.setString(3, dev_sn);
        			}else{
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDS4);       				        				
        				pstmt.setDate(1, Date.valueOf(from_date));
        				pstmt.setDate(2, Date.valueOf(to_date));
        			}
        		}else{
                    if(!dev_sn.equals("")){
                    	pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDS5);                   	
                    	pstmt.setDate(1, Date.valueOf(from_date));  				
        				pstmt.setString(2, dev_sn);
        			}else{
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDS1);        				
        				pstmt.setDate(1, Date.valueOf(from_date));
        			}
        		}
        	}else{
        		if(!to_date.equals("")){
        			if(!dev_sn.equals("")){
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDS6);        				      				
        				pstmt.setDate(1, Date.valueOf(to_date));
        				pstmt.setString(2, dev_sn);
        			}else{
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDS2);        				       				        				
        				pstmt.setDate(1, Date.valueOf(to_date));        				
        			}
        		}else{
                    if(!dev_sn.equals("")){
                    	pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDS3);                   	         	
        				pstmt.setString(1, dev_sn);
        			}else{
        				pstmt = connection.prepareStatement(SELECT_FINISHED_RECORDS8);
        				
        			}
        		}
        	}

            rs = pstmt.executeQuery();

            while (rs.next()) {            	
                sn = rs.getInt("sn");
                device_sn = rs.getString("device_sn");
                fault = rs.getString("fault");
                repair = rs.getString("repair");
                fault_date = rs.getDate("fault_date");
                repair_date = rs.getDate("repair_date");
                
                rec_list.add(new Record(sn, device_sn, fault, repair, fault_date, repair_date, "Y"));
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured." + se.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
        return rec_list;

    }

}
