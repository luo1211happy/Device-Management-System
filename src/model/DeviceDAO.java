package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;



public class DeviceDAO {
	private static final String INSERT_DEVICE = "INSERT INTO device(sn,model_sn,buy_date,keeper) VALUES(CONCAT(?,device_sn_seq.NEXTVAL),?,?,?)";
    private static final String GET_MAX_SN = "SELECT MAX(sn) AS max_sn FROM device";
    private static final String SELECT_DEVICE = "SELECT * FROM device WHERE keeper = ? ORDER BY sn ASC";
    private static final String SELECT_DEVICE_M = "SELECT device.* FROM keeper, device WHERE keeper = keeper.sn AND keeper.department_sn = ? ORDER BY device.sn ASC";
    
    private static final String SELECT_CLASS_SN = "SELECT device.* FROM device, model, item, class WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND class.sn=item.class_sn AND class.sn=? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_CLASS_SN_GT_DATE = "SELECT device.* FROM device, model, item, class WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND class.sn=item.class_sn AND class.sn=? AND buy_date >= ? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_CLASS_SN_LT_DATE = "SELECT device.* FROM device, model, item, class WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND class.sn=item.class_sn AND class.sn=? AND buy_date <= ? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_CLASS_SN_BT_DATE = "SELECT device.* FROM device, model, item, class WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND class.sn=item.class_sn AND class.sn=? AND buy_date BETWEEN ? AND ? AND keeper=? ORDER BY device.sn ASC";
   
    private static final String SELECT_ITEM_SN = "SELECT device.* FROM device, model, item WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND item.sn=? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_ITEM_SN_GT_DATE = "SELECT device.* FROM device, model, item WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND item.sn=? AND buy_date >= ? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_ITEM_SN_LT_DATE = "SELECT device.* FROM device, model, item WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND item.sn=? AND buy_date <= ? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_ITEM_SN_BT_DATE = "SELECT device.* FROM device, model, item WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND item.sn=? AND buy_date BETWEEN ? AND ? AND keeper=? ORDER BY device.sn ASC";
    
    private static final String SELECT_MODEL_SN = "SELECT device.* FROM device, model WHERE model.sn=device.model_sn AND model.sn=? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_MODEL_SN_GT_DATE = "SELECT device.* FROM device, model WHERE model.sn=device.model_sn AND model.sn=? AND buy_date >= ? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_MODEL_SN_LT_DATE = "SELECT device.* FROM device, model WHERE model.sn=device.model_sn AND model.sn=? AND buy_date <= ? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_MODEL_SN_BT_DATE = "SELECT device.* FROM device, model WHERE model.sn=device.model_sn AND model.sn=? AND buy_date BETWEEN ? AND ? AND keeper=? ORDER BY device.sn ASC";
    
    private static final String SELECT_NO_SN = "SELECT device.* FROM device WHERE  keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_NO_GT_DATE = "SELECT device.* FROM device WHERE  buy_date >= ? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_NO_LT_DATE = "SELECT device.* FROM device WHERE  buy_date <= ? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_NO_BT_DATE = "SELECT device.* FROM device WHERE  buy_date BETWEEN ? AND ? AND keeper=? ORDER BY device.sn ASC";
    
    private static final String SELECT_CLASS_SN_S = "SELECT device.* FROM device, model, item, class WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND class.sn=item.class_sn AND class.sn=? ORDER BY keeper ASC";
    private static final String SELECT_CLASS_SN_GT_DATE_S = "SELECT device.* FROM device, model, item, class WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND class.sn=item.class_sn AND class.sn=? AND buy_date >= ? ORDER BY keeper ASC";
    private static final String SELECT_CLASS_SN_LT_DATE_S = "SELECT device.* FROM device, model, item, class WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND class.sn=item.class_sn AND class.sn=? AND buy_date <= ? ORDER BY keeper ASC";
    private static final String SELECT_CLASS_SN_BT_DATE_S = "SELECT device.* FROM device, model, item, class WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND class.sn=item.class_sn AND class.sn=? AND buy_date BETWEEN ? AND ? ORDER BY keeper ASC";
   
