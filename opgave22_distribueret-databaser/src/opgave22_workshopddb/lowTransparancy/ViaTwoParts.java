package opgave22_workshopddb.lowTransparancy;
import java.sql.*;
import java.util.*;
public class ViaTwoParts {
    public static void main(String[] args) {
    try {
        ArrayList<Person> liste = new ArrayList<Person>();
// læser tabellen Personjyl via native-driver
        Connection minConnection;
        String server = "localhost";
        String dbnavn = "ddba"; // <---- Skift DB navn
        String login = "sa";
        String password = "MyPass@word";  // <----- skal måske skiftes
        minConnection = DriverManager.getConnection("jdbc:sqlserver://" + server + ";databaseName=" + dbnavn +
                ";user=" + login + ";password=" + password + ";");

        String sql= "select * from personadr";
        Statement stmt = minConnection.createStatement();
        ResultSet res=stmt.executeQuery(sql);
        while (res.next()) {
            Person p = new Person();
                    p.setCpr(res.getString("cpr"));
                    p.setNavn(res.getString("navn"));
                    p.setBynavn(res.getString("bynavn"));
            liste.add(p);
        };

// l�ser tabellen Personoeer via native-driver
        Connection minCon2;
        dbnavn = "ddbb"; // <---- Skift DB navn
        minCon2 = DriverManager.getConnection("jdbc:sqlserver://" + server + ";databaseName=" + dbnavn +
                ";user=" + login + ";password=" + password + ";");

        String sql2= "select cpr, loen, skatteprocent from personloen";
        Statement stmt2 = minCon2.createStatement();
        ResultSet res2=stmt2.executeQuery(sql2);
        while (res2.next()) {
            Person p = null;
            String targetCPR = res2.getString("cpr");
            for(int i = 0; i < liste.size(); i++){
                if(liste.get(i).cpr.equals(targetCPR)){
                    p = liste.get(i);
                    break;
                }
            }
            p.setLoen(res2.getInt("loen"));
            p.setSkatteprocent(res2.getInt("skatteprocent"));
        }

// udskriver indholdet af de to tabeller
        for (int i=0;i<liste.size();i++) {
            Person s = liste.get(i);
            System.out.print(s.getCpr() + "\t");
            System.out.print(s.getNavn() + "\t");
            System.out.print(s.getBynavn() + "\t");
            System.out.print(s.getLoen() + "\t");
            System.out.print(s.getSkatteprocent());
            System.out.println();
        }
    }
		catch (Exception e) {
        System.out.println("fejl:  "+e.getMessage());
    }
}
}
