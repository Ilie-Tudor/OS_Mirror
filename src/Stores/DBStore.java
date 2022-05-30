package Stores;

import Application.BaseApplication;
import Application.PreDefinedApplication;
import Application.UserDefinedApplication;
import DBconnection.MySqlConnection;

import java.sql.*;
import java.util.Vector;

public class DBStore implements BaseStore{
    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void exportAllFromMemory(MemoryStore ms) {
        try {
            conn = MySqlConnection.getConnection();
            statement = conn.createStatement();
            statement.execute("delete from applications where id>0");
            conn = MySqlConnection.getConnection();
            preparedStatement = conn.prepareStatement("insert into mirroros.applications values(?,?,?,?,?)");
            // Result set get the result of the SQL query
            for(int i=0;i<ms.getApplications().size();i++){
                preparedStatement.setInt(1,ms.getApplications().get(i).getId());
                preparedStatement.setString(2,ms.getApplications().get(i).getName());
                preparedStatement.setString(3,ms.getApplications().get(i).getUrl());
                preparedStatement.setLong(4,ms.getApplications().get(i).getInstallerSize());
                if(ms.getApplications().get(i) instanceof UserDefinedApplication) {
                    preparedStatement.setString(5, "user_defined");
                }
                else{
                    preparedStatement.setString(5, "pre_defined");
                }
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MemoryStore importAllIntoMemory() {
        MemoryStore ms = new MemoryStore();
        Vector<BaseApplication> v = new Vector<>();
        try{
            conn = MySqlConnection.getConnection();
            statement = conn.createStatement();
            resultSet = statement
                    .executeQuery("select * from mirroros.applications");
            while (resultSet.next()) {

                int id = Integer.parseInt(resultSet.getString("id"));
                String application_name = resultSet.getString("application_name");
                String url = resultSet.getString("url");
                long installer_size = Long.parseLong(resultSet.getString("installer_size"));
                String type = resultSet.getString("application_type");
                if(type.equals("user_defined")){

                    v.add(new UserDefinedApplication(id,application_name,url,installer_size));
                }
                else{
                    v.add(new PreDefinedApplication(id,application_name,url,installer_size));
                }
            }
            ms.setApplications(v);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ms;
    }



}