    private static final String SELECT_ITEM_SN_S = "SELECT device.* FROM device, model, item WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND item.sn=? ORDER BY keeper ASC";
    private static final String SELECT_ITEM_SN_GT_DATE_S = "SELECT device.* FROM device, model, item WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND item.sn=? AND buy_date >= ? ORDER BY keeper ASC";
    private static final String SELECT_ITEM_SN_LT_DATE_S = "SELECT device.* FROM device, model, item WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND item.sn=? AND buy_date <= ? ORDER BY keeper ASC";
    private static final String SELECT_ITEM_SN_BT_DATE_S = "SELECT device.* FROM device, model, item WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND item.sn=? AND buy_date BETWEEN ? AND ? ORDER BY keeper ASC";
    
    private static final String SELECT_MODEL_SN_S = "SELECT device.* FROM device, model WHERE model.sn=device.model_sn AND model.sn=? ORDER BY keeper ASC";
    private static final String SELECT_MODEL_SN_GT_DATE_S = "SELECT device.* FROM device, model WHERE model.sn=device.model_sn AND model.sn=? AND buy_date >= ? ORDER BY keeper ASC";
    private static final String SELECT_MODEL_SN_LT_DATE_S = "SELECT device.* FROM device, model WHERE model.sn=device.model_sn AND model.sn=? AND buy_date <= ? ORDER BY keeper ASC";
    private static final String SELECT_MODEL_SN_BT_DATE_S = "SELECT device.* FROM device, model WHERE model.sn=device.model_sn AND model.sn=? AND buy_date BETWEEN ? AND ? ORDER BY keeper ASC";
    
    private static final String SELECT_NO_SN_S = "SELECT device.* FROM device ORDER BY keeper ASC";
    private static final String SELECT_NO_GT_DATE_S = "SELECT device.* FROM device WHERE  buy_date >= ? ORDER BY keeper ASC";
    private static final String SELECT_NO_LT_DATE_S = "SELECT device.* FROM device WHERE  buy_date <= ? ORDER BY keeper ASC";
    private static final String SELECT_NO_BT_DATE_S = "SELECT device.* FROM device WHERE  buy_date BETWEEN ? AND ? ORDER BY keeper ASC";
    
    
    private static final String SELECT_CLASS_SN_M = "SELECT device.* FROM keeper, device, model, item, class WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND class.sn=item.class_sn AND class.sn=? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_CLASS_SN_GT_DATE_M = "SELECT device.* FROM keeper, device, model, item, class WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND class.sn=item.class_sn AND class.sn=? AND buy_date >= ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_CLASS_SN_LT_DATE_M = "SELECT device.* FROM keeper, device, model, item, class WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND class.sn=item.class_sn AND class.sn=? AND buy_date <= ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_CLASS_SN_BT_DATE_M = "SELECT device.* FROM keeper, device, model, item, class WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND class.sn=item.class_sn AND class.sn=? AND buy_date BETWEEN ? AND ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
   
