package com.company;

import oracle.jdbc.pool.OracleDataSource;

import javax.xml.transform.Result;
import java.sql.*;

public class OracleDB {
    private String user;
    private String passwd;
    private String host;
    private String port;
    private String service;
    private String ConnectionString;
    Connection con;


    public OracleDB(String user, String passwd, String host, String port, String service) {
        this.user = user;
        this.passwd = passwd;
        this.host = host;
        this.port = port;
        this.service = service;
        this.ConnectionString="jdbc:oracle:thin:@(DESCRIPTION="
                + "(ADDRESS=(PROTOCOL=tcp)(HOST=" + host + ")(PORT=" + port + "))"
                + "(CONNECT_DATA=(SERVICE_NAME=" + service + ")))";
        con = null;
    }

    public void connect(){
        try {
            OracleDataSource dataSource = new OracleDataSource();
            dataSource.setURL(ConnectionString);
            dataSource.setUser(user);
            dataSource.setPassword(passwd);
            con = dataSource.getConnection();
            System.out.println("Verbindung steht");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getListe(String sql){
        String ausgabe="";
        try { Statement befehl = con.createStatement();

           ResultSet ergebnisse =  befehl.executeQuery(sql);

           while(ergebnisse.next()){
               String name = ergebnisse.getString("name");
               String vorname = ergebnisse.getString("vorname");
               int pnr= ergebnisse.getInt("personal_nr");
               Date geb = ergebnisse.getDate("geburtstag");

               ausgabe +="" + name + ", " + vorname + " , " + geb +"\n";

           }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ausgabe;
    }

    public void createTable(String name){
        Statement befehl = null;
        try {
            befehl = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            befehl.execute("CREATE TABLE" + name + "( \"name\" varchar2(50), \"vorname\" varchar2(50)))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
