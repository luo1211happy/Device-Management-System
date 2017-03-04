package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentDAO {
	
	private Connection connection = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public DepartmentDAO() {
        connection = DBConnection.getDBConnection();
    }

    public void addDepartment(Department dep) {
        try {
            pstmt = connection.prepareStatement(INSERT_DEPARTMENT);

            pstmt.setString(1, dep.getName());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("A database error occured."
                    + e.getMessage());
        } finally {

            DBConnection.close(rs, pstmt, null, connection);
        }

    }

    public int deleteDepartment(int sn) {
        int k = 0;
        try {
            pstmt = connection.prepareStatement(DELETE_DEPARTMENT);
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

    public ArrayList<Department> getDepartments() {

        ArrayList<Department> dep_list = new ArrayList<Department>();
        int sn;
        String name;
        Department dep;

        try {
            pstmt = connection.prepareStatement(SELECT_DEPARTMENT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                sn = rs.getInt("sn");
                name = rs.getString("name");
                dep = new Department(sn, name);
                dep_list.add(dep);
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured."
                    + se.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
        return dep_list;

    }

    public Department getDepartment(int sn) {

        Department dep = null;

        try {
            pstmt = connection.prepareStatement(SELECT_SINGLE_DEPARTMENT);
            pstmt.setInt(1, sn);
            rs = pstmt.executeQuery();
            if (rs.next())
                dep = new Department(sn, rs.getString("name"));

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured."
                    + se.getMessage());
        } finally {
            DBConnection.close(rs, pstmt, null, connection);
        }
        return dep;

    }

    public int updateDepartment(int sn, String name) {
        int k = 0;
        try {
            if (!name.equals("") && sn != 0) {
                pstmt = connection.prepareStatement(UPDATE_DEPARTMENT);
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
    
    private static final String INSERT_DEPARTMENT = "INSERT INTO department(sn,name) VALUES(department_sn_seq.NEXTVAL,?)";
    private static final String SELECT_DEPARTMENT = "SELECT * FROM department ORDER BY sn ASC";
    private static final String SELECT_SINGLE_DEPARTMENT = "SELECT * FROM department WHERE sn=?";
    private static final String DELETE_DEPARTMENT = "DELETE FROM department WHERE sn=?";
    private static final String UPDATE_DEPARTMENT = "UPDATE department SET name = ? WHERE sn = ?";

}