    private static final String SELECT_ITEM_SN_M = "SELECT device.* FROM keeper, device, model, item WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND item.sn=? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_ITEM_SN_GT_DATE_M = "SELECT device.* FROM keeper, device, model, item WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND item.sn=? AND buy_date >= ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_ITEM_SN_LT_DATE_M = "SELECT device.* FROM keeper, device, model, item WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND item.sn=? AND buy_date <= ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_ITEM_SN_BT_DATE_M = "SELECT device.* FROM keeper, device, model, item WHERE model.sn=device.model_sn AND model.item_sn=item.sn AND item.sn=? AND buy_date BETWEEN ? AND ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    
    private static final String SELECT_MODEL_SN_M = "SELECT device.* FROM keeper, device, model WHERE model.sn=device.model_sn AND model.sn=? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_MODEL_SN_GT_DATE_M = "SELECT device.* FROM keeper, device, model WHERE model.sn=device.model_sn AND model.sn=? AND buy_date >= ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_MODEL_SN_LT_DATE_M = "SELECT device.* FROM keeper, device, model WHERE model.sn=device.model_sn AND model.sn=? AND buy_date <= ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_MODEL_SN_BT_DATE_M = "SELECT device.* FROM keeper, device, model WHERE model.sn=device.model_sn AND model.sn=? AND buy_date BETWEEN ? AND ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    
    private static final String SELECT_NO_SN_M = "SELECT device.* FROM keeper, device WHERE  keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_NO_GT_DATE_M = "SELECT device.* FROM keeper, device WHERE  buy_date >= ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_NO_LT_DATE_M = "SELECT device.* FROM keeper, device WHERE  buy_date <= ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_NO_BT_DATE_M = "SELECT device.* FROM keeper, device WHERE  buy_date BETWEEN ? AND ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    
    
    
    private static final String SELECT_DEVICE_BY_SN = "SELECT * FROM device WHERE sn = ?";
    private static final String UPDATE_DEVICE = "UPDATE device SET buy_date = ?, status = ?, isElectric = ?, position = ? WHERE sn = ?";
    private static final String DELETE_DEVICE_BY_SN = "DELETE FROM device WHERE sn=?";
    
    
    private static final String SELECT_DEV_BY_STATUS = "SELECT * FROM device WHERE status = ? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_DEV_BY_ISELECTRIC = "SELECT * FROM device WHERE isElectric = ? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_DEV_BY_LOC = "SELECT * FROM device WHERE position = ? AND keeper=? ORDER BY device.sn ASC";    
    private static final String SELECT_DEV_BY_LOC_STATUS = "SELECT * FROM device WHERE status = ? AND position = ? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_DEV_BY_LOC_ISELECTRIC = "SELECT * FROM device WHERE isElectric = ? AND position = ? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_DEV_BY_STATUS_ISELECTRIC = "SELECT * FROM device WHERE status = ? AND isElectric = ? AND keeper=? ORDER BY device.sn ASC";
    private static final String SELECT_DEV_BY_LOC_STATUS_ISELECTRIC = "SELECT * FROM device WHERE status = ? AND isElectric = ? AND position = ? AND keeper=? ORDER BY device.sn ASC";
    
    private static final String SELECT_DEV_BY_STATUS_S = "SELECT * FROM device WHERE status = ? ORDER BY keeper ASC";
    private static final String SELECT_DEV_BY_ISELECTRIC_S = "SELECT * FROM device WHERE isElectric = ? ORDER BY keeper ASC";
    private static final String SELECT_DEV_BY_LOC_S = "SELECT * FROM device WHERE position = ? ORDER BY keeper ASC";    
    private static final String SELECT_DEV_BY_LOC_STATUS_S = "SELECT * FROM device WHERE status = ? AND position = ? ORDER BY keeper ASC";
    private static final String SELECT_DEV_BY_LOC_ISELECTRIC_S = "SELECT * FROM device WHERE isElectric = ? AND position = ? ORDER BY keeper ASC";
    private static final String SELECT_DEV_BY_STATUS_ISELECTRIC_S = "SELECT * FROM device WHERE status = ? AND isElectric = ? ORDER BY keeper ASC";
    private static final String SELECT_DEV_BY_LOC_STATUS_ISELECTRIC_S = "SELECT * FROM device WHERE status = ? AND isElectric = ? AND position = ? ORDER BY keeper ASC";
    
    private static final String SELECT_DEV_BY_STATUS_M = "SELECT device.* FROM keeper, device WHERE status = ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_DEV_BY_ISELECTRIC_M = "SELECT device.* FROM keeper, device WHERE isElectric = ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_DEV_BY_LOC_M = "SELECT device.* FROM keeper, device WHERE position = ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";    
    private static final String SELECT_DEV_BY_LOC_STATUS_M = "SELECT device.* FROM keeper, device WHERE status = ? AND position = ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_DEV_BY_LOC_ISELECTRIC_M = "SELECT device.* FROM keeper, device WHERE isElectric = ? AND position = ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_DEV_BY_STATUS_ISELECTRIC_M = "SELECT device.* FROM keeper, device WHERE status = ? AND isElectric = ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    private static final String SELECT_DEV_BY_LOC_STATUS_ISELECTRIC_M = "SELECT device.* FROM keeper, device WHERE status = ? AND isElectric = ? AND position = ? AND keeper=keeper.sn AND keeper.department_sn=? ORDER BY device.sn ASC";
    
    
    
    private static final String UPDATE_STATUS_BY_SN = "UPDATE device SET status = ? WHERE sn = ?";
    private static final String UPDATE_LOCATION_KEEPER_BY_SN = "UPDATE device SET position = ?, keeper = ? WHERE sn = ?";
    
    
    
    
    

    private Connection connection = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private ResultSet rs = null;

    private Statement stmt = null;

    public DeviceDAO() {
        connection = DBConnection.getDBConnection();
    }

