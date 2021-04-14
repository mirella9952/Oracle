package com.company;

public class Main {

    public static void main(String[] args) {
        OracleDB db = new OracleDB("SUS_FS191_Master", "m", "oracle.s-atiw.de", "1521", "atiwora");
        db.connect();
        System.out.println(db.getListe("SELECT * FROM PERSONAL p"));

        db.createTable("Test");
    }
}
