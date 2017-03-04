package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DBConnection;
import model.Class;

public class ClassDAO {

    private static final String DELETE_CLASS = "DELETE FROM class WHERE sn=?";

    private static final String INSERT_CLASS = "INSERT INTO class(sn,name) VALUES(class_sn_seq.NEXTVAL,?)";
    private static final String SELECT_CLASS_COUNT = "SELECT COUNT(*) FROM CLASS";
    private static final String SELECT_CLASS = "SELECT * FROM class ORDER BY sn ASC";
    private static final String SELECT_SINGLE_CLASS = "SELECT * FROM class WHERE sn=?";

    private static final String UPDATE_CLASS = "UPDATE class SET name = ? WHERE sn = ?";

    private Connection connection = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public ClassDAO() {
        connection = DBConnection.getDBConnection();
    }

    public void addClass(Class cls) {
        try {
            pstmt = connection.prepareStatement(INSERT_CLASS);

            pstmt.setString(1, cls.getName());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {

            DBConnection.close(rs, pstmt, null, connection);
        }

    }

    public int deleteClass(int sn) {
        int k = 0;
        try {
            pstmt = connection.prepareStatement(DELETE_CLASS);
            pstmt.setInt(1, sn);
            k = pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {

            DBConnection.close(rs, pstmt, null, connection);
        }
        return k;
    }

    public ArrayList<Class> getClasses() {

        ArrayList<Class> cls_list = new ArrayList<Class>();
        int sn;
        String name;
        Class cls;

        try {
            pstmt = connection.prepareStatement(SELECT_CLASS);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                sn = rs.getInt("sn");
                name = rs.getString("name");
                cls = new Class(sn, name);
                cls_list.add(cls);
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured."
                    + se.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
        return cls_list;

    }

    public Class getClasses(int sn) {

        Class cls = null;

        try {
            pstmt = connection.prepareStatement(SELECT_SINGLE_CLASS);
            pstmt.setInt(1, sn);
            rs = pstmt.executeQuery();
            if (rs.next())
                cls = new Class(sn, rs.getString("name"));

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured."
                    + se.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
        return cls;

    }

    public int updateClass(int sn, String name) {
        int k = 0;
        try {
            if (!name.equals("") && sn != 0) {
                pstmt = connection.prepareStatement(UPDATE_CLASS);
                pstmt.setString(1, name);
                pstmt.setInt(2, sn);

                k = pstmt.executeUpdate();
            } else
                return k;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {

            DBConnection.close(rs, pstmt, null, connection);
        }
        return k;
    }

    public int getCLassCount() {
        int k = 0;
        try {

            pstmt = connection.prepareStatement(SELECT_CLASS_COUNT);

            rs = pstmt.executeQuery();
            
            if(rs.next())
                k = rs.getInt(1);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {

            DBConnection.close(rs, pstmt, null, connection);
        }
        return k;
    }
}