    public int addDevice(Device device, int device_num) {
        int k = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String sn = sdf.format(new java.util.Date());
        String max_date;
        
        try {       	
        	pstmt = connection.prepareStatement(GET_MAX_SN);
        	rs = pstmt.executeQuery();
        	if(rs.next()){
        		if(rs.getString("max_sn")==null)
        			max_date = sn;
        		else        			
        	        max_date = rs.getString("max_sn").substring(0, 8);
        	    if(sn.compareTo(max_date) > 0){
        	    	cstmt = connection.prepareCall("{call EM.SEQ_RESET(?)}");
        			cstmt.setString(1, "device_sn_seq");
        			cstmt.execute();
        	    }
        	    	
        	}
        	
            pstmt = connection.prepareStatement(INSERT_DEVICE);
            pstmt.setString(1, sn + "-");
            pstmt.setInt(2, device.getModel_sn());
            pstmt.setDate(3, device.getBuy_date());
            pstmt.setString(4, device.getKeeper());
           
            for (int i = 0; i < device_num; i++) {
                if (pstmt.executeUpdate() == 1) 
                    k++;
                else
                	break;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(cstmt);
            DBConnection.close(rs, pstmt, stmt, connection);
        }

        return k;

    }

    public ArrayList<Device> getDevice(String keeper) {
        ArrayList<Device> dev_list = new ArrayList<Device>();

        try {
            pstmt = connection.prepareStatement(SELECT_DEVICE);
            pstmt.setString(1, keeper);
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	
                Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), keeper, getMonthSpace(rs.getDate("buy_date").toString()));
                dev_list.add(dev);
                //System.out.println(rs.getDate("buy_date").toString());
            }

        } catch (SQLException e) {
            // TODO 
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return dev_list;
    }
   
    public ArrayList<Device> getDevice(String status, String isElectric, int loc_sn, String keeper) {
        ArrayList<Device> dev_list = new ArrayList<Device>();

        try {
        	if(!status.equals("0")&&isElectric.equals("0")&&(loc_sn == 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_STATUS);
                pstmt.setString(1, status);
        		pstmt.setString(2, keeper);
                rs = pstmt.executeQuery();
        		
        	}else if(status.equals("0")&&!isElectric.equals("0")&&(loc_sn == 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_ISELECTRIC);
                pstmt.setString(1, isElectric);
        		pstmt.setString(2, keeper);
                rs = pstmt.executeQuery();
        		
        	}else if(status.equals("0")&&isElectric.equals("0")&&(loc_sn != 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_LOC);
                pstmt.setInt(1, loc_sn);
        		pstmt.setString(2, keeper);
                rs = pstmt.executeQuery();
        		
        	}else if(!status.equals("0")&&!isElectric.equals("0")&&(loc_sn == 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_STATUS_ISELECTRIC);
                pstmt.setString(1, status);
                pstmt.setString(2, isElectric);
        		pstmt.setString(3, keeper);
                rs = pstmt.executeQuery();
        		
        	}else if(!status.equals("0")&&isElectric.equals("0")&&(loc_sn != 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_LOC_STATUS);
                pstmt.setString(1, status);
                pstmt.setInt(2, loc_sn);
        		pstmt.setString(3, keeper);
                rs = pstmt.executeQuery();
        		
        	}else if(status.equals("0")&& !isElectric.equals("0")&&(loc_sn != 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_LOC_ISELECTRIC);
                pstmt.setString(1, isElectric);
                pstmt.setInt(2, loc_sn);
        		pstmt.setString(3, keeper);
                rs = pstmt.executeQuery();
        		
        	}else if(!status.equals("0")&&!isElectric.equals("0")&&(loc_sn != 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_LOC_STATUS_ISELECTRIC);
                pstmt.setString(1, status);
                pstmt.setString(2, isElectric);
                pstmt.setInt(3, loc_sn);
        		pstmt.setString(4, keeper);
                rs = pstmt.executeQuery();
        		
        	}else{
        		pstmt = connection.prepareStatement(SELECT_DEVICE);         
        		pstmt.setString(1, keeper);
                rs = pstmt.executeQuery();       		
        	}
            

            while (rs.next()) {
                Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), keeper, getMonthSpace(rs.getDate("buy_date").toString()));
                dev_list.add(dev);
            }

        } catch (SQLException e) {
            // TODO 
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return dev_list;
    }
    
    public ArrayList<Device> getDevice(String status, String isElectric, int loc_sn, int dep_sn) {
        ArrayList<Device> dev_list = new ArrayList<Device>();

        try {
        	if(!status.equals("0")&&isElectric.equals("0")&&(loc_sn == 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_STATUS_M);
                pstmt.setString(1, status);
        		pstmt.setInt(2, dep_sn);
                rs = pstmt.executeQuery();
        		
        	}else if(status.equals("0")&&!isElectric.equals("0")&&(loc_sn == 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_ISELECTRIC_M);
                pstmt.setString(1, isElectric);
        		pstmt.setInt(2, dep_sn);
                rs = pstmt.executeQuery();
        		
        	}else if(status.equals("0")&&isElectric.equals("0")&&(loc_sn != 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_LOC_M);
                pstmt.setInt(1, loc_sn);
        		pstmt.setInt(2, dep_sn);
                rs = pstmt.executeQuery();
        		
        	}else if(!status.equals("0")&&!isElectric.equals("0")&&(loc_sn == 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_STATUS_ISELECTRIC_M);
                pstmt.setString(1, status);
                pstmt.setString(2, isElectric);
        		pstmt.setInt(3, dep_sn);
                rs = pstmt.executeQuery();
        		
        	}else if(!status.equals("0")&&isElectric.equals("0")&&(loc_sn != 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_LOC_STATUS_M);
                pstmt.setString(1, status);
                pstmt.setInt(2, loc_sn);
        		pstmt.setInt(3, dep_sn);
                rs = pstmt.executeQuery();
        		
        	}else if(status.equals("0")&& !isElectric.equals("0")&&(loc_sn != 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_LOC_ISELECTRIC_M);
                pstmt.setString(1, isElectric);
                pstmt.setInt(2, loc_sn);
        		pstmt.setInt(3, dep_sn);
                rs = pstmt.executeQuery();
        		
        	}else if(!status.equals("0")&&!isElectric.equals("0")&&(loc_sn != 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_LOC_STATUS_ISELECTRIC_M);
                pstmt.setString(1, status);
                pstmt.setString(2, isElectric);
                pstmt.setInt(3, loc_sn);
        		pstmt.setInt(4, dep_sn);
                rs = pstmt.executeQuery();
        		
        	}else{
        		pstmt = connection.prepareStatement(SELECT_DEVICE_M);         
        		pstmt.setInt(1, dep_sn);
                rs = pstmt.executeQuery();       		
        	}
            

            while (rs.next()) {
                Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                dev_list.add(dev);
            }

        } catch (SQLException e) {
            // TODO 
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return dev_list;
    }
    
    public ArrayList<Device> getDevice(String status, String isElectric, int loc_sn) {
        ArrayList<Device> dev_list = new ArrayList<Device>();

        try {
        	if(!status.equals("0")&&isElectric.equals("0")&&(loc_sn == 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_STATUS_S);
                pstmt.setString(1, status);
        		
                rs = pstmt.executeQuery();
        		
        	}else if(status.equals("0")&&!isElectric.equals("0")&&(loc_sn == 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_ISELECTRIC_S);
                pstmt.setString(1, isElectric);
        		
                rs = pstmt.executeQuery();
        		
        	}else if(status.equals("0")&&isElectric.equals("0")&&(loc_sn != 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_LOC_S);
                pstmt.setInt(1, loc_sn);
        		
                rs = pstmt.executeQuery();
        		
        	}else if(!status.equals("0")&&!isElectric.equals("0")&&(loc_sn == 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_STATUS_ISELECTRIC_S);
                pstmt.setString(1, status);
                pstmt.setString(2, isElectric);
        		
                rs = pstmt.executeQuery();
        		
        	}else if(!status.equals("0")&&isElectric.equals("0")&&(loc_sn != 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_LOC_STATUS_S);
                pstmt.setString(1, status);
                pstmt.setInt(2, loc_sn);
        		
                rs = pstmt.executeQuery();
        		
        	}else if(status.equals("0")&& !isElectric.equals("0")&&(loc_sn != 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_LOC_ISELECTRIC_S);
                pstmt.setString(1, isElectric);
                pstmt.setInt(2, loc_sn);
        		
                rs = pstmt.executeQuery();
        		
        	}else if(!status.equals("0")&&!isElectric.equals("0")&&(loc_sn != 0)){
        		pstmt = connection.prepareStatement(SELECT_DEV_BY_LOC_STATUS_ISELECTRIC_S);
                pstmt.setString(1, status);
                pstmt.setString(2, isElectric);
                pstmt.setInt(3, loc_sn);
        		
                rs = pstmt.executeQuery();
        		
        	}else{
        		pstmt = connection.prepareStatement(SELECT_NO_SN_S);         
        		
                rs = pstmt.executeQuery();       		
        	}
            

            while (rs.next()) {
                Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                dev_list.add(dev);
            }

        } catch (SQLException e) {
            // TODO 
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return dev_list;
    }
    
    
    
    
    
    public ArrayList<Device> getDevice(int class_sn, int item_sn, int model_sn, String from_date, String to_date, String keeper) {
        ArrayList<Device> dev_list = new ArrayList<Device>();

        try {
        	if(model_sn != 0){
        		if(from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_MODEL_SN);
        			pstmt.setInt(1, model_sn);
        			pstmt.setString(2, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(!from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_MODEL_SN_GT_DATE);
        			pstmt.setInt(1, model_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setString(3, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(from_date.equals("")&&!to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_MODEL_SN_LT_DATE);
        			pstmt.setInt(1, model_sn);
        			pstmt.setDate(2, Date.valueOf(to_date));
        			pstmt.setString(3, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else{
        			pstmt = connection.prepareStatement(SELECT_MODEL_SN_BT_DATE);
        			pstmt.setInt(1, model_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setDate(3, Date.valueOf(to_date));
        			pstmt.setString(4, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}
        		
        	}else if(item_sn != 0){
        		if(from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_ITEM_SN);
        			pstmt.setInt(1, item_sn);
        			pstmt.setString(2, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(!from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_ITEM_SN_GT_DATE);
        			pstmt.setInt(1, item_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setString(3, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(from_date.equals("")&&!to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_ITEM_SN_LT_DATE);
        			pstmt.setInt(1, item_sn);       			
        			pstmt.setDate(2, Date.valueOf(to_date));
        			pstmt.setString(3, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else{
        			pstmt = connection.prepareStatement(SELECT_ITEM_SN_BT_DATE);
        			pstmt.setInt(1, item_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setDate(3, Date.valueOf(to_date));
        			pstmt.setString(4, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}
        	}else if(class_sn != 0){
        		if(from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_CLASS_SN);
        			pstmt.setInt(1, class_sn); 
        			pstmt.setString(2, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(!from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_CLASS_SN_GT_DATE);
        			pstmt.setInt(1, class_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setString(3, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(from_date.equals("")&&!to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_CLASS_SN_LT_DATE);
        			pstmt.setInt(1, class_sn);
        			pstmt.setDate(2, Date.valueOf(to_date));
        			pstmt.setString(3, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else{
        			pstmt = connection.prepareStatement(SELECT_CLASS_SN_BT_DATE);
        			pstmt.setInt(1, class_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setDate(3, Date.valueOf(to_date));
        			pstmt.setString(4, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}
        	}else{
        		if(from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_NO_SN);
        			pstmt.setString(1, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(!from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_NO_GT_DATE);
        			pstmt.setDate(1, Date.valueOf(from_date));
        			pstmt.setString(2, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(from_date.equals("")&&!to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_NO_LT_DATE);
        			pstmt.setDate(1, Date.valueOf(to_date));
        			pstmt.setString(2, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else{
        			pstmt = connection.prepareStatement(SELECT_NO_BT_DATE);
        			pstmt.setDate(1, Date.valueOf(from_date));
        			pstmt.setDate(2, Date.valueOf(to_date));
        			pstmt.setString(3, keeper);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}   		
        	}   

        } catch (SQLException e) {
            // TODO 
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
    
    }
    
    
    public ArrayList<Device> getDevice(int class_sn, int item_sn, int model_sn, String from_date, String to_date, int dep_sn) {
        ArrayList<Device> dev_list = new ArrayList<Device>();

        try {
        	if(model_sn != 0){
        		if(from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_MODEL_SN_M);
        			pstmt.setInt(1, model_sn);
        			pstmt.setInt(2, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(!from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_MODEL_SN_GT_DATE_M);
        			pstmt.setInt(1, model_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setInt(3, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(from_date.equals("")&&!to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_MODEL_SN_LT_DATE_M);
        			pstmt.setInt(1, model_sn);
        			pstmt.setDate(2, Date.valueOf(to_date));
        			pstmt.setInt(3, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else{
        			pstmt = connection.prepareStatement(SELECT_MODEL_SN_BT_DATE_M);
        			pstmt.setInt(1, model_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setDate(3, Date.valueOf(to_date));
        			pstmt.setInt(4, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}
        		
        	}else if(item_sn != 0){
        		if(from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_ITEM_SN_M);
        			pstmt.setInt(1, item_sn);
        			pstmt.setInt(2, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(!from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_ITEM_SN_GT_DATE_M);
        			pstmt.setInt(1, item_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setInt(3, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(from_date.equals("")&&!to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_ITEM_SN_LT_DATE_M);
        			pstmt.setInt(1, item_sn);       			
        			pstmt.setDate(2, Date.valueOf(to_date));
        			pstmt.setInt(3, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else{
        			pstmt = connection.prepareStatement(SELECT_ITEM_SN_BT_DATE_M);
        			pstmt.setInt(1, item_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setDate(3, Date.valueOf(to_date));
        			pstmt.setInt(4, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}
        	}else if(class_sn != 0){
        		if(from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_CLASS_SN_M);
        			pstmt.setInt(1, class_sn); 
        			pstmt.setInt(2, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(!from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_CLASS_SN_GT_DATE_M);
        			pstmt.setInt(1, class_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setInt(3, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(from_date.equals("")&&!to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_CLASS_SN_LT_DATE_M);
        			pstmt.setInt(1, class_sn);
        			pstmt.setDate(2, Date.valueOf(to_date));
        			pstmt.setInt(3, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else{
        			pstmt = connection.prepareStatement(SELECT_CLASS_SN_BT_DATE_M);
        			pstmt.setInt(1, class_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setDate(3, Date.valueOf(to_date));
        			pstmt.setInt(4, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}
        	}else{
        		if(from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_NO_SN_M);
        			pstmt.setInt(1, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(!from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_NO_GT_DATE_M);
        			pstmt.setDate(1, Date.valueOf(from_date));
        			pstmt.setInt(2, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(from_date.equals("")&&!to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_NO_LT_DATE_M);
        			pstmt.setDate(1, Date.valueOf(to_date));
        			pstmt.setInt(2, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else{
        			pstmt = connection.prepareStatement(SELECT_NO_BT_DATE_M);
        			pstmt.setDate(1, Date.valueOf(from_date));
        			pstmt.setDate(2, Date.valueOf(to_date));
        			pstmt.setInt(3, dep_sn);
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}   		
        	}   

        } catch (SQLException e) {
            // TODO 
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
    
    }
    
    
    
    public ArrayList<Device> getDevice(int class_sn, int item_sn, int model_sn, String from_date, String to_date) {
        ArrayList<Device> dev_list = new ArrayList<Device>();

        try {
        	if(model_sn != 0){
        		if(from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_MODEL_SN_S);
        			pstmt.setInt(1, model_sn);
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(!from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_MODEL_SN_GT_DATE_S);
        			pstmt.setInt(1, model_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(from_date.equals("")&&!to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_MODEL_SN_LT_DATE_S);
        			pstmt.setInt(1, model_sn);
        			pstmt.setDate(2, Date.valueOf(to_date));
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else{
        			pstmt = connection.prepareStatement(SELECT_MODEL_SN_BT_DATE_S);
        			pstmt.setInt(1, model_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setDate(3, Date.valueOf(to_date));
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}
        		
        	}else if(item_sn != 0){
        		if(from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_ITEM_SN_S);
        			pstmt.setInt(1, item_sn);
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(!from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_ITEM_SN_GT_DATE_S);
        			pstmt.setInt(1, item_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(from_date.equals("")&&!to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_ITEM_SN_LT_DATE_S);
        			pstmt.setInt(1, item_sn);       			
        			pstmt.setDate(2, Date.valueOf(to_date));
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else{
        			pstmt = connection.prepareStatement(SELECT_ITEM_SN_BT_DATE_S);
        			pstmt.setInt(1, item_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setDate(3, Date.valueOf(to_date));
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}
        	}else if(class_sn != 0){
        		if(from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_CLASS_SN_S);
        			pstmt.setInt(1, class_sn); 
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(!from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_CLASS_SN_GT_DATE_S);
        			pstmt.setInt(1, class_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(from_date.equals("")&&!to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_CLASS_SN_LT_DATE_S);
        			pstmt.setInt(1, class_sn);
        			pstmt.setDate(2, Date.valueOf(to_date));
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else{
        			pstmt = connection.prepareStatement(SELECT_CLASS_SN_BT_DATE_S);
        			pstmt.setInt(1, class_sn);
        			pstmt.setDate(2, Date.valueOf(from_date));
        			pstmt.setDate(3, Date.valueOf(to_date));
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}
        	}else{
        		if(from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_NO_SN_S);
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(!from_date.equals("")&&to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_NO_GT_DATE_S);
        			pstmt.setDate(1, Date.valueOf(from_date));
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else if(from_date.equals("")&&!to_date.equals("")){
        			pstmt = connection.prepareStatement(SELECT_NO_LT_DATE_S);
        			pstmt.setDate(1, Date.valueOf(to_date));
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}else{
        			pstmt = connection.prepareStatement(SELECT_NO_BT_DATE_S);
        			pstmt.setDate(1, Date.valueOf(from_date));
        			pstmt.setDate(2, Date.valueOf(to_date));
        			
        			rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Device dev = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
                        dev_list.add(dev);
                    }
                    return dev_list;
        		}   		
        	}   

        } catch (SQLException e) {
            // TODO 
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
    
    }
    
    
    
    public Device getDeviceBySn(String sn) {
        Device device = null;

        try {
            pstmt = connection.prepareStatement(SELECT_DEVICE_BY_SN);
            pstmt.setString(1, sn);
            rs = pstmt.executeQuery();
            if (rs.next())
                device = new Device(rs.getString("sn"), rs.getInt("model_sn"), rs.getDate("buy_date"), rs.getString("status"), rs.getString("isElectric"), rs.getInt("position"), rs.getString("keeper"), getMonthSpace(rs.getDate("buy_date").toString()));
            return device;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {

            DBConnection.close(rs, pstmt, null, connection);
        }
    }
    
    public int updateDevice(String sn, Date buy_date, String status, String isElectric, int position) {

        int k = 0;

        try {
            pstmt = connection.prepareStatement(UPDATE_DEVICE);
            pstmt.setDate(1, buy_date);
            pstmt.setString(2, status);
            pstmt.setString(3, isElectric);
            pstmt.setInt(4, position);
            pstmt.setString(5, sn);

            k = pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO 
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return k;
    }
    
    public int updateDevice(String sn, String status) {

        int k = 0;

        try {
            pstmt = connection.prepareStatement(UPDATE_STATUS_BY_SN);
            pstmt.setString(1, status);
            pstmt.setString(2, sn);
            
            k = pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO 
            throw new RuntimeException("A database error occured." + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return k;
    }
    
    public int updateDevice(String sn, int location_sn, String keeper_sn) {

        int k = 0;

        try {
            pstmt = connection.prepareStatement(UPDATE_LOCATION_KEEPER_BY_SN);
            pstmt.setInt(1, location_sn);
            pstmt.setString(2, keeper_sn);
            pstmt.setString(3, sn);
            
            k = pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO 
            throw new RuntimeException("A database error occured." + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return k;
    }
    
    public int deleteDevice(String sn) {

        
        int k = 0;

        try {

            pstmt = connection.prepareStatement(DELETE_DEVICE_BY_SN);
            pstmt.setString(1, sn);

            k = pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO 
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }

        return k;
    }
    
    
    public static double getMonthSpace(String date){
    	try{
    		
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	java.util.Date beginDate;
        	java.util.Date endDate = new java.util.Date();
        	beginDate = sdf.parse(date);
        	int beginYear = beginDate.getYear();
        	int beginMonth = beginDate.getMonth();
        	
        	int endYear = endDate.getYear();
        	int endMonth = endDate.getMonth();

        	int difMonth = (endYear-beginYear)*12+(endMonth-beginMonth);

        	DecimalFormat df = new DecimalFormat("0.0");
        	
        	return Double.parseDouble(df.format(difMonth/12.0));
    	}catch(Exception e){
    		throw new RuntimeException("A date error occured." + e.getMessage());
    	}
    	
    }

}
